package com.example.gps;


import java.util.ArrayList;

import android.app.Activity;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;

public class Campus extends Activity{	
	private Resources paquetes;
	
	
	public Campus(Context contexto){
		miHilo();
		paquetes=contexto.getApplicationContext().getResources();
		
	}
	



	public void cargarEdificios(){
		ArrayList<GroundOverlayOptions> overlayList = new ArrayList<GroundOverlayOptions>();
		
		Projection proyeccion = Mapa.getMapa().getProjection();
		Point coor = proyeccion.toScreenLocation(new LatLng(32.532463, -116.964945));
		
		System.out.println("x: " + coor.x + "y: " + coor.y);
		overlayList.add(getImagen(R.drawable.ed2a,229,126,55f,40f,new LatLng(32.531709,-116.96701),0.0f));
		
		overlayList.add(getImagen(R.drawable.ed6b,134,3,50f,45f,new LatLng(32.532355,-116.96616),0.0f));
		overlayList.add(getImagen(R.drawable.ed6c,184,13,40f,25f,new LatLng(32.532202,-116.96565),0.0f));
		overlayList.add(getImagen(R.drawable.ed6d,144,25,50f,50f,new LatLng(32.532862,-116.965597),0.0f));
		overlayList.add(getImagen(R.drawable.ed6e,155,64,55f,45f,new LatLng(32.533147,-116.965165),0.0f));
		overlayList.add(getImagen(R.drawable.ed6gfh,170,63,80f,80f,new LatLng(32.533651,-116.965388),0.0f));
		overlayList.add(getImagen(R.drawable.ed6i,239,36,45f,45f,new LatLng(32.532795,-116.964795),0.0f));
		
		overlayList.add(getImagen(R.drawable.ed9a,209,158,55f,40f,new LatLng(32.532423, -116.963894),0.0f));
		overlayList.add(getImagen(R.drawable.ed9b,193,142,55f,40f,new LatLng(32.532594, -116.964111),0.0f));
		
		overlayList.add(getImagen(R.drawable.ed14c15c,344,129,55f,40f,new LatLng(32.531849, -116.964113),0.0f));
		overlayList.add(getImagen(R.drawable.ed14g,319,183,55f,40f,new LatLng(32.532463, -116.964945),0.0f));
		overlayList.add(getImagen(R.drawable.ed14k,139,125,55f,40f,new LatLng(32.532179, -116.964220),0.0f));
		overlayList.add(getImagen(R.drawable.ed15a,65,93,85f,85f,new LatLng(32.530935,-116.962325),0.0f));
		
		for(int i=0;i<overlayList.size();i++)
			Mapa.getMapa().addGroundOverlay(overlayList.get(i));
		
		
	}
	
	public GroundOverlayOptions getImagen(int id,int dwidth,int dheight,float width,float height,LatLng coordenadas,float grados){
		GroundOverlayOptions imagen = new GroundOverlayOptions();
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,id,dwidth,dheight))).bearing(grados);
		imagen.position(coordenadas,width,height);
		return imagen;
	}
	

	public Bitmap decodificarPaquete(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);
        
	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}
	
	
	public int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	
	

	public void miHilo (){
		final Handler handler = new Handler();
		
		
	    new Thread(){
		
		
			public void run() {
				// TODO Auto-generated method stub
				try{
					Thread.sleep(0000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				handler.post(ejecutarAccion);
			};
			
			final Runnable ejecutarAccion = new Runnable(){
				public void run(){
					cargarEdificios();
				}
				
			};
		
			
		}.start();
		
		
	}	
}
