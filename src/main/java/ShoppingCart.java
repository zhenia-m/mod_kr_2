import java.util.*;
import java.text.*;
/**
* Containing items and calculating price.
*/
public class ShoppingCart {
    /**
     * Tests all class methods.
     */
    public static void main(String[] args){
        // TODO: add tests here
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(ItemFactory.createNewItem("Apple", 0.99, 5));
        cart.addItem(ItemFactory.createSecondFreeItem("Banana", 20.00, 4));
        cart.addItem(ItemFactory.createSaleItem( "A long piece of toilet paper", 17.20, 1));
        cart.addItem(ItemFactory.createRegularItem("Nails", 2.00, 500));
        System.out.println(cart.formatTicket());
    }

    public void addItem(Item item){
        if (item.getTitle() == null || item.getTitle().length() == 0 || item.getTitle().length() > 32)
            throw new IllegalArgumentException("Illegal title");
        if (item.getPrice() < 0.01)
            throw new IllegalArgumentException("Illegal price");
        if (item.getQuantity() <= 0)
            throw new IllegalArgumentException("Illegal quantity");
        items.add(item);
    }
    public String formatTicket() {
        double total = calculateItemsParameters();
        return getFormattedTicketTable(total);
    }

    /**
     * Formats shopping price.
     *
     * @return string as lines, separated with \n,
     * first line: # Item Price Quan. Discount Total
     * second line: ---------------------------------------------------------
     * next lines: NN Title $PP.PP Q DD% $TT.TT
     * 1 Some title $.30 2 - $.60
     * 2 Some very long $100.00 1 50% $50.00
     * ...
     * 31 Item 42 $999.00 1000 - $999000.00
     * end line: ---------------------------------------------------------
     * last line: 31 $999050.60
     *
     * if no items in cart returns "No items." string.
     */
    public String getFormattedTicketTable(double total){
        if (items.size() == 0)
            return "No items.";

        String[] header = {"#", "Item", "Price", "Quan.", "Discount", "Total"};
        int[] align = new int[]{1, -1, 1, 1, 1, 1};
        List<String[]> lines = convertItemsToTableLines();
        int index = items.size();
        String[] footer = {String.valueOf(index), "", "", "", "", MONEY.format(total)};
        // formatting table
        // column max length
        int[] width = new int[]{0, 0, 0, 0, 0, 0};

        for (String[] line : lines) {
            adjustColmunWidth(width, line);
        }
        adjustColmunWidth(width, header);
        adjustColmunWidth(width, footer);
        // line length
        int lineLength = width.length - 1;
        for (int w : width)
            lineLength += w;
        StringBuilder sb = new StringBuilder();
        // header
        appendFormattedLine(header, align, width, sb, true);
        // separator
        appendSeparator(lineLength, sb);
        // lines
        for (String[] line : lines) {
            appendFormattedLine(line, align, width, sb, true);
        }
        if (lines.size() > 0) {
            // separator
            appendSeparator(lineLength, sb);
        }
        // footer
        appendFormattedLine(footer, align, width, sb, false);
        return sb.toString();
    }

    private double calculateItemsParameters(){
        double total = 0.00;
        for (Item item : items) {
            item.setDiscount(item.calculateDiscount());
            item.setTotalPrice(item.calculateTotalPrice());
            total += item.getTotalPrice();
        }
        return total;
    }

    private List<java.lang.String[]> convertItemsToTableLines() {
        // formatting each line
        List<String[]> lines = new ArrayList<String[]>();
        double total = 0.00;
        int index = 0;
        for (Item item : items) {
            lines.add(new String[]{
                    String.valueOf(++index),
                    item.getTitle(),
                    MONEY.format(item.getPrice()),
                    String.valueOf(item.getQuantity()),
                    (item.getDiscount() == 0) ? "-" : (String.valueOf(item.getDiscount()) + "%"),
                    MONEY.format(item.getTotalPrice())
            });
        }
        return lines;
    }

    private void appendSeparator(int lineLength, StringBuilder sb) {
        for (int i = 0; i < lineLength; i++)
            sb.append("-");
        sb.append("\n");
    }

    private void adjustColmunWidth(int[] width, String[] line) {
        for (int i = 0; i < line.length; i++)
            width[i] = (int) Math.max(width[i], line[i].length());
    }

    private void appendFormattedLine(String[] header, int[] align, int[] width, StringBuilder sb, Boolean newLine) {
        for (int i = 0; i < header.length; i++)
            appendFormatted(sb, header[i], align[i], width[i]);
        if(newLine) sb.append("\n");
    }

    // --- private section -----------------------------------------------------
    private static final NumberFormat MONEY;
        static {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            MONEY = new DecimalFormat("$#.00", symbols);
        }
    /**
     * Appends to sb formatted value.
     * Trims string if its length > width.
     * @param align -1 for align left, 0 for center and +1 for align right.
     */
    public static void appendFormatted(StringBuilder sb, String value, int align, int width){
        if (value.length() > width)
            value = value.substring(0,width);
        int before = (align == 0)
            ? (width - value.length()) / 2
            : (align == -1) ? 0 : width - value.length();
        int after = width - value.length() - before;
        addSpace(sb, value, before);
        addSpace(sb, " ", after);
    }

    private static void addSpace(StringBuilder sb, String value, int before) {
        while (before-- > 0)
            sb.append(" ");
        sb.append(value);
    }

    /** Container for added items */
    private List<Item> items = new ArrayList<Item>();
}
