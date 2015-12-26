package com.tbkobenkyo.sql.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tbkobenkyo.sql.common.Utility;
import com.tbkobenkyo.sql.dao.SqlObenkyoDao;
import com.tbkobenkyo.sql.sqlstatement.SQLStatement;
import com.tbkobenkyo.sql.sqlstatement.TableListSQL;
import com.tbkobenkyo.sql.views.TableListView;

/**
 * テーブル一覧Controllerクラス
 * @author shimapee
 *
 */
@SuppressWarnings("serial")
public class TableListController extends AbstractController{
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		// 変数
		String uid = null;
		Connection con = null;
		String[] param = new String[1];
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		SQLStatement sql = new TableListSQL();
		uid = getSessionVarable(req);
		if(uid != null) {
			param[0] = uid;
			
		} else {
			param[0] = Utility.getPropValue("ORA_SCH");
		}
		try {
			con = getConnection(uid);
			SqlObenkyoDao dao = new SqlObenkyoDao(con);
			List<Object> list = null;
			list = dao.getList(sql.getSQLStatement(), param);
			TableListView view = new TableListView();
			out.println(view.getTableListData(list));
			
		} catch (SQLException e) {
			out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
	}
}
