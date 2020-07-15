package com.example.messengerlite.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.messengerlite.R;
import com.example.messengerlite.ui.fragments.ChatsFragment;
import com.example.messengerlite.ui.fragments.PeopleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id = menuItem.getItemId();
                Fragment fragment = null;
                if(id==R.id.chats)
                {
                    fragment = new ChatsFragment();
                }
                else if(id==R.id.people)
                {
                    fragment = new PeopleFragment();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragments_container,fragment)
                .commit();

                return true;
            }
        });

        navigation.setSelectedItemId(R.id.chats);


    }


}
