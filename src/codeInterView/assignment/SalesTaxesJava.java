package codeInterView.assignment;

/*
* Rafael Rodriguez
* rafase282@gmail.com
* (347) 264-9463
* 
* When I decided to do this program I picture the code being used
* on small business that would eventually require a SalesTaxesJava object
* with at least a name, price, quantity, and declared categories
* for tax purposes hence why I went straight for an object that 
* contains what is currently needed, and can be evolved without major 
* re-design and not just extract the information and work with that.
* 
* The code was written and tested using sun Java jdk 1.7
* and the Eclipse IDE. The easiest way is to import the 
* project or properly compile it keeping in mind the 
* package name. The program needs one argument which is 
* the full path to the text file with the input if this 
* is not in the same directory already.
* 
* 
* My Run Examples:
* 
* EXAMPLE 1:
* 1 imported box of chocolates: 10.50
* 1 imported bottle of perfume: 54.63
* Sales Taxes: 7.63
* Total: 65.13
* 
* EXAMPLE 2:
* 1 imported box of chocolates: 10.50
* 1 imported bottle of perfume: 54.65
* Sales Taxes: 7.65
* Total: 65.15
* 
* EXAMPLE 3:
* 1 imported bottle of perfume: 32.19
* 1 bottle of perfume: 20.89
* 1 packet of headache pills: 9.75
* 1 box of imported chocolates: 11.85
* Sales Taxes: 6.70
* Total: 74.68
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * The class SalesTaxesJava is used to create an SalesTaxesJava object that
 * holds information such as name, price, and quantity. This information is then
 * used to calculate sale taxes, and import taxes to be added to the bill.
 *
 * @version 1.02 08 Dec 2013
 * @author Rafael Rodriguez
 */

public class SalesTaxesJava {
    /*
     * The class SalesTaxesJava is defined with the three key pieces of
     * information: String name, and two Big decimals price, and quantity.
     */

    private String itemName;
    private BigDecimal originalPrice;
    private BigDecimal itemQuantity;
    private final BigDecimal importTaxRate = new BigDecimal("0.0500");
    private final BigDecimal saleTaxRate = new BigDecimal("0.1000");

    /**
     * Creating an array of strings for the categories of Books, Medicine, and
     * Food. This piece of code should be further changed to implement more
     * items until a more efficient way to identifying the category of a item is
     * implemented to the object.
     */

    private String[] exemptedItems = { "book", "chocolate", "pill" };

    /** This string is used to check whether a item is imported or not. */
    private String importedItem = "imported";

    public SalesTaxesJava(BigDecimal quantity, String name, BigDecimal price) {
        /** Setting up the object structure */
        this.itemName = name;
        this.originalPrice = price;
        this.itemQuantity = quantity;
    }

    public BigDecimal getQuantity() {
        return itemQuantity;
    }

    public String getName() {
        return itemName;
    }

    public BigDecimal getPrice() {
        return originalPrice;
    }

    /**
     * Method to calculate the sale tax of the current item. It returns the
     * price of the item with the sale tax included.
     */
    public BigDecimal saleTax() {
        /**
         * The initial sale tax will be zero. Then the code checks whether the
         * keywords specified on the string array "exemptedItems" appears on the
         * name of the item, if it does, then no sale tax is applied, otherwise
         * calculate the sale tax via a calculator method and return it.
         */

        BigDecimal saleTax = BigDecimal.ZERO;
        for (String exemptItem : exemptedItems) {
            if (this.getName()
                .contains(exemptItem)) {
                return saleTax;
            }
        }

        saleTax = calculateTax(saleTaxRate);
        return saleTax;
    }

    /**
     * Method to compute the amount of import taxes to pay. It returns the price
     * with the imported tax included.
     */
    public BigDecimal taxImport() {
        /**
         * The initial import tax will be zero. Then the code checks whether the
         * keyword specified on the string "importedItem" appears on the name of
         * the item, if it does, then it calls the method to calculate the tax
         * and returns it.
         */

        BigDecimal importTax = BigDecimal.ZERO;
        if (this.getName()
            .contains(importedItem)) {
            importTax = calculateTax(importTaxRate);
            return importTax;
        }
        return importTax;
    }

    /**
     * Method to calculate any tax rate with the result rounded up to the
     * nearest 0.05 value. it takes one parameter which is the tax rate to be
     * applied and return the price of the item with taxes applied.
     */
    public BigDecimal calculateTax(final BigDecimal taxType) {
        /*
         * The equation used is((price*quanityt*tax)*20)/20 For the rounding
         * method 1/20 = 0.05 which is the precision requested
         */
        BigDecimal taxedItem = this.getPrice()
            .multiply(getQuantity().multiply(taxType));
        taxedItem = taxedItem.multiply(new BigDecimal("20"))
            .setScale(0, RoundingMode.UP)
            .setScale(2);
        taxedItem = taxedItem.divide(new BigDecimal("20"), RoundingMode.UP);

        return taxedItem;
    }

    /**
     * The main method which should take the
     * 
     * @param args
     *            to be used for input of a text document with the data for the
     *            program to evaluate.
     */
    public static void main(String[] args) {
        /**
         * A Try-Catch is implemented to prevent the run of the code without
         * proper amount of arguments, in this case only one is required to
         * supply the file which should be in the same directory as the program,
         * or a full path should be provided with the name.
         * 
         * It will then proceed to create a list of SalesTaxesJava objects from
         * a text file, and calculate how much taxes each item should pay and
         * display the results on the screen. It used a custom method to create
         * a list of SalesTaxesJava objects from a text file.
         */

        try {
            String filename = args[0];
            List<SalesTaxesJava> items = makeItemList(filename);
            BigDecimal combinedItemTaxes = BigDecimal.ZERO;
            BigDecimal totalPrice = BigDecimal.ZERO;
            BigDecimal totalTaxes = BigDecimal.ZERO;

            for (int i = 0; i < items.size(); i++) {

                SalesTaxesJava one = items.get(i);
                BigDecimal salesTax = one.saleTax();
                BigDecimal importTaxes = one.taxImport();
                combinedItemTaxes = salesTax.add(importTaxes);
                totalTaxes = totalTaxes.add(combinedItemTaxes);
                totalPrice = totalPrice.add(combinedItemTaxes)
                    .add(one.getPrice());

                // This setup the desired output format for each item.
                System.out.println(one.getQuantity() + " " + one.getName() + ": " + (one.getPrice()
                    .add(combinedItemTaxes)));
            }
            System.out.println("Sales Taxes: " + totalTaxes);
            System.out.println("Total: " + totalPrice);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: Please enter the file name!");
        }
    }

    /**
     * This method takes a text file and read each line, from which its data is
     * broken down on as quantity-name, and price; which is then further divided
     * and used as quantity, name, and price to be converted into a
     * SalesTaxesJava object, and added to a list of SalesTaxesJava objects. It
     * takes only one parameter "filename" which is the name of the text file
     * where the data is, and returns lItem which is a list of SalesTaxesJava
     * objects.
     */
    private static List<SalesTaxesJava> makeItemList(String filename) {

        List<SalesTaxesJava> itemsList = new ArrayList<SalesTaxesJava>();

        // Try-catch is used to prevent errors due to missing file.
        try (BufferedReader lines = new BufferedReader(new FileReader(filename))) {
            String currentLine;

            while ((currentLine = lines.readLine()) != null) {
                // Divide the current line into quantity-name & price data.
                String[] splitedLine = currentLine.split(" at ");

                // Divide the quantity and name from the extracted data.
                String[] quantityAndName = splitedLine[0].split(" ", 2);
                String name = quantityAndName[1];
                BigDecimal quantity = new BigDecimal(quantityAndName[0]);
                BigDecimal price = new BigDecimal(splitedLine[1]);

                // Create the SalesTaxesJava object with corresponding data.
                SalesTaxesJava item = new SalesTaxesJava(quantity, name, price);
                itemsList.add(item);
            }
            lines.close();
        } catch (IOException e) {
            System.err.println("Error: file not found!");
        }
        return itemsList;
    }
}