public class Manufacturer {
    // instance variables
    private final String companyName;
    private final Address companyAddress;
    // default constructor
    public Manufacturer() {
        this.companyName = "";
        this.companyAddress = null;
    }
    // parameterized constructor
    public Manufacturer(String compName, Address address) {
        this.companyName = compName;
        this.companyAddress = address;
    }
    // to get the name of the manufacturer company
    public String getCompanyName() {
        return companyName;
    }
    // to get the Address object
    public Address getCompanyAddress() {
        return companyAddress;
    }
}
