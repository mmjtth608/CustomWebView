package com.tth.customwebview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void circleclick(View view) {
		Intent intent = new Intent();
		intent.putExtra("loadingtype", TbWebView.Circle);
		intent.setClass(this, WebViewActivity.class);
		startActivity(intent);
	}

	public void horizontalclick(View view) {
		Intent intent = new Intent();
		intent.putExtra("loadingtype", TbWebView.Horizontal);
		intent.setClass(this, WebViewActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
