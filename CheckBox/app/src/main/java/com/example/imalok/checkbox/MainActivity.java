package com.example.imalok.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox a,b,c,acc;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        order();
    }
    public void order(){
        a = (CheckBox)findViewById(R.id.checkBox);
        b = (CheckBox)findViewById(R.id.checkBox2);
        c = (CheckBox)findViewById(R.id.checkBox3);
        acc = (CheckBox)findViewById(R.id.checkBox4);
        b1 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder s = new StringBuilder();
                s.append("Order List");
                int amt = 0;
                if(a.isChecked()){
                    s.append("\nPizza Rs 150");
                    amt+=150;
                }
                if(b.isChecked()){
                    s.append("\nCoffee Rs 50");
                    amt+=50;
                }
                if(c.isChecked()){
                    s.append("\nMomos Rs 70");
                    amt+=70;
                }
                s.append("\nTotal Amount="+amt);
                Toast.makeText(getApplicationContext(),s.toString(),Toast.LENGTH_LONG).show();


            }
        });
        acc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(acc.isChecked()){
                    b1.setEnabled(true);
                }
                else{
                    b1.setEnabled(false);
                }
            }
        });

    }
}
