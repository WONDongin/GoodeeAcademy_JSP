package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import main.Student;

/*
인터페이스 방식으로 Mapper 사용하기
1. mybatis-config.xml 의 mapper에 package로 설정
2. namespace : mapper.StudentMapper. 인터페이스의 전체 이름이 namespace임
3. 메서드의 이름의 sql문장의 key값임 => 같은 이름의 메서드 허용안함(id 값이 같아지기 때문에)
   = Mapper 인터페이스는 오버로딩 불가 
   
   ibatis  : 1~2버전
   myBatis : 3버전 부터 사용
*/
public interface StudentMapper {
	@Select("select * from student")
	List<Student> select();
	
	// @Select("select * from student") => 오버로딩시 오류발생
	// List<Student> select(int studno);
	
	@Select("select * from student where grade = #{value}")
	List<Student> selectGrade(int i);
	
	@Select("select * from student where Studno = #{value}")
	Student selectStudno(String string);
	/*
	xml 방식
	<select id="selectStudno" parameterTpye="string" resultType="Student">
	 	select * from student where Studno = #{value}
	</select> 
	*/
	
	@Select("select * from student where name = #{value}")
	List<Student> selectpick(String string);
	
	@Select("select * from student where grade = #{grade} and height >= #{height}")
	List<Student> selectGradeHeight(Map<String, Object> map);

	@Select("select * from student where grade = #{grade} and height >= #{height}")
	// @Param("grade")int a : a변수를 grade key 값으로 설정
	List<Student> selectGradeHeight2
	(@Param("grade")int grade, @Param("height")int height);

	@Insert("insert into student (studno,name,jumin,id) values (#{studno},#{name},#{jumin},#{id})")
	int insert(Student st);
	
	
	@Update("update student set grade=#{grade}, weight=#{weight}, height=#{height} where name=#{name}")
	int update(Student st);

	@Delete("delete from student where name=#{value}")
	int deleteName(String name);


	

	


	
	
}
