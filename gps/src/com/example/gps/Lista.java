package com.example.gps;

import java.util.ArrayList;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Lista implements OnItemSelectedListener,OnClickListener {
	private Resources paquetes;
	 Spinner facultad;
	 Spinner seccion;
	 String[] arregloFacultad=null;
	 private String letraSeccion=null;
	 private int[] entero;
	 Context contexto;
	 private int idResource;
	 private Button boton;
	 private TypedArray arrayEdificios,arrayIDEdificios,arraySecciones;
	 private int posicion;
	public Lista(Context contexto,Spinner lista,Spinner seccion,Button button){
		paquetes= contexto.getApplicationContext().getResources();
		this.contexto=contexto;
		this.seccion = seccion;
		this.facultad = lista;
		this.boton = button;
		// Array of place type names
		//arregloFacultad = paquetes.getStringArray(R.array.facultad);
				
		// Creating an array adapter with an array of Place types
		// to populate the spinner
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(contexto, android.R.layout.simple_spinner_dropdown_item, arregloFacultad);
				
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				contexto, R.array.facultad, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Setting adapter on Spinner to set place types
		facultad.setAdapter(adapter);
		
		this.facultad.setOnItemSelectedListener(this);
		this.seccion.setOnItemSelectedListener(this);
		this.boton.setOnClickListener(this);
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		switch (parent.getId()) {
		case R.id.spr_place_type:

			// Retrieves an array
			arraySecciones = paquetes.obtainTypedArray(
					R.array.seccion_array);
			
			
	
			
			
			
			CharSequence[] secciones = arraySecciones.getTextArray(pos);
			
			System.out.println("Posicion1:"+secciones[0]);
			arraySecciones.recycle();
            
			
			TypedArray arrayEnteros = paquetes.obtainTypedArray(R.array.coordenadas);
			idResource = arrayEnteros.getResourceId(pos, 0);
			arrayEnteros.recycle();
			// Create an ArrayAdapter using the string array and a default
			// spinner layout
			ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
					contexto, android.R.layout.simple_spinner_item,
					android.R.id.text1, secciones);

			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			// Apply the adapter to the spinner
			this.seccion.setAdapter(adapter);

			break;

		case R.id.spr_place_name:
				//int[] entero = paquetes.getIntArray(R.array.coordenadas); 
				arrayEdificios = paquetes.obtainTypedArray(idResource);
				arrayIDEdificios = paquetes.obtainTypedArray(R.array.lista);
				
				this.posicion = pos;
				
				letraSeccion = arraySecciones.getString(pos);
	
				
				
			break;
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int cont=0;
		while(arrayIDEdificios.getResourceId(cont, 0)!= arrayEdificios.getResourceId(posicion, 0)){
			cont++;
		}
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
		markerOptions.position(Campus.getCoordenadas(cont));
		System.out.println(cont);
		markerOptions.title(letraSeccion);
		Mapa.getMapa().addMarker(markerOptions);
		//System.out.println(Campus.getCoordenadas(cont));
	}
	
	

}
