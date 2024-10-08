package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.FishDTO;

public class FishDAO {
	private Connection conn = null;
	public FishDAO(){
		init();
	}
	
	//1. 드라이버 로드
	private void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("DB 드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//2. 커넥션 자원 획득.
	private boolean conn() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","11111111");
			System.out.println("Connection 자원 획득 성공.");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void add(FishDTO f) {
		if(conn()) {
			try {
				String sql = "insert into foodlist values (?,?,default)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, f.getId());
				pstmt.setString(2, f.getPwd());
				int result = pstmt.executeUpdate();
				if(result > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				try {
					if(conn != null) {
						conn.close();
					}
				}catch(Exception e2) {
					
				}
			}
		}else {
			System.out.println("DB Connection 실패");
		}
	}
	
	public void delete(FishDTO f) {
		if(conn()) {
			try {
				String sql = "delete from fishlist where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, f.getId());
				pstmt.setString(2, f.getPwd());
				int result = pstmt.executeUpdate();
				if(result > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}else {
			System.out.println("커넥션 자원 획득 실패");
		}
	}
	
	//검색기능.
	public void findId(FishDTO f) {
		if(conn()) {
			try {
				String sql = "select * from fishlist where id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, f.getId());
				int result = pstmt.executeUpdate();
				if(result > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}else {
			System.out.println("커넥션 자원 획득 실패");
		}
	}
	
	
	
	
}
