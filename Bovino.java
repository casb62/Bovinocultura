package br.com.casb;

public abstract class Bovino {

    private int numero;
    private String sexo;
    private String dataNascimento;
    private int idade;
    private int numeroMae;
    private boolean nascidoPropriedade;
    private boolean mortoPropriedade;
    private boolean vendido;
    private String dataObito;
    private String dataVenda;
    //private double peso;
    //private double valor;
    //Pode ter um atributo que chama outra classe: Classe variável = new classe().

    public Bovino() {

    }

    public Bovino(int numero, String sexo, String dataNascimento, int idade, int numeroMae, boolean nascidoPropriedade, boolean mortoPropriedade, boolean vendido, String dataObito, String dataVenda) {
        this.numero = numero;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.numeroMae = numeroMae;
        this.nascidoPropriedade = nascidoPropriedade;
        this.mortoPropriedade = mortoPropriedade;
        this.vendido = vendido;
        this.dataObito = dataObito;
        this.dataVenda = dataVenda;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getNumeroMae() {
        return numeroMae;
    }

    public void setNumeroMae(int numeroMae) {
        this.numeroMae = numeroMae;
    }

    public boolean isNascidoPropriedade() {
        return nascidoPropriedade;
    }

    public void setNascidoPropriedade(boolean nascidoPropriedade) {
        this.nascidoPropriedade = nascidoPropriedade;
    }

    public boolean isMortoPropriedade() {
        return mortoPropriedade;
    }

    public void setMortoPropriedade(boolean mortoPropriedade) {
        this.mortoPropriedade = mortoPropriedade;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public String getDataObito() {
        return dataObito;
    }

    public void setDataObito(String dataObito) {
        this.dataObito = dataObito;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

    public abstract void declararCompra();

    public abstract void declararMorte();

    public abstract void declararVenda();

}//Fim da classe Bovino.
