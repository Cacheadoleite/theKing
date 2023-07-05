package adandroid.v2.appnovovipclientelegal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import adandroid.v2.appnovovipLegalcliente.R;
import adandroid.v2.appnovovipclientelegal.api.AppUtil;
import adandroid.v2.appnovovipclientelegal.model.Cliente;
import adandroid.v2.appnovovipclientelegal.model.ClientePF;


import com.shashank.sony.fancydialoglib.FancyAlertDialog;

public class PessoaFisicaActivity extends AppCompatActivity {
    Cliente novoVip;
    ClientePF novoClientePF;

    private SharedPreferences preferences;
    EditText edtCPF;
    EditText edtNomeCompleto;
    Button btnSalvarContinuar, btnCancelar, btnVoltar;
    boolean isFormularioOK, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R
                .layout.activity_pessoa_fisica);
        inicioFormulario();
        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validaFormulario()) {
                    novoClientePF.setCpf(edtCPF.getText().toString());
                    novoClientePF.setNomeCompleto(edtNomeCompleto.getText().toString());
                    salvarSharePreferences();
                    Intent intent;
                    if (isPessoaFisica)
                        intent = new Intent(PessoaFisicaActivity.this,
                                CredencialDeAcessoActivity.class);
                    else
                        intent = new Intent(PessoaFisicaActivity.this,
                                PessoaJuridicaActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FancyAlertDialog.Builder
                        .with(PessoaFisicaActivity.this)
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
                        .onPositiveClicked(dialog -> Toast.makeText(PessoaFisicaActivity.this, "Cancelado com sucesso", Toast.LENGTH_SHORT).show())
                        .onNegativeClicked(dialog -> Toast.makeText(PessoaFisicaActivity.this, "Continue seu cadastro", Toast.LENGTH_SHORT).show())
                        .build()
                        .show();
            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PessoaFisicaActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void inicioFormulario() {
        edtCPF = findViewById(R.id.edtCPF);
        edtNomeCompleto = findViewById(R.id.edtNomeCompleto);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVoltar = findViewById(R.id.btnVoltar);
        novoClientePF = new ClientePF();
        novoVip = new Cliente();
        restaurarSharePreferences();
    }

    private void salvarSharePreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("cpf", edtCPF.getText().toString());
        dados.putString("nomeCompleto", edtNomeCompleto.getText().toString());
        dados.apply();
    }

    private void restaurarSharePreferences() {
        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessaoFisica", true);

    }

    private Boolean validaFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(edtCPF.getText().toString())) {
            edtCPF.setError("*");
            edtCPF.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(edtNomeCompleto.getText().toString())) {
            edtNomeCompleto.setError("*");
            edtNomeCompleto.requestFocus();
            retorno = false;
        }

        return retorno;
    }

}