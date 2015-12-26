package com.tbkobenkyo.sql.views;

import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * テーブル一覧表示用ビュー
 * @author shimapee
 *
 */
public class TableListView {
	/**
	 * テーブルリスト表示
	 * @param list
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String getTableListData(List<Object> list) {
		StringBuffer out = new StringBuffer();
		
		out.append("<table class='table table-bordered'>");
		out.append("<thead><tr><td>テーブル一覧</td></tr></thead>");
		out.append("<tbody id='tablelists'>");
		for(int i = 1; i < list.size(); i++) {
			out.append("<tr>");
			for(int j = 0; j < ((List<String>) list.get(i)).size(); j++) {
				out.append("<td>");
				out.append("<a id='"+StringEscapeUtils.escapeHtml4(((List<String>) list.get(i)).get(j))+"'>");
				out.append(StringEscapeUtils.escapeHtml4(((List<String>) list.get(i)).get(j))+"</a>");
				out.append("</td>");
			}
			out.append("</tr>");
		}
		out.append("</tbody></table>");
		return out.toString();
	}
}
