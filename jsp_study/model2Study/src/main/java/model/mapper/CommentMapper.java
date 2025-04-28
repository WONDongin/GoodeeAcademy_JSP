package model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import model.comment.Comment;

public interface CommentMapper {

	@Select("select ifnull(max(seq),0) from comment where num=${value}")
	int maxseq(int num);

	@Insert("insert into comment (num,seq,writer,content,regdate)"
			+" values (#{num},#{seq},#{writer},#{content},now())")
	int insert(Comment comm);

	@Select("select * from comment where num=${value}")
	List<Comment> list(int num);

	@Delete("delete from comment where num=#{num} and seq=#{seq}")
	int delete(@Param("num")int num, @Param("seq")int seq);
}
