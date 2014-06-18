package com.example.gps;


import android.app.Activity;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;


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
		GroundOverlayOptions imagen = new GroundOverlayOptions();
		
		
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed15a,65,93)));
		imagen.position(new LatLng(32.530935,-116.962325),85f,85f);
		Mapa.getMapa().addGroundOverlay(imagen);
	
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed6b,134,3)));
		imagen.position(new LatLng(32.532355,-116.96616),50f,45f);
		Mapa.getMapa().addGroundOverlay(imagen);
		
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed6c,184,13)));
		imagen.position(new LatLng(32.532202,-116.96565),40f,25f);
		Mapa.getMapa().addGroundOverlay(imagen);
		
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed6d,144,25)));
		imagen.position(new LatLng(32.532862,-116.965597),50f,50f);
		Mapa.getMapa().addGroundOverlay(imagen);
		
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed6e,155,64)));
		imagen.position(new LatLng(32.533147,-116.965165),55f,45f);
		Mapa.getMapa().addGroundOverlay(imagen);
		
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed6gfh,120,13)));
		imagen.position(new LatLng(32.533651,-116.965388),80f,80f);
		Mapa.getMapa().addGroundOverlay(imagen);
		
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed6i,239,36)));
		imagen.position(new LatLng(32.532795,-116.964795),45f,45f);
		Mapa.getMapa().addGroundOverlay(imagen);
		
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed7e8b,281,186)));
		imagen.position(new LatLng(32.531935, -116.964580),210f,210f);
		Mapa.getMapa().addGroundOverlay(imagen);
		
		
		
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,R.drawable.ed2a,229,126)));
		imagen.position(new LatLng(32.531709,-116.96701),55f,40f);
		Mapa.getMapa().addGroundOverlay(imagen);
		
		
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
					Thread.sleep(20000);
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
