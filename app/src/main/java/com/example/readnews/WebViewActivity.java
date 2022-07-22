package com.example.readnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

   private WebView webView;
   Toolbar toolbar;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_web_view);

      webView = findViewById(R.id.webview);

      toolbar = findViewById(R.id.toolbar2);
      setSupportActionBar(toolbar);

      Intent intent = getIntent();
      String URL = intent.getStringExtra("url");

      webView.setWebViewClient(new WebViewClient());
      webView.loadUrl(URL);


   }


}