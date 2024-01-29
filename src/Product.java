public class Product {

    private static int id = 0;
    private String name;
    private int quantity;

    public Product(String name) {
        this.name = name;
    }

    public Product(String name, int quantity) {
        id = getId();
        id++;
        this.name = name;
        this.quantity = quantity;
    }

    public static int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name: " + name +
                ", quantity: " + quantity +
                '}';
    }
}
