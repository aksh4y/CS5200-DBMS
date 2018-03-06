package edu.northeastern.cs5200.model;

public class HeadingWidget extends Widget {
	
	private int widgetId;
	private int size;

	public HeadingWidget() {
		super();
	}

	public HeadingWidget(int widgetId, int size) {
		super();
		this.widgetId = widgetId;
		this.size = size;
	}

	

	public HeadingWidget(int id, int pageId, String name, int width, int height, String cssClass, String cssStyle,
			String text, int order, WidgetType type, int size) {
		super(id, pageId, name, width, height, cssClass, cssStyle, text, order, type);
		this.size = size;
	}

	public int getWidgetId() {
		return widgetId;
	}
	public void setWidgetId(int widgetId) {
		this.widgetId = widgetId;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
