package creati.movil.tuguia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URI;

import creati.movil.tuguia.util.AppUtil;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    ImageView usrImg,bannerImg;
    TextView usrTxt;

    NavigationView nav;
    DrawerLayout drawerLayout;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(AppUtil.PREFERENCE_NAME, MODE_PRIVATE);
        editor = preferences.edit();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout.setDrawerListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout
                ,R.string.open_drawer, R.string.close_drawer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(this);

        usrImg = (ImageView) nav.findViewById(R.id.img_usr);
        bannerImg = (ImageView) nav.findViewById(R.id.img);

        usrTxt = (TextView) nav.findViewById(R.id.txt_usr);
        usrTxt.setText(preferences.getString(AppUtil.KEY_USR_NAME, ""));

        Uri uriUsr = Uri.parse(preferences.getString(AppUtil.KEY_USR_IMG, ""));
        Uri uriBanner  = Uri.parse(preferences.getString(AppUtil.KEY_USR_IMG_BANNER,""));

        Picasso.with(this).load(uriUsr).into(usrImg);
        Picasso.with(this).load(uriBanner).into(bannerImg);


    }

    //region Config Toggle & DrawerLayout Events
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return  true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        toggle.onDrawerSlide(drawerView,slideOffset);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        toggle.onDrawerOpened(drawerView);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        toggle.onDrawerClosed(drawerView);

    }

    @Override
    public void onDrawerStateChanged(int newState) {
        toggle.onDrawerStateChanged(newState);
    }
    //endregion

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_logout:
                editor.putBoolean(AppUtil.KEY_LOGIN, false);
                editor.commit();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

                finish();

                break;

        }

        drawerLayout.closeDrawers();

        return false;
    }


}
