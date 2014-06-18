package com.example.gps;



import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.VisibleRegion;



public class Mapa extends Activity implements OnMapClickListener, OnMapLongClickListener, OnMarkerClickListener {
	public static GoogleMap map=null;
	Mapa mapa=null;
	Marker marker = null;
	boolean markerClicked;
	PolygonOptions polygonOptions;
	Polygon polygon;
    PolylineOptions polylineOptions;
    Polyline polyLine;
    boolean band = true;
	
	
    
    
    
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState){
	    
		super.onCreate(savedInstanceState);
		onTrimMemory(TRIM_MEMORY_RUNNING_LOW);
	}
	
	
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	public void onTrimMemory(int level) {
		// TODO Auto-generated method stub
		super.onTrimMemory(level);
		LocationManager location;
		setContentView(R.layout.mapa);
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		location = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		setUpMap(location);
	}



	


	public static GoogleMap getMapa(){
		return map;
		
	}

	public void setUpMap(LocationManager locationManager) {
		// TODO Auto-generated method stub
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.setMyLocationEnabled(true);
		
	    map.setOnMapClickListener(this);
	    map.setOnMapLongClickListener(this);
	    map.setOnMarkerClickListener(this);
	    markerClicked = false;
	    mapa = this;
		LocationListener locationListener = new LocationListener(){

			@Override
			public void onLocationChanged(Location myLocation) {
				// TODO Auto-generated method stub
			    	
				if(myLocation!=null){
				
					double latitude = myLocation.getLatitude();
				
					double longitude = myLocation.getLongitude();
					
					
					/*GroundOverlayOptions imagen = new GroundOverlayOptions();
					
					imagen.image(BitmapDescriptorFactory.fromResource(R.drawable.next));
					
					imagen.position(latLng,10f,20f);
					
					map.addGroundOverlay(imagen);
					*/
					
					if(marker != null){
						marker.remove();
					}
					
					LatLng latLng = new LatLng(latitude,longitude);
					
					map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
					
					map.animateCamera(CameraUpdateFactory.zoomTo(50));
					
					
					marker = map.addMarker(new MarkerOptions().position(latLng));
					marker.setTitle("Tu estas aqui");
					if(band){
						new Campus(mapa);
						band=false;
					}
				}	
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
		/*Criteria criteria = new Criteria();
		
		String provider = locationManager.getBestProvider(criteria, true);
		
		//Location myLocation = locationManager.getLastKnownLocation(provider);
		
		
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		double latitude = myLocation.getLatitude();
		
		double longitude = myLocation.getLongitude();
		
		LatLng latLng = new LatLng(latitude,longitude);
		
		map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
		
		map.animateCamera(CameraUpdateFactory.zoomTo(20));
		
		map.addMarker(new MarkerOptions().position(latLng)).setTitle("Tu estas aqui");*/
		
	}

	@Override
	public void onMapLongClick(LatLng point) {
		// TODO Auto-generated method stub
		 map.addMarker(new MarkerOptions().position(point).title(point.toString()));
		  
		 markerClicked = false;
	}

	@Override
	public void onMapClick(LatLng point) {
		// TODO Auto-generated method stub
		map.animateCamera(CameraUpdateFactory.newLatLng(point));
		markerClicked = false;
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		
		if(markerClicked){
			   
			   if(polyLine != null){
			    polyLine.remove();
			    polyLine = null;
			   }
			   
			   polylineOptions.add(marker.getPosition());
			   polylineOptions.color(Color.RED);
			   polyLine = map.addPolyline(polylineOptions);
			   /*polygonOptions.add(marker.getPosition());
			   polygonOptions.strokeColor(Color.RED);
			   polygonOptions.fillColor(Color.BLUE);
			   polygon = map.addPolygon(polygonOptions);*/
			   
			   
			  }else{
			   if(polyLine != null){
			    polyLine.remove();
			    polyLine = null;
			   }
			   
			   polylineOptions = new PolylineOptions().add(marker.getPosition());
			   markerClicked = true;
			  }
		return true;
	}
	
	public void setPolyline(double lati,double longi,double latf,double longf,int color){
		polylineOptions = new PolylineOptions();
		polylineOptions.add(new LatLng(lati,longi),new LatLng(latf,longf));
		polylineOptions.color(color);
		
	    map.addPolyline(polylineOptions);
		
		
	}
	
	public void iniciaCampus(){
		Intent intent = new Intent(this,Campus.class);
		startActivity(intent);
	}
	
	
	
	public Polyline getPolyline(){
		return polyLine;
	}
	
	
	
}
