package model;

import java.io.Serializable;

public class Paginate implements Serializable {
	private int itemsPerPage = 10;		// 1ページあたりの表示件数  
	private long currentPage = 1; 		// 現在のページ
	private long maxBlogsCount;			// ブログの総件数
	private long maxPagesCount;			// ページの総数
	private boolean isExistPrePage;		// 前のページが存在するか
	private boolean isExistNextPage;	// 次のページが存在するか
	
	public Paginate() {}
	
	public Paginate(int itemsPerPage, long currentPage, long maxBlogsCount, long maxPagesCount, boolean isExistPrePage, boolean isExistNextPage) {
		this.itemsPerPage = itemsPerPage;
		this.currentPage = currentPage;
		this.maxBlogsCount = maxBlogsCount;
		this.maxPagesCount = maxPagesCount;
		this.isExistPrePage = isExistPrePage;
		this.isExistNextPage = isExistNextPage;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public long getMaxBlogsCount() {
		return maxBlogsCount;
	}

	public long getMaxPagesCount() {
		return maxPagesCount;
	}

	public boolean isExistPrePage() {
		return isExistPrePage;
	}

	public boolean isExistNextPage() {
		return isExistNextPage;
	}


	
}
