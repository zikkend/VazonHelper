package com.example.myvazonhelper;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button library;
Button reminder;
Button forum;
Button vazonlist;
private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().show();

//        toolbar =(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        Drawable d = getResources().getDrawable(R.drawable.ic_action_menu);
//        toolbar.setOverflowIcon(d);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'> VazonHelper</font>"));

        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);

        menu.setIcon(R.drawable.ic_action_flower);

        library =(Button)findViewById(R.id.library);
        library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLibrary = new Intent(MainActivity.this, LibraryActivity.class);
                startActivity(intentLibrary);
            }
        });

        reminder =(Button)findViewById(R.id.reminder);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReminder = new Intent(MainActivity.this, ReminderActivity.class);
                startActivity(intentReminder);
            }
        });

        forum =(Button)findViewById(R.id.forum);
        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForum = new Intent(MainActivity.this, ForumActivity.class);
                startActivity(intentForum);
            }
        });

        vazonlist =(Button)findViewById(R.id.vazonlist);
        vazonlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVazonlist = new Intent(MainActivity.this, VazonlistActivity.class);
                startActivity(intentVazonlist);
            }
        });
    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        item.setIcon(R.drawable.ic_action_books);
        switch (item.getItemId()){
            case R.id.menu_libr_:
                Intent intentMenuLibr = new Intent(MainActivity.this, LibraryActivity.class);
                startActivity(intentMenuLibr);
                break;
            case R.id.menu_remind:
                Intent intentMenuMain = new Intent(MainActivity.this, ReminderActivity.class);
                startActivity(intentMenuMain);
                break;
            case R.id.menu_vazonlist:
                Intent intentMenuVazonlist = new Intent(MainActivity.this, VazonlistActivity.class);
                startActivity(intentMenuVazonlist);
                break;
            case R.id.menu_forums:
                Intent intentMenuForums = new Intent(MainActivity.this, ForumActivity.class);
                startActivity(intentMenuForums);
                break;
            case R.id.menu_calendar:
                Intent intentMenuCalendar = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intentMenuCalendar);
                break;
            case R.id.menu_sett:
                Intent intentMenuSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentMenuSettings);
                break;
            case R.id.menu_user:
                Intent intentMenuUser = new Intent(MainActivity.this, UserActivity.class);
                startActivity(intentMenuUser);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
