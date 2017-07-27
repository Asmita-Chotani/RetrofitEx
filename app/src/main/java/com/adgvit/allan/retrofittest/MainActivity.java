package com.adgvit.allan.retrofittest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.adgvit.allan.retrofittest.data.remote.RetrofitClient;
import com.adgvit.allan.retrofittest.data.remote.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.progress_bar_text));
        pDialog.show();

        initData();
    }

    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        adapter = new RecyclerAdapter(this, current);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //adding divider between each item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void initData() {
        Service apiService = RetrofitClient.getClient().create(Service.class);

        //Making the retrofit call
        Call call = apiService.getAnswers();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                current = response.body().getInfo();
                initView();

                if(pDialog != null)
                    pDialog.dismiss();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast toast = new Toast(getApplicationContext());
                toast.setText("ERROR");
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
                if(pDialog != null)
                    pDialog.dismiss();
            }
        });
    }
}
