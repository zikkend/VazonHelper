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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseUser user;
    private DatabaseReference reference;
    FirebaseDatabase database;

    EditText etPassword, etEmail, etNickname;
    Button btRegist, btChat, btAuthent;
    String displayname;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'> Форуми</font>"));
        menu.setIcon(R.drawable.ic_action_flower);

        etPassword = (EditText) findViewById(R.id.password);
        etEmail = (EditText)findViewById(R.id.email);
        etNickname = (EditText)findViewById(R.id.nick);

        btRegist = (Button)findViewById(R.id.regist);
        btChat = (Button)findViewById(R.id.chat);
        btAuthent = (Button)findViewById(R.id.authent);

        btAuthent.setOnClickListener(this);
        btRegist.setOnClickListener(this);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("nic");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               displayname = (String)(dataSnapshot.getValue());
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

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //метод getCurrentUser() щоб отримати FirebaseUser обєкт, який містить інформацію про зареєстрованого користувача
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent intent = new Intent(UserActivity.this, ChatActivity.class);
                    intent.putExtra("logo", displayname);
                    startActivity(intent);

                } else {
                    // User is signed out
                }
            }
        };

    }
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        item.setIcon(R.drawable.ic_action_books);
        switch (item.getItemId()){
            case R.id.menu_mainn:
                Intent intentMenuMain = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intentMenuMain);
                break;
            case R.id.menu_libr_:
                Intent intentMenuLibr = new Intent(UserActivity.this, LibraryActivity.class);
                startActivity(intentMenuLibr);
                break;
            case R.id.menu_remind:
                Intent intentMenuReminder = new Intent(UserActivity.this, ReminderActivity.class);
                startActivity(intentMenuReminder);
                break;
            case R.id.menu_vazonlist:
                Intent intentMenuVazonlist = new Intent(UserActivity.this, VazonlistActivity.class);
                startActivity(intentMenuVazonlist);
                break;
            case R.id.menu_forums:
                Intent intentMenuForums = new Intent(UserActivity.this, ForumActivity.class);
                startActivity(intentMenuForums);
                break;
            case R.id.menu_calendar:
                Intent intentMenuCalendar = new Intent(UserActivity.this, CalendarActivity.class);
                startActivity(intentMenuCalendar);
                break;
            case R.id.menu_sett:
                Intent intentMenuSettings = new Intent(UserActivity.this, SettingsActivity.class);
                startActivity(intentMenuSettings);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.authent){
            signin(etEmail.getText().toString(),etPassword.getText().toString());
            etNickname.setText(displayname);
        }
        if (v.getId() == R.id.regist){
            registration(etEmail.getText().toString(),etPassword.getText().toString(), etNickname.getText().toString());
            reference.push().setValue(etNickname.getText().toString());
        }
        if(v.getId() == R.id.chat){
            signin(etEmail.getText().toString(),etPassword.getText().toString());
            Intent intent = new Intent (UserActivity.this, ChatActivity.class);
            intent.putExtra("logo",displayname);
            startActivity(intent);
        }
    }
    void signin(String email , String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(UserActivity.this, "Aвторизація пройшла успішно", Toast.LENGTH_SHORT).show();
            }else
                    Toast.makeText(UserActivity.this, "Aвторизація провалена", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void registration(String email , String password, String nic){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(UserActivity.this, "Реєстрація пройшла успешно", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(UserActivity.this, "Реєстрація провалена", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
