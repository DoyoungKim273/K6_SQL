package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementPracCountry {
	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from country");
			
			while(rs.next()) {
				System.out.print(rs.getString("Name") + ",");
				System.out.print(rs.getString("Code") + ",");
				System.out.print(rs.getString("Continent") + ",");
				System.out.print(rs.getString("Region") + ",");
				System.out.print(rs.getString("SurfaceArea") + ",");
				System.out.print(rs.getString("IndepYear") + ",");
				System.out.print(rs.getString("Population") + ",");
				System.out.print(rs.getString("LifeExpectancy") + ",");
				System.out.print(rs.getString("GNP") + ",");
				System.out.print(rs.getString("GNPOld") + ",");
				System.out.print(rs.getString("LocalName") + ",");
				System.out.print(rs.getString("GovernmentForm") + ",");
				System.out.print(rs.getString("HeadOfState") + ",");
				System.out.print(rs.getString("Capital") + ",");
				System.out.print(rs.getString("Code2") + "\n");
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}
	}
}
