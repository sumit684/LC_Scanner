package alabs.qsg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import android.widget.TextView;

public class AddItem extends AppCompatActivity implements View.OnClickListener {


    EditText editTextItemName,editTextBrand;
    Button buttonAddItem;
    TextView textView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_item);
//        textView4 = (TextView)findViewById(R.id.textView2);
//        textView4.setText(MainActivity.scannedData);
        editTextItemName = (EditText)findViewById(R.id.et_item_name);
        editTextBrand = (EditText)findViewById(R.id.et_brand);
        editTextItemName.setText(MainActivity.scannedData);
        buttonAddItem = (Button)findViewById(R.id.btn_add_item);
        buttonAddItem.setOnClickListener(this);


    }

    //This is the part where data is transafeered from Your Android phone to Sheet by using HTTP Rest API calls

    private void   addItemToSheet() {

        final ProgressDialog loading = ProgressDialog.show(this,"Sending Data to Server","Please wait");
        final String name = editTextItemName.getText().toString().trim();
        final String brand = editTextBrand.getText().toString().trim();




        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyCTx-rL0DOqBPdKY8a1wSBBenCrkFZR-gclIvfuhqhPFj9A4T_/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(AddItem.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params PAYOFF_DRUM_NUMBER, PAYOFF_DRUM_TYPE, PAYOFF_TARE_WEIGHT, PAYOFF_DRUM_SIZE,
                parmas.put("PAYOFF_DRUM_NUMBER","12251");
                parmas.put("PAYOFF_DRUM_TYPE","Iron");
                parmas.put("PAYOFF_TARE_WEIGHT","500 Kg");
                parmas.put("PAYOFF_DRUM_SIZE","12C X 1.5 SQ. MM.");

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }




    @Override
    public void onClick(View v) {

        if(v==buttonAddItem){
            addItemToSheet();

            //Define what to do when button is clicked
        }
    }
}
