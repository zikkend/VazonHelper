package com.example.myvazonhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

public class VazonlistActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vazonlist_activity);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'> Список вазонів</font>"));

        menu.setIcon(R.drawable.ic_action_flower);
    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_mainn:
                Intent intentMenuMain = new Intent(VazonlistActivity.this, MainActivity.class);
                startActivity(intentMenuMain);
                break;
            case R.id.menu_remind:
                Intent intentMenuReminder = new Intent(VazonlistActivity.this, ReminderActivity.class);
                startActivity(intentMenuReminder);
                break;
            case R.id.menu_libr_:
                Intent intentMenuLibr = new Intent(VazonlistActivity.this, LibraryActivity.class);
                startActivity(intentMenuLibr);
                break;
            case R.id.menu_forums:
                Intent intentMenuForums = new Intent(VazonlistActivity.this, ForumActivity.class);
                startActivity(intentMenuForums);
                break;
            case R.id.menu_calendar:
                Intent intentMenuCalendar = new Intent(VazonlistActivity.this, CalendarActivity.class);
                startActivity(intentMenuCalendar);
                break;
            case R.id.menu_sett:
                Intent intentMenuSettings = new Intent(VazonlistActivity.this, SettingsActivity.class);
                startActivity(intentMenuSettings);
                break;
            case R.id.menu_user:
                Intent intentMenuUser = new Intent(VazonlistActivity.this, UserActivity.class);
                startActivity(intentMenuUser);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
