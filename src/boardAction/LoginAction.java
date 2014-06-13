package boardAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.*;

import database.DatabaseConfig;
import database.TestClass2;

@Results({
	@Result(name = "loginTop", value = "login.action", type = ServletRedirectResult.class),
		@Result(name = "boardTop", value = "boardTop.action", type = ServletRedirectResult.class),
		@Result(name = "createAccount", value = "createAccount.action", type = ServletRedirectResult.class)})
public class LoginAction extends AbstractAction {

	private static final long serialVersionUID = 1L;

	public int loginID;
	public String loginName;
	public String password;
	public String loginImage;
	public String errorMsg;
	private ServletContext servletContext;
	DatabaseConfig databaseConfig = new DatabaseConfig();
	private String ExsistLoginSql = "SELECT * from login where login_name = ? AND password = ?;";

	// 初期アクセス
	public String execute() throws Exception {
		this.servletContext = ServletActionContext.getServletContext();
		// ID、パスワード共にセッション値が残っていた場合は初期値に入れる
		System.out.print("a"+sessionMap.get("Name")+"b");
		if (sessionMap.get("loginName") != null) {
			this.loginName = (String) sessionMap.get("loginName");
			this.password = (String) sessionMap.get("password");
			System.out.println("セッションを使っています");
		}
		
		// ログイン画面を表示
		return "success";
	}

	// ログインを押す
	public String login() throws Exception {

		// それぞれフォームに入っている値を取ってくる

		// IDとパスワードがデータベースに登録されているか確認
		Connection con = databaseConfig.getDatabaseConnection();
		PreparedStatement ps = con.prepareStatement(ExsistLoginSql);
		ps.setString(1, this.loginName);
		ps.setString(2, this.password);
		ResultSet rs = ps.executeQuery();

		// 登録されていなかったら、エラーメッセージをセットしてログイン画面に戻る
		while (rs.next()) {
			// 登録されていたら、ログイン情報をセッションにセットして掲示板トップ画面へ遷移
			sessionMap.put("loginID", rs.getString("login_id"));
			sessionMap.put("loginName", rs.getString("login_name"));
			sessionMap.put("loginPass", rs.getString("password"));
			sessionMap.put("loginImage", rs.getString("login_image"));

			return "boardTop";

		} 
			this.errorMsg = "ログイン名かパスワードが間違っています。";
			return "error";

	}

	//アカウント作成を押す
	public String createAccount(){
		return "createAccount";
	}
	
	//ログインせずに利用を押す
	public String useBoard(){
		return "boardTop";
	}

	


}
