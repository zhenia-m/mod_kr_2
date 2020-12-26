public class SaleItem extends Item {
    public SaleItem(String title, double price, int quantity){
        super(title,price,quantity);
    }

    public int calculateDiscount() {
        int discount = 70;
        if (discount < 80) {
            discount += this.getQuantity() / 10;
            if (discount > 80)
                discount = 80;
        }
        return discount;
    }
}
