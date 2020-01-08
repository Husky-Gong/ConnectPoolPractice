package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

/*
 * This class is designated to read properties from a XML file 
 * and set the connection
 * 1. A private method is to utilize XMLReaderUtil to read connection required information
 * 2. A public method is to help user touch the private method and returns the Connection object.
 */

public class ConnectionUtil01 {
	//1. get Net Information
	@SuppressWarnings("unused")
	private static Connection setConnection() {
		Map<String,String> sqlInfoMap = XMLReaderUtil.getSQLInfo();
		String userName = sqlInfoMap.get("userName");
		String passWord = sqlInfoMap.get("passWord");
		String url = sqlInfoMap.get("url");
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,userName,passWord);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Connection getConn() {
		return setConnection();
	}
}
