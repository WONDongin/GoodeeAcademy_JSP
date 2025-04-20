package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.board.Board;

public interface BoardMapper {
	@Select("select ifnull(max(num),0) from board")
	int maxnum();
	
	String sql = "insert into board (num,writer,pass,title,content,file1,regdate,readcnt,grp,grplevel,grpstep,boardid)"
			+ " values (#{num},#{writer},#{pass},#{title},#{content},#{file1},now(),0,#{grp},#{grplevel},#{grpstep},#{boardid})";
	@Insert(sql)
	int insert(Board board);
	
	
	@Select("select count(*) from board where boardid = #{value}")
	int count(String boardid);

	
	/*
	limit #{start}, #{limit} => limit 0, 10 => 0번에서 10개 조회 - 1page
	limit #{start}, #{limit} => limit 10, 10 => 10번에서 10개 조회 - 2page
	*10개 보다 적을시 적은 값만큼 조회
	num(grp) 컬럼의 역순
	11
	...
	5	
	4
	3
	2
	1	
	*/
	@Select("select * from board where boardid=#{boardid}"
			+ " order by grp desc, grpstep asc limit #{start}, #{limit}")
	List<Board> list(Map<String, Object> map);
	
	@Select("select * from board where num=#{value}")
	Board selectOne(int num);

	@Update("update board set readcnt = readcnt + 1 where num = #{value}")
	void readcntAdd(int num);
	
	@Update("update board set grpstep = grpstep + 1 "
			+ " where grp=#{grp} and grpstep > #{grpstep}")
	void grpStepAdd(@Param("grp")int grp, @Param("grpstep")int grpstep);

	@Update("update board "
			+ " set writer=#{writer},title=#{title},content=#{content},"
			+ "file1=#{file1} where num=#{num}")
	int update(Board board);

	@Delete("delete from board where num=#{value}")
	int delete(int num);

	@Select("select count(*) from board where grp=#{value} and grplevel > 0")
	int replyCnt(int grp);
	
	
	
}
