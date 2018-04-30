package jdbconn.model;

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

    public Request(String customerId, String accountNum, String status, String createdBy, String serviceNum)
    {
        this.customerId = customerId;
        this.accountNum = accountNum;
        this.status = status;
        this.createdBy = createdBy;
        this.serviceNum = serviceNum;
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
