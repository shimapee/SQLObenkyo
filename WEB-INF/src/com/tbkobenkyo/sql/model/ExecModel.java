package com.tbkobenkyo.sql.model;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tbkobenkyo.sql.dao.SqlObenkyoDao;

/**
 * SQL実行用モデルクラス
 * SqlObenkyoDaoを継承
 * @author shimapee
 *
 */
public class ExecModel extends SqlObenkyoDao {

	/**
	 * コンストラクタ
	 * @param con
	 */
	public ExecModel(Connection con) {
		super(con);
	}
	
	/**
	 * SELECT文であるかチェック
	 * 先頭に「select」の文字列があればTRUE
	 * @param sql
	 * @return boolean
	 */
	public boolean isSelectStatement (String sql) {
		Pattern pattern = Pattern.compile("^select", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(sql);
		return match.find();
	}

}
