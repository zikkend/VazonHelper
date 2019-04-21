package com.example.myvazonhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ReminderActivity extends AppCompatActivity {
    Button kalendar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remindrer_activity);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'> Нагадування</font>"));

        menu.setIcon(R.drawable.ic_action_flower);
        kalendar = (Button)findViewById(R.id.kalendar);
        kalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CalendarIntent = new Intent(ReminderActivity.this, CalendarActivity.class );
                startActivity(CalendarIntent);
            }
        });
    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_mainn:
                Intent intentMenuMain = new Intent(ReminderActivity.this, MainActivity.class);
                startActivity(intentMenuMain);
                break;
            case R.id.menu_libr_:
                Intent intentMenuLibr = new Intent(ReminderActivity.this, LibraryActivity.class);
                startActivity(intentMenuLibr);
                break;
            case R.id.menu_vazonlist:
                Intent intentMenuVazonlist = new Intent(ReminderActivity.this, VazonlistActivity.class);
                startActivity(intentMenuVazonlist);
                break;
            case R.id.menu_forums:
                Intent intentMenuForums = new Intent(ReminderActivity.this, ForumActivity.class);
                startActivity(intentMenuForums);
                break;
            case R.id.menu_calendar:
                Intent intentMenuCalendar = new Intent(ReminderActivity.this, CalendarActivity.class);
                startActivity(intentMenuCalendar);
                break;
            case R.id.menu_sett:
                Intent intentMenuSettings = new Intent(ReminderActivity.this, SettingsActivity.class);
                startActivity(intentMenuSettings);
                break;
            case R.id.menu_user:
                Intent intentMenuUser = new Intent(ReminderActivity.this, UserActivity.class);
                startActivity(intentMenuUser);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}