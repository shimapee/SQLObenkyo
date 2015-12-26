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
import com.tbkobenkyo.sql.sqlstatement.TableDetailSQL;
import com.tbkobenkyo.sql.views.SqlObenkyoView;

/**
 * テーブル詳細表示Controllerクラス
 * @author shimapee
 *
 */
@SuppressWarnings("serial")
public class TableDetailController extends AbstractController {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
		
		// 変数
		String table_name = null;	// tableパラメータ
		String uid = null;			// session変数 USR
		String[] param = new String[2];
		Connection con = null;
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		table_name = req.getParameter("table");
		uid = getSessionVarable(req);
		
		if(uid != null) {
			param[0] = uid;
			
		} else {
			param[0] = Utility.getPropValue("ORA_SCH");
		}
		param[1] = table_name;
		SQLStatement sql = new TableDetailSQL();
		try {
			con = getConnection(uid);
			SqlObenkyoDao dao = new SqlObenkyoDao(con);
			List<Object> list = null;
			list = dao.getList(sql.getSQLStatement(), param);
			SqlObenkyoView view = new SqlObenkyoView();
			out.println(view.getResultData(list));
		} catch (SQLException e) {
			out.println(e.getMessage());
			out.println(sql.getSQLStatement());
			e.printStackTrace();
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
