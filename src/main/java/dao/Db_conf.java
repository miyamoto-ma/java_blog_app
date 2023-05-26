package dao;

public class Db_conf {
	private final String D_CLASS = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/blog";
	private final String DB_USER = "manabu";
	private final String DB_PASS = "Mpa0515";
	public String getD_CLASS() { return D_CLASS; }
	public String getJDBC_URL() { return JDBC_URL; }
	public String getDB_USER() { return DB_USER; }
	public String getDB_PASS() { return DB_PASS; }	
}
