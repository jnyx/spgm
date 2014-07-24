package com.example.gps;





import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.VisibleRegion;



public class Mapa extends Activity implements OnMapClickListener, OnMarkerClickListener {
	public static GoogleMap map=null;
	Mapa mapa=null;
	Marker marker = null;
	boolean markerClicked;
	PolygonOptions polygonOptions;
	Polygon polygon;
    PolylineOptions polylineOptions;
    Polyline polyLine;
    boolean band = true;
    Location locationOld=null;
    GroundOverlay imgOverlay;
    
    Spinner mSprPlaceType;
    
    String[] mPlaceType=null;
    String[] mPlaceTypeName=null;
    
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
		
				//Convoca los componentes Spinner
				
				
			
	}



	


	public static GoogleMap getMapa(){
		return map;
		
	}

	public void setUpMap(LocationManager locationManager) {
		// TODO Auto-generated method stub
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		map.setMyLocationEnabled(true);
		
	   // map.setOnMapClickListener(this);
		new Ruta();
	    //map.setOnMapLongClickListener(this);
	    map.setOnMarkerClickListener(this);
	    markerClicked = false;
	    mapa = this;
	    map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(32.5317124,-116.9653299)));
	    map.animateCamera(CameraUpdateFactory.zoomTo(16));
	    new Campus(mapa);
		LocationListener locationListener = new LocationListener(){

			@Override
			public void onLocationChanged(Location myLocation) {
				// TODO Auto-generated method stub
			
				if(myLocation!=null || locationOld != myLocation){
				
					double latitude = myLocation.getLatitude();
					
					double longitude = myLocation.getLongitude();
					
				
					
					if(marker != null){
						marker.remove();
					}
					
					LatLng latLng = new LatLng(latitude,longitude);
					
					map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
					
					map.animateCamera(CameraUpdateFactory.zoomTo(17));
					
					
					marker = map.addMarker(new MarkerOptions().position(latLng));
					marker.setTitle("Tu estas aqui");
					
				    locationOld = myLocation;
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

	
	/* (non-Javadoc)
	 * @see com.google.android.gms.maps.GoogleMap.OnMapLongClickListener#onMapLongClick(com.google.android.gms.maps.model.LatLng)
	 */
	/*@Override
	public void onMapLongClick(LatLng point) {
		// TODO Auto-generated method stub
		// map.addMarker(new MarkerOptions().position(point).title(point.toString()));
		
		
		
		if(band){
			Projection proyeccion = map.getProjection(); 
			Point coor = proyeccion.toScreenLocation(point);
			Toast.makeText(this,"x: "+coor.x+"y: "+coor.y+ "Lat: "+point.latitude+"Long: "+point.longitude, Toast.LENGTH_LONG).show();
			System.out.println("x: "+coor.x+"y: "+coor.y+ "Lat: "+point.latitude+"Long: "+point.longitude);
			GroundOverlayOptions gOverlay = new GroundOverlayOptions();
			gOverlay.image(BitmapDescriptorFactory.fromResource(R.drawable.ed143));
		    gOverlay.position(point,20f,18f).bearing(6f);
		    imgOverlay=map.addGroundOverlay(gOverlay);
		    band=false;
		}
		else{
			imgOverlay.remove();
			band=true;
			
		}	
		//markerClicked = false;
	}*/

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
	
	
	
	
	public Polyline getPolyline(){
		return polyLine;
	}
	
	
	
}
