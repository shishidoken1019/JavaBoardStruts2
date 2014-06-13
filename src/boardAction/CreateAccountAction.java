package boardAction;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletRedirectResult;

import database.DatabaseConfig;

@Results({
		@Result(name = "boardTop", value = "boardTop.action", type = ServletRedirectResult.class),
		@Result(name = "createAccount", value = "createAccount.action", type = ServletRedirectResult.class) })
public class CreateAccountAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
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
	private File loginImage;
	private String loginImageContentType;
	private String loginImageFileName;
	/**
	 * エラーメッセージ
	 */
	private String errorMsg = "";

	// 使うオブジェクトをインスタンス化
	DiskFileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	DatabaseConfig databaseConfig = new DatabaseConfig();
	ServletContext sc = ServletActionContext.getServletContext();

	String insertSql = "INSERT INTO login(login_name,password,login_image) VALUES(?,?,?);";
	String selectSql = "select * from login where login_name = ?;";
	String path = sc.getRealPath("");

	public String execute() {

		return "success";
	}

	public String create() {

		try {

			// 入力チェック
			// 半角チェック
			this.errorMsg += new utility.Check()
					.CheckSingleByte(this.loginName);
			this.errorMsg += new utility.Check().CheckSingleByte(this.password);

			// 文字数チェック
			this.errorMsg += new utility.Check().checkStringLength(
					this.loginName, 32);
			this.errorMsg += new utility.Check().checkStringLength(
					this.password, 32);

			// 拡張子チェック
			if (this.loginImage != null) {
				this.errorMsg += new utility.Check()
						.checkSuffix(this.loginImageFileName);
			}
			//nullチェック
			if(this.loginName.length() ==0){
				this.errorMsg = "なにか文字を入力してください";
				
			}

			// すでにエラーメッセージがある場合は、アカウント作成画面に戻る
			if (this.errorMsg.length() > 0) {
				return "error";
			}

			// 登録されている情報と重複がないかチェック
			Connection con = databaseConfig.getDatabaseConnection();
			PreparedStatement ps = con.prepareStatement(selectSql);
			ps.setString(1, this.loginName);
			ResultSet rs = ps.executeQuery();

			// レコードが存在する場合は、重複があるのでエラー
			while (rs.next()) {
				this.errorMsg = "すでにそのアカウント名は存在します。他のアカウント名を設定してください";
				return "error";
			}

			//レコードない場合は登録

			// まず画像をアップロード
			if (this.loginImage != null) {
				// FileItem item = (FileItem) this.loginImage;
				// item.write(new File(path + "/img/" +
				// this.loginImage.getName()));
				File f = new File(path + "/img/" + this.loginImageFileName);
				FileUtils.copyFile(this.loginImage.getAbsoluteFile(), f);
			}

			// 画像ファイルがない場合は、defoult.jpgで登録
			String fileName;
			if (this.loginImage == null) {
				fileName = "defoult.jpg";
			} else {
				fileName = this.loginImage.getName();
			}
			// データベースに登録
			PreparedStatement ps2 = con.prepareStatement(insertSql);
			ps2.setString(1, this.loginName);
			ps2.setString(2, this.password);
			ps2.setString(3, fileName);
			ps2.executeUpdate();
			
			//ログインIDを取得するためにSelect文を発行
			PreparedStatement ps3 = con.prepareStatement(selectSql);
			ps3.setString(1, this.loginName);
			ResultSet rs3 = ps3.executeQuery();
			
			//中身をまわす
			if(rs3.next()){
				this.loginID = rs3.getInt("login_id");
			}			

		} catch (InstantiationException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "errorPage";
		} catch (IllegalAccessException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "errorPage";
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "errorPage";
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return "errorPage";
		}

		//セッションにログイン情報を登録
		sessionMap.put("loginID", this.loginID);
		sessionMap.put("loginName", this.loginName);
		sessionMap.put("password", this.password);
		// 遷移先へ
		return "boardTop";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public File getLoginImage() {
		return loginImage;
	}

	public void setLoginImage(File loginImage) {
		this.loginImage = loginImage;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public int getLoginID() {
		return loginID;
	}

	public void setLoginID(int loginID) {
		this.loginID = loginID;
	}

	public String getLoginImageContentType() {
		return loginImageContentType;
	}

	public void setLoginImageContentType(String loginImageContentType) {
		this.loginImageContentType = loginImageContentType;
	}

	public String getLoginImageFileName() {
		return loginImageFileName;
	}

	public void setLoginImageFileName(String loginImageFileName) {
		this.loginImageFileName = loginImageFileName;
	}

}
