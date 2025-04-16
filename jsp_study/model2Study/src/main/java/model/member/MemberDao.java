package model.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import model.MybatisConnection;
import model.mapper.MemberMapper;

public class MemberDao {
	private Class<MemberMapper> cls = MemberMapper.class;
	private Map<String,Object> map = new HashMap<>();
	
	// 회원가입
	public boolean insert(Member mem) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			if(session.getMapper(cls).insert(mem) > 0) return true;
			else return false;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return false;
	}
	// 사용자의 입력한 id값과 DB안 id를 비교하여 속성 전달
	public Member selectOne(String id) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).selectOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return null;		 
	}
	// 회원목록
	public List<Member> list() {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).selectList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return null;
	}
	// 수정(업데이트) 
	public boolean update(Member mem) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).update(mem) > 0;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return false;
	}
	// 삭제	
	public boolean delete(String id) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).delete(id) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return false;
	}
	// 아이디 찾기 
	public String idSearch(String email, String tel) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			map.clear();
			map.put("email", email);
			map.put("tel", tel);
			return session.getMapper(cls).idSearch(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return null; // 레코드 찾기 실패 또는 오류발생
		
	}
	// 비밀번호 찾기
	public String pwSearch(String id, String email, String tel) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			map.clear();
			map.put("id", id);
			map.put("email", email);
			map.put("tel", tel);
			return session.getMapper(cls).pwSearch(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return null;
	}
	
	// 비밀번호수정
	public boolean updatePass(String id, String pass) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			map.clear();
			map.put("id", id);
			map.put("pass", pass);
			return session.getMapper(cls).updatePass(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return false;
		
	}
	// 메일보내기
	public List<Member> emailList(String[] ids) {
		SqlSession session = MybatisConnection.getConnection();

		try {
			map.clear();
			map.put("ids",ids);
			return session.getMapper(cls).emailList(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return null;
	}
}