package com.tbkobenkyo.sql.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tbkobenkyo.sql.common.Utility;

/**
 * Controller抽象化クラス
 * @author shimapee
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractController extends HttpServlet {
	public abstract void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
	public abstract void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
	/**
	 * @param req
	 * @return String
	 */
	protected String getSessionVarable(HttpServletRequest req) {
		String uid = null;
		HttpSession session = req.getSession(false);
		if(session != null) {
			uid = (String) session.getAttribute("USR");
		}
		return uid;
	}
	/**
	 * @param uid
	 * @return java.sql.Connection
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	protected Connection getConnection(String uid) throws ClassNotFoundException, SQLException {
		if(uid == null) {
			return Utility.getDbConnection();
		} else {
			return Utility.getDbConnection(uid, uid);
		}
	}
}
