package com.example.honey.myapplication3;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
        drawer.addDrawerListener(mToggle);
        mToggle.syncState();
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);
    }

    public void selectItemDrawer(MenuItem menuItem) {
        Fragment myFragent = null;

        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.kostanai:
                fragmentClass = Kostanai.class;
                break;
            case R.id.social:
                fragmentClass = Social.class;
                break;
            case R.id.pravo:
                fragmentClass = Law.class;
                break;
            case R.id.zdravo:
                fragmentClass = Health.class;
                break;
            case R.id.education:
                fragmentClass = Education.class;
                break;
            case R.id.culture:
                fragmentClass = Culture.class;
                break;
            case R.id.sport:
                fragmentClass = Sport.class;
                break;
            default:
                fragmentClass = Kostanai.class;
                break;
        }

        try {
            myFragent = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, myFragent).commit();
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawer.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }
}
