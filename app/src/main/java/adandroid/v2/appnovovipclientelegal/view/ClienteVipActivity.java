package adandroid.v2.appnovovipclientelegal.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import adandroid.v2.appnovovipLegalcliente.R;
import adandroid.v2.appnovovipclientelegal.api.AppUtil;
import adandroid.v2.appnovovipclientelegal.model.Cliente;


public class ClienteVipActivity extends AppCompatActivity {
    // Declarar objeto
    Cliente novoVip;
    private SharedPreferences preferences;

    // Declarar atributos
    EditText edtPrimeiroNome, edtSobreNome;
    CheckBox cbkPessoaFisica;
    Button  btnSalvarContinuar, btnCancelar;
    Boolean isFormularioOK, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_vip);
        initFormulario();
        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormularioOK = validaFormulario()){
                    novoVip.setPrimeiroNome(edtPrimeiroNome.getText().toString());
                    novoVip.setSobreNome(edtSobreNome.getText().toString());
                    novoVip.setPessoaFisica(isPessoaFisica);

                    salvarSharePreferences();

                        Intent intent = new Intent(ClienteVipActivity.this,PessoaFisicaActivity.class);
                        startActivity(intent);



                }
            }
        });
    }

    private void initFormulario() {
        edtPrimeiroNome = findViewById(R.id.edtPrimeiroNome);
        edtSobreNome = findViewById(R.id.edtSobreNome);
        cbkPessoaFisica = findViewById(R.id.cbkPessoaFisicar);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);
        isFormularioOK = false;
        isPessoaFisica = false;
        novoVip = new Cliente();
        restaurarSharePreferences();
    }
    private Boolean validaFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(edtPrimeiroNome.getText().toString())) {
            edtPrimeiroNome.setError("*");
            edtPrimeiroNome.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(edtSobreNome.getText().toString())) {
            edtSobreNome.setError("*");
            edtSobreNome.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    public void cbpessoaFisica(View view){
        isPessoaFisica = cbkPessoaFisica.isChecked();

    }
   private void salvarSharePreferences() {
       preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
       SharedPreferences.Editor dados = preferences.edit();
       dados.putString("primeiroNome",novoVip.getPrimeiroNome());
       dados.putString("sobreNome",novoVip.getSobreNome());
       dados.putBoolean("pessoaFisica",novoVip.isPessoaFisica());
       dados.apply();
   }

    private void restaurarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);

    }

}