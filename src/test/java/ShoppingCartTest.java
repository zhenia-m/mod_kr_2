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
        assertEquals(80, ItemFactory.createSaleItem("Test",0.00,500).calculateDiscount());
        assertEquals(71, ItemFactory.createSaleItem("Test",0.00,10).calculateDiscount());
        assertEquals(70, ItemFactory.createSaleItem("Test",0.00,9).calculateDiscount());
        assertEquals(70, ItemFactory.createSaleItem("Test",0.00,1).calculateDiscount());
        assertEquals(0,  ItemFactory.createNewItem("Test",0.00,20).calculateDiscount());
        assertEquals(0,  ItemFactory.createNewItem("Test",0.00,10).calculateDiscount());
        assertEquals(0,  ItemFactory.createNewItem("Test",0.00,1).calculateDiscount());
        assertEquals(80, ItemFactory.createSecondFreeItem("Test",0.00,500).calculateDiscount());
        assertEquals(53, ItemFactory.createSecondFreeItem("Test",0.00,30).calculateDiscount());
        assertEquals(51, ItemFactory.createSecondFreeItem("Test",0.00,10).calculateDiscount());
        assertEquals(50, ItemFactory.createSecondFreeItem("Test",0.00,9).calculateDiscount());
        assertEquals(50, ItemFactory.createSecondFreeItem("Test",0.00,2).calculateDiscount());
        assertEquals(0,  ItemFactory.createSecondFreeItem("Test",0.00,1).calculateDiscount());
    }

}