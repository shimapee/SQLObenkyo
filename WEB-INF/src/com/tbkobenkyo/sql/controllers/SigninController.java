package com.tbkobenkyo.sql.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tbkobenkyo.sql.model.SigninModel;

/**
 * サインイン用Controllerクラス
 * @author shimapee
 *
 */
@SuppressWarnings("serial")
public class SigninController extends AuthController {
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
		
		Connection con = null;
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		String userid = req.getParameter("userid");
		String passwd = req.getParameter("passwd");
		
		PrintWriter out = res.getWriter();
		
		// ユーザ認証
		try {
			con = getConnection();
			SigninModel model = new SigninModel(con);
			if(model.authUser(userid, passwd)){
				// session start
				HttpSession session = req.getSession(true);
				session.setAttribute("USR", userid);
				res.sendRedirect("/SQLObenkyo/");

			} else {
				out.println("<Script>alert('認証に失敗しました。'); location.href = '/SQLObenkyo/'</script>");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			res.sendRedirect("/SQLObenkyo/");
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
