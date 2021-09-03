package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText etQuery;
    SwipeRefreshLayout swipeRefreshLayout;
    Button btnSearch,btnAboutUs;
    Dialog dialog;
    final String API_KEY ="a9ad91b75d324bd6901637c6169f9e7e";//91faa9eab2344325b1d20af3cd1cc685
    Adapter adapter;
    List<Articles>  articles = new ArrayList<>();
    int pageCount=1;
    public static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);

        etQuery = findViewById(R.id.etQuery);
        btnSearch = findViewById(R.id.btnSearch);
        btnAboutUs = findViewById(R.id.aboutUs);
        dialog = new Dialog(MainActivity.this);
       Calendar calendar=new GregorianCalendar();//2021-05-26
        int day=calendar.get(Calendar.DAY_OF_MONTH);
      int month=  calendar.get(Calendar.MONTH)+1;
      int year=calendar.get(Calendar.YEAR);
      String val="";



     final String date=year+"-"+month+"assa"+"-"+day;
        Log.i( "onCreate: ",date);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ++pageCount;
                //yourstory.com
                //thehindu.com,bbc.co.uk,
                //thehindu.com,bbc.co.uk,theguardian.com,indianexpress.com,downtoearth.com,sciencedirect.com,livemint.com,
                retrieveJson("","grabcad.com",API_KEY,20,"popularity",pageCount,"2021-04-20","en");
            }
        });*/
       // retrieveJson("test","grabcad.com",API_KEY,5,"popularity",pageCount,"2021-08-03","en");
        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call= ApiClient.getInstance().getApi().bitcoinData("bitcoin",API_KEY,"2021-09-03","publishedAt","en");
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles()!=null ){
                    Log.d(TAG,"Call123");
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

            }
        });




    }

   /* public void retrieveJson(String query ,String domains, String apiKey,int size,String sortBy,int pageNumber,String date,String language){
        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call= ApiClient.getInstance().getApi().getSpecificData(query,apiKey,size,sortBy,pageNumber,date,domains,language);
        Log.d(TAG, "retrieveJson: "+call);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                Log.d(TAG, "onResponse: "+response.body().getArticles().size());
                if (response.isSuccessful() && response.body().getArticles()!=null ){
                    Log.d(TAG,"Call123");
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/



}
