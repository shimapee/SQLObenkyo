package com.tbkobenkyo.sql.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * セッション情報チェックControllerクラス
 * @author shimapee
 *
 */
@SuppressWarnings("serial")
public class SessionCheckerController extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		
		res.setContentType("application/json; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		// session check
		HttpSession session = req.getSession(false);
		if(session != null) {
			out.println("{\"user\": "+ session.getAttribute("USR") +"}");
		} else {
			out.println("{\"user\": "+ 0 +"}");
		}
	}
}
