package project.domain;

public class MaterialDetails extends Material {

    private String description;
    private String notes;
    private String supplier;
    private int price;
    private String currency;

    public MaterialDetails(int ID, String name, int companyID) {
        super(ID, name, companyID);
    }

    public MaterialDetails(){}

    public MaterialDetails(int ID, String name, int companyID, String description, String notes,
                           String supplier, int price, String currency){
        super(ID, name, companyID);
        this.description = description;
        this.notes = notes;
        this.supplier = supplier;
        this.price = price;
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
