package com.tth.customwebview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

	private String mUrl;
	private TbWebView mWebView;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		mUrl = "http://www.baidu.com";
		mWebView = (TbWebView) findViewById(R.id.webView1);
		mWebView.setProgressStyle(getIntent().getIntExtra("loadingtype",
				TbWebView.Horizontal));
		mWebView.setCacheMode(WebSettings.LOAD_NO_CACHE);
		mWebView.loadUrl(mUrl);
		mWebView.setWebViewClient(new ETeamsWebViewClient());
		mWebView.setClickable(true);
	}

	// To handle the back button key press
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// LogUtil.i(this, "keyCode=" keyCode);
		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mWebView.onDestory();
	}

	// Web视图
	private class ETeamsWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			if (!TextUtils.isEmpty(view.getTitle()))
				setTitle(view.getTitle());
		}
	}

}
