package com.webstart.teamup.activities.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.webstart.teamup.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    public void confirmPayment(View view) {
        Intent PaymentSuccessActivity = new Intent(this, PaymentSuccessActivity.class);
        startActivity(PaymentSuccessActivity);
    }
}