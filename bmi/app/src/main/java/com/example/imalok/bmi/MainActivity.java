package com.example.imalok.bmi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
{

    EditText name,weight,height;
    ToggleButton gender;
    CheckBox tc,toast,textView;
    TextView result;
    Button bmi;
    Button refresh;
    Button alok;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alok = (Button)findViewById(R.id.button4);
        alok.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                // set title
                alertDialogBuilder.setTitle("BMI");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Click Yes to Exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog,int id)
                                    {
                                        // if this button is clicked, close
                                        // current activity
                                        MainActivity.this.finish();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog,int id)
                                    {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
        calculate();
    }
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do You Want to Exit?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void calculate()
    {
        name = (EditText)findViewById(R.id.editText);
        weight = (EditText)findViewById(R.id.editText2);
        height = (EditText)findViewById(R.id.editText3);
        gender = (ToggleButton)findViewById(R.id.toggleButton);
        tc = (CheckBox)findViewById(R.id.checkBox);
        toast = (CheckBox)findViewById(R.id.checkBox2);
        textView = (CheckBox)findViewById(R.id.checkBox3);
        result = (TextView)findViewById(R.id.textView2);
        bmi = (Button)findViewById(R.id.button);
        refresh = (Button)findViewById(R.id.button2);




        bmi.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String a = name.getText().toString();
                String num1 = weight.getText().toString();
                String num2 = height.getText().toString();
                if ((num1.compareTo(".")!=0) && (num2.compareTo(".")!=0))
                {
                    if (a.length()>0 && num1.length()>0 && num2.length()>0)
                    {
                        double x = Double.parseDouble(num1);
                        double y = Double.parseDouble(num2);
                        double b, z;
                        b = ((y / 100) * (y / 100));
                        z = (x / b);
                        if (gender.getText().equals("ON"))
                        {
                            if (toast.isChecked() && textView.isChecked())
                            {
                                result.setText(a + ",(MALE)\nYour BMI is:" + z);
                                Toast.makeText(getApplicationContext(), a + ",(MALE)\nYour BMI is:" + z, Toast.LENGTH_SHORT).show();
                            }
                            if (!toast.isChecked() && textView.isChecked())
                            {
                                result.setText(a + ",(MALE)\nYour BMI is:" + z);
                            }
                            if (toast.isChecked() && !textView.isChecked())
                            {
                                result.setText("");
                                Toast.makeText(getApplicationContext(), a + ",(MALE)\nYour BMI is:" + z, Toast.LENGTH_SHORT).show();
                            }
                            if (!toast.isChecked() && !textView.isChecked())
                            {
                                Toast.makeText(getApplicationContext(), "Select at least 1 Checkbox", Toast.LENGTH_SHORT).show();
                            }
                        } else
                            {
                                if (toast.isChecked() && textView.isChecked())
                                {
                                    result.setText(a + ",(FEMALE)\nYour BMI is:" + z);
                                    Toast.makeText(getApplicationContext(), a + ",(FEMALE)\nYour BMI is:" + z, Toast.LENGTH_SHORT).show();
                                }
                                if (!toast.isChecked() && textView.isChecked())
                                {
                                    result.setText(a + ",(FEMALE)\nYour BMI is:" + z);
                                }
                                if (toast.isChecked() && (!textView.isChecked()))
                                {
                                    result.setText("");
                                    Toast.makeText(getApplicationContext(), a + ",(FEMALE)\nYour BMI is:" + z, Toast.LENGTH_SHORT).show();
                                }
                                if (!toast.isChecked() && !textView.isChecked())
                                {
                                    Toast.makeText(getApplicationContext(), "Select at least 1 Checkbox", Toast.LENGTH_SHORT).show();
                                }
                            }

                    }
                    else
                    {
                    Toast.makeText(getApplicationContext(),"Input All Fields",Toast.LENGTH_SHORT).show();
                    }
            }
            else
                {
                    Toast.makeText(getApplicationContext(),"Input Correct Input",Toast.LENGTH_SHORT).show();
                }
            }
        });
        tc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (tc.isChecked())
                {
                    bmi.setEnabled(true);
                }
                else
                {
                    bmi.setEnabled(false);
                }
            }
        });

        refresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)

            {
                name.setText("");
                weight.setText("");
                height.setText("");
                result.setText("");
                toast.setChecked(false);
                textView.setChecked(false);
                tc.setChecked(false);
                gender.setChecked(false);
            }
        });

    }

}
