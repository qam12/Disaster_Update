package com.example.qamber.disaster_update;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.qamber.disaster_update.ModelClass.Hero;
import com.example.qamber.disaster_update.Services.APIService;
import com.example.qamber.disaster_update.Services.ApiClient;

import org.xmlpull.v1.XmlPullParserFactory;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VictimLIst extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victim_list);

        listView = (ListView) findViewById(R.id.listViewHeroes);

        //calling the method to display the heroes
        getHeroes();
    }

    private void getHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        APIService api = retrofit.create(APIService.class);

        Call<List<Result>> call = api.getHeroes();

        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                List<Result> heroList = response.body();

                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).toString();
                }


                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));
                Toast.makeText(getApplicationContext(),"Pass", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed" +  t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
