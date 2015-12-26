package com.tbkobenkyo.sql.model;

import java.sql.Connection;
import java.sql.SQLException;
import com.tbkobenkyo.sql.common.Utility;
import com.tbkobenkyo.sql.dao.SqlObenkyoDao;

/**
 * 認証用モデルクラス
 * SqlObenkyoDaoを継承
 * @author shimapee
 *
 */
public class SigninModel extends SqlObenkyoDao{
	
	/**
	 * コンストラクタ
	 * @param con
	 */
	public SigninModel(Connection con) {
		super(con);
	}

	/**
	 * ユーザ認証
	 * DBに格納しているパスワード値と一致していればTRUE
	 * 
	 * @param id
	 * @param passwd
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean authUser(String id, String passwd) throws SQLException {
		
		StringBuffer sql = new StringBuffer();
		String [] userid = {id};
		sql.append("SELECT PASSWORD FROM PWMASTER WHERE USER_ID = ?");
		String dbP = null;
		dbP = (String) getFirstData(sql.toString(), userid);
		//System.out.println(dbP);
		String reqP = Utility.getPasswordAddSalt(passwd, id);
		if(dbP == null) return false;
		if(dbP.equals(reqP)) return true;
		
		return false;
		
	}
	
	/**
	 * パスワード変更用
	 * @param id
	 * @param passwd
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean changePassword(String id, String passwd) throws SQLException {
		
		StringBuffer sql = new StringBuffer();
		String reqP = Utility.getPasswordAddSalt(passwd, id);
		String [] param = {reqP,id};
		sql.append("UPDATE PWMASTER SET PASSWORD = ? WHERE USER_ID = ?");
		int i = updateQuery(sql.toString(), param);
		if(i == 1) {
			updateQueryCommited(sql.toString(), param);
			return true;
		} else {
			return false;
		}
		
	}
	
}
