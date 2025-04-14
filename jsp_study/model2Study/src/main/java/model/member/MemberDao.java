package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DBConnection;

public class MemberDao {
	 public boolean insert(Member mem) {
		 Connection conn = DBConnection.getConnection();
		 PreparedStatement pstmt = null;
		 String sql = "insert into member (id,pass,name,gender,tel,email,picture)"
				 + " values (?,?,?,?,?,?,?)";
		 try {
			 pstmt = conn.prepareStatement(sql);
			 //pstmt.setString(1,값) => sql의 첫번째 ?에 해당하는 값을 String 형태로 설정
			 pstmt.setString(1, mem.getId());//joinForm.jsp에서 아이디로 입력된값
			 pstmt.setString(2, mem.getPass());//joinForm.jsp에서 비밀번호로 입력된값
			 pstmt.setString(3, mem.getName());//joinForm.jsp에서 이름으로 입력된값
			 pstmt.setInt(4, mem.getGender()); //DB에서 정수형태로 입력
			 pstmt.setString(5, mem.getTel());
			 pstmt.setString(6, mem.getEmail());
			 pstmt.setString(7, mem.getPicture()); //사진 이미지의 이름으로 입력된 값
			 //executeUpdate() : 실행 후 변경된 레코드의 갯수 리턴 
			 if(pstmt.executeUpdate() > 0) return true;
			 else return false;
		 } catch(SQLException e) {
			 e.printStackTrace();
		 } finally {
			 DBConnection.close(conn,pstmt,null);
		 }
		 return false;
	 }
	 
	 // 사용자의 입력한 id값과 DB안 id를 비교하여 속성 전달
	 public Member selectOne(String id) {
		 //id : loginForm.jsp 에서 입력한 아이디값
		 Connection conn = DBConnection.getConnection();
		 String sql = "select * from member where id = ?";
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 try {
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, id);
			 rs = pstmt.executeQuery();
			 if(rs.next()) { //id에 해당하는 레코드가 존재
				 Member mem = new Member();
				 mem.setId(rs.getString("id")); //rs.getString("컬럼명")
				 mem.setPass(rs.getString("pass"));
				 mem.setName(rs.getString("name"));
				//rs.getInt("gender") : gender컬럼의 값을 int형태로 처리
				 mem.setGender(rs.getInt("gender"));
				 mem.setTel(rs.getString("tel"));
				 mem.setEmail(rs.getString("email"));
				 mem.setPicture(rs.getString("picture"));
				 return mem;
			}
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 DBConnection.close(conn,pstmt,rs);
		 }
		 return null;		 
	 }
	 
	 public List<Member> list() {
		 Connection conn = DBConnection.getConnection();
		 String sql = "select * from member";
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 List<Member> list = new ArrayList<>();
		 try {
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 Member mem = new Member();
				 mem.setId(rs.getString("id"));
				 mem.setPass(rs.getString("pass"));
				 mem.setName(rs.getString("name"));
				 mem.setGender(rs.getInt("gender"));
				 mem.setTel(rs.getString("tel"));
				 mem.setEmail(rs.getString("email"));
				 mem.setPicture(rs.getString("picture"));
				 list.add(mem);
			 }
			 return list;
		 } catch (SQLException e) {
			 e.printStackTrace();
		 } finally {
			 DBConnection.close(conn,pstmt,rs);
		 }
		 return null;
	 }
	 
	 public boolean update(Member mem) {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = null;
			String sql = "update member set name=?,gender=?,tel=?,email=?,"
					+ "picture=? where id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem.getName());
				pstmt.setInt(2, mem.getGender());
				pstmt.setString(3, mem.getTel());
				pstmt.setString(4, mem.getEmail());
				pstmt.setString(5, mem.getPicture());
				pstmt.setString(6, mem.getId());
				return pstmt.executeUpdate() > 0;
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
			return false;
	}
		
	 public boolean delete(String id) {
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = null;
			String sql = "delete from member where id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				return pstmt.executeUpdate() > 0;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBConnection.close(conn, pstmt, null);
			}
			return false;
			
	}
	 
	public String idSearch(String email, String tel) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select id from member where email=? and tel =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,email);
			pstmt.setString(2,tel);
			rs = pstmt.executeQuery();
			if(rs.next()) { // true : 레코드 찾기 성공
				return rs.getString("id"); // id 값을 리턴
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return null; // 레코드 찾기 실패 또는 오류발생
		
	}
	
	public String pwSearch(String id, String email, String tel) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select pass from member where id=? and email=? and tel =?";
		try {
			pstmt = conn.prepareStatement(sql); // sql 실행
			pstmt.setString(1, id);    // id 값 비교
			pstmt.setString(2, email); // email 값 비교
			pstmt.setString(3, tel);   // tel 값 비교
			rs = pstmt.executeQuery(); // sql 문 결과값
			if(rs.next()) { 		   // true : 레코드 찾기 성공
				return rs.getString("pass"); // pass 값을 리턴
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public boolean updatePass(String id, String pass) {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update member set pass=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);    
			pstmt.setString(2, id);
			return pstmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return false;
		
	}

	// 메일 
	public List<Member> emailList(String[] ids) {
		// ids : 선택한 아이디 목록 "[test1, test2]"
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		// sb = 'test1','test2'
		for(int i=0; i< ids.length; i++) {
			sb.append("'" + ids[i] + ((i < ids.length - 1)?"',":"'"));
		}
		
		List<Member> list= new ArrayList<Member>();
		// sql : select * from member where id in ('test1','test2')
		String sql = "select * from member where id in (" + sb.toString() + ")";
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				Member m = new Member();
				 m.setId(rs.getString("id"));
				 m.setPass(rs.getString("pass"));
				 m.setName(rs.getString("name"));
				 m.setGender(rs.getInt("gender"));
				 m.setTel(rs.getString("tel"));
				 m.setEmail(rs.getString("email"));
				 m.setPicture(rs.getString("picture"));
				 list.add(m);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(conn, pstmt, rs);
		}
		return null;
	}
}