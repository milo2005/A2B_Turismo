package creati.movil.tuguia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import creati.movil.tuguia.util.AppUtil;

/**
 * Created by Dario Chamorro on 13/08/2015.
 */
public class RootActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences(AppUtil.PREFERENCE_NAME
                , MODE_PRIVATE);
        boolean login = preferences.getBoolean(AppUtil.KEY_LOGIN, false);

        Intent intent = null;
        if(login)
            intent = new Intent(this, MainActivity.class);
        else
            intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
        finish();
    }
}
