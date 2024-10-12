package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.IdeaDTO;

public class IdeaDAO {
	private Connection conn = null;
	public IdeaDAO(){
		init();
	}

	private void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로드 성공.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private boolean conn() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","11111111");
//			System.out.println("Connection 자원 획득 성공.");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	//기능을 수행할때 만다 conn()메소드를 호출한다.
	
	public void insert(IdeaDTO i) {
		if(conn()) {
			try {
				String sql = "insert into ideabank values(ideabank_seq.nextval, ?, ?, ?,default)";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, i.getTitle());
				pstmt.setString(2, i.getContent());
				pstmt.setString(3, i.getWriter());
				int result = pstmt.executeUpdate();
				if(result > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
			}
			
		}else {
			System.out.println("Connection 자원 획득 실패.");
		}
	}
	
	
	public ArrayList<IdeaDTO> selectAll() {
		ArrayList<IdeaDTO> flist = new ArrayList<>();
		if(conn()) {
			try {
				String sql = "select * from ideabank";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) { //next() : rs에서 참조하는 테이블에서 튜플을 순차적으로 하나씩 접근하는 메소드.
					IdeaDTO iTemp = new IdeaDTO();
					iTemp.setNum(rs.getInt("num"));
					iTemp.setTitle(rs.getString("title")); //getString( String columnLabel ) : 테이블에 컬럼명을 매개변수로 받겠다.
					iTemp.setContent(rs.getString("content"));
					iTemp.setWriter(rs.getString("writer"));
					iTemp.setIndate(rs.getString("indate"));
					flist.add(iTemp);
					//여기서 return flist; 를 하면 1개 튜플에 값만 리턴됩니다.
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection 자원 획득 실패.");
		}
		//데이블속 모든 튜플속 값이 답겨있는 flist를 리턴해야 맞다. 
		return flist;
	}
	
	public void delete(IdeaDTO i) {
		//조건문을 작성할때 웬만하면 조건에 참과 거짓일때 실행문을 모두 작성하는 것이 좋다.
		//디버깅하기 편해진다.
		if(conn()) { 
			try {
				String sql = "delete from ideabank where num = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i.getNum());
				int resultset = pstmt.executeUpdate();
				if(resultset > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection 자원 획득 실패");
		}
	}
	
	public void modify(IdeaDTO i) {
		if(conn()) {
			try {
				//정보가 수정되면 "작성일"도 업데이터 된다.
				String sql = "update ideabank set title = ?, content = ?, indate = sysdate where num = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, i.getTitle());
				pstmt.setString(2, i.getContent());
				pstmt.setInt(3, i.getNum());
				int resultSet = pstmt.executeUpdate();
				if(resultSet > 0) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection 자원 획득 실패");
		}
	}
	
	
	public IdeaDTO selectOne(int i){
		if(conn()) {
			try {
				String sql = "select * from ideabank where num = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,i);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					IdeaDTO iTemp = new IdeaDTO();
					iTemp.setNum(rs.getInt("num"));
					iTemp.setTitle(rs.getString("title"));
					iTemp.setContent(rs.getString("content"));
					iTemp.setWriter(rs.getString("writer"));
					iTemp.setIndate(rs.getString("indate"));
					return iTemp;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Connection 자원 획득 실패");
		}
		return null;
	}
	
	//검색에서 ArrayList를 사용하는 이유 : 
	//하나의 검색조건에 여러 튜플의 값이 매칭될 수 있다.
	public ArrayList<IdeaDTO> select(String searchW) {
		ArrayList<IdeaDTO> flist = new ArrayList<>();
		if(conn()) {
			try {
				String sql = "select * from ideabank where title like '%"+searchW+"%'";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					IdeaDTO iTemp = new IdeaDTO();
					iTemp.setNum(rs.getInt("num"));
					iTemp.setTitle(rs.getString("title"));
					iTemp.setContent(rs.getString("content"));
					iTemp.setWriter(rs.getString("writer"));
					iTemp.setIndate(rs.getString("indate"));
					flist.add(iTemp);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flist;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
