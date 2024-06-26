class Customer {
    private String customerId;
    private String name;
    private String contactNumber;
    private String location;

    public Customer(String customerId, String name, String contactNumber, String location) {
        this.customerId = customerId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.location = location;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId + "\nName: " + name + "\nContact Number: " + contactNumber + "\nLocation: " + location;
    }
}
