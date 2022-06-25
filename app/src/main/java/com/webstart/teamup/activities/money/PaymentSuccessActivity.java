package com.webstart.teamup.activities.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.webstart.teamup.activities.HomeActivity;

public class PaymentSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.webstart.teamup.R.layout.activity_payment_success);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent HomeActivity = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(HomeActivity);
                finish();
            }
        }, 3000);
    }
}