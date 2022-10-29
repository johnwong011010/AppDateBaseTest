package com.example.datebasetest;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String URL_booking = "http://192.168.42.120/AppApiTest/ApiTest.php";//這邊請填入Api的網址
    //如果是localhost的話，請記得把localhost換成ip位址
    List<Booking> bookingList; //宣告一個Booking型態的List
    TextView user,Tel,date,booking_date; //宣告有四個TextView
    Button btn; //宣告有一個按鍵

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //顯示哪一個Layout

        user = findViewById(R.id.user); //綁定元件
        Tel = findViewById(R.id.Tel);//綁定元件
        date = findViewById(R.id.date);//綁定元件
        booking_date = findViewById(R.id.booking_date);//綁定元件
        btn = findViewById(R.id.button);//綁定元件
        bookingList = new ArrayList<Booking>(); //new一個Booking型態的ArrayList

        btn.setOnClickListener(new View.OnClickListener() { //設置一個監聽器在按鍵
            @Override
            public void onClick(View view) { //當這個按鍵被按下時所執行的動作
                //new DBhelper();
                loadBooking();
                Log.d("133", "onClick: jj");
            }
        });
    }

    private void loadBooking() {
        RequestQueue requestQueue = Volley.newRequestQueue(this); //創建一個請求的queue
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_booking, new Response.Listener<String>() { //用字串來進行請求
            @Override
            public void onResponse(String response) { //當有回應時
                Log.d("111", "onResponse: 123");
                try {
                    JSONArray jsonArray = new JSONArray(response); //建立一個json的array來存放資料
                    System.out.println("e123");
                    JSONObject obj; //用來把回應裡面的資料取出
                    for (int i=0;i<jsonArray.length();i++){
                        obj = jsonArray.getJSONObject(i); //取得array的資料
                        bookingList.add(new Booking(obj.getString("userID"),
                                obj.getString("TelNum"),
                                obj.get("Date").toString(),
                                obj.get("booking_date").toString())); //將資料新增到Booking型態的List
                    }
                    Log.d("1922", "onResponse: "+bookingList.get(1).getBooking_date());
                    //可使用recyclerView來進行資料的顯示
                    user.setText(bookingList.get(1).getUserID()); //印出userID
                    Tel.setText(bookingList.get(1).getTelephone().toString()); //印出user的電話
                    date.setText(bookingList.get(1).getDate().toString()); //印出user的哪一天進行訂位
                    booking_date.setText(bookingList.get(1).getBooking_date().toString()); //印出user訂了哪一天的位置
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley",error.toString());
            }
        });
        requestQueue.add(stringRequest);

    }
}