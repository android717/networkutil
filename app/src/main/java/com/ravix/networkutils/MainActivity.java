package com.ravix.networkutils;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ravix.networkutil.DefaultSpeedCategoryProvider;
import com.ravix.networkutil.TGNetworkUtil;

public class MainActivity extends AppCompatActivity {

    private TGNetworkUtil networkUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = findViewById(R.id.tvNetworkStatus);

        networkUtil = TGNetworkUtil.getInstance(this, false, new DefaultSpeedCategoryProvider());

        // Custom Speed Category Provider
      //   networkUtil = NetworkUtil.getInstance(this, true, new CustomSpeedCategoryProvider(InternetConstants.Kbps_50000, InternetConstants.Kbps_20000, InternetConstants.Kbps_10000, InternetConstants.Kbps_3000, InternetConstants.Kbps_1000));

     /*   boolean isInternetAvailable = networkUtil.isInternetAvailable();
        if (isInternetAvailable) {
            Log.e("Internet", "Internet is available");
            // Internet is available, proceed with network operations
        } else {
            Log.e("Internet", "No internet connection");
            // No internet connection
        }*/

        //Observe network state changes
        networkUtil.getNetworkState().observe(this, networkState -> {
            if (networkState != null) {
                // Handle the network state changes here
                textView.setText(networkState.toString());
            }
        });


        Button button = findViewById(R.id.btnCheckStatus);
        button.setOnClickListener(v -> {
            networkUtil.updateNetworkState();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (networkUtil != null) {
            networkUtil.unregisterNetworkCallback();
        }
    }
}
