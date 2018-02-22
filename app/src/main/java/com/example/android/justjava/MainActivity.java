/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates the price of the order.
     *
     * return @price
     *
     */
    private int calculatePrice() {
        int priceCoffee = 5;
        return quantity * priceCoffee;
    }

    /**
     * Form the string for the order message.
     *
     * return a string
     *
     */
    private String createOrderSummary(String userName, int price, boolean hasWhipp, boolean hasChoco) {
        String priceMessage = "Name: " + userName;
        priceMessage += "\nAdd whipped cream? " + hasWhipp;
        priceMessage += "\nAdd Chocolate? " + hasChoco;
        priceMessage +=  "\nQuantity: " + quantity;
        priceMessage +=  "\nTotal $ " + price + "\nThank you!";
        return priceMessage;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view){

        EditText userName = (EditText) findViewById(R.id.user_name_view);
        String stringUserName = userName.getText().toString();

        CheckBox whippCream = (CheckBox) findViewById(R.id.cream_checkbox);
        boolean hasWhipp = whippCream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChoco = chocolate.isChecked();

        int price = calculatePrice();
        displayMessage(createOrderSummary(stringUserName, price, hasWhipp, hasChoco));
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
