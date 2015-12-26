package com.tbkobenkyo.sql.views;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 * SQL実行用ビュークラス
 * @author shimapee
 *
 */
public class SqlObenkyoView {

	/**
	 * 結果表示
	 * @param list
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String getResultData(List<Object> list) {
		StringBuffer out = new StringBuffer();
		out.append("<table class='table table-bordered'>");
		for(int i = 0; i < list.size(); i++) {
			if(i == 0) {
				out.append("<thead>");
				out.append("<tr class='active'>");
				for(int j = 0; j < ((List<String>) list.get(i)).size(); j++) {
					out.append("<td>");
					out.append(StringEscapeUtils.escapeHtml4(((List<String>) list.get(i)).get(j)));
					out.append("</td>");
				}
				out.append("</tr>");
				out.append("</thead>");
			} else {
				out.append("<tbody>");
				out.append("<tr>");
				for(int j = 0; j < ((List<String>) list.get(i)).size(); j++) {
					out.append("<td>");
					out.append(StringEscapeUtils.escapeHtml4(((List<String>) list.get(i)).get(j)));
					out.append("</td>");
				}
				out.append("</tr>");
				out.append("</tbody>");
			}
		}
		out.append("</table>");
		return out.toString();
	}
}
