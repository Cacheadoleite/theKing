package adandroid.v2.appnovovipclientelegal.api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import adandroid.v2.appnovovipLegalcliente.R;
import adandroid.v2.appnovovipclientelegal.model.Cliente;


import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {
  //adicionar as caracterista da classe
    private List<Cliente> aClientes;  //lista de cliente adapter
    private Context aContext;

    public ClienteAdapter(List<Cliente> aClientes, Context aContext) {
        this.aClientes = aClientes;
        this.aContext = aContext;
    }

    @NonNull
    @Override
    public ClienteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View linhaView = inflater.inflate(R.layout.linha_detalhe_cliente,parent,false);
        ViewHolder viewHolder = new ViewHolder(linhaView);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolder holder, int position) {
        Cliente objLinha = aClientes.get(position);

        TextView txtPrimeiroNome = holder.rvPrimeiroNome;
        txtPrimeiroNome.setText(objLinha.getPrimeiroNome());

        Button btnPessoaFisica = holder.rvPessoaFisica;
        btnPessoaFisica.setText(objLinha.isPessoaFisica() ? "CPF" : "CNPJ");
    }

    @Override
    public int getItemCount() {
        return aClientes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       // o viewholder é responsavel para o click linha linha
        public TextView rvPrimeiroNome ;
        public Button rvPessoaFisica ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rvPrimeiroNome = itemView.findViewById(R.id.rvPrimeiroNome);
            rvPessoaFisica = itemView.findViewById(R.id.rvPessoaFisica);

            itemView.setOnClickListener(ViewHolder.this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
           Cliente clienteSelecionado = aClientes.get(position);

            if(position != RecyclerView.NO_POSITION){
                Log.i(AppUtil.LOG_APP,
                        "Clente ID"+position+"Primeiro Nome"+clienteSelecionado.getPrimeiroNome());
                Toast.makeText(aContext,
                        "Clente ID"+position+"Primeiro Nome"+clienteSelecionado.getPrimeiroNome(),Toast.LENGTH_LONG).show();


            }

        }
    }
}
