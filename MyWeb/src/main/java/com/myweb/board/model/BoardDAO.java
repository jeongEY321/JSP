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
	public List<BoardVO> listBoard() {
		List<BoardVO> boardList = new ArrayList<>();
		String sql = "SELECT * FROM my_board";
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



		return null;
	}

	//수정
	public void updateBoard(String title, String content, int bId) {


	}

	//삭제
	public void deleteBoard(int bId) {


	}

}
