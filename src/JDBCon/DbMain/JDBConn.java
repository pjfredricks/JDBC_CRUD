package JDBCon.DbMain;

import java.sql.*;

/*
 * Establishes the connection to the Database
 */
class JDBConn {
    /*
     * Creates the tables if they aren't present in the DB
     */
    public JDBConn() {
		try
		{  	
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/first?autoReconnect=true&useSSL=false","root","root123");  
			Statement stmt=con.createStatement();

            String customerTable = "CREATE TABLE IF NOT EXISTS customer(customer_id VARCHAR(30),\n"
	                + "	 account_num VARCHAR(30) NOT NULL,\n"
	                + "	 status VARCHAR(30) NOT NULL,\n"
	                + "	 created_by VARCHAR(30) NOT NULL,\n"
	                + "   PRIMARY KEY (customer_id)); ";

            String subscriberTable = "CREATE TABLE IF NOT EXISTS subscriber (\n"
					+ "	 service_num varchar(30),\n"
	                + "	 customer_id varchar(30) NOT NULL,\n"
	                + "	 account_num varchar(30) NOT NULL,\n"
	                + "	 status varchar(30) NOT NULL,\n"
	                + "	 created_by varchar(30) NOT NULL,\n"
	                + "  PRIMARY KEY (service_num),\n"
	                + "  FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ); ";

            stmt.execute(customerTable);
            stmt.execute(subscriberTable);
			con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  	
	}
}  