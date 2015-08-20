package creati.movil.tuguia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.net.URI;
import java.util.List;

import creati.movil.tuguia.adapters.SitioAdapter;
import creati.movil.tuguia.models.Sitio;
import creati.movil.tuguia.util.AppUtil;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener, SitioAdapter.OnItemClick, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    ImageView usrImg,bannerImg;
    TextView usrTxt;

    NavigationView nav;
    DrawerLayout drawerLayout;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ActionBarDrawerToggle toggle;

    RecyclerView list;
    SitioAdapter adapter;

    SwipeRefreshLayout refresh;
    FloatingActionButton fab;

    List<Sitio> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Sitio.init(this);

        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.primary, R.color.primaryDark
                , R.color.accent);
        refresh.setOnRefreshListener(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        list = (RecyclerView) findViewById(R.id.list);
        data = Sitio.listAll(Sitio.class);
        adapter = new SitioAdapter(this, data);
        adapter.setOnItemClick(list, this);

        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));

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

        Transformation rounded = new RoundedTransformationBuilder()
                .oval(true).build();

        Picasso.with(this).load(uriUsr).transform(rounded).into(usrImg);
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


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_ID, data.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "Cargando nuevo contenido"
                ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        refresh.setRefreshing(false);
    }
}
