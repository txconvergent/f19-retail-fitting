package edu.utexas.quickfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CheckOutScreen extends AppCompatActivity {

    private Button checkout;
    private CustomerDB customerDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_screen);

        checkout = (Button) findViewById(R.id.checkoutButton);
        customerDB = CustomerDB.getInstance();
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int roomNum  = Integer.parseInt(((EditText)findViewById(R.id.Room_Number_Input)).getText().toString());
                Person nextCustomer = customerDB.checkout(roomNum);
                if(nextCustomer != null) {
                    if(customerDB.checkin(nextCustomer) != -1){
                        // send text to next customer that their room is ready.
                    }
                }
                Intent startIntent = new Intent(getApplicationContext(), ThankYou.class );
                startActivity(startIntent);
            }
        });
    }

}
