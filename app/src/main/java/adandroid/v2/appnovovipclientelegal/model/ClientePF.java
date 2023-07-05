package adandroid.v2.appnovovipclientelegal.model;

public class ClientePF extends Cliente{

    private int fk;
    private String cpf;
    private String nomeCompleto;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }
}
