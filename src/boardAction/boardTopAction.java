package boardAction;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletRedirectResult;

import database.DatabaseConfig;

@Results({
		@Result(name = "loginTop", value = "login.action", type = ServletRedirectResult.class),
		@Result(name = "boardTop", value = "boardTop.action", type = ServletRedirectResult.class),
		@Result(name = "threadDetail", value = "threadDetail.action", type = ServletRedirectResult.class),
		@Result(name = "createAccount", value = "createAccount.action", type = ServletRedirectResult.class) })
public class boardTopAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	DatabaseConfig databaseConfig = new DatabaseConfig();
	ServletContext sc = ServletActionContext.getServletContext();

	/**
	 * ログインID
	 */
	private int loginID;

	/**
	 * ログイン名
	 */
	private String loginName;
	/**
	 * ログインパスワード
	 */
	private String password;
	/**
	 * ログインイメージ
	 */
	private String loginImage;
	/**
	 * スレッド名
	 */
	private String threadName = "SEKAINOOWARI";
	/**
	 * スレッドイメージ
	 */
	private String threadImage = "";
	/**
	 * スレッドID
	 */
	private int threadID;
	/**
	 * ページカウント
	 */
	private int pageCount;
	

	private ArrayList<boardTopAction> threadList = new ArrayList<boardTopAction>();
	public ArrayList<String> nameList = new ArrayList<String>();

	private String SelectThreadContents = "select thread_id,thread_name,thread_image from thread limit ?,?";

	private int pageThreadCount = 8;
	private int pageNumber = 1;
	

	// 初期処理
	public String execute() {

		// セッション情報を持ってくる
		this.loginName = (String) sessionMap.get("loginName");
		System.out.println(sessionMap.get("loginName"));

		try {
			//コネクションを作る
			Connection con = databaseConfig.getDatabaseConnection();

			// 掲示板トップ画面に表示するスレッド情報を取得
			getBoardTopInfomation(con);
			
			//ページ数をカウント。ページ数を取得
			int allThreadCount =0;
			String sql = "select count(*) from thread;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				allThreadCount = rs.getInt(1);
			}
			
			//ページ数を取得
			if(allThreadCount%pageThreadCount == 0){
				pageCount = allThreadCount%pageThreadCount;
			}else{
				pageCount = allThreadCount%pageThreadCount+1;
			}

		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return "success";
	}

	//ページング処理が行われたとき
	public String paging() {
		try {
			//コネクションを作る
			Connection con = databaseConfig.getDatabaseConnection();

			// 掲示板トップ画面に表示するスレッド情報を取得
			getBoardTopInfomation(con);
			
			//ページ数をカウントし、ページ数を取得
			String sql = "select count(*) from thread;";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pageCount = rs.getInt(1);
			}
			
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		return "success";

	}

	//ログイントップ画面へ
	public String loginTop() {
		return "loginTop";
	}

	//スレッド詳細画面へ
	public String threadDetail(){
		sessionMap.put("threadID", getThreadID());
		return "threadDetail";
	}

	
	/**
	 * 掲示板トップ画面に表示するスレッド情報を取得
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void getBoardTopInfomation(Connection con) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {

		// データベースから掲示板のスレッド情報を任意の数、拾ってくる
		PreparedStatement ps = con.prepareStatement(SelectThreadContents);
		ps.setInt(1, (pageNumber - 1) * pageThreadCount);
		ps.setInt(2, pageThreadCount);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			// リストに情報を格納
			boardTopAction bt = new boardTopAction();
			bt.setThreadID(rs.getInt("thread_id"));
			bt.setThreadName(rs.getString("thread_name"));
			bt.setThreadImage(rs.getString("thread_image"));
			threadList.add(bt);
		}

	}
	
	/**
	 * @return loginImage
	 */
	public String getLoginImage() {
		return loginImage;
	}

	/**
	 * @param loginImage
	 *            セットする loginImage
	 */
	public void setLoginImage(String loginImage) {
		this.loginImage = loginImage;
	}

	/**
	 * @return threadList
	 */
	public ArrayList<boardTopAction> getThreadList() {
		return threadList;
	}

	/**
	 * @return threadImage
	 */
	public String getThreadImage() {
		return threadImage;
	}

	/**
	 * @param threadImage
	 *            セットする threadImage
	 */
	public void setThreadImage(String threadImage) {
		this.threadImage = threadImage;
	}

	/**
	 * @param threadList
	 *            セットする threadList
	 */
	public void setThreadList(ArrayList<boardTopAction> threadList) {
		this.threadList = threadList;
	}

	/**
	 * @return pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber
	 *            セットする pageNumber
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * @return loginID
	 */
	public int getLoginID() {
		return loginID;
	}

	/**
	 * @param loginID
	 *            セットする loginID
	 */
	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}

	/**
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            セットする loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return pageThreadCount
	 */
	public int getpageThreadCount() {
		return pageThreadCount;
	}

	/**
	 * @param pageThreadCount
	 *            セットする pageThreadCount
	 */
	public void setpageThreadCount(int pageThreadCount) {
		this.pageThreadCount = pageThreadCount;
	}

	/**
	 * @return threadName
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * @param threadName
	 *            セットする threadName
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	/**
	 * @return threadID
	 */
	public int getThreadID() {
		return threadID;
	}

	/**
	 * @param threadID セットする threadID
	 */
	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	
}
