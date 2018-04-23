package JDBCon;

import java.sql.*;

class jdbConn
{  
	public jdbConn()
	{  
		try
		{  	
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/first?autoReconnect=true&useSSL=false","root","root123");  
			Statement stmt=con.createStatement();  
			
		String sql = "CREATE TABLE IF NOT EXISTS customer(customer_id varchar(30),\n"
	                + "	 account_num varchar(30) NOT NULL,\n"
	                + "	 status varchar(30) NOT NULL,\n"
	                + "	 created_by varchar(30) NOT NULL,\n"
	                + "   PRIMARY KEY (customer_id)); ";
			
			String sql1 = "CREATE TABLE IF NOT EXISTS subscriber (\n"
					+ "	 service_num varchar(30),\n"
	                + "	 customer_id varchar(30) NOT NULL,\n"
	                + "	 account_num varchar(30) NOT NULL,\n"
	                + "	 status varchar(30) NOT NULL,\n"
	                + "	 created_by varchar(30) NOT NULL,\n"
	                + "  PRIMARY KEY (service_num),\n"
	                + "  FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ); ";
			
			stmt.execute(sql);
			stmt.execute(sql1);
			
			
			con.close();  
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  	
	}
}  