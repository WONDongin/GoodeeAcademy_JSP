package main;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper;

public class Main5_interface {
	private final static SqlSessionFactory sqlMap;
	private final static Class<StudentMapper> cls = StudentMapper.class;

	static {
		InputStream input = null;
		try {
			input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}

	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("이몽룡 학생 추가하기");
		Student st = new Student();
		st.setStudno(1002);
		st.setName("이몽룡");
		st.setJumin("9710011234567");
		st.setId("leemy97");
		int cnt = session.getMapper(cls).insert(st);
		System.out.println(cnt + "건을 학생 정보에 추가함");
		Student dbst = session.getMapper(cls).selectpick("이몽룡").get(0);
		System.out.println(dbst);
		
		System.out.println("이몽룡 학생의 학년:1, 몸무게:80, 키:175로 수정하기");
		st.setGrade(1);
		st.setWeight(80);
		st.setHeight(175);		
		cnt = session.getMapper(cls).update(st);
		System.out.println(cnt + "건을 학생 정보 수정");
		dbst = session.getMapper(cls).selectpick("이몽룡").get(0);
		System.out.println(dbst);
		
		System.out.println("이몽룡학생의 정보 삭제");
		cnt = session.getMapper(cls).deleteName(st.getName());
		System.out.println(cnt + "건을 학생 정보 삭제");
		System.out.println(session.getMapper(cls).selectpick("이몽룡"));
		
		
		
	}
}
