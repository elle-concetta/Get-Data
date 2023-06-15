import java.text.*;
import javax.swing.*;
import java.util.*;
public class TestProduct {
    // to set or retrieve the date
    static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    public static void main(String[] args) throws ParseException {
        // declare the required variables
        String street, city, state, zip;
        String companyName;
        String productName;
        int quantity;
        String dateManu;
        double unitPrice;
        new Product();
        Product product;
        new Manufacturer();
        Manufacturer manufacture;
        Address address;
        Database productDB = new Database();
        Database deletedProdDB = new Database();
        Date present;
        boolean isDone = false;
        int subMenu;
        // loop until the program is done
        while (!isDone) {
            // prompt the user for the menu
            int selectedMenu = GetData.getInt("""
                    Welcome to Product Inventory
                    \t1. Create Products
                    \t2. Update Product (Quantity/Price)
                    \t3. Delete Product
                    \t4. To locate single product
                    \t5. Inventory Report
                    \t6. List of deleted Products
                    \t7. Quit""");
            // use the conditional structured statement to switch to the selected menu
            switch (selectedMenu) {
                // case 1 to add products to the inventory list
                case 1 -> {
                    // prompt the user and get the product's information, manufacturer and address
                    productName = GetData.getString("Enter product name: ");
                    quantity = GetData.getInt("Enter the quantity of product: ");
                    unitPrice = GetData
                            .getDouble("Enter the unit price of product(in $): ");
                    companyName = GetData.getString("Enter the name of the manufacturing product: ");
                    street = GetData.getString("Enter street address of company: ");
                    city = GetData.getString("Enter city address of company: ");
                    state = GetData.getString("Enter state address of company: ");
                    zip = GetData.getString("Enter ZIP code of company: ");
                    dateManu = GetData.getString("Enter date of manufacturing product in the form of(MM/dd/yyyy): ");
                    address = new Address(city, state, zip);
                    manufacture = new Manufacturer(companyName, address);
                    present = sdf.parse(dateManu);
                    // create the product object
                    product = new Product(productName, quantity, unitPrice, present, manufacture);
                    // add product to the database
                    productDB.add(product);
                }
                // case 2 user chooses to update the product's price or quantity information
                case 2 -> {
                    // prompt the user to update the product name
                    productName = GetData.getString("Enter product name to update: ");
                    // get the option chosen by the user
                    subMenu = GetData.getInt("Select option to update: \n1. To update quantity\n2. To update price\n");
                    // search for product in the database
                    productDB.search(productName);
                    // condition to check whether the product is or isn't in the database
                    if (!productDB.inList()) {
                        // if the condition is true about the product not being found in the database
                        JOptionPane.showMessageDialog(null, "Product not found.");
                    } else {
                        // depending on whether the user wants to update the information
                        switch (subMenu) {
                // if user selects 1, then the user wants to update the quantity
                case 1 -> {
                    // prompt the user whether they want to add or deduct the quantity of the product
                    int menuOpt = GetData.getInt("Select option to update: \n1. To add quantity\n2. To deduct quantity\n");
                    int addRDeductQuant;
                    // condition to check for the valid input
                    if (menuOpt < 1 || menuOpt > 2) {
                        JOptionPane.showMessageDialog(null, "Please chose the correct choice");
                    }
                    else {
                        // depending on the user's choice switch to add or deduct quantity
                        switch (menuOpt) {
                //case 1 to add the quantity
                case 1 -> {
                    // prompt for the quantity amount
                    addRDeductQuant = GetData.getInt("Enter the amount of quantity to add: ");
                    // get product from the database
                    product = productDB.getProduct();
                    // then update the quantity
                    product.upDateQuantity(addRDeductQuant);
                }
                // case 2 to deduct quantity
                case 2 -> {
                    // prompt for the quantity amount
                    addRDeductQuant = GetData.getInt("Enter the amount of quantity to deduct: ");
                    // get the product from the database
                    product = productDB.getProduct();
                    // then update the quantity
                    product.upDateQuantity(addRDeductQuant * -1);
                }
                        }
                        JOptionPane.showMessageDialog(null, "Quantity of the product is updated successfully.");
                                }
                            }
                // case 2 to update the price
                case 2 -> {
                    // prompt user for the new price
                    double priceToUpdate = GetData.getDouble("Enter the unit price of product to update: ");
                    // validation of the price
                    if (priceToUpdate < 0) {
                        JOptionPane.showMessageDialog(null, "Price of the product is not updated.");
                    }
                    else {
                        // get product from the database
                        product = productDB.getProduct();
                        // update price of the respective product
                        product.upDatePrice(priceToUpdate);
                        // display information to the user
                        JOptionPane.showMessageDialog(null, "Price of the product is updated successfully.");
                    }
                }
                    default -> JOptionPane.showMessageDialog(null, "Unable to process the option selected from the update list.");
                        }
                    }
                }
                // case 3 to delete the product from the database if exists.
                case 3 -> {
                    // get name of the product
                    productName = GetData.getString("Enter product name to update: ");
                    // search for the product
                    productDB.search(productName);
                    // check whether the product exists in the database
                    if (productDB.inList()) {
                        // if product exists, then get the index
                        int index = productDB.getIndex();
                        // add product to the deleted product database
                        deletedProdDB.add(productDB.getProduct());
                        // delete the product from the product database
                        productDB.delete(index);
                        // display the status information of the deleted product
                        JOptionPane.showMessageDialog(null, "The \"" + productName + "\" product is deleted successfully.");
                    }
                    // otherwise display the status information of product not being found
                    else {
                        JOptionPane.showMessageDialog(null, "Product not found.");
                    }
                }
                // case 4 to display the single product's information
                case 4 -> {
                    // prompt the user for the product name that they want to update
                    productName = GetData.getString("Enter name of the product to display about: ");
                    // search for the product
                    productDB.search(productName);
                    // check whether the product exists in the database
                    if (productDB.inList()) {
                        // display the information about the product
                        displaySingleProduct(productDB.getProduct(),
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                    // otherwise display the error message
                    else {
                        JOptionPane.showMessageDialog(null, "Product not found.");
                    }
                }
                // case 5 to display the inventory information
                case 5 -> {
                    // check whether the inventory in the database null or not
                    if (productDB.getList() != null) {
                        // if product is not null, display the inventory information
                        displayInventory(productDB, JOptionPane.INFORMATION_MESSAGE);
                    }
                    // otherwise, display the error message
                    else {
                        JOptionPane.showMessageDialog(null, "There are no products present in the inventory.");
                    }
                }
                // case 6 to display the deleted inventory information
                case 6 -> {
                    // check whether the deleted inventory database is either null or not
                    if (deletedProdDB.getList() != null) {
                        displayInventory(deletedProdDB, JOptionPane.INFORMATION_MESSAGE);
                    }
                    // otherwise, display the error message
                    else {
                        JOptionPane.showMessageDialog(null, "There are no products present in the inventory.");
                    }
                }
                // case 7 if the user chooses to quit then isDone is true
                case 7 -> isDone = true;
                    // if user chooses something other than the 1 - 7 case options, then display an error message
                    default -> JOptionPane.showMessageDialog(null, "Unable to process the option selected from the menu list.");
            }
        }
    }
    // public method to display the inventory list
    public static void displayInventory(Database productDB, int Type_Message) {
        StringBuilder inventResult = new StringBuilder();
        ArrayList<Product> prodList = productDB.getList();
        inventResult.append(String.format("%-30s \t%s %10s %15s %20s %15s\n",
                "Product", "Purchase Date", "Quantity", "Price($)",
                "Manufacturer", "State"));
        for (int i = 0; i < productDB.size(); i++) {
            inventResult.append(prodList.get(i).getProductInformation()).append("\n");
        }
        JTextArea text = new JTextArea(inventResult.toString(), 10, 60);
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null, pane, "Inventory Details", Type_Message);
    }
    // public method to display the single product
    public static void displaySingleProduct(Product product, int Type_Message) {
        String productInfo = "Product Name: " + product.getProductName() + "\n";
        productInfo += String.format("Product's Unit Price: $%.2f", product.getUnitPrice()) + "\n";
        productInfo += "Quantity of product: " + product.getQuantity() + "\n";
        JTextArea text = new JTextArea(productInfo, 10, 30);
        JScrollPane pane = new JScrollPane(text);
        JOptionPane.showMessageDialog(null, pane, product.getProductName() + " Details", Type_Message);
    }
}
