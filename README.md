# JDBC_CRUD

## A project to implement CRUD operations using JDBC

## There are two tables Customer and Subscriber
### Customer has columns
- CustomerId : VarChar - PK - Not Null
- AccountNumber : VarChar - Not Null
- Status : VarChar - Not Null
- CreatedBy : VarChar - Not Null

### Subscriber has columns
- ServiceNumber : VarChar - PK - Not Null
- CustomerId : VarChar - FK - Not Null
- AccountNumber : VarChar - Not Null
- Status : VarChar - Not Null
- CreatedBy : VarChar - Not Null

### **Note: Status can only be LEGACY/MIGRATED**

