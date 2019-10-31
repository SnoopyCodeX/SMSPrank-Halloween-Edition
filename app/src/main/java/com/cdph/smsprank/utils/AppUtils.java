package com.cdph.smsprank.utils;

import android.app.Application;
import android.content.Context;

public class AppUtils extends Application
{
	private static AppUtils app;
	
	@Override
	public void onCreate()
	{
		super.onCreate();
		this.app = this;
	}
	
	public static synchronized AppUtils getInstance()
	{
		return app;
	}
}
