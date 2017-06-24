package com.example.jbleo.evalapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.jbleo.evalapp.fragment.ClientsFragment;
import com.example.jbleo.evalapp.fragment.TestTemplateFragment;
import com.example.jbleo.evalapp.model.Client;
import com.example.jbleo.evalapp.model.TestTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;


public class MainActivity extends SessionActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ClientsFragment.OnListFragmentInteractionListener,
        TestTemplateFragment.OnListFragmentInteractionListener,
        TestTemplateFragment.OnAddButtonInteractionListener{

        private FirebaseUser mUser = null;

        private final Runnable mUpdateUITimerTask = new Runnable() {
            public void run() {
                // do whatever you want to change here, like:
                ((TextView) findViewById(R.id.text_user_mail)).setText("Second text to display!");
            }
        };
        private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mUser == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new ClientsFragment())
                    .commit();
        }else{

        }
    }

    @Override
    protected void loginComplete() {

    }

    @Override
    protected void logoutComplete() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume(){
        super.onResume();

        mHandler.postDelayed(mUpdateUITimerTask, 100);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        Class fragmentClass;

        if (id == R.id.nav_user_list){
            fragmentClass = ClientsFragment.class;
        } else if (id == R.id.nav_activities_list) {
            fragmentClass = TestTemplateFragment.class;
        } else{
            fragmentClass = ClientsFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onListFragmentInteraction(Client client) {
        Intent intent = new Intent(this, TestTemplateActivity.class);
        intent.putExtra("client", client);
        startActivity(intent);
    }

    @Override
    public void onListFragmentInteraction(TestTemplate template) {
        Intent intent = new Intent(this, TestTemplateActivity.class);
        intent.putExtra("template", template);
        startActivity(intent);
    }

    @Override
    public void onAddButtonInteractionListener() {
        Intent intent = new Intent(this, TestTemplateActivity.class);
        intent.putExtra("template", (Serializable)null);
        startActivity(intent);
    }
}
