package edu.utexas.quickfit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    EditText number;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button checkInButton = findViewById(R.id.checkMeInButton);
        final Switch phoneOrEmail = findViewById(R.id.phoneOrEmail);

        //Updates input boxes for user choice of phone number or email
        phoneOrEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((TextView)findViewById(R.id.phoneEmailTitle)).setText("Email Address:");
                    ((TextView)findViewById(R.id.inputNumber)).setHint("johndoe@email.com");
                } else {
                    ((TextView)findViewById(R.id.phoneEmailTitle)).setText("Phone Number:");
                    ((TextView)findViewById(R.id.inputNumber)).setHint("(XXX) XXX-XXXX");
                }
            }
        });

        //Runs check in queue process
        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //We will add send text message/email methods here
            public void onClick(View v){
                if(phoneOrEmail.isChecked()){
                    sendMail();
                }
                if(!phoneOrEmail.isChecked()) {
                    sendText();
                }
            }
        });

    }

    private void sendMail() {
        String email = getUserInfo();
        String message = getName() + ", here is your room!";
        String subject = "Fitting Room Queue Confirmation";

        //sends Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, email, subject, message);

        javaMailAPI.execute();
    }

    private void sendText(){
        number = findViewById(R.id.inputNumber);
        String phoneNumber = number.toString();
        message = "Thank you for checking in";
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);

        } catch(Exception e){
            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
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
        EditText userInfoText = findViewById(R.id.inputNumber);
        String userInfo = userInfoText.getText().toString();
        return userInfo;
    }

}

