package com.android.bz;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class Servicio extends Service {
	
	
	
	private static Timer timer = new Timer(); 
	private int estado =0;
	public static SQLiteDatabase db = null;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	String TAG="carlos3";
	@Override
	public void onCreate() {
		try {
			android.os.Debug.waitForDebugger();
			db = openOrCreateDatabase("agenda.db",
					SQLiteDatabase.CREATE_IF_NECESSARY, null);
		} catch (Exception e) {

		}
//		Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
//		Log.d(TAG, "onCreate");
//		player = MediaPlayer.create(this, R.raw.braincandy);
//		player.setLooping(false); // Set looping
	}

	@Override
	public void onDestroy() {
		//Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		timer.cancel();
		
//		player.stop();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
//		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
//		player.start();
		timer.scheduleAtFixedRate(new mainTask(), 0, 1000);
	}
    private class mainTask extends TimerTask
    { 
        public void run() 
        {
        	Cursor c = db.rawQuery("SELECT * from servicio ", null);
    		if (c != null) {
    			if (c.moveToFirst()) {
    				estado = c.getInt(c.getColumnIndex("activo"));
    			}
    		}
    		c.close();
    		
            if(estado==1) {
        	llamada();
            }
        }
    }
    
    void llamada()
    {
		try {

			URL url = new URL("http://www.fundspeople.com/lalala.php");

			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
			urlc.setRequestProperty("User-Agent", "Android Application:");
			urlc.setRequestProperty("Connection", "close");
			urlc.setConnectTimeout(1000 * 30); // mTimeout is in seconds
			urlc.connect();
			if (urlc.getResponseCode() == 200) {
				Log.d("visita web:", "OK");
				
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Log.d("visita web:", "MAL1");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.d("visita web:", "MAL2");

		}
    	
    }
    
}
