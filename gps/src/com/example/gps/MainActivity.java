package com.example.gps;


import com.google.android.gms.maps.MapFragment;


import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

private BDHelper openHelper;
private Mapa mapa;
MenuItem item;
	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BDHelper openHelper = new BDHelper(this);
		mapa = new Mapa(((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(),(LocationManager) getSystemService(Context.LOCATION_SERVICE));
		this.openHelper = openHelper;
		openHelper.open();
	}
	
	
	
	public boolean onMenuItemSelected(int featureId,MenuItem item){
		this.item = item;
		if(item.getItemId() == R.id.guardar){
			mapa.getPolyline().getPoints().get(0);
			openHelper.insertar(mapa.getPolyline().getPoints().get(0).latitude, mapa.getPolyline().getPoints().get(0).longitude, mapa.getPolyline().getPoints().get(1).latitude,mapa.getPolyline().getPoints().get(1).longitude,mapa.getPolyline().getColor() );
			return true;
		}
		return super.onMenuItemSelected(featureId, item);	
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_menu, menu);
		return true;
	}

}
