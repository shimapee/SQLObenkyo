package com.tbkobenkyo.sql.sqlstatement;

/**
 * テーブル詳細取得用SQL格納クラス
 * SQLStatementを継承
 * @author shimapee
 *
 */
public class TableDetailSQL extends SQLStatement {

	@Override
	public String getSQLStatement() {
		sql.append("select ").append(br);
		sql.append("tbc.table_name AS テーブル名,").append(br);
		sql.append("tbc.column_name AS 項目名,").append(br);
		sql.append("case ").append(br);
		sql.append("when tbc.data_type = 'NUMBER' then ").append(br);
		sql.append("tbc.data_type||' ('||tbc.data_precision||','||tbc.data_scale||')' ").append(br);
		sql.append("when tbc.data_type IN ('VARCHAR2','CHAR','NVARCHAR2', 'RAW', 'UROWID', 'NCHAR') then").append(br);
		sql.append("tbc.data_type||' ('||tbc.data_length||')' ").append(br);
		sql.append("else tbc.data_type end as データ型,").append(br);
		sql.append("case when tbc.NULLABLE = 'N' then '〇' END AS 必須,").append(br);
		sql.append("tbc.data_default as 省略時値,").append(br);
		sql.append("conc.POSITION AS 主キー").append(br);
		sql.append("from all_tab_cols tbc").append(br);
		sql.append("left outer join all_constraints cons").append(br);
		sql.append("on tbc.owner = cons.owner").append(br);
		sql.append("and tbc.table_name = cons.table_name and cons.constraint_type = 'P'").append(br);
		sql.append("left outer join all_cons_columns conc").append(br);
		sql.append("on tbc.owner = conc.owner").append(br);
		sql.append("and tbc.table_name = conc.table_name").append(br);
		sql.append("and tbc.column_name = conc.column_name").append(br);
		sql.append("and conc.constraint_name = cons.constraint_name").append(br);
		sql.append("where tbc.owner = ? and tbc.table_name = ?").append(br);
		sql.append("order by tbc.table_name,tbc.column_id");
		return sql.toString();
	}

}
