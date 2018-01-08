package com.cyberbrainz.sage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mprofession, mcustomer;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       mprofession = (Button) findViewById(R.id.profession);
       mcustomer = (Button) findViewById(R.id.customer);

       mprofession.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, ProfessionLoginActivity.class);
               startActivity(intent);
               finish();
               return;

           }
       });
        mcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomerLoginActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });
    }
}
