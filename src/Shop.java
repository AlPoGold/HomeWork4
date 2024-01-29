import CustomExceptions.WrongAmountException;
import CustomExceptions.WrongCustomerException;
import CustomExceptions.WrongOrderException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Shop {
    public final String NEGATIVE_AMOUNT = "Invalid order. Negative amount of product";
    public final String LACK_AMOUNT = "Invalid order. Not enough product into the storage";
    public final String WRONG_ORDER = "Invalid order. Product doesn't exist";
    public final String WRONG_CUSTOMER = "Customer is already existing";

    private String nameShop;

    private ArrayList<Product> products;
    private ArrayList<Customer> customers;
    private int countOrders;

    public Shop(String nameShop, ArrayList<Product> products, ArrayList<Customer> customers) {
        this.nameShop = nameShop;
        this.products = products;
        this.customers = customers;
        this.countOrders = 0;
    }

    public Shop() {
        this.nameShop = "Default Shop";
        products = new ArrayList<>();
        customers = new ArrayList<>();
        this.countOrders = 0;
    }

    public void addProduct(Product product) {
        for (Product pr : products
        ) {
            if (product.getName().equals(pr.getName())) {
                pr.setQuantity(product.getQuantity() + pr.getQuantity());
                return;
            }
        }
        products.add(product);
    }

    public void addCustomer(Customer customer) {
        try {
            for (Customer cst : customers
            ) {
                if (cst.equals(customer)) throw new WrongCustomerException(WRONG_CUSTOMER);
            }
            customers.add(customer);
        } catch (WrongCustomerException e) {
            writeLog(e.getMessage());
        }

    }

    public  ArrayList<Product> getProducts() {
        return products;
    }

    public boolean getOrder(Order order) {
        boolean isAcceptOrder = false;
        Product product = order.getProduct();
        Product productInStorage = null;

        try {
            boolean isHere = false;
            for (Product pr : getProducts()
            ) {
                if (pr.getName().equals(product.getName())) {
                    isHere = true;
                    productInStorage = pr;
                    break;
                }

            }
            if (!isHere) throw new WrongOrderException(WRONG_ORDER);
        }catch (WrongOrderException e) {
            writeLog(e.getMessage());
        }
            int amount = order.getAmount();
            try {
                if (amount < 0) throw new WrongAmountException(NEGATIVE_AMOUNT);
                if (productInStorage.getQuantity() - amount < 0) throw new WrongAmountException(LACK_AMOUNT);
                productInStorage.setQuantity(productInStorage.getQuantity() - amount);
                countOrders++;
                isAcceptOrder = true;
                writeOrders(order.toString());

            } catch (WrongAmountException e) {
                writeLog(e.getMessage());
            }
            return isAcceptOrder;

        }


    public int getCountOrders() {
        return countOrders;
    }
    public void showCountOrders(){
        System.out.printf("The count of all orders: %d\n", countOrders);
    }

    public static void writeLog(String message) {
        try(FileWriter fileWriter = new FileWriter(".\\src\\logging.txt", true)){
            fileWriter.write(LocalDate.now() + " " + message);
            fileWriter.write("\n");

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public static void writeOrders(String message) {
        try(FileWriter fileWriter = new FileWriter(".\\src\\orders.txt", true)){
            fileWriter.write(LocalDate.now() + " " + message);
            fileWriter.write("\n");

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Products: ");
        sb.append("\n");
        for (Product product: products
             ) {
            sb.append(product.getName()).append(" ").append(product.getQuantity());
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("Customers: ");
        sb.append("\n");
        for (Customer cst: customers
             ) {
            sb.append(cst.getFirstName()).append(" ").append(cst.getLastName()).append(" ")
                    .append(cst.getGender()).append(" ").append(cst.getCountOrders());
            sb.append("\n");
        }


        return sb.toString();
    }
}
