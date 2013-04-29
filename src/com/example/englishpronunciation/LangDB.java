package com.example.englishpronunciation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LangDB{
	
	
//Column names for new database
	public static final String KEY_ID = "_id";
	public static final String PRIMARY_LETTER = "PRIMARY_LETTER";
	public static final String SUBLETTERS = "SUBLETTERS";
	public static final String WORD = "WORD";
	public static final String SOUND_1 = "SOUND_1";
	public static final String SOUND_2 = "SOUND_2";
	public static final String SOUND_3 = "SOUND_3";
	public static final String SYLLABLE_COUNT = "SYLLABLE_COUNT";
	//TODO create public field for each column in DB


private LangDBOpenHelper langDBOpenHelper;

public LangDB(Context context){
	langDBOpenHelper = new LangDBOpenHelper(context, LangDBOpenHelper.DATABASE_NAME, null,
												LangDBOpenHelper.DATABASE_VERSION);
}

public void closeDatabase(){
	langDBOpenHelper.close();
}
	

//Create New Rows using the Content Values class:
	
public void addNewRow(String key, String primary, String subletters, String word, String sound1, String sound2, 
			String sound3, String syllable){
		
		ContentValues newValues = new ContentValues();
		
		newValues.put(KEY_ID, key);
		newValues.put(PRIMARY_LETTER, primary);
		newValues.put(SUBLETTERS, subletters);
		newValues.put(WORD, word);
		newValues.put(SOUND_1, sound1);
		newValues.put(SOUND_2, sound2);
		newValues.put(SOUND_3, sound3);
		newValues.put(SYLLABLE_COUNT, syllable);
		
		SQLiteDatabase db = langDBOpenHelper.getWritableDatabase();
		db.insert(LangDBOpenHelper.DATABASE_TABLE, null, newValues);
		
	}
	

private static class LangDBOpenHelper extends SQLiteOpenHelper {
	
	
	private static final String DATABASE_NAME = "LangDB.db";
	private static final String DATABASE_TABLE = "LangDB";
	private static final int DATABASE_VERSION = 1;
	
	//Create a new Database:
	private static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + 
			
			" (" + KEY_ID + " integer primary key autoincrement, " 
			+ PRIMARY_LETTER + " text not null, " 
			+ SUBLETTERS + " text not null, " 
			+ WORD + " text not null " 
			+ SOUND_1 + " text not null " 
			+ SOUND_2 + " text not null " 
			+ SOUND_3 + " text not null " 
			+ SYLLABLE_COUNT + " text not null );";


	
	public LangDBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Log the version upgrade:
		Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to " + newVersion + ", which will delete previous data");
		
		//Upgrade existing database to conform to newest version by first dropping old table, then generating new one:
		db.execSQL("DROP TABLE IF EXISTS" + DATABASE_TABLE);
		
		onCreate(db);
		
	}
	
	

}

}
