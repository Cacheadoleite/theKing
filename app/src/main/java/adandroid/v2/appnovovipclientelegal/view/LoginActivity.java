package adandroid.v2.appnovovipclientelegal.view;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.TextView;
import android.widget.Toast;


import adandroid.v2.appnovovipLegalcliente.R;
import adandroid.v2.appnovovipclientelegal.api.AppUtil;
import adandroid.v2.appnovovipclientelegal.controller.ClienteController;
import adandroid.v2.appnovovipclientelegal.model.Cliente;

import com.shashank.sony.fancydialoglib.FancyAlertDialog;

import java.util.List;

public class LoginActivity extends AppCompatActivity {


    Cliente cliente;
    private SharedPreferences preferences;
   AlertDialog dialog;

    EditText edtEmail;
    EditText edtSenhaB;
    CheckBox cbkLembrar;
    TextView txtRecuperar;
    Button btnAcessar;
    TextView btnSejaVip;
    TextView txtTermo;
    ClienteController controller;
    Boolean isFormularioOK, isLembrarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_novo);
        initFormulario();
        cliente = new Cliente();
        controller = new ClienteController(getApplicationContext());



       //  cliente.setId(15);
        // cliente.setPrimeiroNome("REIALINO");
        // cliente.setSobreNome("LETIE");
       //  cliente.setEmail("REIALDINO@gmail.com");
        // cliente.setSenha("_234");
       //  cliente.setPessoaFisica(true);

       //  controller.incluir(cliente);

       // controller.alterar(cliente);
      // controller.deletar(cliente);
        List<Cliente> clientes = controller.listar();


        

        btnSejaVip.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ClienteVipActivity.class);
            startActivity(intent);
            finish();
        });





        btnAcessar.setOnClickListener(v -> {

            if (isFormularioOK = validaFormulario()) {
               if( validarDadosDoUsuario()){
                   salvarSharePreferences();

                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                   startActivity(intent);
                   finish();

               }else {
                    Toast.makeText(getApplicationContext(),
                            "verifique os dados digitado",Toast.LENGTH_LONG).show();
                }

            }
        });


        txtRecuperar.setOnClickListener(v -> Toast.makeText(getApplicationContext(),
                "Carregando tela recuperação", Toast.LENGTH_LONG).show());
        txtTermo.setOnClickListener(v -> {
            FancyAlertDialog.Builder
                    .with(this)
                    .setTitle("Politica de privacidade e Termo de uso")
                    .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                    .setMessage("Do you really want to Exit ?")
                    .setNegativeBtnText("Discordo")
                    .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                    .setPositiveBtnText("Concordo")
                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  // for @ColorRes use setNegativeBtnBackgroundRes(R.color.colorvalue)
                    //.setAnimation(Animation.POP)
                    .isCancellable(true)
                    .setIcon(R.drawable.ibnaicon, View.VISIBLE)
                    .onPositiveClicked(dialog -> Toast.makeText(LoginActivity.this, "Obrigado seja bem vindo! continue com seu cadastro", Toast.LENGTH_SHORT).show())
                    .onNegativeClicked(dialog -> Toast.makeText(LoginActivity.this, "lamentamos, mas é necessario concordar com o termo de politica", Toast.LENGTH_SHORT).show())
                    .build()
                    .show();
        });

    }

    private Boolean validaFormulario() {
        boolean retorno = true;
        if (TextUtils.isEmpty(edtEmail.getText().toString())) {
            edtEmail.setError("*");
            edtEmail.requestFocus();
            retorno = false;
        }
        if (TextUtils.isEmpty(edtSenhaB.getText().toString())) {
            edtSenhaB.setError("*");
            edtSenhaB.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    private void initFormulario() {
        edtEmail = findViewById(R.id.edtEmail);
        edtSenhaB = findViewById(R.id.edtSenhaB);
        cbkLembrar = findViewById(R.id.cbkLembrar);
        txtRecuperar = findViewById(R.id.txtRecuperar);
        btnAcessar = findViewById(R.id.btnAcessar);
        btnSejaVip = findViewById(R.id.btnSejaViP);
        txtTermo = findViewById(R.id.txtTermo);
        isFormularioOK = false;
        cliente = new Cliente();
        restaurarSharePreferences();
    }

    public void lembraSenha(View view) {
        isLembrarSenha = cbkLembrar.isChecked();

    }

    private boolean validarDadosDoUsuario() {

        return true;
    }
    private void salvarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putBoolean("loginAutomatico",isLembrarSenha);
        dados.putString("emailCliente",edtEmail.getText().toString());
        dados.putString("senha",edtSenhaB.getText().toString());
        dados.apply();


    }

    private void restaurarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);

        cliente.setEmail(preferences.getString("email","teste@teste.com"));
        cliente.setSenha(preferences.getString("senha","12345"));
        cliente.setPrimeiroNome(preferences.getString("primeiroNome","cliente"));
        cliente.setSobreNome(preferences.getString("sobreNome","fake"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica",true));
        isLembrarSenha = preferences.getBoolean("loginAutomatico",false);


    }


}