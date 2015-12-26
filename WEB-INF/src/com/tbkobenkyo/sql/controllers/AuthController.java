package com.tbkobenkyo.sql.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import com.tbkobenkyo.sql.common.Utility;

/**
 * 認証系Controller抽象化クラス
 * @author shimapee
 */
@SuppressWarnings("serial")
public abstract class AuthController extends AbstractController {


	protected Connection getConnection() throws ClassNotFoundException, SQLException {
		return Utility.getDbConnection(
				Utility.getPropValue("ORA_AUTH_USR"), 
				Utility.getPropValue("ORA_AUTH_PAS"));
	}
}
