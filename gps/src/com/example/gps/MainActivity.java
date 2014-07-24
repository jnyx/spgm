package com.example.gps;





import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnKeyListener;
import android.widget.TabHost;
import android.widget.Toast;



public class MainActivity extends TabActivity {

private BDHelper openHelper;
//private Mapa mapa;
MenuItem item;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//BDHelper openHelper = new BDHelper(this);
		//this.mapa = new Mapa();
		//this.openHelper = openHelper;
		//openHelper.open();
		TabHost host = getTabHost();
		
		host.addTab(host.newTabSpec("mapa").setIndicator("Mapa").setContent(new Intent(this,Mapa.class)));
		host.addTab(host.newTabSpec("herramientas").setIndicator("Herramientas").setContent(new Intent(this,Herramientas.class)));
		
	}
	
	/*
	public boolean onMenuItemSelected(int featureId,MenuItem item){
		this.item = item;
		if(item.getItemId() == R.id.guardar){
			//mapa.getPolyline().getPoints().get(0);
			//openHelper.insertar(mapa.getPolyline().getPoints().get(0).latitude, mapa.getPolyline().getPoints().get(0).longitude, mapa.getPolyline().getPoints().get(1).latitude,mapa.getPolyline().getPoints().get(1).longitude,mapa.getPolyline().getColor() );
			return true;
		}
		
		
		if(item.getItemId() == R.id.cargar){
			Cursor fila = openHelper.getCoordenadas("1");
			if(fila.moveToNext()){
				Toast.makeText(this, fila.getString(4), Toast.LENGTH_SHORT).show();
			    //mapa.setPolyline(fila.getDouble(1), fila.getDouble(2), fila.getDouble(3), fila.getDouble(4), fila.getInt(5));
			    
			}
			else
				Toast.makeText(this, "hubo un error", Toast.LENGTH_SHORT).show();
			return true;
		}
		
		return super.onMenuItemSelected(featureId, item);	
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_menu, menu);
		return true;
	}*/
	
	
	
	
	
	public void miHilo (){
		final Handler handler = new Handler();
		
		
	    new Thread(){
		
		
			public void run() {
				// TODO Auto-generated method stub
				try{
					Thread.sleep(5000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				handler.post(ejecutarAccion);
			};
			
			final Runnable ejecutarAccion = new Runnable(){
				public void run(){
					
				}
				
			};
		
			
		}.start();
		
		
	}	

}
