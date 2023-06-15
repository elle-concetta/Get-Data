public class Address {
    private final String city;
    private final String state;
    private final String zip;
    // parameterized constructor
    public Address(String city, String st, String zip) {
        // instance variables
        this.city = city;
        state = st;
        this.zip = zip;
    }
    // to get the city name
    public String getCity() {
        return city;
    }
    // to get the state name
    public String getState() {
        return state;
    }
    // to get the zip code
    public String getZip() {
        return zip;
    }
}
