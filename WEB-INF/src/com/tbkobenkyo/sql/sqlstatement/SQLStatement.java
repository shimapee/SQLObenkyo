package com.tbkobenkyo.sql.sqlstatement;

/**
 * SQL文格納抽象クラス
 * @author shimapee
 *
 */
public abstract class SQLStatement {
	protected String br = System.getProperty("line.separator");
	protected StringBuffer sql = new StringBuffer();

	/**
	 * SQL文返却用抽象メソッド
	 * @return String
	 */
	public abstract String getSQLStatement();
}
