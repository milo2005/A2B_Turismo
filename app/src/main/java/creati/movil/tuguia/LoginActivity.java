package creati.movil.tuguia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import creati.movil.tuguia.models.Usuario;
import creati.movil.tuguia.util.AppUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button register, login;
    TextInputLayout usr, pass;
    View table;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences(AppUtil.PREFERENCE_NAME, MODE_PRIVATE);
        editor = preferences.edit();

        register = (Button) findViewById(R.id.btn_register);
        login = (Button) findViewById(R.id.btn_login);

        usr = (TextInputLayout) findViewById(R.id.edit_usr);
        pass = (TextInputLayout) findViewById(R.id.edit_pass);

        table = findViewById(R.id.table);

        Usuario.init(this);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_login:
                validateUsuario();
                break;
            case R.id.btn_register:
                Snackbar.make(table, "Conexion no disponible", Snackbar.LENGTH_LONG)
                        .setAction("Reintentar", this).show();
                break;
        }

    }

    private void validateUsuario() {
        String usrTxt = usr.getEditText().getText().toString();
        String passTxt = pass.getEditText().getText().toString();

        Usuario usuario = Usuario.findUsuarioByUsrAndPass(usrTxt, passTxt);

        if(usuario == null){
            pass.setErrorEnabled(true);
            pass.setError(getString(R.string.login_error));
        }else{

            editor.putBoolean(AppUtil.KEY_LOGIN, true);
            editor.putString(AppUtil.KEY_USR_NAME, usuario.getNombre());
            editor.putString(AppUtil.KEY_USR_IMG, usuario.getUrlusr());
            editor.putString(AppUtil.KEY_USR_IMG_BANNER, usuario.getUrlbanner());
            editor.commit();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
