package model;

import java.util.ArrayList;
import java.util.List;

import dao.BlogDAO;

public class BlogLogic {
	public boolean executeAdd(Blog blog) {
		BlogDAO dao = new BlogDAO();
		return dao.addBlog(blog);
	}
	
	public List<Blog> executeFind() {
		BlogDAO dao = new BlogDAO();
		List<Blog> blogList = new ArrayList<>();
		blogList = dao.findAll();
		return blogList;
	}
}
