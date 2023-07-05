package adandroid.v2.appnovovipclientelegal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import adandroid.v2.appnovovipLegalcliente.R;
import adandroid.v2.appnovovipclientelegal.api.ClienteAdapter;
import adandroid.v2.appnovovipclientelegal.controller.ClienteController;
import adandroid.v2.appnovovipclientelegal.model.Cliente;


import java.util.List;

public class ConsultarClientesActivity extends AppCompatActivity {
    ClienteAdapter adapter;
    ClienteController controller;
    List<Cliente> clientes;
    Cliente obj;

    RecyclerView rvClientesVip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_clientes);
        rvClientesVip = findViewById(R.id.rvClientesVip);
        controller = new ClienteController(getApplicationContext());
        // criando a lista de cliente
        clientes = controller.listar();

        /*for (int i = 0; i < 50; i++){

            obj = new Cliente();
            obj.setPrimeiroNome("cliente" +i);
            obj.setPessoaFisica(i % 2 ==0);
            clientes.add(obj);

        }*/

        adapter = new ClienteAdapter(clientes,getApplicationContext());

        rvClientesVip.setAdapter(adapter);
        rvClientesVip.setLayoutManager(new LinearLayoutManager(this));
    }
}
