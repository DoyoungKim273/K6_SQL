package edu.pnu;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class MemberCreate {
	public static void main(String[] args) {
//		PreparedStatement psmt = null;
//		ResultSet rs = null;
//		Random rnd = new Random();
////		int randomNumberInRange = rnd.nextInt(2010 - 2000 + 1) + 2000; // 난수가 모든 user에서 동일 값으로 지정되어버림
//		int randomNumberInRange2 = rnd.nextInt(5 - 1 + 1) + 1;
//		System.out.println(rnd.nextInt(2010 - 2000 + 1) + 2000);
		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
//			if (!createTable(con))
//				return;

//			psmt = con.prepareStatement("insert into Member(username,password,birthyear) values(?,?,?)");
//			for (int i = 1 ; i <= 5 ; i++) {
//			// psmt.setXXX
//				psmt.setString(1, "user" + i);
//				psmt.setString(2, "pass" + i);
//				psmt.setInt(3, rnd.nextInt(2010 - 2000 + 1) + 2000); // 각 user에서 난수가 달라짐
//				
//			int result = psmt.executeUpdate();
//			System.out.println("Member 테이블에 " + result + "개가 입력되었습니다.");
//			insertTable(con);
//			createTable(con);
//			deleteTable(con);
//			updateTable(con);
			showTable(con);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");

	}

	public static boolean insertTable(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Random rnd = new Random();
//		int randomNumberInRange = rnd.nextInt(2010 - 2000 + 1) + 2000; // 난수가 모든 user에서 동일 값으로 지정되어버림
//		int randomNumberInRange2 = rnd.nextInt(5 - 1 + 1) + 1;
		System.out.println(rnd.nextInt(2010 - 2000 + 1) + 2000);
		try {
			psmt = con.prepareStatement("insert into Member(username,password,birthyear) values(?,?,?)");
			for (int i = 1; i <= 5; i++) {
				// psmt.setXXX
				psmt.setString(1, "user" + i);
				psmt.setString(2, "pass" + i);
				psmt.setInt(3, rnd.nextInt(2010 - 2000 + 1) + 2000); // 각 user에서 난수가 달라짐
				int result = psmt.executeUpdate();
				System.out.println("Member 테이블에 " + result + "개가 입력되었습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done");
		return true;
	}

	public static boolean createTable(Connection con) {
		Statement st = null;
		try {
			st = con.createStatement();
			st.execute("DROP TABLE Member IF EXISTS");
			st.execute("CREATE TABLE Member (" + "id int NOT NULL AUTO_INCREMENT PRIMARY KEY," + "username varchar(10),"
					+ "password varchar(10)," + "birthyear int NOT NULL,"
					+ "regidate date NOT NULL DEFAULT (curdate()))");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public static boolean deleteTable(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
//		Statement st = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("user id를 입력하세요.");
		int id = scanner.nextInt();
		try {
			psmt = con.prepareStatement("DELETE FROM Member WHERE id = ?");
			psmt.setInt(1, id);
			int result = psmt.executeUpdate();
			System.out.println("Member 테이블에 user" + id + "값이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean updateTable(Connection con) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
//		Statement st = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("user id를 입력하세요. :");
		int id = scanner.nextInt();
		System.out.println("username : ");
		String username = scanner.next();
		System.out.println("password : ");
		String password = scanner.next();
		System.out.println("birthyear : ");
		int birthyear = scanner.nextInt();
		try {
//			UPDATE MEMBER 
//			SET USERNAME = 'USER100' 
//			WHERE ID = 3;
			psmt = con.prepareStatement("UPDATE Member SET username = ?, password = ?, birthyear = ? WHERE id = ?");
			psmt.setString(1, username);
			psmt.setString(2, password);
			psmt.setInt(3, birthyear);
			psmt.setInt(4, id);
			int result = psmt.executeUpdate();
			System.out.println("Member 테이블에 user" + id + "값이 수정되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean showTable(Connection con) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from member");
			ResultSetMetaData meta = rs.getMetaData();

			int count = meta.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.println(rs.getString(i) + ((i == count) ? " " : ","));
				}
				System.out.println();
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			System.out.println(" 연결 실패 : " + e.getMessage());
		}
		return false;
	}

}
