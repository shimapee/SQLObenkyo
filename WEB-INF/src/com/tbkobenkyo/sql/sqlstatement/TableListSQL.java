package com.tbkobenkyo.sql.sqlstatement;

/**
 * テーブル一覧取得用SQL格納クラス
 * SQLStatementを継承
 * @author shimapee
 *
 */
public class TableListSQL extends SQLStatement {
	
	@Override
	public String getSQLStatement() {
		sql.append("select table_name from all_tables where owner = ?");
		return sql.toString();
	}

}
