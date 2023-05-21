package model;

import java.util.ArrayList;
import java.util.List;

import dao.BlogDAO;

public class BlogLogic {
	// ブログの投稿処理
	public boolean executeAdd(Blog blog) {
		BlogDAO dao = new BlogDAO();
		return dao.addBlog(blog);
	}
	
	// ブログ内容をすべて取得
	public List<Blog> executeFindAll() {
		BlogDAO dao = new BlogDAO();
		List<Blog> blogList = new ArrayList<>();
		blogList = dao.findAll();
		return blogList;
	}
	
	// ブログ1件分を取得
	public Blog executeFindById(int id) {
		BlogDAO dao = new BlogDAO();
		Blog blog = dao.findById(id);
		return blog;
	}
	
	// ブログ内容を1ページ分取得
	public List<Blog> executeFindByPage(long currentPage, int itemsPerPage) {
		BlogDAO dao = new BlogDAO();
		List<Blog> blogList = new ArrayList<>();
		blogList = dao.findByPage(currentPage, itemsPerPage);
		return blogList;
	}
	
	// ブログの総件数を取得
	public long executeGetTotal() {
		BlogDAO dao = new BlogDAO();
		long total = dao.getTotal();
		return total;
	}
	
	// ブログの削除処理（投稿ユーザーのみ）
	public boolean executeDelete(int id) {
		BlogDAO dao = new BlogDAO();
		return dao.deleteBlog(id);
	}
	
	// ブログの編集処理（投稿ユーザーのみ）
	public boolean executeUpdate(Blog blog) {
		BlogDAO dao = new BlogDAO();
		return dao.updateBlog(blog);
	}
}
