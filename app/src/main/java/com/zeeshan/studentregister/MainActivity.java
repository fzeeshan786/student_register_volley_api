package com.zeeshan.studentregister;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity
{
    String url = "https://fzeeshan786.000webhostapp.com/student_register.php";
    RequestQueue requestQueue;
    EditText e1,e2,e3,e4;
    Button btn;

    TextView textViewbutton, textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.editTextTextPersonName);
        e2 = findViewById(R.id.editTextTextPersonName2);
        e3 = findViewById(R.id.editTextPhone);
        e4 = findViewById(R.id.editTextTextPersonName3);
        //btn = findViewById(R.id.button);

        textViewbutton = findViewById(R.id.textView4);
        textView = findViewById(R.id.textView7);

        requestQueue = Volley.newRequestQueue(this);

        textViewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ProgressDialog pd = new ProgressDialog(MainActivity.this);
                pd.setMessage("Please Wait");
                pd.setCanceledOnTouchOutside(false);
                pd.show();

                String name = e1.getText().toString();
                String clg = e2.getText().toString();
                String phone = e3.getText().toString();
                String address = e4.getText().toString();

                // String k format me
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        if (name.isEmpty() || clg.isEmpty() || phone.isEmpty() || address.isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "please fill form", Toast.LENGTH_SHORT).show();
                        }

                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                })
                    // init block
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String, String> data = new HashMap<String,String>();
                        data.put("name",e1.getText().toString());
                        data.put("collegename",e2.getText().toString());
                        data.put("phone",e3.getText().toString());
                        data.put("address",e4.getText().toString());
                        return data;
                    }
                };
                requestQueue.add(request);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}