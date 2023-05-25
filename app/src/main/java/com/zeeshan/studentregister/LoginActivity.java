package com.zeeshan.studentregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity
{
    String url2 = "https://fzeeshan786.000webhostapp.com/student_login.php";
    RequestQueue requestQueue;
    EditText e1,e2;
    Button btn;
    TextView textView, textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1 = findViewById(R.id.editTextTextPersonName4);
        e2 = findViewById(R.id.editTextPhone2);
        textView1 = findViewById(R.id.textView3);
        textView = findViewById(R.id.textView);
        requestQueue = Volley.newRequestQueue(this);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("please wait");
                pd.setCancelable(false);
                pd.show();

                String name = e1.getText().toString();
                String phone = e2.getText().toString();

                StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        if (name.isEmpty() || phone.isEmpty())
                        {
                            Toast.makeText(LoginActivity.this, "Please fill form", Toast.LENGTH_SHORT).show();
                        }
                       else if (response.equals("true"))
                        {
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        }
                        pd.dismiss();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(LoginActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                })
                    // init block
                {
                    // get parameter
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String,String> data = new HashMap<>();
                        data.put("name",e1.getText().toString());
                        data.put("phone",e2.getText().toString());

                        return data;
                    }
                };
                requestQueue.add(request);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}