import java.text.SimpleDateFormat;
import java.util.Date;
public class Product {
    // instance variables
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    // to hold the Manufacturer object
    Manufacturer manufacture;
    // to hold product name
    String productName;
    // to hold quantity
    int quantity;
    // to hold the unit price
    double unitPrice;
    // to hold the product date
    Date productCreated;
    // default constructor
    public Product() {
        this.productName = "";
        this.quantity = 0;
        this.unitPrice = 0.0;
        this.productCreated = null;
        this.manufacture = null;
    }
    // parameterized constructor
    public Product(String prodName, int quantity, double unitPrice, Date productCreated, Manufacturer manufacture) {
        this.productName = prodName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.productCreated = productCreated;
        this.manufacture = manufacture;
    }
    // to get the name of the product
    public String getProductName() {
        return productName;
    }
    // to get the quantity of the product
    public int getQuantity() {
        return quantity;
    }
    // to get the unit price of the product
    public double getUnitPrice() {
        return unitPrice;
    }
    // to update the quantity of the product
    public void upDateQuantity(int quantity_upDate) {
        quantity += quantity_upDate;
    }
    // to update the price of the product
    public void upDatePrice(double price_upDate) {
        this.unitPrice = price_upDate;
    }
    // to get the product information
    public String getProductInformation() {
        String result = "";
        result += String.format("%-30s", productName);
        String dateForm = sdf.format(productCreated);
        result += String.format("\t %s", dateForm);
        result += String.format("%10d", quantity);
        result += String.format("\t%15.2f", unitPrice);
        result += String.format("\t%15s", manufacture.getCompanyName());
        result += String.format("\t%20s", manufacture.getCompanyAddress().getState());
        return result;
    }
}
