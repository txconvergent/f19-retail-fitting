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
    //private EditText Edit;
    private TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_screen);

        checkout = (Button) findViewById(R.id.checkoutButton);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input  = ((EditText)findViewById(R.id.Room_Number_Input)).getText().toString();
                // use 'input' to clear customer info from server.
                Intent startIntent = new Intent(getApplicationContext(), ThankYou.class );
                startActivity(startIntent);
            }
        });
    }

}
