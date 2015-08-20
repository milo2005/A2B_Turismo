package creati.movil.tuguia;

import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import creati.movil.tuguia.models.Sitio;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_ID="id";

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    ImageView iconNumber,img;
    TextView number, loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);

        iconNumber = (ImageView) findViewById(R.id.ic_call);
        img = (ImageView) findViewById(R.id.img);
        number = (TextView) findViewById(R.id.number);
        loc = (TextView) findViewById(R.id.address);

        long id = getIntent().getExtras().getLong(KEY_ID);

        Sitio sitio = Sitio.findById(Sitio.class, id);

        collapsingToolbarLayout.setTitle(sitio.getNombre());
        collapsingToolbarLayout.setContentScrimColor(R.color.primary);
        loc.setText(sitio.getDireccion());

        if(sitio.getTipo() == Sitio.LUGAR){
            iconNumber.setVisibility(ImageView.GONE);
            number.setVisibility(TextView.GONE);
        }else{
            number.setText(sitio.getTelefono());
        }

        Uri uri = Uri.parse(sitio.getUrl());
        Picasso.with(this).load(uri).into(img);
    }


}
