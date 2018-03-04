package edu.northeastern.cs5200.model;

public class ImageWidget extends Widget {

	private int widgetId;
	private String src;

	public ImageWidget() {
		super();
	}
	
	public ImageWidget(int id, int pageId, String name, int width, int height, String cssClass, String cssStyle,
			String text, int order, WidgetType type, String src) {
		super(id, pageId, name, width, height, cssClass, cssStyle, text, order, type);
		this.src = src;
	}

	public ImageWidget(int widgetId, String src) {
		super();
		this.widgetId = widgetId;
		this.src = src;
	}

	public int getWidgetId() {
		return widgetId;
	}
	public void setWidgetId(int widgetId) {
		this.widgetId = widgetId;
	}
	public ImageWidget(String src) {
		super();
		this.src = src;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}
}
