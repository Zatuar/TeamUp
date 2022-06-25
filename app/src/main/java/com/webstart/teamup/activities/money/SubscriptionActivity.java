package com.webstart.teamup.activities.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;

import com.webstart.teamup.R;

public class SubscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbuscription);
    }


    public void showFullSubscriptionDetails(View view) {
        findViewById(R.id.subscriptionFullDetails).setVisibility(View.VISIBLE);
    }

    public void hideSubscriptionFullDetails(View view) {
        findViewById(R.id.subscriptionFullDetails).setVisibility(View.INVISIBLE);
    }

    public void goToPaymentPage(View view) {
        Intent paymentActivity =new Intent(this, PaymentActivity.class);
        startActivity(paymentActivity);
    }
}