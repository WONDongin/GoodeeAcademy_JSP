package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main3_Professor {
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
		
		/*
		* ProfessorMapper.xml 파일을 이용하기
		* Professor.java 파일 이용하기
		1. 교수테이블에 등록된 레코드의 건수를 출력하기.
		2. 교수테이블에 등록된 모든 정보를 출력하기
		3. 교수중 101번 학과의 교수 정보를 출력하기
		4. 교수중 성이 김씨인 시간강사 정보를 출력하기 
		*/
		
		// 1
		int x =0;
		x = (Integer) session.selectOne("professor.count");
		System.out.println("======== professor 테이블의 레코드 건수 ========\n" + x + "건");
		
		// 2
		System.out.println("======== professor 테이블의 레코드 정보 ========");
		List<Professor> list = session.selectList("professor.list");
		for(Professor m : list) System.out.println(m);
		
		// 3
		System.out.println("======== professor 테이블의 101번 학과 정보 ========");
		list = session.selectList("professor.deptno","101");
		for(Professor m : list) System.out.println(m);
		
		// 3
		System.out.println("======== professor 테이블의 성이 김씨인 시간강사 정보 ========");
		list = session.selectList("professor.all","김%");
		for(Professor m : list) System.out.println(m);
	
	}	
}
