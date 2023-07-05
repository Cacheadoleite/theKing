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


import com.shashank.sony.fancydialoglib.FancyAlertDialog;

public class CredencialDeAcessoActivity extends AppCompatActivity {

    Button btnCadastro;
    EditText edtNome;
    EditText edtEmail;
    EditText edtSenhaA;
    EditText edtSenhaB;
    CheckBox cbkTermoUso;
    Boolean isFormularioOK, isPessoaFisica;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        initFormulario();


        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFormularioOK = true;

               if(TextUtils.isEmpty(edtNome.getText().toString())){
                   edtNome.setError("*");
                   edtNome.findFocus();
                   isFormularioOK = false;
               }
                if(TextUtils.isEmpty(edtEmail.getText().toString())){
                    edtEmail.setError("*");
                    edtEmail.findFocus();
                    isFormularioOK = false;
                }
                if(TextUtils.isEmpty(edtSenhaA.getText().toString())){
                    edtSenhaA.setError("*");
                    edtSenhaA.findFocus();
                    isFormularioOK = false;
                }
                if(TextUtils.isEmpty(edtSenhaB.getText().toString())){
                    edtSenhaB.setError("*");
                   edtSenhaB.findFocus();
                    isFormularioOK = false;
                }
                if(!cbkTermoUso.isChecked()){
                    isFormularioOK = false;
                }

                if (isFormularioOK) {
                    if(!validarSenha()){
                        edtSenhaA.setError("*");
                        edtSenhaB.setError("*");
                        edtSenhaA.findFocus();

                        FancyAlertDialog.Builder
                                .with(CredencialDeAcessoActivity.this)
                                .setTitle("ATENÇÃO")
                                .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                                .setMessage("Senha diferente, tente novamente.")
                                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                                .setPositiveBtnText("Continuar")
                                .isCancellable(true)
                                .setIcon(R.drawable.ibnaicon, View.VISIBLE)
                                .build()
                                .show();

                    }else {
                         salvarSharePreferences();
                        Intent iTelaPricipal = new Intent(CredencialDeAcessoActivity.this,LoginActivity.class);
                        startActivity(iTelaPricipal);
                        finish();
                    }
                }
            }

            private boolean validarSenha() {
                boolean retorno = false;
               int senhaA, senhaB;
               senhaA =Integer.parseInt(edtSenhaA.getText().toString());
               senhaB =Integer.parseInt(edtSenhaB.getText().toString());
               retorno = senhaA == senhaB;
               return retorno;
            }
        });
    }

    private void initFormulario() {
        btnCadastro = findViewById(R.id.btnCadastro);
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenhaA = findViewById(R.id.edtSenhaA);
        edtSenhaB = findViewById(R.id.edtSenhaB);
        cbkTermoUso = findViewById(R.id.cbkTermoUso);
        isFormularioOK = false;
        restaurarSharePreferences();
    }

    public void validaTermo(View view) {
        if(!cbkTermoUso.isChecked()){
            Toast.makeText(getApplicationContext(),
                    "É necessario aceitar os termos de uso para continuar o cadastro",Toast.LENGTH_LONG).show();
        }
    }
    private void salvarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
       dados.putString("email",edtEmail.getText().toString());
       dados.putString("senha",edtSenhaA.getText().toString());
        dados.apply();
    }
    private void restaurarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica",true);
        if(isPessoaFisica)
        edtNome.setText(preferences.getString("nomeCompleto","verifique os dados"));
        else
            edtNome.setText(preferences.getString("razaoSocial","verifique os dados"));


    }
}