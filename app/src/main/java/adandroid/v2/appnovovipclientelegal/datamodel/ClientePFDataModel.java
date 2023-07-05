package adandroid.v2.appnovovipclientelegal.datamodel;

//MOR - Modelo Objeto Relacional

public class ClientePFDataModel {


    public static final String TABELA = "clientePF";
    public static final String ID = "id";
    public static final String FK = "clienteID";
    public static final String CPF = "cpf";
    public static final String NOME_COMPLETO = "nomeCompleto";
    public static final String DATA_INC = "datainc";
    public static final String DATA_ALT = "dataalt";

    public static  String query;

    public static String gerarTabela(){

      query = "CREATE TABLE "+TABELA+" ( ";
      query += ID+" INTEGER PRIMARY KEY AUTOINCREMENT, ";
      query += FK+" INTEGER, ";
      query += CPF+" TEXT, ";
      query += NOME_COMPLETO+" TEXT, ";
      query += DATA_INC+" TEXT, ";
      query += DATA_ALT+" TEXT, ";
      query += "FOREIGN KEY("+FK+") REFERENCES cliente(id) ";

      query += ")";

        return query;
    }
}
