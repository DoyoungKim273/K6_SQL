package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementPracKor_2 {
	public static void main(String[] args) {
		Statement st = null;
		ResultSet rs = null;

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/World", "scott", "tiger")) {

			st = con.createStatement();
			rs = st.executeQuery(
					"select name, population from city where countrycode = 'KOR' order by population DESC");

			while (rs.next()) {
				System.out.println(rs.getString("Name") + "," + rs.getInt("population"));
			}
		} catch (Exception e) {
			try {
				st.close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}
}
