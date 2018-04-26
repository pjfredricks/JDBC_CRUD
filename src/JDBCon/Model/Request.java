package JDBCon.Model;

/**
 * A POJO class which contains our table structure
 */
public class Request
{
    private String customerId;
    private String accountNum;
    private String status;
    private String createdBy;
    private String serviceNum;

    public Request(String CustomerId, String AccountNum, String Status, String CreatedBy, String ServiceNum)
    {
        this.customerId = CustomerId;
        this.accountNum = AccountNum;
        this.status = Status;
        this.createdBy = CreatedBy;
        this.serviceNum = ServiceNum;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public String getAccountNum()
    {
        return accountNum;
    }

    public String getStatus()
    {
        return status;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public String getServiceNum()
    {
        return serviceNum;
    }
}
