package com.android.alaa.financeapp.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.alaa.financeapp.R;
import com.android.alaa.financeapp.controllers.InputController;
import com.android.alaa.financeapp.controllers.QueryController;

import java.util.ArrayList;

class NavigationItem {
    String mTitle;
    int mImage;

    public NavigationItem(String mTitle, int mImage) {
        this.mTitle = mTitle;
        this.mImage = mImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int mImage) {
        this.mImage = mImage;
    }
}

class NavigationAdapter extends ArrayAdapter<NavigationItem> {

    public NavigationAdapter(Context mContext, ArrayList<NavigationItem> mNavigationItem) {
        super(mContext, 0, mNavigationItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NavigationItem item = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.navigation_item, parent, false);

        TextView title = (TextView) convertView.findViewById(R.id.navigation_title);
        ImageView image = (ImageView) convertView.findViewById(R.id.navigation_image);

        title.setText(item.getTitle());
        image.setImageResource(item.getImage());

        return convertView;
    }
}

public class NavigationActivity extends ActionBarActivity {

    private static final String HOME_FRAG_TAG = "HOME";
    private static final String EXPENSES_FRAG_TAG = "EXPENSES";
    private static final String INCOME_FRAG_TAG = "INCOME";
    private static final String BUDGET_FRAG_TAG = "BUDGET";
    private static final String CANVAS_FRAG_TAG = "CANVAS";

    private static final int HOME_FRAG_ID = 0;
    private static final int INCOME_FRAG_ID = 1;
    private static final int EXPENSES_FRAG_ID = 2;
    private static final int BUDGET_FRAG_ID = 3;
    private static final int CANVAS_FRAG_ID = 4;

    InputController iController;
    QueryController qController;

    DrawerLayout mNavigationDrawer;
    ListView mNavigationList;
    NavigationAdapter mNavigationAdapter;
    ArrayList<NavigationItem> mNavigationMenu;
    ActionBarDrawerToggle mNavigationToggle;
    FragmentManager mFragManager;
    Fragment mCurrFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        mFragManager = getSupportFragmentManager();

        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_layout);
        mNavigationDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mNavigationList = (ListView) findViewById(R.id.navigation_list);
        mNavigationMenu = new ArrayList<NavigationItem>();

        mNavigationMenu.add(new NavigationItem(getString(R.string.navigation_home), R.drawable.navigation_arrow));
        mNavigationMenu.add(new NavigationItem(getString(R.string.navigation_income), R.drawable.navigation_arrow));
        mNavigationMenu.add(new NavigationItem(getString(R.string.navigation_expenses), R.drawable.navigation_arrow));
        mNavigationMenu.add(new NavigationItem(getString(R.string.navigation_budget), R.drawable.navigation_arrow));
        mNavigationMenu.add(new NavigationItem(getString(R.string.navigation_canvas), R.drawable.navigation_arrow));

        mNavigationAdapter = new NavigationAdapter(this, mNavigationMenu);
        mNavigationList.setAdapter(mNavigationAdapter);

        mNavigationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mNavigationDrawer.closeDrawers();
                Log.d("NavigationActivity", " " + position + " " + id);
                navigateTo(position);
            }
        });
        // Set the drawer toggle as the DrawerListener
        mNavigationToggle = new ActionBarDrawerToggle(this, mNavigationDrawer, R.string.drawer_open, R.string.drawer_close);
        mNavigationDrawer.setDrawerListener(mNavigationToggle);

        iController = new InputController(this);
        qController = new QueryController(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigateTo(HOME_FRAG_ID);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mNavigationToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void navigateTo(int id) {

        switch (id) {
            case HOME_FRAG_ID:
                mCurrFragment = mFragManager.findFragmentByTag(HOME_FRAG_TAG);
                if (mCurrFragment == null)
                    mCurrFragment = new QueryFragment();

                mFragManager
                        .beginTransaction()
                        .replace(R.id.content_frame, mCurrFragment,
                                HOME_FRAG_TAG).commit();

                break;
            case INCOME_FRAG_ID:
                mCurrFragment = mFragManager.findFragmentByTag(INCOME_FRAG_TAG);
                if (mCurrFragment == null)
                    mCurrFragment = new IncomeInputFragment();

                mFragManager
                        .beginTransaction()
                        .replace(R.id.content_frame, mCurrFragment,
                                INCOME_FRAG_TAG).commit();
                break;
            case EXPENSES_FRAG_ID:
                mCurrFragment = mFragManager.findFragmentByTag(EXPENSES_FRAG_TAG);
                if (mCurrFragment == null)
                    mCurrFragment = new ExpenseInputFragment();

                mFragManager
                        .beginTransaction()
                        .replace(R.id.content_frame, mCurrFragment,
                                EXPENSES_FRAG_TAG).commit();
                break;
            case BUDGET_FRAG_ID:
                break;
            case CANVAS_FRAG_ID:
                mCurrFragment = mFragManager.findFragmentByTag(CANVAS_FRAG_TAG);
                if (mCurrFragment == null)
                    mCurrFragment = new CanvasFragment();

                mFragManager
                        .beginTransaction()
                        .replace(R.id.content_frame, mCurrFragment,
                                CANVAS_FRAG_TAG).commit();

                break;
        }
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mNavigationToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mNavigationToggle.onConfigurationChanged(newConfig);
    }

}
