package com.tollywood2bollywood.t2bliveapp;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import customfonts.MyEditText;


public class ContactFragment extends Fragment {
    MyEditText edit_first_name,edit_last_name,number_phone,subject_mail,email_gmail,discripition_;
    Button btn;

    public ContactFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contact, container, false);
        edit_first_name = (MyEditText) view.findViewById(R.id.edit_first_name);
        edit_last_name = (MyEditText) view.findViewById(R.id.edit_last_name);
        number_phone = (MyEditText) view.findViewById(R.id.number_phone);
        subject_mail = (MyEditText) view.findViewById(R.id.subject_mail);
        email_gmail = (MyEditText) view.findViewById(R.id.email_gmail);
        discripition_= (MyEditText) view.findViewById(R.id.discripition_);
        btn= (Button) view.findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_first_name.getText().toString().trim().equals("") || edit_first_name.getText().toString().trim().equals("null")){
                    operDailog("Please Enter First Name");
                }else if(edit_last_name.getText().toString().trim().equals("") || edit_last_name.getText().toString().trim().equals("null")){
                    operDailog("Please Enter Last Name");
                }else if(number_phone.getText().toString().trim().equals("") || number_phone.getText().toString().trim().equals("null")){
                    operDailog("Please Enter Phone Number");
                }else if(subject_mail.getText().toString().trim().equals("") || subject_mail.getText().toString().trim().equals("null")){
                    operDailog("Please Enter Subject");
                }else if(email_gmail.getText().toString().trim().equals("") || email_gmail.getText().toString().trim().equals("null")){
                    operDailog("Please Enter Emailid");
                }else if(!isValidEmail(email_gmail.getText().toString())){
                    operDailog("Invalid Emailid");
                }else if(discripition_.getText().toString().trim().equals("") || discripition_.getText().toString().trim().equals("null")){
                    operDailog("Please Enter Reason of contact");
                }else {
                    sendMessage();
                }
            }
        });
        return view ;
    }
    private void sendMessage() {
        Log.i("Send email", "");
        String[] TO = {"admin@tollywood2bollywood.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject_mail.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Name :"+edit_first_name.getText().toString() +" "+edit_last_name.getText().toString()
                +"\n"+"Contact No : "+number_phone.getText().toString()
                +"\nEmail Id : "+email_gmail.getText().toString()
                +"\nReason To Contact : "+discripition_.getText().toString());
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Finished send email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    public final static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void operDailog(String msg){
        /*AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);
        builder.setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        //alert.setTitle("Alert");
        alert.show();*/


        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.simple_message_dailog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView textMsg = (TextView) dialog.findViewById(R.id.textMsg);
        textMsg.setText(msg);
        TextView ok = (TextView) dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
