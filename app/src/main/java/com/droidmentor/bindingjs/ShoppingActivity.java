package com.droidmentor.bindingjs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ShoppingActivity extends AppCompatActivity {

    Button btnPlaceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        btnPlaceOrder=(Button)findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ordersIntent=new Intent(ShoppingActivity.this,PaymentActivity.class);
                startActivity(ordersIntent);
            }
        });
    }
}
