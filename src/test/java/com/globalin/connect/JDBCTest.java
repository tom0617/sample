package com.globalin.connect;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class JDBCTest {
	//이 메소드는 테스트용 메소드에요.
	@Test
	public void testConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","JUEHYUN1","1234");
			assertNotNull(conn);
			
		} catch (ClassNotFoundException | SQLException e) {
			// fail 메소드가 실행되면 무조건 테스트 실패
			// fail(메세지)
			//fail(e.getMessage());
			e.printStackTrace();
		}
	}

}
