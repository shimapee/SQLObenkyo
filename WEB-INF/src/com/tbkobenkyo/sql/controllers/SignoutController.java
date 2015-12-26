package com.tbkobenkyo.sql.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * サインアウト用Controllerクラス
 * @author shimapee
 *
 */
@SuppressWarnings("serial")
public class SignoutController extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		session.invalidate();
		res.sendRedirect("/SQLObenkyo/");
		
	}
}
