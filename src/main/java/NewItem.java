public class NewItem extends Item {
    public NewItem(String title, double price, int quantity){
        super(title,price,quantity);
    }

    public int calculateDiscount(){
        return 0;
    }
}
