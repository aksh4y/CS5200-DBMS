package edu.northeastern.cs5200.model;

public class HtmlWidget extends Widget {

	private int widgetId;
	private String html;
	
	public HtmlWidget(int widgetId, String html) {
		super();
		this.widgetId = widgetId;
		this.html = html;
	}
	
	public HtmlWidget(int id, int pageId, String name, int width, int height, String cssClass, String cssStyle,
			String text, int order, WidgetType type, String html) {
		super(id, pageId, name, width, height, cssClass, cssStyle, text, order, type);
		this.html = html;
	}
	
	public HtmlWidget() {
		super();
	}
	
	public int getWidgetId() {
		return widgetId;
	}
	
	public void setWidgetId(int widgetId) {
		this.widgetId = widgetId;
	}
	
	public HtmlWidget(String html) {
		super();
		this.html = html;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
