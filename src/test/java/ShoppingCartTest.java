import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    public void testAppendFormatted() {
        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", 0, 14);
        assertEquals(sb.toString(), "   SomeLine    ");
        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", 0, 15);
        assertEquals(sb.toString(), "   SomeLine     ");
        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", 0, 5);
        assertEquals(sb.toString(), "SomeL ");
        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", 1, 15);
        assertEquals(sb.toString(), "       SomeLine ");
        sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "SomeLine", -1, 15);
        assertEquals(sb.toString(), "SomeLine        ");
    }

    @Test
    public void testCalculateDiscount() {
        assertEquals(80, Item.calculateDiscount(ShoppingCart.ItemType.SALE, 500));
        assertEquals(73, Item.calculateDiscount(ShoppingCart.ItemType.SALE, 30));
        assertEquals(71, Item.calculateDiscount(ShoppingCart.ItemType.SALE, 10));
        assertEquals(70, Item.calculateDiscount(ShoppingCart.ItemType.SALE, 9));
        assertEquals(70, Item.calculateDiscount(ShoppingCart.ItemType.SALE, 1));
        assertEquals(0, Item.calculateDiscount(ShoppingCart.ItemType.NEW, 20));
        assertEquals(0, Item.calculateDiscount(ShoppingCart.ItemType.NEW, 10));
        assertEquals(0, Item.calculateDiscount(ShoppingCart.ItemType.NEW, 1));
        assertEquals(80, Item.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 500));
        assertEquals(53, Item.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 30));
        assertEquals(51, Item.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 10));
        assertEquals(50, Item.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 9));
        assertEquals(50, Item.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 2));
        assertEquals(0, Item.calculateDiscount(ShoppingCart.ItemType.SECOND_FREE, 1));
    }

}