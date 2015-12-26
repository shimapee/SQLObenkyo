package com.tbkobenkyo.sql.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tbkobenkyo.sql.model.SigninModel;

/**
 * パスワード変更用Controllerクラス
 * @author shimapee
 *
 */
@SuppressWarnings("serial")
public class ChangePasswordController extends AuthController {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	}


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String newpw = null;
		String retype = null;
		String msg = "入力内容に誤りがあります。\n再度入力してください。";
		Connection con = null;
		String uid = null;		// session変数 USR
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		newpw = req.getParameter("newpw");
		retype = req.getParameter("retype");
		uid = getSessionVarable(req);
		
		try {
			if(newpw != null && retype != null) {
				if(newpw.equals(retype)) {
					con = getConnection();
					SigninModel model = new SigninModel(con);
					if(model.changePassword(uid, newpw)) {
						msg = "パスワードを変更しました。";
					} else {
						msg = "パスワードを変更できませんでした。\n再度入力してください。";
					}
				}
			}
			out.println("<Script>alert('" + msg +"'); location.href = '/SQLObenkyo/'</script>");
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
	

}
