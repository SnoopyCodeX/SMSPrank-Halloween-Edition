package com.cdph.smsprank.utils;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
import android.telephony.SmsManager;

import com.cdph.smsprank.receiver.SMSReceiver;

public final class SMSUtils
{
	public static final boolean sendSms(String msg, String... recepients)
	{
		SmsManager sms = SmsManager.getDefault();
		boolean ret = false;

		try {
			for(String recepient : recepients)
				sms.sendTextMessage(recepient, null, msg, null, null);
			ret = true;
		} catch(Exception e) {
			e.printStackTrace();
			ret = false;
		}
		
		return ret;
	}
	
	public static final void setupSmsReceiver()
	{
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		
		AppUtils.getInstance().registerReceiver(SMSReceiver.create(), filter);
	}
}
