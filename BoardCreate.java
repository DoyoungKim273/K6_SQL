package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class BoardCreate {
	public static void main(String[] args) {

//		int randomNumberInRange = rnd.nextInt(100 - 0 + 1) + 0;
		try (Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "abcd")) {
//			if (!createTable(con))
//				return;
//			insertTable(con);
			showTable(con);
		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println("Done");

	}

	public static boolean createTable(Connection con) {
		Statement st = null;
		try {
			st = con.createStatement();
			st.execute("DROP TABLE Board IF EXISTS");
			st.execute("CREATE TABLE Board (" + "num int NOT NULL AUTO_INCREMENT PRIMARY KEY," + "title varchar(200),"
					+ "content varchar(2000)," + "id varchar(10)," + "postdate date NOT NULL DEFAULT (curdate()),"
					+ "visitcount int DEFAULT 0)");

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

	public static boolean insertTable(Connection con) {
//		Statement st = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		Random rnd = new Random();
		try {
			psmt = con.prepareStatement("insert into Board(title, content, id, visitcount) values(?, ?, ? ,?)");
			for (int j = 1; j <= 10; j++) {
				for (int i = 1; i <= 5; i++) {
					// psmt.setXXX
					psmt.setString(1, "title" + i);
					psmt.setString(2, "content" + i);
					psmt.setString(3, "user" + i);
//					psmt.setString(4, "postdate" + i);				
					psmt.setInt(4, rnd.nextInt(100 - 0 + 1) + 0);
					int result = psmt.executeUpdate();
					System.out.println("Board 테이블에 " + result + "개가 입력되었습니다.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
//		} finally {
//			try {
//			} catch (SQLException e){
//				e.printStackTrace();
//			}
		}
		return true;
	}
	
	public static boolean showTable(Connection con) {
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from board");
			ResultSetMetaData meta = rs.getMetaData();

			int count = meta.getColumnCount();
			int rowCount = 0;

			while (rs.next()) {
				rowCount ++;
				for (int i = 1; i <= count; i++) {
					System.out.println(rs.getString(i) + ((i == count) ? " " : ","));
//					System.out.println( i % 5 == 0 ? "-------" : "");
				}
				if(rowCount % 5 == 0) {
					System.out.println("-----------------");
				} else {
					System.out.println();
				}
			}
			rs.close();
			st.close();
		} catch (Exception e){
			System.out.println(" 연결 실패 : " + e.getMessage());
		}
		return false;
	}
}
