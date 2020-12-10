package alabs.qsg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    public static String scannedData;


    Button scanBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Activity activity = this;
        scanBtn = (Button) findViewById(R.id.scan_btn);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
            }
        });
    }

    //
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            scannedData = result.getContents();
//            if (scannedData != null) {
//                // Here we need to handle scanned data...
////            new SendRequest().execute();
//
//
//            } else {
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//
//    public class SendRequest extends AsyncTask<String, Void, String> {
//
//
//        protected void onPreExecute(){}
//
//        protected String doInBackground(String... arg0) {
//
//            try{
//
//                //Enter script URL Here
//                URL url = new URL("https://script.google.com/macros/s/AKfycbz86qdzg2QLIq7dGi9Ociw1_ZQ-E8KOteIYWM1-rM9bzftL0HG6/exec");
//
//                JSONObject postDataParams = new JSONObject();
//
//                //int i;
//                //for(i=1;i<=70;i++)
//
//
//                //    String usn = Integer.toString(i);
//
//                //Passing scanned code as parameter
//
//                postDataParams.put("sdata",scannedData);
//
//
//                Log.e("params",postDataParams.toString());
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000 /* milliseconds */);
//                conn.setConnectTimeout(15000 /* milliseconds */);
//                conn.setRequestMethod("GET");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//
//                int responseCode=conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//
//                    BufferedReader in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    StringBuffer sb = new StringBuffer("");
//                    String line="";
//
//                    while((line = in.readLine()) != null) {
//
//                        sb.append(line);
//                        break;
//                    }
//
//                    in.close();
//                    return sb.toString();
//
//                }
//                else {
//                    return new String("false : "+responseCode+"\n Scanned Data not transfered\n "+"contact Santosh Singh");
//                }
//            }
//            catch(UnknownHostException e){
//                return new String("Unable to connect internet \n Please check your internet connection \n and Try again");
//            }
//            catch(Exception e){
//                return new String("Exception: " + e.getMessage());
//            }
//        }
//
//        @Override
//       protected void onPostExecute(String result) {
//            Toast.makeText(getApplicationContext(), result,
//                    Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(getApplicationContext(),AddItem.class);
//            startActivity(intent);
//
//        }
//    }

//    public String getPostDataString(JSONObject params) throws Exception {
//
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//
//        Iterator<String> itr = params.keys();
//
//        while(itr.hasNext()){
//
//            String key= itr.next();
//            Object value = params.get(key);
//
//            if (first)
//                first = false;
//            else
//                result.append("&");
//
//            result.append(URLEncoder.encode(key, "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//        }
//        return result.toString();
//    }
}
