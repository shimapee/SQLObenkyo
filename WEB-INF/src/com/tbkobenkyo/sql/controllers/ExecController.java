package com.tbkobenkyo.sql.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tbkobenkyo.sql.model.ExecModel;
import com.tbkobenkyo.sql.views.SqlObenkyoView;

/**
 * SQL実行Controllerクラス
 * @author shimapee
 *
 */
@SuppressWarnings("serial")
public class ExecController extends AbstractController {

	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
		
		// 変数ぅ
		String sql = null;		// SQLパラメータ
		String commit = null;	// Commitパラメータ
		String uid = null;		// session変数 USR
		Connection con = null;
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		sql = req.getParameter("sql");
		commit = req.getParameter("commit");
		uid = getSessionVarable(req);
		try {
			con = getConnection(uid);
			ExecModel dao = new ExecModel(con);
			if(dao.isSelectStatement(sql)) {
				List<Object> list = null;
				list = dao.getList(sql);
				SqlObenkyoView view = new SqlObenkyoView();
				out.println(view.getResultData(list));
			} else {
				if(commit != null) {
					int count = dao.updateQueryCommited(sql);
					out.println("<p>処理件数：" + count + "</p>");
					out.println("<p>確定しました。</p>");
				} else {
					int count = dao.updateQuery(sql);
					out.println("<p>処理件数：" + count + "</p>");
				}
			}
			
		} catch (SQLException e) {
			out.println("<p>" + e.getMessage() + "</p>");
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
public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
}
}
