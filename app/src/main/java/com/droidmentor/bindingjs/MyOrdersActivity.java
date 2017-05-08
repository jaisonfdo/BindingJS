package com.droidmentor.bindingjs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyOrdersActivity extends AppCompatActivity {

    Button btnTrack;
    TextView tvShopping;
    TextView tvOrderID;

    int orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        btnTrack=(Button)findViewById(R.id.btnTrack);
        tvShopping=(TextView)findViewById(R.id.tvSHopping);
        tvOrderID=(TextView)findViewById(R.id.tvOrderID);

        if(getIntent().getExtras()!=null)
        {
            orderid=getIntent().getExtras().getInt("order",12345);
        }

        if(orderid>0)
            tvOrderID.setText("Order ID : "+orderid);

        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyOrdersActivity.this," Track your order",Toast.LENGTH_SHORT).show();
            }
        });

        tvShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ordersIntent=new Intent(MyOrdersActivity.this,ShoppingActivity.class);
                startActivity(ordersIntent);
                finish();

            }
        });
    }
}
