package com.tbkobenkyo.sql.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL勉強システム用データベース操作クラス
 * @author shimapee
 *
 */
public class SqlObenkyoDao {
	private List<Object> list;
	private Connection con;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetMetaData meta;
	
	/**
	 * コンストラクタ
	 * @param con
	 */
	public SqlObenkyoDao(Connection con) {
		this.con = con;
		list = new ArrayList<Object>();
	}
	
	private void setListData(ResultSet data) throws SQLException {
		List<String> metaDataList = new ArrayList<String>();
		meta = data.getMetaData();
		int colcount = meta.getColumnCount();
		for(int i = 1; i <= colcount; i++) {
			metaDataList.add(meta.getColumnName(i));
		}
		list.add(metaDataList);
		while(data.next()) {
			List<String> dataList = new ArrayList<String>();
			for(int i = 1; i <= colcount; i++) {
				if(data.getObject(i) != null) {
					dataList.add(data.getObject(i).toString());
				} else {
					dataList.add("(NULL)");
				}
			}
			list.add(dataList);
		}
	}
	
	private void DbClose() throws SQLException {
		//if(con != null) con.close();
		if(st != null) st.close();
		if(ps != null) ps.close();
		if(rs != null) rs.close();
	}
	
	private PreparedStatement setParameter(PreparedStatement ps, Object[] param) throws SQLException {
		for(int i = 0; i < param.length; i++) {
			if(param[i] instanceof String) {
				ps.setString(i + 1, param[i].toString());
			} else if(param[i] instanceof Integer) {
				ps.setInt(i + 1, (Integer) param[i]);
			}
		}
		return ps;
		
	}
	
	/**
	 * SELECT用データ返却（パラメータなし）
	 * @param sql
	 * @return List<Object>
	 * @throws SQLException
	 */
	public List<Object> getList(String sql) throws SQLException{
		st = con.createStatement();
		rs = st.executeQuery(sql);
		setListData(rs);
		DbClose();
		return list;
	}
	
	/**
	 * SELECT用データ返却（パラメータあり）
	 * @param sql
	 * @param param
	 * @return List<Object>
	 * @throws SQLException
	 */
	public List<Object> getList(String sql, Object[] param) throws SQLException{
		ps = con.prepareStatement(sql);
		rs = setParameter(ps, param).executeQuery();
		setListData(rs);
		DbClose();
		return list;
	}
	
	/**
	 * 一番最初のカラムデータを返却
	 * @param sql
	 * @param param
	 * @return Object
	 * @throws SQLException
	 */
	public Object getFirstData(String sql, Object[] param) throws SQLException {
		ps = con.prepareStatement(sql);
		rs = setParameter(ps, param).executeQuery();
		Object obj = null;
		while(rs.next()) {
			if(rs.isFirst()) obj = rs.getObject(1);
		}
		DbClose();
		return obj;
	}
	
	/**
	 * 更新SQL実行 ただし更新はしない
	 * 実行後にRollbackする
	 * パラメータなし
	 * @param sql
	 * @return int
	 * @throws SQLException
	 */
	public int updateQuery(String sql) throws SQLException {
		con.setAutoCommit(false);
		int result;
		st = con.createStatement();
		result = st.executeUpdate(sql);
		con.rollback();
		DbClose();
		return result;
	}
	/**
	 * 更新SQL実行 ただし更新はしない
	 * 実行後にRollbackする
	 * パラメータあり
	 * @param sql
	 * @param param
	 * @return int
	 * @throws SQLException
	 */
	public int updateQuery(String sql, Object[] param) throws SQLException {
		con.setAutoCommit(false);
		int result;
		ps = con.prepareStatement(sql);
		result = setParameter(ps, param).executeUpdate();
		con.rollback();
		DbClose();
		return result;
	}
	
	/**
	 * 更新SQL実行 
	 * 実行後にCommitする
	 * パラメータなし
	 * @param sql
	 * @return int
	 * @throws SQLException
	 */
	public int updateQueryCommited(String sql) throws SQLException {
		con.setAutoCommit(false);
		int result;
		st = con.createStatement();
		result = st.executeUpdate(sql);
		con.commit();
		DbClose();
		return result;
	}
	
	/**
	 * 更新SQL実行 
	 * 実行後にCommitする
	 * パラメータあり
	 * @param sql
	 * @param param
	 * @return int
	 * @throws SQLException
	 */
	public int updateQueryCommited(String sql, Object[] param) throws SQLException {
		con.setAutoCommit(false);
		int result;
		ps = con.prepareStatement(sql);
		result = setParameter(ps, param).executeUpdate();
		con.commit();
		DbClose();
		return result;
	}
}
