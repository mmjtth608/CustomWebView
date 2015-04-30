package com.tth.customwebview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * 
 * 备注: EteamsWebView继承自Relativielayout,所以会导致丢失一个WebView的属性，如果
 * 在项目中需要用到，可是此类中加入，然后调用即可，可参考 public void setClickable(boolean value){
 * mWebView.setClickable(value); } 这个方法的定义和调用
 * 
 * @author Administrator
 * 
 */
public class TbWebView extends RelativeLayout {

	public static int Circle = 0x01;// 圆形加载
	public static int Horizontal = 0x02;// 水平加载

	private Context context;

	private WebView mWebView = null; //
	private ProgressBar progressBar = null; // 水平进度条
	private RelativeLayout progressBar_circle = null; // 包含圆形进度条的布局
	private int barHeight = 8; // 水平进度条的高
	private boolean isAdd = false; // 判断是否已经加入进度条
	private int progressStyle = Horizontal; // 进度条样式,Circle表示为圆形，Horizontal表示为水平

	public TbWebView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public TbWebView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public TbWebView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	private void init() {
		mWebView = new WebView(context);
		this.addView(mWebView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		WebSettings webSettings = mWebView.getSettings();
		// 设置WebView属性，能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);
		// 设置可以访问文件
		webSettings.setAllowFileAccess(true);
		// 设置支持缩放
		webSettings.setBuiltInZoomControls(true);
		webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

		webSettings.setLoadWithOverviewMode(true);
		webSettings.setSupportZoom(true);
		webSettings.setUseWideViewPort(true);
		// webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		// webSettings.setDefaultFontSize(25);
		mWebView.setWebViewClient(new webViewClient());

		mWebView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				super.onProgressChanged(view, newProgress);
				if (!isAdd) {
					if (progressStyle == Horizontal) {
						progressBar = (ProgressBar) LayoutInflater
								.from(context).inflate(
										R.layout.progress_horizontal, null);
						progressBar.setMax(100);
						progressBar.setProgress(0);
						TbWebView.this.addView(progressBar,
								LayoutParams.MATCH_PARENT, barHeight);
					} else {
						progressBar_circle = (RelativeLayout) LayoutInflater
								.from(context).inflate(
										R.layout.progress_circle, null);
						TbWebView.this.addView(progressBar_circle,
								LayoutParams.MATCH_PARENT,
								LayoutParams.MATCH_PARENT);
					}
					isAdd = true;
				}
				if (newProgress == 100) {
					if (progressStyle == Horizontal) {
						progressBar.setVisibility(View.GONE);
					} else {
						progressBar_circle.setVisibility(View.GONE);
					}
				} else {
					if (progressStyle == Horizontal) {
						progressBar.setVisibility(View.VISIBLE);
						progressBar.setProgress(newProgress);
					} else {
						progressBar_circle.setVisibility(View.VISIBLE);
					}
				}
			}
		});
	}

	public void setBarHeight(int height) {
		barHeight = height;
	}

	public void setProgressStyle(int style) {
		progressStyle = style;
	}

	public void setClickable(boolean value) {
		mWebView.setClickable(value);
	}

	public void setUseWideViewPort(boolean value) {
		mWebView.getSettings().setUseWideViewPort(value);
	}

	public void setSupportZoom(boolean value) {
		mWebView.getSettings().setSupportZoom(value);
	}

	public void setBuiltInZoomControls(boolean value) {
		mWebView.getSettings().setBuiltInZoomControls(value);
	}

	public void setJavaScriptEnabled(boolean value) {
		mWebView.getSettings().setJavaScriptEnabled(value);
	}

	public void setCacheMode(int value) {
		mWebView.getSettings().setCacheMode(value);
	}

	public void setWebViewClient(WebViewClient value) {
		mWebView.setWebViewClient(value);
	}

	public void loadUrl(String url) {
		mWebView.loadUrl(url);
	}

	// Web视图
	private class webViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	public boolean canGoBack() {
		// TODO Auto-generated method stub
		return mWebView.canGoBack();
	}

	public void goBack() {
		// TODO Auto-generated method stub
		mWebView.goBack();
	}

	public void onDestory() {
		CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeAllCookie();
		CookieManager.getInstance().removeSessionCookie();
		CookieSyncManager.getInstance().sync();
		CookieSyncManager.getInstance().startSync();
	}

}
