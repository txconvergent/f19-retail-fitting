package edu.utexas.quickfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckOutScreen extends AppCompatActivity {

    private Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_screen);

        checkout = (Button) findViewById(R.id.checkoutButton);
        checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                openThankYou();
            }
        });
    }

    public void openThankYou(){
        Intent intent = new Intent(this, ThankYou.class);
        startActivity(intent);
    }
}