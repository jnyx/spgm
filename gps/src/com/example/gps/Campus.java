package com.example.gps;


import java.util.ArrayList;

import android.app.Activity;



import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class Campus {	
	private Resources paquetes;
	private ProgressDialog pDialog;
	private Context cont;
	public static ArrayList<GroundOverlayOptions> overlayList; 
	public ArrayList<Integer> arrayEnteros;
	public Campus(Context contexto){
		paquetes=contexto.getApplicationContext().getResources();
		
		overlayList = new ArrayList<GroundOverlayOptions>();
		arrayEnteros = new ArrayList<Integer>();
		cont = contexto;
		proceso();
	} 
	
	public void proceso(){
		cargarEdificios();
		pDialog = new ProgressDialog(cont);
		pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pDialog.setMessage("Procesando...");
		pDialog.setCancelable(false);
		pDialog.setMax(overlayList.size());
		new UpdateProgress().execute();
	}
	

    public static LatLng getCoordenadas(int pos){
    	return overlayList.get(pos).getLocation();
    }

	public void cargarEdificios(){
		
		
		//Projection proyeccion = Mapa.getMapa().getProjection();
		
		
		overlayList.add(getImagen(R.drawable.ed1a,357,70,90f,35f,new LatLng(32.53076956524395,-116.96757249534132),0.0f));
		overlayList.add(getImagen(R.drawable.ed1b,196,92,40f,20f,new LatLng(32.5304897192964,-116.967902071774),-3.0f));
		overlayList.add(getImagen(R.drawable.ed1c,125,168,22f,35f,new LatLng(32.53062398892547,-116.968282610178),-7.0f));
		overlayList.add(getImagen(R.drawable.ed1d,138,180,30f,35.5f,new LatLng(32.53054088311557,-116.96878049522637),0f));
		overlayList.add(getImagen(R.drawable.ed1e,159,83,30f,20f,new LatLng(32.53089733300283,-116.96820717304946),-2.0f));
		overlayList.add(getImagen(R.drawable.ed1f,137,157,30f,40f,new LatLng(32.53127272041126,-116.96823868900537),-7f));
		overlayList.add(getImagen(R.drawable.ed1g,296,153,60f,26f,new LatLng(32.531062413200985,-116.96778237819672),2.5f));
		
		overlayList.add(getImagen(R.drawable.ed2a,229,126,55f,40f,new LatLng(32.531709,-116.96701),0.0f));
		overlayList.add(getImagen(R.drawable.ed2b,179,159,40f,40f,new LatLng(32.53108361354693,-116.96707930415869),0.0f));
		overlayList.add(getImagen(R.drawable.ed2c,286,90,65f,26f,new LatLng(32.53128685394271,-116.96775957942009),0f));
		overlayList.add(getImagen(R.drawable.ed2d,308,46,55f,20f,new LatLng(32.531438930600096,-116.96733478456737),0f));
		
		overlayList.add(getImagen(R.drawable.ed3a,147,39,40f,35f,new LatLng(32.532399,-116.966864),3f));
		overlayList.add(getImagen(R.drawable.ed3b,203,42,40f,20f,new LatLng(32.532117,-116.966928),2f));
		overlayList.add(getImagen(R.drawable.ed3c,237,226,35f,25f,new LatLng(32.531805270338495,-116.96744006127118),0f));
		overlayList.add(getImagen(R.drawable.ed3d,140,125,20f,17f,new LatLng(32.531582526910164,-116.96790911257266),0.0f));
		overlayList.add(getImagen(R.drawable.ed3e,237,226,35f,40f,new LatLng(32.53182562250236,-116.96795605123043),-2.5f));
		overlayList.add(getImagen(R.drawable.ed3f,140,125,20f,17f,new LatLng(32.53423195,-116.96908090),0.0f));
		
		overlayList.add(getImagen(R.drawable.ed4a,107,65,35f,37f,new LatLng(32.532357,-116.967957),-2.0f));
		overlayList.add(getImagen(R.drawable.ed4b,140,125,35f,30f,new LatLng(32.531692485346724,-116.96875903755426),0.0f));
		overlayList.add(getImagen(R.drawable.ed4c,234,43,50f,20f,new LatLng(32.53268267055118,-116.9677099585533),0.0f));
		overlayList.add(getImagen(R.drawable.ed4d,167,107,30f,20f,new LatLng(32.532836,-116.967390),0.0f));
		overlayList.add(getImagen(R.drawable.ed4e,340,180,110f,80f,new LatLng(32.532385,-116.967497728765),0.0f));
		overlayList.add(getImagen(R.drawable.ed4f,268,52,50f,20f,new LatLng(32.532183,-116.967625),1.5f));
		
	   
		overlayList.add(getImagen(R.drawable.ed5a,176,31,40f,20f,new LatLng(32.5326716,-116.966822),0.0f));
		overlayList.add(getImagen(R.drawable.ed5b,118,14,35f,20f,new LatLng(32.53305267986,-116.9673103094),0.0f));
		overlayList.add(getImagen(R.drawable.ed5c,121,7,35f,20f,new LatLng(32.53323330,-116.966752),0.0f));
		overlayList.add(getImagen(R.drawable.ed5d,169,26,40f,20f,new LatLng(32.53287177,-116.966785),0.0f));
		
		overlayList.add(getImagen(R.drawable.ed6a,117,31,40f,25f,new LatLng(32.5319907,-116.96611639),10f));
		overlayList.add(getImagen(R.drawable.ed6b,158,16,55f,30f,new LatLng(32.53232679,-116.966134496),8f));
		overlayList.add(getImagen(R.drawable.ed6c,180,63,37f,17f,new LatLng(32.532212878075,-116.96564432),10f));
		overlayList.add(getImagen(R.drawable.ed6d,144,25,50f,40f,new LatLng(32.532848030134026,-116.96559336036444),-3.0f));
		overlayList.add(getImagen(R.drawable.ed6e,185,94,55f,45f,new LatLng(32.533138044667254,-116.96514576673509),0.0f));
		overlayList.add(getImagen(R.drawable.ed6gfh,170,63,75f,70f,new LatLng(32.53360896295746,-116.96535229682922),1.0f));
	    overlayList.add(getImagen(R.drawable.ed6i,239,36,50f,30f,new LatLng(32.53277368902591,-116.96479238569736),0.0f));
		
	    overlayList.add(getImagen(R.drawable.ed7ab,153,204,30f,40f,new LatLng(32.5310946, -116.9631421566),10f));
	    overlayList.add(getImagen(R.drawable.ed7c,153,204,40f,30f,new LatLng(32.530893, -116.9635611587),10f));
	    overlayList.add(getImagen(R.drawable.ed7d,192,186,30f,30f,new LatLng(32.531000, -116.9639058),0f));
	    overlayList.add(getImagen(R.drawable.ed7e,232,139,40f,25f,new LatLng(32.53163510345448, -116.96399174630642),0.5f));
	    overlayList.add(getImagen(R.drawable.ed7f,193,136,47f,30f,new LatLng(32.531292, -116.963335),10f));
	    overlayList.add(getImagen(R.drawable.ed7non,175,101,40f,30f,new LatLng(32.53080772, -116.96313377),10f));
	    
	    overlayList.add(getImagen(R.drawable.ed8a,224,277,35f,47f,new LatLng(32.531842, -116.963002),0f));
	    overlayList.add(getImagen(R.drawable.ed8b,129,186,25f,35f,new LatLng(32.532004269076346, -116.96364507079124),6f));
	    overlayList.add(getImagen(R.drawable.ed8c,281,192,40f,30f,new LatLng(32.531399074103255, -116.96687679737805),0f));
	    
	    overlayList.add(getImagen(R.drawable.ed9a,209,158,40f,40f,new LatLng(32.53238897987, -116.963896527886),7f));
		overlayList.add(getImagen(R.drawable.ed9b,193,142,40f,40f,new LatLng(32.532603, -116.964111),0.0f));
		overlayList.add(getImagen(R.drawable.ed9c,193,142,36f,25f,new LatLng(32.532791, -116.963512),8f));
		
		overlayList.add(getImagen(R.drawable.ed10a,209,158,40f,20f,new LatLng(32.532377, -116.963264),8f));
		overlayList.add(getImagen(R.drawable.ed10h,209,158,15f,20f,new LatLng(32.53155680, -116.96647144),10f));
		
		overlayList.add(getImagen(R.drawable.ed11a,109,348,20f,60f,new LatLng(32.534582, -116.962552),7f));
		overlayList.add(getImagen(R.drawable.ed11b,299,97,35f,15f,new LatLng(32.534448, -116.963484),7f));
		overlayList.add(getImagen(R.drawable.ed11c,299,97,35f,15f,new LatLng(32.534759, -116.963433),7f));
		overlayList.add(getImagen(R.drawable.ed11d,105,122,15f,20f,new LatLng(32.534363, -116.963919),7f));
		
		overlayList.add(getImagen(R.drawable.ed12,81,80,12f,12f,new LatLng(32.531924, -116.962144),0f));
		overlayList.add(getImagen(R.drawable.ed12a,281,53,27f,12f,new LatLng(32.531881, -116.961955),5.0f));
		overlayList.add(getImagen(R.drawable.ed12b1,261,213,34f,30f,new LatLng(32.531998, -116.96262247),-15f));
		overlayList.add(getImagen(R.drawable.ed12b2,209,17,30f,15f,new LatLng(32.531983, -116.962727),-14f));
		overlayList.add(getImagen(R.drawable.ed12,81,80,12f,12f,new LatLng(32.531983, -116.962452),0f));
		overlayList.add(getImagen(R.drawable.ed12c,219,104,35f,25f,new LatLng(32.532257, -116.962313),8.0f));
		overlayList.add(getImagen(R.drawable.ed12d,448,12,45f,15f,new LatLng(32.532216, -116.961905),8.0f));
		overlayList.add(getImagen(R.drawable.ed12e,136,259,25f,40f,new LatLng(32.531985, -116.961664),6.0f));
		overlayList.add(getImagen(R.drawable.ed12f,448,42,34f,9f,new LatLng(32.532048, -116.961878),7.0f));
		overlayList.add(getImagen(R.drawable.ed12un,258,140,20f,20f,new LatLng(32.532122, -116.962153),9.0f));
		
		overlayList.add(getImagen(R.drawable.ed13b,147,245,30f,60f,new LatLng(32.530855780233935, -116.96650095283987),5.0f));
		overlayList.add(getImagen(R.drawable.ed13c14f,147,245,57f,40f,new LatLng(32.532001, -116.9650425),-2.0f));
		overlayList.add(getImagen(R.drawable.ed13d,184,139,50f,45f,new LatLng(32.531692768, -116.96514442),-3.0f));
		
		
		overlayList.add(getImagen(R.drawable.ed142,116,259,20f,30f,new LatLng(32.532195, -116.964769),7f));
		overlayList.add(getImagen(R.drawable.ed143,176,181,30f,18f,new LatLng(32.531973, -116.964184),6f));
		overlayList.add(getImagen(R.drawable.ed14a,317,84,65f,37f,new LatLng(32.530350, -116.964225),2f));
		overlayList.add(getImagen(R.drawable.ed14b,121,68,20f,15f,new LatLng(32.530646, -116.964355),0f));
		overlayList.add(getImagen(R.drawable.ed14c,69,112,15f,25f,new LatLng(32.53070568, -116.964116469),2f));
		overlayList.add(getImagen(R.drawable.ed14d,106,160,20f,40f,new LatLng(32.53106071, -116.964447),2f));
		overlayList.add(getImagen(R.drawable.ed14c15c,344,129,60f,20f,new LatLng(32.531837, -116.964095),7f));
		
		overlayList.add(getImagen(R.drawable.ed14e,144,132,40f,40f,new LatLng(32.5314335598, -116.965995356),10f));
		
		overlayList.add(getImagen(R.drawable.ed14g,319,183,65f,45f,new LatLng(32.53245568929477,-116.96495532989502),0.0f));
		overlayList.add(getImagen(R.drawable.ed14h,319,183,30f,30f,new LatLng(32.532169,-116.964265),7f));
		overlayList.add(getImagen(R.drawable.ed14i,65,93,30f,30f,new LatLng(32.531550,-116.969574),-4.0f));
		overlayList.add(getImagen(R.drawable.ed14j,66,23,30f,20f,new LatLng(32.533044,-116.9677605),0.0f));
		overlayList.add(getImagen(R.drawable.ed14k,41,50,15f,20f,new LatLng(32.533929785, -116.967662349),0.0f));
		overlayList.add(getImagen(R.drawable.ed15a,85,113,85f,85f,new LatLng(32.530935,-116.962325),0.0f));
		//overlayList.add(getImagen(R.drawable.ed15c,505,177,40f,15f,new LatLng(32.531828,-116.963974),7f));
		overlayList.add(getImagen(R.drawable.ed15b,65,93,30f,30f,new LatLng(32.5311907,-116.969643),-4.0f));
		
		overlayList.add(getImagen(R.drawable.ed16a,101,148,40f,40f,new LatLng(32.533019,-116.969280),0f));
		
		
		
		
	}
	
	public GroundOverlayOptions getImagen(int id,int dwidth,int dheight,float width,float height,LatLng coordenadas,float grados){
		GroundOverlayOptions imagen = new GroundOverlayOptions();
		imagen.image(BitmapDescriptorFactory.fromBitmap(decodificarPaquete(paquetes,id,dwidth,dheight)));
	    imagen.position(coordenadas,width,height).bearing(grados);
		
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
	
	

	
	
	private void tareaLarga()
    {
    	try { 
    		Thread.sleep(1000); 
    	} catch(InterruptedException e) {}
    }

	
	public class UpdateProgress extends AsyncTask<Void, Integer, Void>{
        int progress;
    	@Override
    	protected Void doInBackground(Void... params) {
    	
    	for(int i=0;i<overlayList.size();i++){
    		tareaLarga();
    		progress=i;
    		publishProgress(i);
    	}
    		
    		
    		
			return null;
    		
    		
    	}
    	
    	@Override
    	protected void onProgressUpdate(Integer... values) {
    		int progreso = values[0].intValue();
    		Mapa.getMapa().addGroundOverlay(overlayList.get(progress));
    		pDialog.setProgress(progreso);
    	}
    	
    	@Override
    	protected void onPreExecute() {
    		
    		
    		
    		pDialog.setProgress(0);
    		pDialog.show();
    	}
    	
    	@Override
    	protected void onPostExecute(Void result) {
    		
    		
    			pDialog.dismiss();
    			Toast.makeText(cont, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
    		
    	}
    	
    }
	
	
}
