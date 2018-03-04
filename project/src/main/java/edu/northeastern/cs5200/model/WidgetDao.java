package edu.northeastern.cs5200.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Statement;

public class WidgetDao {

	DbConfig cfg;
	public static WidgetDao instance = null;
	public static WidgetDao getInstance() {
		if (instance == null) {
			instance = new WidgetDao();
		}
		return instance;
	}
	
	private WidgetDao() {
		cfg = new DbConfig();
	}
	
	public int createWidgetForPage(int pageId, Widget widget) throws SQLException, ClassNotFoundException {		
		int result = widgetHelper(true, widget, pageId);
		return result;
	}
	
	public int widgetHelper(boolean create, Widget widget, int id) throws ClassNotFoundException, SQLException {
		String sql;
		if(create)
			sql = "INSERT INTO Widget (id, pageID, name, width, height, cssClass, cssStyle, text, `order`, type, size, html, src, url, sharable, expandable) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		else
			sql = "UPDATE Widget SET id=?, pageID=?, name=?, width=?, height=?, cssClass=?, cssStyle=?, text=?, `order`=?, type=?, size=?, html=?, src=?, url=?, sharable=?, expandable=? WHERE Widget.id=?";
		Connection conn = cfg.getConnection();		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, widget.getId());
		if(create)
			stmt.setInt(2, id);
		else
			stmt.setInt(2, widget.getPageId());
		stmt.setString(3, widget.getName());
		stmt.setInt(4, widget.getWidth());
		stmt.setInt(5, widget.getHeight());
		stmt.setString(6, widget.getCssClass());
		stmt.setString(7, widget.getCssStyle());
		stmt.setString(8, widget.getText());
		stmt.setInt(9, widget.getOrder());
		stmt.setString(10, widget.getType().toString());
		// Default null to be overridden per case
		stmt.setNull(11, java.sql.Types.INTEGER);
		stmt.setNull(12, java.sql.Types.VARCHAR);
		stmt.setNull(13, java.sql.Types.VARCHAR);
		stmt.setNull(14, java.sql.Types.VARCHAR);
		stmt.setNull(15, java.sql.Types.BOOLEAN);
		stmt.setNull(16, java.sql.Types.BOOLEAN);
		if(!create)
			stmt.setInt(17, id);
		switch(widget.getType().toString()) {
			case "Heading":
				HeadingWidget header = (HeadingWidget) widget;
				stmt.setInt(11, header.getSize());
				break;
			case "Html":
				HtmlWidget html = (HtmlWidget) widget;
				stmt.setString(12, html.getHtml());
				break;
			case "Image":
				ImageWidget img = (ImageWidget) widget;
				stmt.setString(13, img.getSrc());
				break;
			case "YouTube":
				YouTubeWidget ut = (YouTubeWidget) widget;
				stmt.setString(14, ut.getUrl());
				stmt.setBoolean(15, ut.isShareable());
				stmt.setBoolean(16, ut.isExpandable());
		}
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;
	}
	
	public Collection<Widget> findWidgetsForPage(int pageId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Widget WHERE pageID = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, pageId);
		Collection<Widget> widgets = new ArrayList<Widget>();
		ResultSet res = stmt.executeQuery();
		
		while (res.next()) {
			WidgetClassifier wc = new WidgetClassifier(
					res.getInt("id"), res.getInt("pageID"),
					res.getString("name"), res.getInt("width"),
					res.getInt("height"), res.getString("cssClass"),
					res.getString("cssStyle"), res.getString("text"),
					res.getInt("order"), res.getString("type"),
					res.getInt("size"),	res.getString("html"),
					res.getString("src"), res.getString("url"),
					res.getBoolean("sharable"), res.getBoolean("expandable"));
			Widget w = getWidget(wc);
			widgets.add(w);
		}
		
		stmt.close();
		conn.close();
		return widgets;
	}
	
	private Widget getWidget(WidgetClassifier wc) {
		switch(wc.getType().toString()) {
			case "Heading":
				return new HeadingWidget(
						wc.getId(), 
						wc.getPageID(), 
						wc.getName(), 
						wc.getWidth(), 
						wc.getHeight(), 
						wc.getCssClass(), 
						wc.getCssStyle(),
						wc.getText(),
						wc.getOrder(),
						WidgetType.valueOf(wc.getType()),
						wc.getSize());
			case "Html":
				return new HtmlWidget(
						wc.getId(), 
						wc.getPageID(), 
						wc.getName(), 
						wc.getWidth(), 
						wc.getHeight(), 
						wc.getCssClass(), 
						wc.getCssStyle(),
						wc.getText(),
						wc.getOrder(),
						WidgetType.valueOf(wc.getType()),
						wc.getHtml());
			case "Image":
				return new ImageWidget(
						wc.getId(), 
						wc.getPageID(), 
						wc.getName(), 
						wc.getWidth(), 
						wc.getHeight(), 
						wc.getCssClass(), 
						wc.getCssStyle(),
						wc.getText(),
						wc.getOrder(),
						WidgetType.valueOf(wc.getType()),
						wc.getSrc());
			case "YouTube":
				return new YouTubeWidget(
						wc.getId(), 
						wc.getPageID(), 
						wc.getName(), 
						wc.getWidth(), 
						wc.getHeight(), 
						wc.getCssClass(), 
						wc.getCssStyle(),
						wc.getText(),
						wc.getOrder(),
						WidgetType.valueOf(wc.getType()),
						wc.getUrl(),
						wc.isSharable(),
						wc.isExpandable());
		}
		return null;
	}

	public Collection<Widget> findAllWidgets() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Widget";
		Connection conn = cfg.getConnection();
		Statement stmt = conn.createStatement();
		Collection<Widget> widgets = new ArrayList<Widget>();
		ResultSet res = stmt.executeQuery(sql);
		while (res.next()) {
			WidgetClassifier wc = new WidgetClassifier(
					res.getInt("id"), res.getInt("pageID"),
					res.getString("name"), res.getInt("width"),
					res.getInt("height"), res.getString("cssClass"),
					res.getString("cssStyle"), res.getString("text"),
					res.getInt("order"), res.getString("type"),
					res.getInt("size"),	res.getString("html"),
					res.getString("src"), res.getString("url"),
					res.getBoolean("sharable"), res.getBoolean("expandable"));
			Widget w = getWidget(wc);
			widgets.add(w);
		}
		
		stmt.close();
		conn.close();
		return widgets;
	}
	
	public Widget findWidgetById(int widgetId) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Widget WHERE Widget.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, widgetId);
		ResultSet res = stmt.executeQuery();
		Widget widget = null;
		if (res.next()) {
			WidgetClassifier wc = new WidgetClassifier(
					res.getInt("id"), res.getInt("pageID"),
					res.getString("name"), res.getInt("width"),
					res.getInt("height"), res.getString("cssClass"),
					res.getString("cssStyle"), res.getString("text"),
					res.getInt("order"), res.getString("type"),
					res.getInt("size"),	res.getString("html"),
					res.getString("src"), res.getString("url"),
					res.getBoolean("sharable"), res.getBoolean("expandable"));
			widget = getWidget(wc);
		}
		stmt.close();
		conn.close();
		return widget;
	}

	public int updateWidget(int widgetId, Widget widget) throws SQLException, ClassNotFoundException {
		int result = widgetHelper(false, widget, widgetId);
		return result;	
	}
	
	public int deleteWidget(int widgetId) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM Widget WHERE Widget.id = ?";
		Connection conn = cfg.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, widgetId);
		int result = stmt.executeUpdate();
		stmt.close();
		conn.close();
		return result;
		
	}
}
