package edu.northeastern.cs5200.model;

public class YouTubeWidget extends Widget {

	private int widgetId;
	private String url;
	private boolean shareable;
	private boolean expandable;
	
	public YouTubeWidget() {
		super();
	}
	
	public YouTubeWidget(int id, int pageId, String name, int width, int height, String cssClass, String cssStyle,
			String text, int order, WidgetType type, String url, boolean shareable, boolean expandable) {
		super(id, pageId, name, width, height, cssClass, cssStyle, text, order, type);
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}
	
	public YouTubeWidget(int widgetId, String url, boolean shareable, boolean expandable) {
		super();
		this.widgetId = widgetId;
		this.url = url;
		this.shareable = shareable;
		this.expandable = expandable;
	}
	public int getWidgetId() {
		return widgetId;
	}
	public void setWidgetId(int widgetId) {
		this.widgetId = widgetId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isShareable() {
		return shareable;
	}
	public void setShareable(boolean shareable) {
		this.shareable = shareable;
	}
	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
}
