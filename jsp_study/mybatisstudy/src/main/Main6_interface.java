package main;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.StudentMapper2;

public class Main6_interface {
	private final static SqlSessionFactory sqlMap;
	private final static Class<StudentMapper2> cls = StudentMapper2.class;
	private static Map<String,Object> map = new HashMap<>();

	static {
		InputStream reader = null;
		try {
			reader = Resources.getResourceAsStream("mapper/mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlMap = new SqlSessionFactoryBuilder().build(reader);
	}
	public static void main(String[] args) {
		SqlSession session = sqlMap.openSession();
		System.out.println("모든 학생 정보 조회하기");
		List<Student> list = session.getMapper(cls).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 정보 조회하기");
		map.put("grade", 1);
		list = session.getMapper(cls).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 중 키가 175이상 학생 정보 조회하기");
		map.clear();
		map.put("height", 175);
		list = session.getMapper(cls).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("몸무게가 60이하 학생 정보 조회하기");
		map.clear();
		map.put("weight", 60);
		list = session.getMapper(cls).select(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("============= select2 =============");
		System.out.println("모든 학생 정보 조회하기");
		list = session.getMapper(cls).select2(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 정보 조회하기");
		map.put("grade", 1);
		list = session.getMapper(cls).select2(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("1학년 중 키가 175이상 학생 정보 조회하기");
		map.clear();
		map.put("grade", 1);
		map.put("height", 175);
		list = session.getMapper(cls).select2(map);
		for(Student s : list) System.out.println(s);
		
		System.out.println("몸무게가 60이하 학생 정보 조회하기");
		map.clear();
		map.put("weight", 60);
		list = session.getMapper(cls).select2(map);
		for(Student s : list) System.out.println(s);
	}
}
