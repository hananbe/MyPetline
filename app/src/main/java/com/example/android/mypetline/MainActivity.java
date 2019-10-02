package com.example.android.mypetline;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.android.mypetline.Fragments.AdoptionInfoFragment;
import com.example.android.mypetline.Fragments.mapsFragment;
import com.example.android.mypetline.Fragments.rssFragment;
import com.example.android.mypetline.Fragments.tipsFragment;


/*
Main activity handles the navigation drawer
 */
public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer = null;
    private Toolbar toolbar;
    private Fragment fragment;
    private FragmentTransaction fragmentTransaction;

    //map category field represent the type of markers which will be display on the map.
    private String currentMapCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();

        //set default map category to vet for ver services
        currentMapCategory = "vet";
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //listener to drawer events
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });


        setDrawerSettings();
        fragment = new rssFragment();
        setFragmentTransaction();


    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void initToolbar() {
        //creating toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.mypet_tool_bar_icon);
    }


    private void setDrawerSettings() {

        //set drawer direction right to left
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        NavigationView nw = (NavigationView) findViewById(R.id.nav_view);
        nw.setItemIconTintList(null);


        //navigation menu designing
        //create menu item state property
        int[][] state = new int[][]{
                new int[]{android.R.attr.state_checked}, // checked
                new int[]{-android.R.attr.state_checked}
        };

        // define different colors
        int[] color = new int[]{
                Color.rgb(255, 46, 84),
                (Color.WHITE)
        };
        //define menu items backround colors according item's state and color arrays.
        ColorStateList csl = new ColorStateList(state, color);

        //set text color state according item state.
        nw.setItemTextColor(new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{android.R.attr.state_focused},
                        new int[]{}
                },
                new int[]{
                        Color.rgb(255, 128, 192),
                        Color.rgb(100, 200, 192),
                        Color.BLACK
                }
        ));
        setupDrawerContent(nw);

    }

    //create items content
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    //changing current fragment according the item which was pressed.
    public void selectDrawerItem(MenuItem menuItem) {
        Intent i;
        switch (menuItem.getItemId()) {
            case R.id.nav_rss:
                fragment = new rssFragment();
                break;
            case R.id.nav_vetMaps:
                fragment = new mapsFragment();
                currentMapCategory = "vet";
                break;
            case R.id.nav_StoresMaps:
                fragment = new mapsFragment();
                currentMapCategory = "petstores";
                break;
            case R.id.nav_adoption:
                fragment = new AdoptionInfoFragment();
                break;
            case R.id.nav_tips:
                fragment = new tipsFragment();
                break;
            default:
                currentMapCategory = "vet";
                break;

        }
        //passimg map type in bundle to the fragement maps use,
        Bundle bundle = new Bundle();
        bundle.putString("mapCategory", currentMapCategory);
        fragment.setArguments(bundle);

        setFragmentTransaction();
        // the Highlight of  the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();

    }

    //set fragment transcation in order to open the desire freagment
    private void setFragmentTransaction() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPlaceHolder, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public String getCurrentMapCategory() {
        return currentMapCategory;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }


}




