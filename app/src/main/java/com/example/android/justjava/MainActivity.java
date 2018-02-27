/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
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
    private int calculatePrice(boolean addWhipp, boolean addChoco) {
        int priceCoffee = 5;
        if (addWhipp){
            priceCoffee += 1;
        }

        if (addChoco){
            priceCoffee += 2;
        }
        return quantity * priceCoffee;
    }

    /**
     * Form the string for the order message.
     *
     * return a string
     *
     */
    private String createOrderSummary(String userName, int price, boolean hasWhipp, boolean hasChoco) {
        String priceMessage = getString(R.string.order_summary_name, userName);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, hasWhipp);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, hasChoco);
        priceMessage +=  "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage +=  "\n" + getString(R.string.total) + price + "\n" + getString(R.string.thank_you);
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

        int price = calculatePrice(hasWhipp, hasChoco);
        String priceMessage = createOrderSummary(stringUserName, price, hasWhipp, hasChoco);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + stringUserName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity >= 100){
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1){
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
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

}
