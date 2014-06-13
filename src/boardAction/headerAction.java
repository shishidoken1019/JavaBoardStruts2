package boardAction;

import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.apache.struts2.dispatcher.ServletRedirectResult;

import database.DatabaseConfig;

@Results({
	@Result(name = "loginTop", value = "login.action", type = ServletRedirectResult.class),
		@Result(name = "boardTop", value = "boardTop.action", type = ServletRedirectResult.class),
		@Result(name = "createAccount", value = "createAccount.action", type = ServletRedirectResult.class) })
public class headerAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	public String loginTop(){
		return "loginTop";
	}

}
