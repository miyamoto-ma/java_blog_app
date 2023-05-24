package model;

import java.io.Serializable;

public class Blog implements Serializable {
	private int id;
	private int userId;
	private String name;
	private String title;
	private String text;
	private String img;
	private String datetime;
	private int gCount;
	private int gId;
	
	public Blog() {}
	
	public Blog(int id, String title, String text, String img) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.img = img;
	}
	
	public Blog(int userId, String title, String text, String img, String datetime) {
		this.userId = userId;
		this.title = title;
		this.text = text;
		this.img = img;
		this.datetime = datetime;
	}
	public Blog(int id, int userId, String name, String title, String text, String img, String datetime) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.text = text;
		this.img = img;
		this.datetime = datetime;
	}
	
	public Blog(int id, int userId, String name, String title, String text, String img, String datetime, int gCount) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.text = text;
		this.img = img;
		this.datetime = datetime;
		this.gCount = gCount;
	}
	
	public Blog(int id, int userId, String name, String title, String text, String img, String datetime, int gCount, int gId) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.title = title;
		this.text = text;
		this.img = img;
		this.datetime = datetime;
		this.gCount = gCount;
		this.gId = gId;
	}
	
	public int getId() { return id; }
	public int getUserId() { return userId; }
	public String getName() { return name; }
	public String getTitle() { return title; }
	public String getText() { return text; }
	public String getImg() { return img; }
	public String getDatetime() { return datetime; }
	public int getGCount() { return gCount; }
	public int getGId() { return gId; }
}
