package model;

import java.io.Serializable;

public class Good implements Serializable {
	private int id;
	private int blogId;
	private int userId;
	
	public Good() {}
	
	public Good(int blogId, int userId) {
		this.blogId = blogId;
		this.userId = userId;
	}
	
	public Good(int id, int blogId, int userId) {
		this.id = id;
		this.blogId = blogId;
		this.userId = userId;
	}
	
	public int getId() { return id; }
	public int getBlogId() { return blogId; }
	public int getUserId() { return userId; }
}
