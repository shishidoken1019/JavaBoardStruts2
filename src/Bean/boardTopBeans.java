package Bean;

public class boardTopBeans {
	/**
	 * ログインID
	 */
	private int loginID;

	/**
	 * ログイン名
	 */
	private String loginName;
	/**
	 * @return loginID
	 */
	public int getLoginID() {
		return loginID;
	}
	/**
	 * @param loginID セットする loginID
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
	 * @param loginName セットする loginName
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
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return loginImage
	 */
	public String getLoginImage() {
		return loginImage;
	}
	/**
	 * @param loginImage セットする loginImage
	 */
	public void setLoginImage(String loginImage) {
		this.loginImage = loginImage;
	}
	/**
	 * @return threadName
	 */
	public String getThreadName() {
		return threadName;
	}
	/**
	 * @param threadName セットする threadName
	 */
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
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

}
