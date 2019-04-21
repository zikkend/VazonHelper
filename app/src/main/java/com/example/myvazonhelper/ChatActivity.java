package com.example.myvazonhelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;

public class ChatActivity extends AppCompatActivity{
    private DatabaseReference reference;
    ChatAdapter adapter;
    ListView listchat;

    TextView nickname;
    EditText chat;
    ImageButton send;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Чат</font>"));

        menu.setIcon(R.drawable.ic_action_flower);

        Intent intent = getIntent();
        String logo = intent.getStringExtra("logo");

        nickname = (TextView) findViewById(R.id.nickname);
        nickname.setText(logo);
        chat = (EditText) findViewById(R.id.chat);
        send = (ImageButton)findViewById(R.id.send);
        listchat = (ListView)findViewById(R.id.listchat);

        adapter = new ChatAdapter(getBaseContext(), R.layout.list_mesenger);
        listchat.setAdapter(adapter);

        final FirebaseDatabase database  = FirebaseDatabase.getInstance();
        reference = database.getReference("chat");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ChatMessage msg = dataSnapshot.getValue(ChatMessage.class);
                adapter.add(msg);
                scrollToButton();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessage msg = new ChatMessage( chat.getText().toString(), nickname.getText().toString());
                reference.push().setValue(msg);
                chat.setText("");
                scrollToButton();
            }
        });
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_mainn:
                Intent intentMenuMain = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(intentMenuMain);
                break;
            case R.id.menu_remind:
                Intent intentMenuReminder = new Intent(ChatActivity.this, ReminderActivity.class);
                startActivity(intentMenuReminder);
                break;
            case R.id.menu_vazonlist:
                Intent intentMenuVazonlist = new Intent(ChatActivity.this, VazonlistActivity.class);
                startActivity(intentMenuVazonlist);
                break;
            case R.id.menu_forums:
                Intent intentMenuForums = new Intent(ChatActivity.this, ForumActivity.class);
                startActivity(intentMenuForums);
                break;
            case R.id.menu_calendar:
                Intent intentMenuCalendar = new Intent(ChatActivity.this, CalendarActivity.class);
                startActivity(intentMenuCalendar);
                break;
            case R.id.menu_sett:
                Intent intentMenuSettings = new Intent(ChatActivity.this, SettingsActivity.class);
                startActivity(intentMenuSettings);
                break;
            case R.id.menu_user:
                Intent intentMenuUser = new Intent(ChatActivity.this, UserActivity.class);
                startActivity(intentMenuUser);
                break;
            case R.id.menu_libr_:
                Intent intentMenuLibr = new Intent(ChatActivity.this, LibraryActivity.class);
                startActivity(intentMenuLibr);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void scrollToButton(){
        listchat.post(new Runnable() {
            @Override
            public void run() {
                listchat.setSelection(adapter.getCount() - 1);
            }
        });
    }
}

