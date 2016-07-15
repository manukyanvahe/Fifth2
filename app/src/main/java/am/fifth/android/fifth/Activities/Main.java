package am.fifth.android.fifth.Activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Response;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONException;

import am.fifth.android.fifth.Adapters.MainPagerAdapter;
import am.fifth.android.fifth.Commands.LoadProfileData;
import am.fifth.android.fifth.Commands.Login;
import am.fifth.android.fifth.Entities.User;
import am.fifth.android.fifth.Managers.AuthUserManager;
import am.fifth.android.fifth.Managers.TokenManager;
import am.fifth.android.fifth.R;
import am.fifth.android.fifth.Utils.FastBlur;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MAIN";
    MainPagerAdapter mainPagerAdapter;
    ViewPager mainPager;
    Toolbar toolbar;
    View headerView;
    User user;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        user = AuthUserManager.getUser();
        setSupportActionBar(toolbar = (Toolbar) findViewById(R.id.toolbar));


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        setupDrawer();
        fillUserData();

        //Log.i("sss", AuthUserManager.getUser(this).getEmail());

        setupTabs();
    }

    private void setupDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void fillUserData(){
        headerView = navigationView.getHeaderView(0);

        TextView authNameField = (TextView) headerView.findViewById(R.id.authName);
        authNameField.setText(AuthUserManager.getUser().getName());
        TextView authEmailField = (TextView) headerView.findViewById(R.id.authEmail);
        authEmailField.setText(AuthUserManager.getUser().getEmail());

        TextView todayHours = (TextView) headerView.findViewById(R.id.today_hours);
        todayHours.setText(AuthUserManager.getUser().getTodayHours());
        TextView weeklyHours = (TextView) headerView.findViewById(R.id.weekly_hours);
        weeklyHours.setText(AuthUserManager.getUser().getWeeklyHours());

        ProgressBar todayProgress = (ProgressBar) headerView.findViewById(R.id.today_progress);
        todayProgress.setMax(100);
        todayProgress.setProgress(user.getTodayProgress());

        ProgressBar weeklyProgress = (ProgressBar) headerView.findViewById(R.id.weekly_progress);
        weeklyProgress.setMax(100);
        weeklyProgress.setProgress(user.getWeeklyProgress());

        Switch working = (Switch) headerView.findViewById(R.id.working);
        working.setChecked(user.getIsWorking());


        ImageSize targetSize = new ImageSize(150, 150);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoader.getInstance().loadImage(AuthUserManager.getUser().getPhotoUrl(), targetSize, defaultOptions, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                ImageView authProfileImage = (ImageView) headerView.findViewById(R.id.authImage);
                authProfileImage.setImageBitmap(loadedImage);
                FastBlur fastBlur = new FastBlur();
                Bitmap blurredPhoto = fastBlur.fastBlur(loadedImage, 1, 20);
                headerView.setBackground(new BitmapDrawable(getResources(), blurredPhoto));
            }
        });
    }

    private void setupTabs() {
        mainPagerAdapter = new MainPagerAdapter(this, getSupportFragmentManager());
        mainPager = (ViewPager) findViewById(R.id.mainPager);
        mainPager.setAdapter(mainPagerAdapter);

        TabLayout mainPagerTabs = (TabLayout) findViewById(R.id.mainPagerTabs);
        mainPagerTabs.setupWithViewPager(mainPager);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
