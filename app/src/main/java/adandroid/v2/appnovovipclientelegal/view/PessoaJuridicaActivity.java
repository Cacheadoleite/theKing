package adandroid.v2.appnovovipclientelegal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


import adandroid.v2.appnovovipLegalcliente.R;
import adandroid.v2.appnovovipclientelegal.api.AppUtil;
import adandroid.v2.appnovovipclientelegal.model.Cliente;
import adandroid.v2.appnovovipclientelegal.model.ClientePJ;


import com.shashank.sony.fancydialoglib.FancyAlertDialog;

public class PessoaJuridicaActivity extends AppCompatActivity {
    Cliente novoVip;
    ClientePJ novoClientePJ;
    private SharedPreferences preferences;
    EditText edtCNPJ,edtRazaoSocial,edtDAEmpresa;
    CheckBox cbkSimpleNacional,cbkMEI;
    Button btnSalvarContinuar, btnCancelar,btnVoltar;
    boolean isFormularioOK, isSimplesNacional, isMEI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_juridica);
        inicioFormulario();
        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormularioOK = validaFormulario()) {
                    novoClientePJ.setCnpj(edtCNPJ.getText().toString());
                    novoClientePJ.setRazaoSocial(edtRazaoSocial.getText().toString());
                    novoClientePJ.setDataDeAbertura(edtDAEmpresa.getText().toString());
                    novoClientePJ.setSimplesNacional(isSimplesNacional);
                    novoClientePJ.setMei(isMEI);
                    salvarSharePreferences();
                    Intent intent = new Intent(PessoaJuridicaActivity.this, CredencialDeAcessoActivity.class);
                    startActivity(intent);

                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FancyAlertDialog.Builder
                        .with(PessoaJuridicaActivity.this)
                        .setTitle("Confirme o Cancelamento")
                        .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                        .setMessage("Deseja realmente cancelar ?")
                        .setNegativeBtnText("NÃO")
                        .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                        .setPositiveBtnText("SIM")
                        .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  // for @ColorRes use setNegativeBtnBackgroundRes(R.color.colorvalue)
                        //.setAnimation(Animation.POP)
                        .isCancellable(true)
                        .setIcon(R.drawable.ibnaicon, View.VISIBLE)
                        .onPositiveClicked(dialog -> Toast.makeText(PessoaJuridicaActivity.this, "Cancelado com sucesso", Toast.LENGTH_SHORT).show())
                        .onNegativeClicked(dialog -> Toast.makeText(PessoaJuridicaActivity.this, "Continue seu cadastro", Toast.LENGTH_SHORT).show())
                        .build()
                        .show();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PessoaJuridicaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void inicioFormulario() {
        edtCNPJ = findViewById(R.id.edtCNPJ);
        edtRazaoSocial = findViewById(R.id.edtRazaoSocial);
        edtDAEmpresa = findViewById(R.id.edtDAbEmpresa);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVoltar = findViewById(R.id.btnVoltar);
        cbkSimpleNacional = findViewById(R.id.cbkSimpleNacional);
        cbkMEI = findViewById(R.id.cbkMei);
        novoClientePJ= new ClientePJ();
        novoVip = new Cliente();
        restaurarSharePreferences();

    }
    private Boolean validaFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(edtCNPJ.getText().toString())) {
            edtCNPJ.setError("*");
            edtCNPJ.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(edtRazaoSocial.getText().toString())) {
            edtRazaoSocial.setError("*");
            edtRazaoSocial.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(edtDAEmpresa.getText().toString())) {
            edtDAEmpresa.setError("*");
            edtDAEmpresa.requestFocus();
            retorno = false;
        }

        return retorno;
    }
    private void salvarSharePreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("cnpj",edtCNPJ.getText().toString());
        dados.putString("razaoSocial",edtRazaoSocial.getText().toString());
        dados.putBoolean("simpleNacional",isSimplesNacional);
        dados.putBoolean("mei",isMEI);
        dados.putString("dataDeAbertura",edtDAEmpresa.getText().toString());
        dados.apply();
    }

    private void restaurarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);

    }

    public void cbSimples(View view) {
        isSimplesNacional = cbkSimpleNacional.isChecked();

    }

   // public boolean isSimplesNacional() {}



    public void cbMEI(View view) {
        isMEI = cbkMEI.isChecked();
    }
}