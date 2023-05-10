package com.myweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.sql.DataSource;

import com.myweb.commons.PageVO;

public class BoardDAO implements IBoardDAO {

	private DataSource ds;

	private BoardDAO() {
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/myOracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static BoardDAO dao = new BoardDAO();

	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}


	//작성
	public void regist(String writer, String title, String content) {
		String sql = "INSERT INTO my_board (board_id, writer, title, content) VALUES(board_seq.NEXTVAL, ?, ?, ?)";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//리스트 보기
	public List<BoardVO> listBoard(PageVO paging) {
		List<BoardVO> boardList = new ArrayList<>();
		String sql = "SELECT * FROM"
                + "    ("
                + "    SELECT ROWNUM AS rn, tbl.* FROM"
                + "        ("
                + "        SELECT * FROM my_board"
                + "        ORDER BY board_id DESC"
                + "        ) tbl"
                + "    )"
                + "WHERE rn > " + (paging.getPage()-1) * paging.getCpp()
                + "AND rn <= " + paging.getPage() * paging.getCpp();
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) { 
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				
				BoardVO bo = new BoardVO(
							rs.getInt("board_id"),
							rs.getString("writer"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getTimestamp("reg_date").toLocalDateTime(),
							rs.getInt("hit")
						);
				boardList.add(bo);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return boardList;
	}

	//글 보기
	public BoardVO contentBoard(int bId) {
		BoardVO vo = null;
		String sql = "SELECT * FROM my_board WHERE board_id="+bId;
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) { 
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new BoardVO(
						rs.getInt("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getTimestamp("reg_date").toLocalDateTime(),
						rs.getInt("hit")
						);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return vo;
	}

	//수정
	public void updateBoard(String title, String content, int bId) {
		String sql = "UPDATE my_board SET title=?, content=? WHERE board_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) { 
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, bId);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//삭제
	public void deleteBoard(int bId) {
		String sql = "DELETE FROM my_board WHERE board_id="+bId;
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public List<BoardVO> searchBoard(String search, String category) {
		List<BoardVO> list = new ArrayList<>();
		String sql = "SELECT * FROM my_board WHERE "+ category + " LIKE ?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,"%"+search+"%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardVO vo = new BoardVO(
						rs.getInt("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getTimestamp("reg_date").toLocalDateTime(),
						rs.getInt("hit")
						);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return list;
	}
	
	@Override
	public void upHit(int bId) {
		String sql = "UPDATE my_board SET hit=hit+1 WHERE board_id=?";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, bId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int countArticles() {
		int count = 0;
		String sql = "SELECT COUNT(*) FROM my_board";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return count;
	}
	

}
