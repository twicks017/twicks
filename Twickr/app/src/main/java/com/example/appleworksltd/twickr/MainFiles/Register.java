package com.example.appleworksltd.twickr.MainFiles;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appleworksltd.twickr.MysqlDatabase.Sender;
import com.example.appleworksltd.twickr.R;

public class Register extends AppCompatActivity {

    EditText fnameTxt,lnameTxt,usernameTxt,passwordTxt,confPassTxt,phoneTxt,locationTxt;
    //String urlAddress = "https://twickr.000webhostapp.com/twickr/register.php";
    String urlAddress = "http://192.168.43.168/Tickite/register.php";
    CoordinatorLayout coordinator_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fnameTxt = (EditText)findViewById(R.id.fname);
        lnameTxt = (EditText) findViewById(R.id.lname);
        usernameTxt = (EditText) findViewById(R.id.username);
        passwordTxt = (EditText) findViewById(R.id.password);
        confPassTxt = (EditText) findViewById(R.id.confirmPass);

        phoneTxt = (EditText) findViewById(R.id.phone);
        locationTxt = (EditText) findViewById(R.id.location);
        coordinator_layout = (CoordinatorLayout)findViewById(R.id.coordinator_layout);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pass = passwordTxt.getText().toString();
                String cPass = confPassTxt.getText().toString();

                if (pass.equals(cPass)) {
                    Sender r = new Sender(Register.this, urlAddress, coordinator_layout, fnameTxt, lnameTxt, usernameTxt, passwordTxt, confPassTxt, phoneTxt, locationTxt);
                    r.execute();

                }else{

                    Snackbar snackbar = Snackbar
                            .make(coordinator_layout,"Password doesn't match " + cPass +" " +pass, Snackbar.LENGTH_LONG)
                                                                                                                                                               .setAction("Please RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });

// Changing message text color
                    snackbar.setActionTextColor(Color.RED);

// Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();

                }

                //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplication());


            }
        });
    }

}
