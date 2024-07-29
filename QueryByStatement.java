package edu.pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryByStatement {

	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/World";
			String username = "scott";
			String password = "tiger";

			Class.forName(driver); // JDBC 드라이버 로딩하기
			con = DriverManager.getConnection(url, username, password); // DBMS 서버와 접속하기

			// Statement는 Connection 상에서 SQL 문을 처리하는 객체
			// Connection의 createStatement() 메서드를 이용해서 SQL 문을 실행하기 위한 statement 객체 생성
			Statement st = con.createStatement();
			// Statement.executeQuery() 메서드를 사용하여 SQL 쿼리를 실행하고, 결과를 ResultSet 객체로 받아옴
			ResultSet rs = st.executeQuery("select id, name, countrycode, district, population from city limit 10");

			// ResultSet 객체의 next() 메서드를 사용하여 결과 집합을 반복하면서 각 행의 데이터를 처리
			// 각 열의 데이터는 getString() 메서드를 사용하여 가져옴
			while (rs.next()) { // get 데이터타입 (column명)
				System.out.print(rs.getString("id") + ",");
				System.out.print(rs.getString("name") + ",");
				System.out.print(rs.getString("countrycode") + ",");
				System.out.print(rs.getString("district") + ",");
				System.out.print(rs.getString("population") + "\n");
			}
			// 자원 해제: 사용이 완료된 ResultSet, Statement, Connection 객체를 순서대로 닫아서 자원을 반환
			// 메모리 누수를 방지하고 데이터베이스 자원을 효율적으로 관리하는 데 도움됨
			rs.close();
			st.close();
			con.close();

			// 예외가 발생하면 프로그램은 비정상적으로 종료될 수 있기에 예외를 처리하여 프로그램이 계속해서 실행될 수 있도록 하는 것이 중요
			// 이때 try-catch 구문이 사용됨
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());

		}
	}
}
