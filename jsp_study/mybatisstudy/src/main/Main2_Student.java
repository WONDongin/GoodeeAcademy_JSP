package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main2_Student {
	private final static SqlSessionFactory sqlMap;
	// static 초기화 블록
	static {
		// 입력스트림
		InputStream input = null;
		try {
			// xml 파일을 읽어서 환경설정
			input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(input);
	}
	
	public static void main(String[] args) {
		// Connection 객체를 이용하여 mabatis와 db연결
		SqlSession session = sqlMap.openSession();
		System.out.println("학생 테이블에 레코드 추가하기");
		// db의 student 테이블의 컬럼과 같은 프로퍼티를 가지는 클래스 
		Student st = new Student(); 
		st.setStudno(1002);
		st.setName("김삿갓");
		st.setGrade(1);
		st.setId("kimsg2");
		st.setJumin("9901031234567");
		st.setMajor1(101);
		// insert(namespace.id값, 파라미터값)
		// 파라미터를 이용하여 db에 새로운 레코드를 추가. 추가된 레코드 건수 리턴
		int cnt = session.insert("student.insert", st);
		System.out.println("student 레코드 추가 : " + cnt);
		Student stResult = session.selectOne("student.selectNo", st.getStudno());
		System.out.println(stResult);
		
		// 1002번 학생의 학녕을 2학년, 몸무게:80, 키:170, 지도교수:1001 수정하기
		// sql 키 : student.update
		st.setStudno(1002);
		st.setGrade(2);
		st.setWeight(80);
		st.setHeight(170);
		st.setProfno(1001);
		cnt = session.update("student.update",st);
		stResult = session.selectOne("student.selectNo", st.getStudno());
		System.out.println("수정된 레코드의 건수 : " + cnt);
		System.out.println(stResult);
		
		// 1002번 학생 정보 삭제하기
		// student,delete
		cnt = session.delete("student.delete", st.getStudno());
		System.out.println("삭제된 레코드의 건수 : " + cnt);
		stResult = session.selectOne("student.selectNo", st.getStudno());
		System.out.println(stResult); // 조회안됨:null
		
		/*
		 * StudentMapper1.xml 파일을 이용하기
		 * 1. 학생테이블의 등록된 레코드의 건수를 출력하기
		 * 2. 학생테이블의 등록된 레코드의 정보를 출력하기
		 * 3. 학생테이블의 등록된 레코드의 1학년 학생의 정보를 출력하기
		 * 4. 학생테이블의 등록된 레코드의 성이 김씨인 학생의 정보를 출력하기
		 * 5. 학생테이블의 등록된 레코드의 3학년 학생 중 주민번호 기준 여학생 정보를 출력하기
		*/
		
		// 1
		int x =0;
		x = (Integer) session.selectOne("student.count");
		System.out.println("======== student 테이블의 레코드 건수 ========" + x);
		
		// 2
		System.out.println("======== student 테이블의 레코드 정보 ========");
		List<Student> list = session.selectList("student.list");
		for(Student m : list) System.out.println(m);
		
		// 3
		System.out.println("======== student 테이블에서 1학년 학생의 정보 출력 ========");
		list = session.selectList("student.selectgrade",1);
		for(Student m : list) System.out.println(m);
		
		// 4
		System.out.println("======== student 테이블에서 성이 김씨 학생의 정보 출력 ========");
		list = session.selectList("student.selectfname","김%");
		for(Student m : list) System.out.println(m);
		
		// 5
		System.out.println("======== student 테이블에서 3학년 학생 중 주민번호 기준 여학생 학생의 정보 출력 ========");
		list = session.selectList("student.selectall",3);
		for(Student m : list) System.out.println(m);
		
		// session.commit();
	}	
}
