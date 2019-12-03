package edu.utexas.quickfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckOutScreen extends AppCompatActivity {

    private Button checkout;
    private CustomerDB customerDB;

    //
    EditText roomNum;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_screen);

        //
        roomNum = findViewById(R.id.Room_Number_Input);
        //


        checkout = (Button) findViewById(R.id.checkoutButton);
        customerDB = CustomerDB.getInstance();
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                String room = roomNum.getText().toString();
                if(room.length() > 0) {
                    //
                    int roomNum = Integer.parseInt(room);
                    Person nextCustomer = customerDB.checkout(roomNum);
                    if (nextCustomer != null) {
                        if (customerDB.checkin(nextCustomer) != -1) {
                            // send text to next customer that their room is ready.
                        }
                    }
                    Toast.makeText(CheckOutScreen.this, "Thank you for using QuickFit!", Toast.LENGTH_LONG * 3).show();
                    ((TextView)findViewById(R.id.Room_Number_Input)).setText("");
                    //
                }
                //
            }
        });
    }

}
