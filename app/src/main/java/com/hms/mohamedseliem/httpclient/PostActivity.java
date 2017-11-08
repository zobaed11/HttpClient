package com.hms.mohamedseliem.httpclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.hms.mohamedseliem.httpclient.entities.PostResponse;
import com.hms.mohamedseliem.httpclient.network.ApiService;
import com.hms.mohamedseliem.httpclient.network.RetrofitBuilder;

public class PostActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";
    
    @BindView(R.id.post_title)
    TextView title;

    ApiService service;
    TokenManager tokenManager;
    Call<PostResponse> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ButterKnife.bind(this);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));

        if(tokenManager.getToken() == null){
            startActivity(new Intent(PostActivity.this, LoginActivity.class));
            finish();
        }

        service = RetrofitBuilder.createServiceWithAuth(ApiService.class, tokenManager);
    }

    @OnClick(R.id.btn_posts)
    void getPosts(){
        if(tokenManager.getToken().getAccessToken() != null){
            startActivity(new Intent(PostActivity.this, GetPlace.class));
            finish();
        }/*
        call = service.posts();
        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                Log.w(TAG, "onResponse: " + response );

                if(response.isSuccessful()){
                    title.setText(response.body().getData().get(0).getTitle());
                }else {
                    tokenManager.deleteToken();
                    startActivity(new Intent(PostActivity.this, LoginActivity.class));
                    finish();

                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.w(TAG, "onFailure: " + t.getMessage() );
            }
        });*/

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null){
            call.cancel();
            call = null;
        }
    }
}
