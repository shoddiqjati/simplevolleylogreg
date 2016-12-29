package com.example.shodd.logreg;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameET, usernameET, passET, ageET;
    private Button loginBT, regBT;

    private Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameET = (EditText) findViewById(R.id.register_name_ET);
        usernameET = (EditText) findViewById(R.id.register_username_ET);
        passET = (EditText) findViewById(R.id.register_password_ET);
        ageET = (EditText) findViewById(R.id.register_age_ET);
        loginBT = (Button) findViewById(R.id.register_loginBT);
        regBT = (Button) findViewById(R.id.register_regBT);

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        regBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = nameET.getText().toString();
                final String username = usernameET.getText().toString();
                final int age = Integer.parseInt(ageET.getText().toString());
                final String pass = passET.getText().toString();

                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(mContext, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setMessage("Registration Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, age, pass, listener);
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                requestQueue.add(registerRequest);
            }
        });
    }
}
