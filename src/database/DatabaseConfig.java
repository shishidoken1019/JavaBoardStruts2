package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class DatabaseConfig {
	
	/**
	 * データベースコネクションを取ってくる
	 * @return　データベースコネクション　
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Connection getDatabaseConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		//サーブレット設定ファイルの内容を取得
		ServletContext sc = ServletActionContext.getServletContext();
		Class.forName(sc.getInitParameter("DB_Connection")).newInstance();
		
		//設定内容から、コネクションを取得
		Connection con = DriverManager.getConnection(sc.getInitParameter("DB_Server"),sc.getInitParameter("DB_Name"),sc.getInitParameter("DB_Password"));
		return con;
		
	}

}
