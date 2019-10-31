package com.cdph.smsprank.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public final class SMSReceiver extends BroadcastReceiver
{
	private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
	
	private SMSReceiver()
	{}
	
	public static final SMSReceiver create()
	{
		return (new SMSReceiver());
	}

	@Override
	public void onReceive(Context ctx, Intent data)
	{
		try {
			if(data.getAction().equals(ACTION_SMS_RECEIVED))
			{
				Bundle extras = data.getExtras();
				if(extras != null)
				{
					Object[] pdus = (Object[]) extras.get("pdus");
					SmsMessage[] msgs = new SmsMessage[pdus.length];
					StringBuilder str = new StringBuilder();
					
					for(int i = 0; i < msgs.length; i++)
					{
						msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
						str.append(String.format("From: %s\nMessage: %s\n\n", msgs[i].getOriginatingAddress(), msgs[i].getMessageBody()));
					}
					
					
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
