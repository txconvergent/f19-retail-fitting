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

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 0;

    EditText number;
    String message;
    String phoneNo;

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
//                if(phoneOrEmail.isChecked()){
//                    //send Email method
//                }
//                if(!phoneOrEmail.isChecked()) {

                phoneNo = findViewById(R.id.inputNumber).toString();
                message = "Thank you for checking in";
                sendSMS();
                }
//            }
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
        EditText userInfoText = findViewById(R.id.inputNumber);
        String userInfo = userInfoText.getText().toString();
        return userInfo;
    }

    private void sendSMS(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        SEND_SMS_PERMISSION_REQUEST_CODE);
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }


}

