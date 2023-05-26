package dao;

public class ReadJDBC {
	public void read() {
		// JDBCドライバを読み込む
		Db_conf conf = new Db_conf();
		String driver = conf.getD_CLASS();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
	}
}
