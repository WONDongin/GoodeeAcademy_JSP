package model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import model.member.Member;

public interface MemberMapper {
	@Insert("insert into member (id,pass,name,gender,tel,email,picture) values (#{id},#{pass},#{name},#{gender},#{tel},#{email},#{picture})")
	int insert(Member mem);

	@Select("select * from member where id = #{value}")
	Member selectOne(String id);

	@Select("select * from member")
	List<Member> selectList();

	@Update("update member set pass=#{pass},name=#{name},gender=#{gender},tel=#{tel},email=#{email},picture=#{picture} where id=#{id}")
	int update(Member mem);

	@Delete("delete from member where id=#{value}")
	int delete(String id);
	
	@Select("select id from member where email=#{email} and tel =#{tel}")
	String idSearch(Map<String, Object> map);

	@Select("select pass from member where id=#{id} and email=#{email} and tel =#{tel}")
	String pwSearch(Map<String, Object> map);

	@Update("update member set pass=#{pass} where id=#{id}")
	boolean updatePass(Map<String, Object> map);
/*
"<if test='id != null'> and id = #{id} </if>",
"<if test='email != null'> and email = #{email} </if>", 
*/

	@Select({
        "<script>",
        "SELECT * FROM member",
        "WHERE id IN",
        "<foreach collection='ids' item='id' open='(' separator=',' close=')'>",
        "#{id}",
        "</foreach>",
        "</script>"
    })

	List<Member> emailList(Map<String, Object> map);


}