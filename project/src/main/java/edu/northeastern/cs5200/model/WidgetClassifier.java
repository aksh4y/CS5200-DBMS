package edu.northeastern.cs5200.model;

public class WidgetClassifier {

	private int id, pageID, width, height, order, size;
	private String name, cssClass, cssStyle, text, type, html, src, url;
	private boolean sharable, expandable;
	
	public WidgetClassifier(int id, int pageID, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String type, int size, 
			  String html, String src, String url,
			boolean sharable, boolean expandable) {
		super();
		this.id = id;
		this.pageID = pageID;
		this.width = width;
		this.height = height;
		this.order = order;
		this.size = size;
		this.name = name;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.text = text;
		this.type = type;
		this.html = html;
		this.src = src;
		this.url = url;
		this.sharable = sharable;
		this.expandable = expandable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPageID() {
		return pageID;
	}
	public void setPageID(int pageID) {
		this.pageID = pageID;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	public String getCssStyle() {
		return cssStyle;
	}
	public void setCssStyle(String cssStyle) {
		this.cssStyle = cssStyle;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isSharable() {
		return sharable;
	}
	public void setSharable(boolean sharable) {
		this.sharable = sharable;
	}
	public boolean isExpandable() {
		return expandable;
	}
	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}
}
