package com.example.bishoppc.apppdfread;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class DrawActivity extends AppCompatActivity {

    private TouchEventView touchEventView;
    private Button btnSig;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        touchEventView = (TouchEventView)findViewById(R.id.canvas);

        btnSig = (Button)findViewById(R.id.btnACrear);

        btnSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawActivity.this ,  CreateActivity.class);
                startActivity(intent);
            }
        });
    }


}
