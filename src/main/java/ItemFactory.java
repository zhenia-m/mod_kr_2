public class ItemFactory {
    public static Item createNewItem(String title, double price, int quantity){
        return new NewItem(title,price,quantity);
    }

    public static Item createRegularItem(String title, double price, int quantity){
        return new RegularItem(title,price,quantity);
    }

    public static Item createSaleItem(String title, double price, int quantity){
        return new SaleItem(title,price,quantity);
    }

    public static Item createSecondFreeItem(String title, double price, int quantity){
        return new SecondFreeItem(title,price,quantity);
    }
}
