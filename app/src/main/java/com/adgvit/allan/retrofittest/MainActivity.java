package com.adgvit.allan.retrofittest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.adgvit.allan.retrofittest.data.model.AnswerResponse;
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
    private Service mService;
    private TextView mResponseTv;
    private EditText newName, newDesc, newOrg;

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
        call.enqueue(new Callback<AnswerResponse>() {
            @Override
            public void onResponse(Call<AnswerResponse> call, Response<AnswerResponse> response) {
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

        getNew();
    }

    private void getNew() {
        newName = (EditText) findViewById(R.id.newName);
        newDesc = (EditText) findViewById(R.id.newDesc);
        newOrg = (EditText) findViewById(R.id.newOrg);
        mResponseTv = (TextView) findViewById(R.id.tv_response);
        Button sendButton = (Button) findViewById(R.id.button);

        mService = ApiUtils.getService();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = newName.getText().toString().trim();
                String desc = newDesc.getText().toString().trim();
                String org = newOrg.getText().toString().trim();
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(desc) && !TextUtils.isEmpty(org)) {
                    sendPost(name, desc, org);
                }
            }
        });
    }

    public void sendPost(String name, String desc, String org) {
        mService.savePost(name, desc, org).enqueue(new Callback<AnswerResponse>() {
            @Override
            public void onResponse(Call<AnswerResponse> call, Response<AnswerResponse> response) {
                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Toast.makeText(getApplicationContext(), "POST successful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AnswerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showResponse(String response) {
        if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }
}
