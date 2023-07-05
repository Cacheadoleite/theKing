package adandroid.v2.appnovovipclientelegal.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import adandroid.v2.appnovovipLegalcliente.R;
import adandroid.v2.appnovovipclientelegal.api.AppUtil;
import adandroid.v2.appnovovipclientelegal.model.Cliente;
import adandroid.v2.appnovovipclientelegal.model.ClientePF;
import adandroid.v2.appnovovipclientelegal.model.ClientePJ;


import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Cliente cliente;
    ClientePF clientePF;
    ClientePJ clientePJ;
    TextView txtNomeCliente;

    private SharedPreferences preferences;
    List<Cliente> clientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFormulario();
        buscaListaDeClientes();
}

    private void buscaListaDeClientes() {
        clientes = new ArrayList<>();
        clientes.add(cliente);

        for (int i = 0; i < 50; i++) {
            cliente = new Cliente();
            cliente.setPrimeiroNome("Cliente nº "+i);
            clientes.add(cliente);
        }

        for (Cliente obj: clientes ) {

            Log.i(AppUtil.LOG_APP,"Obj: "+obj.getPrimeiroNome());

        }
    }
    private void initFormulario() {


   cliente = new Cliente();
   clientePJ = new ClientePJ();
   clientePF = new ClientePF();
   txtNomeCliente = findViewById(R.id.txtNomeCliente);
   restaurarSharePreferences();
   txtNomeCliente.setText("BEM VINDO "+cliente.getPrimeiroNome());
    }
    private void salvarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        //  dados.putBoolean("loginAutomatico",false);
        //   dados.apply();
    }

    private void restaurarSharePreferences(){
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        cliente.setPrimeiroNome(preferences.getString("primeiroNome","nulo"));
        cliente.setSobreNome(preferences.getString("sobreNome","nulo"));
        cliente.setEmail(preferences.getString("email","nulo"));
        cliente.setSenha(preferences.getString("senha","nulo"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica",true));

        clientePF.setCpf(preferences.getString("cpf","nulo"));
        clientePF.setNomeCompleto(preferences.getString("nomeCompleto","nulo"));

        clientePJ.setCnpj(preferences.getString("cnpj","nulo"));
        clientePJ.setRazaoSocial(preferences.getString("razaoSocial","nulo"));
        clientePJ.setMei(preferences.getBoolean("mei",false));
        clientePJ.setSimplesNacional(preferences.getBoolean("simpleNacional",false));
        clientePJ.setDataDeAbertura(preferences.getString("dataDeAbertura","nulo"));

    }
    public void excluirMinhaConta(View view) {
        FancyAlertDialog.Builder
                .with(MainActivity.this)
                .setTitle("EXCLUIR CONTA")
                .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                .setMessage( "Deseja mesmo excluir seus dados do aplicativo?")
                .setNegativeBtnText("NÃO")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  // for @ColorRes use setNegativeBtnBackgroundRes(R.color.colorvalue)
                .setAnimation(Animation.SLIDE)
                .isCancellable(true)
                .setIcon(R.drawable.ibnaicon, View.VISIBLE)
                .onNegativeClicked(dialog -> Toast.makeText(MainActivity.this, cliente.getPrimeiroNome()+", Esperamos em breve seu retorno!", Toast.LENGTH_SHORT).show())



                .onPositiveClicked(dialog -> Toast.makeText(MainActivity.this, cliente.getPrimeiroNome()+", Volte sempre, obrigado!", Toast.LENGTH_SHORT).show());
                 cliente = new Cliente();
                 clientePF = new ClientePF();
                 clientePJ = new ClientePJ();
                // lembra senha automatico tem que ser resetado
                // salvarSharePreferences();
                 finish() ;
                //.build()
                //.show();
    }

    public void meusDados(View view) {
        Log.i(AppUtil.LOG_APP,"*******CLIENTE******");

        Log.i(AppUtil.LOG_APP,"ID "+cliente.getId());
        Log.i(AppUtil.LOG_APP,"Primeiro Nome "+cliente.getPrimeiroNome());
        Log.i(AppUtil.LOG_APP,"SobreNome "+cliente.getSobreNome());
        Log.i(AppUtil.LOG_APP,"Email "+cliente.getEmail());
        Log.i(AppUtil.LOG_APP,"Senha "+cliente.getSenha());


        Log.i(AppUtil.LOG_APP,"*******CLIENTE PF******");

        Log.i(AppUtil.LOG_APP,"cpf "+clientePF.getCpf());
        Log.i(AppUtil.LOG_APP,"Nome Completo "+clientePF.getNomeCompleto());

        if(!cliente.isPessoaFisica())
            Log.i(AppUtil.LOG_APP,"*******CLIENTE PJ******");

        Log.i(AppUtil.LOG_APP,"cnpj "+clientePJ.getCnpj());
        Log.i(AppUtil.LOG_APP,"razaoSocial "+clientePJ.getRazaoSocial());
        Log.i(AppUtil.LOG_APP,"dataDeAbertura "+clientePJ.getDataDeAbertura());
        Log.i(AppUtil.LOG_APP,"simplesNacional "+clientePJ.isSimplesNacional());
        Log.i(AppUtil.LOG_APP,"mei "+clientePJ.isMei());
    }

    public void atualizarMeusDados(View view) {
        if(cliente.isPessoaFisica()) {
            cliente.setPrimeiroNome("francisco");
            cliente.setSobreNome("Costa");
            clientePF.setNomeCompleto("francisco moura costa");
            //salvarSharePreferences();
            Log.i(AppUtil.LOG_APP, "*******Atualizar dados do CLIENTE******");

            Log.i(AppUtil.LOG_APP, "Primeiro Nome " + cliente.getPrimeiroNome());
            Log.i(AppUtil.LOG_APP, "SobreNome " + cliente.getSobreNome());

            Log.i(AppUtil.LOG_APP, "*******Atualizar dados do CLIENTEPF******");
            Log.i(AppUtil.LOG_APP, "Nome Completo " + clientePF.getNomeCompleto());
        }else {
            clientePJ.setRazaoSocial("francisco ME");
            Log.i(AppUtil.LOG_APP, "*******Atualizar dados do CLIENTEPJ******");
            Log.i(AppUtil.LOG_APP,"razaoSocial "+clientePJ.getRazaoSocial());

        }
    }

    public void consultarClientesVip(View view) {
        Intent intent = new Intent(MainActivity.this,ConsultarClientesActivity.class);
        startActivity(intent);
    }


    public void sairDoAplicativo(View view) {
        FancyAlertDialog.Builder
                .with(MainActivity.this)
                .setTitle("SAIR DO APLICATIVO")
                .setBackgroundColor(Color.parseColor("#303F9F"))  // for @ColorRes use setBackgroundColorRes(R.color.colorvalue)
                .setMessage( "Deseja mesmo sair do aplicativo?")
                .setNegativeBtnText("NÃO")
                .setPositiveBtnBackground(Color.parseColor("#FF4081"))  // for @ColorRes use setPositiveBtnBackgroundRes(R.color.colorvalue)
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  // for @ColorRes use setNegativeBtnBackgroundRes(R.color.colorvalue)
                .setAnimation(Animation.SLIDE)
                .isCancellable(true)
                .setIcon(R.drawable.ibnaicon, View.VISIBLE)
                .onNegativeClicked(dialog -> Toast.makeText(MainActivity.this, cliente.getPrimeiroNome()+", Curta o aplicativo!", Toast.LENGTH_SHORT).show())
                .onPositiveClicked(dialog -> Toast.makeText(MainActivity.this, cliente.getPrimeiroNome()+", Volte sempre, obrigado!", Toast.LENGTH_SHORT).show())
                .build()
                .show();
    }

}
