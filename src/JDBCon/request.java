package JDBCon;

public class request
{
    private String customerId;
    private String accountNum;
    private String status;
    private String createdBy;
    private String serviceNum;

    request(String CustomerId,String AccountNum,String Status,String CreatedBy,String ServiceNum)
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
