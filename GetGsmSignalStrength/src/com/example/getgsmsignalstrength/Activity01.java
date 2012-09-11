// The code below is from web link: http://www.firstdroid.com/2010/05/12/get-provider-gsm-signal-strength/
package com.example.getgsmsignalstrength;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class Activity01 extends Activity {
	/*
	 * This variables need to be global, so we can used them onResume and
	 * onPause method to stop the listener
	 */
	TelephonyManager Tel;
	MyPhoneStateListener MyListener;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/* Update the listener, and start it */
		MyListener = new MyPhoneStateListener();
		Tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	}

	/* Called when the application is minimized */
	@Override
	protected void onPause() {
		super.onPause();
		Tel.listen(MyListener, PhoneStateListener.LISTEN_NONE);
	}

	/* Called when the application resumes */
	@Override
	protected void onResume() {
		super.onResume();
		Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	}

	/* —————————– */
	/* Start the PhoneState listener */
	/* —————————– */
	private class MyPhoneStateListener extends PhoneStateListener {
		/*
		 * Get the Signal strength from the provider, each time there is an
		 * update
		 */
		@Override
		public void onSignalStrengthsChanged(SignalStrength signalStrength) {
			super.onSignalStrengthsChanged(signalStrength);
			Toast.makeText(
					// Tom Xue: lifecycle related
					getApplicationContext(),
					"Go to Firstdroid!!! GSM Cinr = "
							+ String.valueOf(signalStrength
									.getGsmSignalStrength()),
					Toast.LENGTH_SHORT).show();
		}

	};/* End of private Class */

}/* GetGsmSignalStrength */
