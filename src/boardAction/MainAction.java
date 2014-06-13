package boardAction;

import java.util.*;
import java.text.*;
 
public class MainAction extends AbstractAction {
 
    private static final long serialVersionUID = 1L;
 
    public String userId;
    public String sendDate;
    public String comment;
 
    private String getDefaultDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd k:m:s");
        return sdf.format(date);
    }
 
    private String getDefaultMessage(){
        return "Hello !\nMy Name is " + this.userId;
    }
 
    public String execute() throws Exception {
        this.userId = (String)this.sessionMap.get("userId");
        this.sendDate = getDefaultDate();
        this.comment = getDefaultMessage();
        System.out.println("メインの中");
        return "success";
    }
}
