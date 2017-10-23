package project.domain;


public class Company {

    private int companyID;
    private String companyName;

    public Company(int companyID, String companyName) {
        this.companyID = companyID;
        this.companyName = companyName;
    }

    public Company(){}

    public int getCompanyID() {
        return companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
