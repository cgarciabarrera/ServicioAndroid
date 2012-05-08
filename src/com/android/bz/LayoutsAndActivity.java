package com.android.bz;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LayoutsAndActivity extends Activity {
	
	public SQLiteDatabase db = null;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		db = openOrCreateDatabase("agenda.db",
				SQLiteDatabase.CREATE_IF_NECESSARY, null);
        
        

		final String CREATE_TABLE_unique = "CREATE TABLE servicio (activo INTEGER);";
		try {
			db.setVersion(1);
			db.setLocale(Locale.getDefault());
			db.setLockingEnabled(true);
			db.execSQL(CREATE_TABLE_unique);
			ContentValues initialValues = new ContentValues();
			initialValues.put("activo", 1);
			db.insert("servicio",null, initialValues);
			initialValues.clear();
		} catch (Exception e) {
			Log.d("DB",e.getMessage());
		}

        startService(new Intent(LayoutsAndActivity.this, Servicio.class));


    	Button bCreaContacto = (Button) this.findViewById(R.id.web);
    	bCreaContacto.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View view) {
				
    			ContentValues s = new ContentValues();
				s.put("activo",1);
				try {
					db.update("servicio", s, null, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s.clear();
    			
    			
   			
    		}
    	});
    	
    	Button bCreaContacto2 = (Button) this.findViewById(R.id.para);
    	bCreaContacto2.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View view) {
//    			stopService(new Intent(LayoutsAndActivity.this, Servicio.class));
    			ContentValues s = new ContentValues();
    			s.put("activo",0);
				try {
					db.update("servicio", s, null, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s.clear();
   			
    		}
    	});
    }
    

}