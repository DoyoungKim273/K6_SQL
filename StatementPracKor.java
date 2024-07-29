package edu.pnu;

// 국가 코드가 ‘KOR’인 도시를 찾아 인구수를 역순으로 표시하세요. (도시명, 인구수)

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class StatementPracKor {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/World", "scott", "tiger");

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select name, population from city where countrycode = 'KOR' order by population DESC");
			ResultSetMetaData meta = rs.getMetaData();

			int count = meta.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.print(rs.getString(i) + ((i == count) ? "" : ","));
				}
				System.out.println(); // 인구수 역순 정렬 어떻게 할까요???
			}
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}
	}
}
