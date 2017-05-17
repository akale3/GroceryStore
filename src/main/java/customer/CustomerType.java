package customer;

/**
 * Created by Aditya on 2017-05-16.
 */
public enum CustomerType {

    TYPEA("A"), TYPEB("B");

    private final String type;

    CustomerType(String customerType) {
        this.type = customerType;
    }

    public String getType() {
        return type;
    }
}
