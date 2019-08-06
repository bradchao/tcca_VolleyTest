package tw.org.tcca.app.volleytest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private MainApp mainApp;
    private TextView mesg;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainApp = (MainApp)getApplication();

        mesg = findViewById(R.id.mesg);
        img = findViewById(R.id.img);

    }

    public void getTest1(View view) {

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://www.tcca.org.tw",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("brad", response);
                    }
                },
                null
        );

        mainApp.queue.add(stringRequest);

    }

    public void getTest2(View view) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                "http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("brad", response);
                        parseJSON(response);
                    }
                },
                null
        );

        mainApp.queue.add(stringRequest);

    }

    private void parseJSON(String json){
        try{
            JSONArray root = new JSONArray(json);
            mesg.setText("");
            for (int i=0; i<root.length(); i++){
                JSONObject row = root.getJSONObject(i);
                String name = row.getString("Name");
                String org = row.getString("ProduceOrg");
                String line = name + " : " + org + "\n";
                mesg.append(line);
            }
        }catch (Exception e){
            Log.v("brad", e.toString());
        }

    }

    public void postTest1(View view) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "https://www.bradchao.com/autumn/addCust.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("brad", response);
                        //parseJSON(response);
                    }
                },
                null
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("cname", "test1234");
                params.put("tel", "123456789");
                params.put("addr", "test1234");

                return params;
            }
        };

        mainApp.queue.add(stringRequest);
    }
    public void postTest2(View view) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                "https://www.bradchao.com/autumn/fetchCust.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("brad", response);
                        //parseJSON(response);
                    }
                },
                null
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("t", "a");

                return params;
            }
        };

        mainApp.queue.add(stringRequest);
    }

    public void imageTest(View view) {
        ImageRequest request = new ImageRequest("https://s2.yimg.com/uu/api/res/1.2/a.SONd_J4TeE.XJ7SlBw.g--~B/Zmk9dWxjcm9wO2N3PTMxMTtkeD0xMTE7Y2g9MTg3O2R5PTA7dz0zOTI7aD0zMDg7Y3I9MTthcHBpZD15dGFjaHlvbg--/https://media.zenfs.com/zh-tw/setn.com.tw/cf9b4713d5c30bda11e5f83fd9a1bd34",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                    }
                },
                0,0,
                Bitmap.Config.ARGB_8888, null);
        mainApp.queue.add(request);
    }
}
