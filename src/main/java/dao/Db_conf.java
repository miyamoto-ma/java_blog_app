package dao;

public class Db_conf {
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/blog";
	private final String DB_USER = "manabu";
	private final String DB_PASS = "Mpa0515";
	public String getJDBC_URL() { return JDBC_URL; }
	public String getDB_USER() { return DB_USER; }
	public String getDB_PASS() { return DB_PASS; }	
}
