import java.sql.*;

import twitter4j.User;

public class DataBase {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";

	private static final String USER = "root";
	private static final String PASS = "xxxxxxxx";

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

				// Creating table Pos
1
import java.sql.*;
2
​
3
import twitter4j.User;
4
​
5
public class DataBase {
6
​
7
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
8
        static final String DB_URL = "jdbc:mysql://localhost:3306/";
9
​
10
        private static final String USER = "root";
11
        private static final String PASS = "xxxxxxxx";
12
​
13
        Connection conn = null;
14
        Statement stmt = null;
15
​
16
        public void CreateDB() {
17
​
18
                try {
19
                        Class.forName("com.mysql.jdbc.Driver");
20
​
21
                        System.out.println("Connecting to database...");
22
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
23
                        stmt = conn.createStatement();
24
                        // Create connection and statement
25
                        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'TWITTER' AND table_name = 'Possitive'";
26
                        ResultSet rs = stmt.executeQuery(query);
27
                        rs.next();
28
                        boolean dbExists = rs.getInt("COUNT(*)") > 0;
29
                        if (!dbExists) {
30
                                System.out.println("Creating database...");
31
​
32
                                String cdb = "CREATE DATABASE TWITTER";
33
                                stmt.execute(cdb);
34
                                System.out.println("Database TWITTER created successfully...");
35
​
36
                                System.out.println("Connecting to TWITTER database...");
37
                                conn = DriverManager.getConnection(DB_URL, USER, PASS);
38
                                System.out.println("Connected database successfully...");
39
​
40
                                System.out.println("Creating table in TWITTER database...");
41
                                stmt = conn.createStatement();
42
​
43
                                // Creating table Positive to hold positive tweets
44
                                String ctbl1 = "CREATE TABLE TWITTER.Possitive " + "(" + "id INT NOT NULL AUTO_INCREMENT ,"
45
                                                + "twitterId BIGINT not NULL, " + " user VARCHAR(35), " + " text VARCHAR(255), "
46
                                                + " PRIMARY KEY ( id ))";
47
                                System.out.println(ctbl1);
48
                                stmt.executeUpdate(ctbl1);
49
                                System.out.println("Table created");
50
​
51
                                // Creating table Negative to hold Negative tweets
52
                                String ctbl2 = "CREATE TABLE TWITTER.Negative " + "(" + "id INT NOT NULL AUTO_INCREMENT ,"
53
                                                + "twitterId BIGINT not NULL, " + " user VARCHAR(35), " + " text VARCHAR(255), "
54
                                                + " PRIMARY KEY ( id ))";
55
                                stmt.executeUpdate(ctbl2);
56
                                System.out.println("Table Created");
57
                        }
58
                } catch (SQLException se) {
59
​
60
                        se.printStackTrace();
61
                } catch (Exception e) {
62
​
63
                        e.printStackTrace();
64
                } finally {
65
​
66
                        try {
67
                                if (stmt != null)
68
                                        stmt.close();
69
                        } catch (SQLException se2) {
70
                        }
71
                        try {
@AlexKap23
itive to hold positive tweets
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
