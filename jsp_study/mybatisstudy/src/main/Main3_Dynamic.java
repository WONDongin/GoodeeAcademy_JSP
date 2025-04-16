package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main3_Dynamic {
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
		
		System.out.println("학생 전체 레코드 조회하기");
		List<Student> list = session.selectList("student2.select1");
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학생 레코드 조회하기");
		Map<String, Object> map = new HashMap<>();
		map.put("grade", 1);
		list = session.selectList("student2.select1", map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("학생 중 학번이 220111 인 레코드 조회하기");
		map.clear(); // 모든 요소 제거
		map.put("studno", "220111");
		Student st = session.selectOne("student2.select1", map);
		System.out.println(st);
		
		System.out.println("학생 중 키가 180이상인 학생의 정보 조회하기");
		map.clear();
		map.put("height", 180);
		list = session.selectList("student2.select1", map);
		for(Student s : list) System.out.println(s);
		
		/* (and 없어서 오류) - choose/when 코드로 실행
		System.out.println("1학생 중 키가 180이상인 학생의 정보 조회하기");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list = session.selectList("student2.select1", map);
		for(Student s : list) System.out.println(s);
		*/
		//
		System.out.println("======== student2.select2로 조회하기 ========");
		System.out.println("1학생 중 키가 180이상인 학생의 정보 조회하기");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list = session.selectList("student2.select2", map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("학생 중 학번이 220111 인 레코드 조회하기");
		map.clear(); // 모든 요소 제거
		map.put("studno", "220111");
		st = session.selectOne("student2.select2", map);
		System.out.println(st);
		//
		System.out.println("======== student2.select3로 조회하기 ========");
		System.out.println("전체 학생의 정보 조회하기");
		list = session.selectList("student2.select3");
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학생 레코드 조회하기");
		map.clear();
		map.put("grade", 1);
		list = session.selectList("student2.select3", map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("학생 중 학번이 220111 인 레코드 조회하기");
		map.clear();
		map.put("studno", "220111");
		st = session.selectOne("student2.select3", map);
		System.out.println(st);
		
		System.out.println("학생 중 키가 180이상인 학생의 정보 조회하기");
		map.clear();
		map.put("height", 180);
		list = session.selectList("student2.select3", map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학생 중 키가 180이상인 학생의 정보 조회하기");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		list = session.selectList("student2.select3", map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학생 중 키가 180이상이고, 학번이 240111 학생의 정보 조회하기");
		map.clear();
		map.put("grade", 1);
		map.put("height", 180);
		map.put("studno", "240111");
		list = session.selectList("student2.select3", map);
		for(Student s : list) System.out.println(s);
		//
		System.out.println("======== student2.select4로 조회하기 ========");
		System.out.println("101,201,301 학과에 속한 학생의 정보 조회하기");
		// 조회용 : 객체를 추가 생성할수 없는 asList 사용(복사본 느낌)
		List<Integer> mlist = Arrays.asList(101,201,301);
		map.clear();
		map.put("column", "major1");
		map.put("datas", mlist);
		list = session.selectList("student2.select4", map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("몸무게가 75,85,80인 학생의 정보 조회하기");
		map.clear();
		map.put("column", "weight");
		map.put("datas", Arrays.asList(75,80,85));
		list = session.selectList("student2.select4", map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("키가 170,175,180,185인 학생의 정보 조회하기");
		map.clear();
		map.put("column", "height");
		map.put("datas", Arrays.asList(170,175,180,185));
		list = session.selectList("student2.select4", map);
		for(Student s : list) System.out.println(s);
	}
}
