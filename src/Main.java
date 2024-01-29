
public class Main {
    public static void main(String[] args) {

        Shop shop = new Shop();
        Customer cust1 = new Customer("Ivan", "Ivanov", Gender.male, "+78888899900", "Moscow, str.Tverskaya");
        Customer cust2 = new Customer("Vasiliy", "Ivanov", Gender.male, "+9900", "Moscow, str.Popova");
        Customer cust3 = new Customer("Irina", "Ivanova", Gender.female, "+7779900", "Tver, str.Tverskaya");
        Customer cust4 = new Customer("Ivan", "Ivanov", Gender.male, "+78888899900", "Krasnodar, str.Tverskaya");
        Customer cust5 = new Customer("Georgina", "Giorgio", Gender.non_binary_person, "+700000099900", "Irkutsk, str.Tverskaya");


        shop.addCustomer(cust1);
        shop.addCustomer(cust2);
        shop.addCustomer(cust3);
        shop.addCustomer(cust4);//is existing in cust1 error
        shop.addCustomer(cust5);

        Product shoe = new Product("shoes Gucci", 100);
        Product jacket = new Product("jacket Tom Ford", 20);
        Product costume = new Product("costume Valentino", 5);



        shop.addProduct(shoe);
        shop.addProduct(jacket);
        shop.addProduct(costume);

        System.out.println(shop);

        cust1.makeOrder(shop, "shoes Gucci", 10);
        cust2.makeOrder(shop, "jacket Tom Ford", 10);
        cust2.makeOrder(shop, "jacket Tom Ford", -10);//negative amount error
        cust3.makeOrder(shop, "costume Valentino", 100);//lack at storage error

        Product costume1 = new Product("costume Valentino", 10);
        shop.addProduct(costume1);//add existing product

        System.out.println(shop);
        shop.showCountOrders();

    }
}