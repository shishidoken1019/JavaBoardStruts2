package utility;

import java.sql.Connection;

import org.apache.struts2.config.Result;
import org.apache.struts2.dispatcher.ServletRedirectResult;

import boardAction.AbstractAction;

public class OverHead extends AbstractAction{
	private static final long serialVersionUID = 1L;
	
	public void firstProcess(){
		
		//ログインのセッション情報が切れていないか確認
		SessionLogConfirm();
		
	}
	
	/**
	 * ログインのセッション情報が切れていないか確認
	 */
	public boolean SessionLogConfirm(){
		
		/**
		 * セッションが切れいる場合、セッションエラーページへ
		 */
		if(sessionMap.get("loginID") == null){
			return false;
		}
		return true;
	}
	
	
}
