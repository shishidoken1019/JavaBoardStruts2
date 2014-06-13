package utility;

import java.io.File;

public class Check {

	/**
	 * 半角チェックを行う
	 * @param st
	 * @return エラーメッセージ
	 */
	public String CheckSingleByte(String st){
		
		if(st.length() != st.getBytes().length){
			return "半角文字を入力してください";
		}else{
			return "";
		}
	}
	
	/**
	 * 文字数チェックを行う
	 */
	public String checkStringLength(String base,int maxLength){
		
		if(base.length() > maxLength){
			return "文字数が"+maxLength+"文字を超えています";
		}else{
			return "";
		}
	}
	
	/**
	 * 正しい拡張子か判断し、エラーメッセージをセット
	 * @param file
	 * @return エラーメッセージ
	 */
	public String checkSuffix(File file){

		int point = file.getName().lastIndexOf(".");
		String suffix = file.getName().substring(point+1);
		System.out.println(suffix);
		
		if(suffix.equals("") || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg") ||suffix.equalsIgnoreCase("png") ||suffix.equalsIgnoreCase("gif")){
			return "";
		}else{
			return "画像の拡張子が正しくありません（jpg,gif,jpeg,png）のいずれかで登録してください";
		}
		
	}
	
	/**
	 * 正しい拡張子か判断し、エラーメッセージをセット
	 * @param file
	 * @return エラーメッセージ
	 */
	public String checkSuffix(String file){

		int point = file.lastIndexOf(".");
		String suffix = file.substring(point+1);
		System.out.println(suffix);
		
		if(suffix.equals("") || suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg") ||suffix.equalsIgnoreCase("png") ||suffix.equalsIgnoreCase("gif")){
			return "";
		}else{
			return "画像の拡張子が正しくありません（jpg,gif,jpeg,png）のいずれかで登録してください";
		}
		
	} 
	
	
}
