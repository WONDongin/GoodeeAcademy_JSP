package model.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.MybatisConnection;
import model.mapper.BoardMapper;

public class BoardDao {
	private Class<BoardMapper> cls = BoardMapper.class;
	private Map<String,Object> map = new HashMap<>();
	
	public int maxnum() {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).maxnum();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return 0;
	}
	
	// 게시글 등록
	public boolean insert(Board board) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).insert(board) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return false;
	}
	
	// 게시판 종류별 : 등록된 게시글 건수
	public int boardCount(String boardid, String column, String find) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			map.clear();
			map.put("boardid", boardid);
			map.put("column", column);
			map.put("find", find);
			if(column != null) { // 검색 내용이 존재.
				String[] cols = column.split(",");
				switch (cols.length){
				case 3 : map.put("col3", cols[2].trim());
				case 2 : map.put("col2", cols[1].trim());
				case 1 : map.put("col1", cols[0].trim());	
				}
			}
			return session.getMapper(cls).count(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return 0;
	}
	
	// 게시글 리스트
	public List<Board> list(String boardid, int pageNum, int limit, String column, String find) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			map.clear();
			map.put("boardid", boardid); // 게시판 종류
			map.put("start", (pageNum - 1) * limit);
			/*
			pageNum : 1  start : 0
			pageNum : 2  start : 10
			*/
			map.put("limit", limit);
			map.put("column", column);
			map.put("find", find);
			if(column != null) {
				String[] cols = column.split(",");
				switch (cols.length){
				case 3 : map.put("col3", cols[2].trim());
				case 2 : map.put("col2", cols[1].trim());
				case 1 : map.put("col1", cols[0].trim());	
				}
			}
			return session.getMapper(cls).list(map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return null;
	}
	
	// info 페이지
	public Board selectOne(int num) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			return session.getMapper(cls).selectOne(num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return null;
	}
	
	// 조회수 증가
	public void readcntAdd(int num) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			session.getMapper(cls).readcntAdd(num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
	}
	
	// 
	public void grpStepAdd(int grp, int grpstep) {
		SqlSession session = MybatisConnection.getConnection();
		try {
		   session.getMapper(cls).grpStepAdd(grp,grpstep);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
	}
	
	// 댓글 수정
	public boolean update(Board board) {
		SqlSession session = MybatisConnection.getConnection();
		try {
		   return session.getMapper(cls).update(board) > 0;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return false;
	}
	
	// 댓글삭제
	public boolean delete(int num) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).delete(num);
			if (cnt > 0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return false;
	}
	
	// 대댓글
	public int replyCnt(int grp) {
		SqlSession session = MybatisConnection.getConnection();
		try {
			int cnt = session.getMapper(cls).replyCnt(grp);
			return cnt;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return 0;
	}
	// 차트1
	public List<Map<String, Object>> boardgraph1() {
		SqlSession session = MybatisConnection.getConnection();
		List<Map<String, Object>> list = null;
		try {
			return session.getMapper(cls).graph1();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return list;
	}
	// 차트2
	public List<Map<String, Object>> boardgraph2() {
		SqlSession session = MybatisConnection.getConnection();
		List<Map<String, Object>> list = null;
		try {
			return session.getMapper(cls).graph2();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MybatisConnection.close(session);
		}
		return list;
	}
}
