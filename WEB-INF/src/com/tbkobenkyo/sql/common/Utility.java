package com.tbkobenkyo.sql.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author shimapee
 * 
 *  ユーティリティクラス
 *
 */
public class Utility {
	
	/**
	 * Oracleドライバ
	 */
	private static String oraDrv = "oracle.jdbc.OracleDriver";
	
	/**
	 * 引数で渡されたキーのプロパティファイル内の値を返却
	 * @param key
	 * @return String
	 */
	public static String getPropValue(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("SQLObenkyo");
		return bundle.getString(key);
	}
	/**
	 * java.sql.Connectionを返却
	 * 引数がない場合はREADONLY権限のユーザで接続
	 * 
	 * @return java.sql.Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getDbConnection() 
		throws ClassNotFoundException, SQLException{
		Class.forName(oraDrv);
		Connection con = DriverManager.getConnection(
			Utility.getPropValue("ORA_URL"),
			Utility.getPropValue("ORA_USR"),
			Utility.getPropValue("ORA_PAS"));
		return con;
	}
	
	/**
	 * java.sql.Connectionを返却
	 * 引数で渡したユーザ情報で接続
	 * @param user
	 * @param pass
	 * @return java.sql.Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getDbConnection(String user, String pass) 
			throws ClassNotFoundException, SQLException {
		Class.forName(oraDrv);
		Connection con = DriverManager.getConnection(
				Utility.getPropValue("ORA_URL"),
				user, 
				pass);
		return con;
	}
	
	
	/**
	 * ソルト付きパスワードを返却
	 * ソルトはユーザIDをハッシュ化したもの
	 * @param password
	 * @param userId
	 * @return String
	 */
	public static String getPasswordAddSalt(String password, String userId) {
		String salt = getHash(userId);
		return getHash(salt + password);
	}
	
	/**
	 * ハッシュを返却
	 * SHA-256で…
	 * @param val 
	 * @return String
	 */
	private static String getHash(String val) {
		MessageDigest md = null;
		StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(val.getBytes());
			byte[] digest = md.digest();
			for (int i = 0; i < digest.length; i++) {
				sb.append(String.format("%02x", digest[i]));
			}
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
