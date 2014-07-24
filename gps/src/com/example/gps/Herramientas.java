package com.example.gps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Herramientas extends Activity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tools);
		
		new Lista(this,(Spinner) findViewById(R.id.spr_place_type),(Spinner)findViewById(R.id.spr_place_name),(Button)findViewById(R.id.botonAceptar));
		
		
	}
	
	


}
