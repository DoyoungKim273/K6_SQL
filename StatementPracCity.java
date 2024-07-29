package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement; // ctrl + shift + o 해서 자동 import 단축키 사용 하는데 java가 되도록 해줘야함

public class StatementPracCity {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 따로 String 할 필요 없이 하나하나 다 써줘도 됨
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/World", "scott", "tiger");

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from city");
			ResultSetMetaData meta = rs.getMetaData();

			int count = meta.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.print(rs.getString(i) + ((i == count) ? " " : ","));
				}
				System.out.println();
			}
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
			System.out.println(" 연결 실패 : " + e.getMessage());
		}
	}
}
