package model;

public class Blog {
	private int userId;
	private String name;
	private String title;
	private String text;
	private String img;
	private String datetime;
	
	public Blog(int userId, String title, String text, String img, String datetime) {
		this.userId = userId;
		this.title = title;
		this.text = text;
		this.img = img;
		this.datetime = datetime;
	}
	public Blog(int userId, String name, String title, String text, String img, String datetime) {
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.text = text;
		this.img = img;
		this.datetime = datetime;
	}
	
	public int getUserId() { return userId; }
	public String getName() { return name; }
	public String getTitle() { return title; }
	public String getText() { return text; }
	public String getImg() { return img; }
	public String getDatetime() { return datetime; }
}
