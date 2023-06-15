import java.util.ArrayList;
public class Database {
    // instance variables
    private final ArrayList<Product> list;
    private Product prod;
    private int index;
    private boolean found;
    // default constructor
    public Database() {
        list = new ArrayList<>();
        prod = null;
        index = 0;
        found = false;
    }
    // search method
    public void search(String key) {
        found = false;
        int i = 0;
        while (!found && i < list.size()) {
            Product b = list.get(i);
            if (b.getProductName().equalsIgnoreCase(key)) {
                prod = b;
                found = true;
                index = i;
            }
            else i++;
        }
    }
    // add the product to the inventory list
    public void add(Product newProduct) {
        list.add(newProduct);
    }
    // remove the product from the inventory list
    public void delete(int i) {
        list.remove(i);
    }
    // to get index of the current product from the list
    public int getIndex() {
        return index;
    }
    // to get the boolean value which specifies whether the item is found in the list or not
    public boolean inList() {
        return found;
    }
    // to the current product
    public Product getProduct() {
        return prod;
    }
    // to get the size of the inventory list
    public int size() {
        return list.size();
    }
    // to get the inventory list
    public ArrayList<Product> getList() {
        return list;
    }
}
