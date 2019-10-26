package edu.utexas.quickfit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button checkInButton = findViewById(R.id.checkinBtn);
        final Switch phoneOrEmail = findViewById(R.id.phoneOrEmail);

        //Updates input boxes for user choice of phone number or email
        phoneOrEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((TextView)findViewById(R.id.phoneEmailTitle)).setText("Email Address:");
                    ((TextView)findViewById(R.id.phoneEmailEditText)).setHint("johndoe@email.com");
                } else {
                    ((TextView)findViewById(R.id.phoneEmailTitle)).setText("Phone Number:");
                    ((TextView)findViewById(R.id.phoneEmailEditText)).setHint("(XXX) XXX-XXXX");
                }
            }
        });

        //Runs check in queue process
        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //We will add send text message/email methods here
            public void onClick(View v){
                if(phoneOrEmail.isChecked()){
                    //send Email method
                }
                if(phoneOrEmail.isChecked()) {
                    //send phone message method
                }
            }
        });
    }

    //Gets name inputted in name text box, if nothing is entered, returns "Customer"
    private String getName(){
        EditText nameText = findViewById(R.id.nameEditText);
        String name = nameText.getText().toString();
        if(name.equals(""))
            name = "Customer";
        return name;
    }

    private String getUserInfo() {
        EditText userInfoText = findViewById(R.id.phoneEmailEditText);
        String userInfo = userInfoText.getText().toString();
        return userInfo;
    }

}

