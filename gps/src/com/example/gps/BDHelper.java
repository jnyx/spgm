package com.example.gps;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {
	@SuppressLint("SdCardPath") private static String DB_PATH = "/data/data/com.example.gps/databases/";
	 
	private static String DB_NAME = "baseGps.db";
	 
	private SQLiteDatabase myDataBase;
	 
	private final Context myContext;
	
	
	//Establecemos los nombres de las columnas
	public static final String TABLE_NAME= "gps";
	public static final String KEY_ID = "_id";
	
	//Array de strings para su uso en los diferentes m�todos
	//private static final String[] cols = new String[] { KEY_ID, KEY_COL1, KEY_COL2 };

	private static final String LATITUD_INICIAL = "LatitudInicial";

	private static final String LONGITUD_INICIAL = "LongitudInicial";

	private static final String LATITUD_FINAL = "LatitudFinal";

	private static final String LONGITUD_FINAL = "LongitudFinal";

	private static final String COLOR = "Color";
	
	
	public BDHelper(Context context) {
		super(context, DB_NAME, null, 1);
		// TODO Auto-generated constructor stub
		this.myContext = context;

	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		// TODO Auto-generated method stub
		database.execSQL("create table " + TABLE_NAME + "( " 
			     + KEY_ID + " INTEGER PRIMARY KEY, "
				 + LATITUD_INICIAL + " REAL, "
				 + LONGITUD_INICIAL + " REAL, " 
				 + LATITUD_FINAL  + " REAL, "
				 + LONGITUD_FINAL + " REAL, "
				 + COLOR + " INTEGER)" );
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public void createDataBase() throws IOException{
		 
		boolean dbExist = checkDataBase();
		 
		if(dbExist){
		//la base de datos existe y no hacemos nada.
		}
		else{
			//Llamando a este m�todo se crea la base de datos vac�a en la ruta por defecto del sistema
			//de nuestra aplicaci�n por lo que podremos sobreescribirla con nuestra base de datos.
			this.getReadableDatabase();
			 
			try {
			 
			copyDataBase();
			 
			} catch (IOException e) {
			throw new Error("Error copiando Base de Datos");
			}
		}
		 
	}
	
	/**
	* Comprueba si la base de datos existe para evitar copiar siempre el fichero cada vez que se abra la aplicaci�n.
	* @return true si existe, false si no existe
	*/
	private boolean checkDataBase(){
	 
		SQLiteDatabase checkDB = null;
		 
		try{
		 
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
			 
		}catch(SQLiteException e){
		 
			//si llegamos aqui es porque la base de datos no existe todav�a.
			myDataBase=this.getWritableDatabase();
			onCreate(myDataBase);
		 
		}
		if(checkDB != null){
		 
			checkDB.close();
		 
		}
	    return checkDB != null ? true : false;
	}
	
	/**
	* Copia nuestra base de datos desde la carpeta assets a la reci�n creada
	* base de datos en la carpeta de sistema, desde d�nde podremos acceder a ella.
	* Esto se hace con bytestream.
	* */
	private void copyDataBase() throws IOException{
	 
		//Abrimos el fichero de base de datos como entrada
		InputStream myInput = myContext.getAssets().open(DB_NAME);
		 
		//Ruta a la base de datos vac�a reci�n creada
		String outFileName = DB_PATH + DB_NAME;
		 
		//Abrimos la base de datos vac�a como salida
		OutputStream myOutput = new FileOutputStream(outFileName);
		 
		//Transferimos los bytes desde el fichero de entrada al de salida
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}
		 
		//Liberamos los streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	 
	}
	
	public void open() throws SQLException{
		 
		//Abre la base de datos
		try {
		createDataBase();
		} catch (IOException e) {
		throw new Error("Ha sido imposible crear la Base de Datos");
		}
		 
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
		
		 
		}
		 
		@Override
		public synchronized void close() {
		if(myDataBase != null)
		myDataBase.close();
		super.close();
	    }
		
		
		public long insertar(double latInicial, double longInicial,double latFinal,double longFinal,int color) {
			ContentValues newValues = new ContentValues();
			//newValues.put(KEY_ID, _id);
			newValues.put(LATITUD_INICIAL, latInicial);
			newValues.put(LONGITUD_INICIAL, longInicial);
			newValues.put(LATITUD_FINAL, latFinal);
			newValues.put(LONGITUD_FINAL, longFinal);
			newValues.put(COLOR, color);
			
			return myDataBase.insert(TABLE_NAME, null, newValues);
			
		}
		 
		
		public boolean eliminar(long _rowIndex) {
		return myDataBase.delete(TABLE_NAME, KEY_ID + "=" + _rowIndex, null) > 0;
		}
		
		
		public Cursor getCoordenadas(){
			return myDataBase.rawQuery("select * from " + TABLE_NAME, null);
		}
		 
		
		/*public boolean actualizar(Integer _rowIndex, Integer alarma, Integer evento) {
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_COL1,alarma);
		newValues.put(KEY_COL2, evento);
		return myDataBase.update(DATABASE_TABLE, newValues, KEY_ID + "=" + _rowIndex, null) > 0;
		}*/	
		 
		
			
			

		
 

}
