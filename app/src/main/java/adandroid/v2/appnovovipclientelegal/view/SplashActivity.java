package adandroid.v2.appnovovipclientelegal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


import adandroid.v2.appnovovipLegalcliente.R;
import adandroid.v2.appnovovipclientelegal.api.AppUtil;


public class SplashActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    boolean isLembrarSenha = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       salvarSharePreferences();
        restaurarSharePreferences();

        inicializarApp();

    }

    private void inicializarApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;
                if (isLembrarSenha) {
                    intent = new Intent(SplashActivity.this, MainActivity.class);

                } else {
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
                return;
            }
        }, AppUtil.TIME_SPLASH);
    }

   private void salvarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
       SharedPreferences.Editor dados = preferences.edit();
     dados.putBoolean("loginAutomatico",false);
     //   dados.apply();


    }

    private void restaurarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("loginAutomatico",false);


    }
}