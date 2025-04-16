package mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import main.Student;

public interface StudentMapper2 {
	

	// 동적태그 사용시 <script></script>사용
	// < : 파생하는 과정에서 태그로 인식하여 <= 오류발생 => &lt; 
	// > : 태그 시작하는 태그가 없는 끝 태그라서 무시
	@Select({"<script>",
		"select * from student",
		"<where>",
		"<if test='grade != nll'> grade = #{grade}</if>",
		"<if test='height != nll'> height >= #{height}</if>",
		"<if test='weight != nll'> weight &lt;= #{weight}</if>",
		"</where>",
		"</script>"})
	List<Student> select(Map<String, Object> map);

	// trim 방식
	String sql = "select * from student";
	@Select({
		"<script>",
		sql,
		"<trim prefix='where' prefixOverrides='AND || OR'>",
		"<if test='grade != null'> and grade = #{grade} </if>",
		"<if test='height != null'> and height >= #{height} </if>",
		"<if test='weight != null'> and weight &lt;= #{weight} </if>",
		"</trim>",
		"</script>"
	})
	List<Student> select2(Map<String, Object> map);
	

}
