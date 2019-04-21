package com.example.myvazonhelper;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {
    Button btaddevent, btgetevent, btdelevent;
    CheckBox chbox;
    EditText etsetevent, kilday;
    CalendarView calendarView;
    TextView tvviewevent, dataCalendar;
    static DBHelper dbHelper;
    static SQLiteDatabase db;
    String text_WithDB;
    long rowId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'> Календар</font>"));


        menu.setIcon(R.drawable.ic_action_flower);
        btaddevent = (Button)findViewById(R.id.btaddevent);
        btgetevent = (Button)findViewById(R.id.btgetevent);
        btdelevent = (Button)findViewById(R.id.btdelevent);
        chbox =(CheckBox)findViewById(R.id.chbox);
        etsetevent = (EditText)findViewById(R.id.etsetevent);
        kilday = (EditText)findViewById(R.id.kilday);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        tvviewevent = (TextView)findViewById(R.id.tvviewevent);
        dataCalendar = (TextView)findViewById(R.id.dataCalendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
               dataCalendar.setText(dayOfMonth+"."+(month+1)+"."+ year);
            }
        });
        dbHelper = new DBHelper(this);

    }

    public void onClick1(View v) {
        ContentValues cv = new ContentValues();
        String event = etsetevent.getText().toString();
        String date = dataCalendar.getText().toString();

        db = dbHelper.getWritableDatabase();

        switch (v.getId()){
            case R.id.btaddevent :
                cv.put("date", date);
                cv.put("event", event);

                rowId = db.insert("mytable", null, cv);
                if(chbox.isChecked()) startAlarm(true,true);
                else startAlarm(true,false);
                break;
            case R.id.btgetevent:
                text_WithDB = "";
                String find = dataCalendar.getText().toString();
                find = "%"+find+"%";
                Cursor c = db.rawQuery("select * from mytable where date like ?", new String[]{find});
                while(c.moveToNext()){
                    int indexColData = c.getColumnIndex("date");
                    int indexColEvent = c.getColumnIndex("event");
                    text_WithDB = text_WithDB + c.getString(indexColData) + " " + c.getString(indexColEvent) + "\n";
                }
                tvviewevent.setText(text_WithDB);
                break;
            case R.id.btdelevent :
                String textDel =  etsetevent.getText().toString();
                String findDel = "%"+textDel+"%";
                int t = db.delete("mytable" , "event like ?", new String[]{findDel} );
                Intent myIntent = new Intent(CalendarActivity.this, AlarmNotificationReceiver.class);
                myIntent.putExtra("alarm event", textDel);
                myIntent.putExtra("id", rowId);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);
                AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                manager.cancel(pendingIntent);
                break;
        }
        dbHelper.close();
    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_mainn:
                Intent intentMenuMain = new Intent(CalendarActivity.this, MainActivity.class);
                startActivity(intentMenuMain);
                break;
            case R.id.menu_remind:
                Intent intentMenuReminder = new Intent(CalendarActivity.this, ReminderActivity.class);
                startActivity(intentMenuReminder);
                break;
            case R.id.menu_libr_:
                Intent intentMenuLibrary = new Intent(CalendarActivity.this, SettingsActivity.class);
                startActivity(intentMenuLibrary);
                break;
            case R.id.menu_vazonlist:
                Intent intentMenuVazonlist = new Intent(CalendarActivity.this, VazonlistActivity.class);
                startActivity(intentMenuVazonlist);
                break;
            case R.id.menu_forums:
                Intent intentMenuForums = new Intent(CalendarActivity.this, ForumActivity.class);
                startActivity(intentMenuForums);
                break;
            case R.id.menu_sett:
                Intent intentMenuSettings = new Intent(CalendarActivity.this, SettingsActivity.class);
                startActivity(intentMenuSettings);
                break;
            case R.id.menu_user:
                Intent intentMenuUser = new Intent(CalendarActivity.this, UserActivity.class);
                startActivity(intentMenuUser);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context){
            super(context, "myDB", null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "date text,"
                    + "event text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    void startAlarm(boolean isNotification, boolean isRepit){
        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;
        long timeRepit = 300;//Integer.parseInt(kilday.getText().toString())
        myIntent = new Intent(CalendarActivity.this,AlarmNotificationReceiver.class);
        myIntent.putExtra("alarm event", etsetevent.getText().toString());
        pendingIntent = PendingIntent.getBroadcast(this, 0 , myIntent, 0);
        if(isRepit == false){
            manager.set(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis()+ 300,pendingIntent);
        }
        else {
            manager.setRepeating(AlarmManager.RTC_WAKEUP,  300,timeRepit,pendingIntent);
        }
    }
}