package MyFood;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FoodADM {
	FoodADM(){
		init();
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.println("1.음식 등록");
			System.out.println("2.삭제");
			System.out.println("3.수정");
			System.out.println("4.전체보기");
			int num = in.nextInt();
			in.nextLine();
			if(num == 1) {
				insert();
			}else if(num == 2) {
				delete();
			}else if(num == 3) {
				modify();
			}else if(num == 4) {
				allList();
			}else {
				break;
			}
		}
	}

	private void init() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("오라클 드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void insert() {
		FoodDTO f = new FoodDTO();
		Scanner in = new Scanner(System.in);
		System.out.println("등록할 음식 번호를 입력하세요.");
		String inputNum = in.nextLine();
		System.out.println("등록할 음식명을 입력하세요.");
		String inputName = in.nextLine();
		f.setFoodNum(inputNum);
		f.setFoodName(inputName);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			System.out.println("커넥션 자원 획득 성공");
			String sql = "insert into foodlist values (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, f.getFoodNum());
			pstmt.setString(2, f.getFoodName());
			int result = pstmt.executeUpdate();
			if(result == 0) {
				conn.rollback();
			}else {
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
	
	private void delete() {
		Scanner in = new Scanner(System.in);
		System.out.println("삭제할 음식의 번호를 입력하세요.");
		String delFood = in.nextLine();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			String sql = "delete from foodlist where foodnum = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, delFood);
			int result = pstmt.executeUpdate();
			if(result == 0) {
				conn.rollback();
			}else {
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}

	private void modify() {
		Scanner in = new Scanner(System.in);
		FoodDTO f = new FoodDTO();
		System.out.println("수정할 음식의 이름을 입력하세요.");
		String modName = in.nextLine();
		f.setModName(modName);
		System.out.println("수정 후 음식의 이름을 입력하세요.");
		String newName = in.nextLine();
		f.setFoodName(newName);
		System.out.println("수정 후 음식의 변호를 입력하세요.");
		String newNum = in.nextLine();
		f.setFoodNum(newNum);
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			String sql = "update foodlist set foodname = ?, foodnum = ? where foodname = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, newName);
//			pstmt.setString(2, newNum);
//			pstmt.setString(3, modName);
			pstmt.setString(1, f.getFoodName());
			pstmt.setString(2, f.getFoodNum());
			pstmt.setString(3, f.getModName());
			int result = pstmt.executeUpdate();
			if(result == 0) {
				conn.rollback();
			}else {
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
	
	private void allList() {
		// TODO Auto-generated method stub
		System.out.println("--- 전체 음식 리스트 ---");
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			String sql = "select * from foodlist";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String num = rs.getString(1);
				String name = rs.getString(2);
				System.out.println(num + " " + name);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
}
