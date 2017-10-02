import java.sql.*;

import twitter4j.User;

public class DataBase {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";

	private static final String USER = "root";
	private static final String PASS = "ckfklaxtb*-";

	Connection conn = null;
	Statement stmt = null;

	public void CreateDB() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			// Create connection and statement
			String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'TWITTER' AND table_name = 'Possitive'";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			boolean dbExists = rs.getInt("COUNT(*)") > 0;
			if (!dbExists) {
				System.out.println("Creating database...");

				String cdb = "CREATE DATABASE TWITTER";
				stmt.execute(cdb);
				System.out.println("Database TWITTER created successfully...");

				System.out.println("Connecting to TWITTER database...");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				System.out.println("Connected database successfully...");

				System.out.println("Creating table in TWITTER database...");
				stmt = conn.createStatement();

				// Creating table Positive to hold positive tweets
				String ctbl1 = "CREATE TABLE TWITTER.Possitive " + "(" + "id INT NOT NULL AUTO_INCREMENT ,"
						+ "twitterId BIGINT not NULL, " + " user VARCHAR(35), " + " text VARCHAR(255), "
						+ " PRIMARY KEY ( id ))";
				System.out.println(ctbl1);
				stmt.executeUpdate(ctbl1);
				System.out.println("Table created");

				// Creating table Negative to hold Negative tweets
				String ctbl2 = "CREATE TABLE TWITTER.Negative " + "(" + "id INT NOT NULL AUTO_INCREMENT ,"
						+ "twitterId BIGINT not NULL, " + " user VARCHAR(35), " + " text VARCHAR(255), "
						+ " PRIMARY KEY ( id ))";
				stmt.executeUpdate(ctbl2);
				System.out.println("Table Created");
			}
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void Insert(long twitterId, User user2, String text, String tableName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();

			String insert = "INSERT INTO TWITTER." + tableName + "(twitterId,user,text)" + " VALUES ( " + twitterId
					+ " ," + "'" + user2.getScreenName() + "'" + ", \"" + " " + text + " \"  )";
			
			stmt.executeUpdate(insert);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Query Completed!");
	}
}