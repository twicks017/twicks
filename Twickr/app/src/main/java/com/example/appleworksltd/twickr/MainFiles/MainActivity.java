package com.example.appleworksltd.twickr.MainFiles;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appleworksltd.twickr.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Events.OnFragmentInteractionListener,Reviews.OnFragmentInteractionListener,Account.OnFragmentInteractionListener {

    ViewPager mPager;
    TabLayout mTabLayout;
    private MyPagerAdapter mAdapter;
    CollapsingToolbarLayout collapsingToolbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString("Username","");
        if (!username.equalsIgnoreCase("")){
            Toast.makeText(getApplicationContext(),"Welcome "+username,Toast.LENGTH_SHORT).show();

        }else {
            Intent z = new Intent(MainActivity.this, Login.class);
            startActivity(z);
        }




        mAdapter = new MyPagerAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mTabLayout = (TabLayout)findViewById(R.id.tab_layout);
        mPager.setAdapter(mAdapter);

        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static class MyFragment extends Fragment{
        public static final java.lang.String ARG_PAGE ="arge_page" ;

        public MyFragment(){

        }

        public static MyFragment newInstance(int pageNumber){
            MyFragment myFragment = new MyFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_PAGE,pageNumber);

            myFragment.setArguments(arguments);
            return myFragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            
            Bundle arguments = getArguments();
            arguments.getInt(ARG_PAGE);
            int pageNumber = arguments.getInt(ARG_PAGE);
            
            TextView myText = new TextView(getActivity());
            myText.setText("Hello dea" +pageNumber);
            myText.setGravity(Gravity.CENTER);

            return null;
        }
    }



    class MyPagerAdapter extends FragmentStatePagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            MainActivity.MyFragment myFragment = MainActivity.MyFragment.newInstance(position);

            switch(position){
                case 0:
                    return new Events();
                case 1:
                    return new Reviews();
                case 2:
                    return new Account();

            }

            return myFragment;
        }




        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position){
            Locale I = Locale.getDefault();
            switch(position){
                case 0:
                    return "Events";
                case 1:
                    return "News";
                case 2:
                    return "Account";

            }
            return null;
        }


    }


}
