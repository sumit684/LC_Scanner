package alabs.qsg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddItem extends AppCompatActivity implements View.OnClickListener {


    EditText editTextPayoffDrumNumber, editTextPayoffDrumTareWeight, editTextPayoffDrumType, editTextPayoffDrumSize;
    EditText editTextMachineName, editTextMachineId, editTextMachineLocation;
    EditText editTextTakeUpDrumNumber, editTextTakeUpDrumType, editTextTakeUpDrumTareWeight, editTextTakeUpDrumSize;
    Button buttonAddItem;
    TextView textView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_item);
//        textView4 = (TextView)findViewById(R.id.textView2);
//        textView4.setText(MainActivity.scannedData);
        String json = MainActivity.scannedData;
        try {
            JSONObject dataobj = new JSONObject(json);
            editTextPayoffDrumNumber = (EditText) findViewById(R.id.payoff_drum_number);
            editTextPayoffDrumType = (EditText) findViewById(R.id.payoff_drum_type);
            editTextPayoffDrumTareWeight = (EditText) findViewById(R.id.payoff_drum_tare_weight);
            editTextPayoffDrumSize = (EditText) findViewById(R.id.payoff_drum_size);

            editTextMachineName = (EditText) findViewById(R.id.machine_name);
            editTextMachineId = (EditText) findViewById(R.id.machine_id);
            editTextMachineLocation = (EditText) findViewById(R.id.machine_location);

            editTextTakeUpDrumNumber = (EditText) findViewById(R.id.takeup_drum_number);
            editTextTakeUpDrumType = (EditText) findViewById(R.id.takeup_drum_type);
            editTextTakeUpDrumTareWeight = (EditText) findViewById(R.id.takeup_drum_tare_weight);
            editTextTakeUpDrumSize = (EditText) findViewById(R.id.takeup_drum_size);

            //here we pass params PAYOFF_DRUM_NUMBER, PAYOFF_DRUM_TYPE, PAYOFF_TARE_WEIGHT, PAYOFF_DRUM_SIZE, MACHINE_NAME, MACHINE_ID, MACHINE_LOCATION,
            //    TAKEUP_DRUM_NUMBER, TAKEUP_DRUM_TYPE, TAKEUP_TARE_WEIGHT, TAKEUP_DRUM_SIZE
            editTextPayoffDrumNumber.setText(dataobj.has("PAYOFF_DRUM_NUMBER")?dataobj.getString("PAYOFF_DRUM_NUMBER"):null);
            editTextPayoffDrumType.setText(dataobj.has("PAYOFF_DRUM_TYPE")?dataobj.getString("PAYOFF_DRUM_TYPE"):null);
            editTextPayoffDrumTareWeight.setText(dataobj.has("PAYOFF_TARE_WEIGHT")?dataobj.getString("PAYOFF_TARE_WEIGHT"):null);
            editTextPayoffDrumSize.setText(dataobj.has("PAYOFF_DRUM_SIZE")?dataobj.getString("PAYOFF_DRUM_SIZE"):null);
            editTextMachineName.setText(dataobj.has("MACHINE_NAME")?dataobj.getString("MACHINE_NAME"):null);
            editTextMachineId.setText(dataobj.has("MACHINE_ID")?dataobj.getString("MACHINE_ID"):null);
            editTextMachineLocation.setText(dataobj.has("MACHINE_LOCATION")?dataobj.getString("MACHINE_LOCATION"):null);
            editTextTakeUpDrumNumber.setText(dataobj.has("TAKEUP_DRUM_NUMBER")?dataobj.getString("TAKEUP_DRUM_NUMBER"):null);
            editTextTakeUpDrumType.setText(dataobj.has("TAKEUP_DRUM_TYPE")?dataobj.getString("TAKEUP_DRUM_TYPE"):null);
            editTextTakeUpDrumTareWeight.setText(dataobj.has("TAKEUP_TARE_WEIGHT")?dataobj.getString("TAKEUP_TARE_WEIGHT"):null);
            editTextTakeUpDrumSize.setText(dataobj.has("TAKEUP_DRUM_SIZE")?dataobj.getString("TAKEUP_DRUM_SIZE"):null);
            buttonAddItem = (Button) findViewById(R.id.btn_add_item);
            buttonAddItem.setOnClickListener(this);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(AddItem.this,"Invalid Form Input try again",Toast.LENGTH_LONG).show();
        }

    }

    //This is the part where data is transafeered from Your Android phone to Sheet by using HTTP Rest API calls

    private void addItemToSheet() {

        final ProgressDialog loading = ProgressDialog.show(this, "Sending Data to Server", "Please wait");
        final String payoffDrumNumber = editTextPayoffDrumNumber.getText().toString().trim();
        final String payoffDrumType = editTextPayoffDrumType.getText().toString().trim();
        final String payoffDrumTareWeight = editTextPayoffDrumTareWeight.getText().toString().trim();
        final String payoffDrumSize = editTextPayoffDrumSize.getText().toString().trim();
        final String machineName = editTextMachineName.getText().toString().trim();
        final String machineId = editTextMachineId.getText().toString().trim();
        final String machineLocation = editTextMachineLocation.getText().toString().trim();
        final String takeUpDrumNumber = editTextTakeUpDrumNumber.getText().toString().trim();
        final String takeUpDrumType = editTextTakeUpDrumType.getText().toString().trim();
        final String takeUpDrumTareWeight = editTextTakeUpDrumTareWeight.getText().toString().trim();
        final String takeUpDrumSize = editTextTakeUpDrumSize.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyCTx-rL0DOqBPdKY8a1wSBBenCrkFZR-gclIvfuhqhPFj9A4T_/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(AddItem.this, response, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

                //here we pass params PAYOFF_DRUM_NUMBER, PAYOFF_DRUM_TYPE, PAYOFF_TARE_WEIGHT, PAYOFF_DRUM_SIZE, MACHINE_NAME, MACHINE_ID, MACHINE_LOCATION,
                //    TAKEUP_DRUM_NUMBER, TAKEUP_DRUM_TYPE, TAKEUP_TARE_WEIGHT, TAKEUP_DRUM_SIZE
                parmas.put("PAYOFF_DRUM_NUMBER", payoffDrumNumber);
                parmas.put("PAYOFF_DRUM_TYPE", payoffDrumType);
                parmas.put("PAYOFF_TARE_WEIGHT", payoffDrumTareWeight);
                parmas.put("PAYOFF_DRUM_SIZE", payoffDrumSize);

                parmas.put("MACHINE_NAME", machineName);
                parmas.put("MACHINE_ID", machineId);
                parmas.put("MACHINE_LOCATION", machineLocation);

                parmas.put("TAKEUP_DRUM_NUMBER", takeUpDrumNumber);
                parmas.put("TAKEUP_DRUM_TYPE", takeUpDrumType);
                parmas.put("TAKEUP_TARE_WEIGHT", takeUpDrumTareWeight);
                parmas.put("TAKEUP_DRUM_SIZE", takeUpDrumSize);
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

        if (v == buttonAddItem) {
            addItemToSheet();

            //Define what to do when button is clicked
        }
    }
}
