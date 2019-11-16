package edu.utexas.quickfit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    TextView placeTxt;
    Set<String> contactInfoSet = new HashSet<>();
    ArrayList<String> peopleOrderedList = new ArrayList<>();

    int position = 0;

    EditText number;
    String message = "Thank you for checking in";
    String phoneNo;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        placeTxt = findViewById(R.id.placeTxt);
        number = findViewById(R.id.inputPhoneNumber);

        final Button checkInButton = findViewById(R.id.checkMeInButton);
        final Switch phoneOrEmail = findViewById(R.id.phoneOrEmail);

        //Updates input boxes for user choice of phone number or email
        phoneOrEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ((TextView)findViewById(R.id.phoneEmailTitle)).setText("Email Address:");
                    ((TextView)findViewById(R.id.inputPhoneNumber)).setHint("johndoe@email.com");
                } else {
                    ((TextView)findViewById(R.id.phoneEmailTitle)).setText("Phone Number:");
                    ((TextView)findViewById(R.id.inputPhoneNumber)).setHint("(XXX) XXX-XXXX");
                }
            }
        });

        //Runs check in queue process
        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            //We will add send text message/email methods here
            public void onClick(View v) {
                if (phoneOrEmail.isChecked()) {
                    sendMail();
                }
                if (!phoneOrEmail.isChecked()) {
                    sendText();
                }

                position++;
                String phoneNum =  number.getText().toString();

                if (contactInfoSet.add(phoneNum)) {
                    peopleOrderedList.add(phoneNum);

                    Toast.makeText(MainActivity.this, "You are number " + position + " in line!", Toast.LENGTH_LONG * 3).show();
                } else{
                    Toast.makeText(MainActivity.this, "You have already been checked in!", Toast.LENGTH_LONG * 3).show();
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
        //sets the phone number to send the message to

        phoneNo = number.getText().toString();

        // turn on permissions
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
        // send text if permissions enabled
        else{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
        }

    }

    // called when SMS permissions are enabled
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // sends the SMS with SMSManager API
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                } else {
                }
            }
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
        EditText userInfoText = findViewById(R.id.inputPhoneNumber);
        String userInfo = userInfoText.getText().toString();
        return userInfo;
    }

}

