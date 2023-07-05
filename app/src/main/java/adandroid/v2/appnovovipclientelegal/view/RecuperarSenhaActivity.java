package adandroid.v2.appnovovipclientelegal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import adandroid.v2.appnovovipLegalcliente.R;



public class RecuperarSenhaActivity extends AppCompatActivity {
    EditText edtEmail;
    Button btnRecuperar;
    Button btnVoltar;
    private SharedPreferences preferences;
    boolean isFormularioOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        inicioFormulario();
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormularioOK = validaFormulario()){
                    Toast.makeText(getApplicationContext(),
                            "sua senha foi enviada para o e-mail.informado",Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(RecuperarSenhaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecuperarSenhaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicioFormulario() {
        edtEmail = findViewById(R.id.edtEmail);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnVoltar = findViewById(R.id.btnVoltar);

    }
    private Boolean validaFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError("*");
            edtEmail.requestFocus();
            retorno = false;
        }

        return retorno;
    }
}