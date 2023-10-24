import org.junit.Test;

public class MoneyCoin {
    private double value;
    private String issueDate;
    private String ownerID;

    public MoneyCoin(double value, String issueDate, String ownerID) {
        this.value = value;
        this.issueDate = issueDate;
        this.ownerID = ownerID;
    }

    // Getter and Setter methods
    @Test
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
}
