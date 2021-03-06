package jdbconn.dbmain;

import jdbconn.model.Request;
import java.sql.*;
import java.util.Scanner;

/**
 * A class which contains all the JDBC CRUD operations performed
 *
 * @author Jfredricks
 * @version 1.0
 */
public class DbMain {
    static Scanner s = new Scanner(System.in);
    public static final String CUSTOMER = "customer";
    public static final String SUBSCRIBER = "subscriber";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String SERVICE_NUMBER = "ServiceNumber";
    public static final String ACCOUNTNUMBER = "AccountNumber:";
    public static final String STATUS = "Status:";
    public static final String CREATED_BY = "CreatedBy:";
    public static final String LEGACY = "LEGACY";
    public static final String MIGRATED = "MIGRATED";
    private String accNo;
    private String status;
    private String cBy;
    private String id;

    /**
     * Checks if the id exists in a table or not
     *
     * @param id        is the id used to retrieve the row
     * @param stmt      is the SQL statement to be executed
     * @param tableName is the name of the table used
     * @return false if a row with id already exists or else true
     * @throws Exception
     */
    public static boolean checkId(String id, Statement stmt, String tableName) throws Exception {
        String checkId = "Select * from " + tableName + " where customer_id='" + id + "';";
        ResultSet rs = stmt.executeQuery(checkId);
        if (rs.next()) {
            System.out.println(tableName + " already exists");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Adds rows into subscriber table
     * @param r is the Object with values to be entered
     * @param stmt is the SQL statement to be executed
     * @throws Exception
     */
    public void add(Request r, Statement stmt) throws Exception {
        String sNo;
        System.out.println("Enter number of subscribers");
        int n = s.nextInt();
        System.out.println("Enter your details");
        while (n > 0) {
            System.out.println();
            System.out.println(ACCOUNTNUMBER);
            accNo = s.next();
            System.out.println(STATUS);
            status = s.next();
            System.out.println(CREATED_BY);
            cBy = s.next();
            System.out.println("ServiceNum:");
            sNo = s.next();
            if (checkId(sNo, stmt, SUBSCRIBER)) {
                String subs = "INSERT INTO subscriber VALUES ('" + sNo + "','" + r.getCustomerId() + "','" + accNo + "','" + status + "','" + cBy + "');";
                stmt.execute(subs);
                n--;
            } else
                continue;
        }
    }

    /**
     * Modifies the tables selected by the user and update the column values
     * @param r is the Object containing values to be updated
     * @param stmt is the SQL statement to be executed
     */
    public void modify(Request r, Statement stmt) {
        System.out.println("Modify 1.customer table or 2.subscriber table");
        int c = s.nextInt();
        String sql = "";
        String table = "";
        String col = "";

        if (c == 1) {
            table = CUSTOMER;
            col = CUSTOMER_ID;

        } else if (c == 2) {
            table = SUBSCRIBER;
            col = SERVICE_NUMBER;

        } else {
            System.out.println("Enter a valid choice");
            return;
        }

        System.out.println("Enter modified details, press enter if not modified");
        System.out.println(ACCOUNTNUMBER);
        accNo = s.next();
        if (accNo.equals("\n")) accNo += r.getAccountNum();
        System.out.println(STATUS);
        status = s.next();
        if (status.equals("\n")) status += r.getStatus();
        System.out.println(CREATED_BY);
        cBy = s.next();
        if (cBy.equals("\n")) cBy += r.getCreatedBy();

        System.out.println("Enter the id you want to modify");
        id = s.next();

        sql = "UPDATE " + table + " SET AccountNumber ='" + accNo + "', Status ='" + status + "', CreatedBy ='" + cBy + "' WHERE " + col + " = " + id + ";";

        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Terminating due to invalid inputs");
        }
    }

    /**
     * Deletes the rows form the tables based on rowId the user has entered
     * @param stmt is the SQL statement tot be executed
     * @throws Exception
     */
    public void delete(Statement stmt) throws Exception {
        System.out.println("DeleteRow from 1.customer or 2.subscriber");
        int c = s.nextInt();
        int i = 1;
        String table = "";
        String col = "";

        do {
            System.out.println("Enter the CustomerId or ServiceNum you want to delete");
            id = s.next();
            System.out.println();
            String checkId = "Select * from customer where customer_id ='" + id + "';";
            ResultSet rs = stmt.executeQuery(checkId);

            if (c == 1) {
                if (rs.next()) {
                    System.out.println("Records with this CustomerId will also be deleted in subscriber table");
                    System.out.println("Do you want to exit now?(y/n)");
                    if (s.next().equalsIgnoreCase("y")) {
                        String subs = "DELETE from subscriber where customer_id = '" + id + "';";
                        stmt.execute(subs);
                    }

                    table = CUSTOMER;
                    col = CUSTOMER_ID;

                } else if (c == 2) {
                    table = SUBSCRIBER;
                    col = SERVICE_NUMBER;
                }
            } else {
                System.out.println("Enter a valid choice");
                continue;
            }

            String sql = "DELETE from " + table + " where " + col + " = '" + id + "';";
            stmt.execute(sql);


            System.out.println("enter 0 to quit");
            i = s.nextInt();
            if (i == 0)
                break;
            else
                continue;
        } while (i > 1);
    }

    /**
     * Lists all the rows present in a table
     * @param stmt
     */
    public void list(Statement stmt) {
        String sql = "SELECT * FROM ";
        int count = 0;


        System.out.println("List 1.Customer or 2.Subscriber");
        int c = s.nextInt();

        if (c == 1) {
            count = 4;
            sql = sql + CUSTOMER;
            System.out.println("Cid\t\tANum\tSts\t\tCBy");
        } else if (c == 2) {
            count = 5;
            sql = sql + SUBSCRIBER;
            System.out.println("SNum\tCId\t\tANum\tSts\t\t\tCBy");
        }

        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                do {
                    while (count > 0) {
                        System.out.print(rs.getString(count) + "\t");
                        count--;
                    }
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.out.println("Invalid inputs, Connection Terminated");
        }
    }

    /**
     * <p>
     * Establishes a connection with the Database and gets the details to be entered
     * Lists all the services for the user to select whichever service he wants
     * </p>
     * @param args is unused
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new JDBConn();
        DbMain db = new DbMain();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/first?autoReconnect=true&useSSL=false", "root", "root123");
        Statement stmt = con.createStatement();

        do {
            String cId;
            String accNo;
            String status;
            String cBy;
            String sNo;
            System.out.println("Enter your details [Details cannot be null]");

            System.out.println(CUSTOMER_ID);
            cId = s.next();
            System.out.println(ACCOUNTNUMBER);
            accNo = s.next();
            System.out.println(STATUS);
            status = s.next();
            System.out.println(CREATED_BY);
            cBy = s.next();
            System.out.println(SERVICE_NUMBER);
            sNo = s.next();

            Request r = new Request(cId, accNo, status, cBy, sNo);

            if (checkId(cId, stmt, CUSTOMER)) {
                if (status.equals(MIGRATED) || status.equals(LEGACY)) {
                    String cust = "INSERT INTO customer VALUES ('" + r.getCustomerId() + "','" + r.getAccountNum() + "','" + r.getStatus() + "','" + r.getCreatedBy() + "');";
                    stmt.execute(cust);
                    System.out.println("Record Created");
                } else
                    System.out.println("Pls enter status as MIGRATED or LEGACY");
            } else {
                System.out.println("Do you want to add new subscribers?(Y/N)");
                String reply = s.next();
                if (reply.equalsIgnoreCase("y")) {
                    int o = 0;
                    System.out.println("Do you want to 1.Add , 2.Modify, 3.DeleteRow, 4.List, other for Exit");
                    o = s.nextInt();
                    switch (o) {
                        case 1:
                            db.add(r, stmt);
                            break;
                        case 2:
                            db.modify(r, stmt);
                            break;
                        case 3:
                            db.delete(stmt);
                            break;
                        case 4:
                            db.list(stmt);
                            break;
                        default:
                            System.out.println("Connection terminated");
                            break;
                    }
                }
            }
            System.out.println("Enter 0 to quit! or any number to continue");
        } while (s.nextInt() != 0);

        System.out.println("Connection Terminated");
        s.close();
    }
}


