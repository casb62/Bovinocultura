package br.com.casb;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Vaca extends Bovino {

    private int numeroCrias;
    private boolean brucelose;
    private ArrayList<Parto> partos;
    
    public Vaca() {

    }

    public Vaca(int numero, String sexo, String dataNascimento, int idade, int numeroMae, boolean nascidoPropriedade, boolean mortoPropriedade, boolean vendido, String dataObito, String dataVenda, int numeroCrias, boolean brucelose, ArrayList<Parto> partos) {
        super(numero, sexo, dataNascimento, idade, numeroMae, nascidoPropriedade, mortoPropriedade, vendido, dataObito, dataVenda);
        this.numeroCrias = numeroCrias;
        this.brucelose = brucelose;
        partos = new ArrayList<>();
    }

    public int getNumeroCrias() {
        return numeroCrias;
    }

    public void setNumeroCrias(int numeroCrias) {
        this.numeroCrias = numeroCrias;
    }

    public boolean isBrucelose() {
        return brucelose;
    }

    public void setBrucelose(boolean brucelose) {
        this.brucelose = brucelose;
    }

    public void incrementarCrias() {
        numeroCrias++;
    }
    
    public void adicionarParto(Parto parto){
        partos.add(parto);
    }
    
    public void excluirParto(Parto parto){
        partos.remove(parto);
    }
    
    public int quantidadePartos(){
        return partos.size();
    }
    
    public Parto getParto(int posicao){
        return partos.get(posicao);
    }
    
    public ArrayList<Parto> obterPartos(){
        return partos;
    }
                
    /**
     * Grava os atributos de um animal por vez e incrementa em 1 o número de
     * crias da mãe do animal.
     *
     * @throws IOException
     * @throws ParseException
     */
    public void declararNascimento() throws IOException, ParseException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String dn = leitor.nextLine();
        if (dn.equalsIgnoreCase("s")) {
            System.out.println("Digite o número do animal: ");
            Scanner na = new Scanner(System.in);
            super.setNumero(na.nextInt());
            super.setSexo("fêmea");
            System.out.println("Digite a data de nascimento do animal: ");
            Scanner da = new Scanner(System.in);
            String dna = da.nextLine();
            super.setDataNascimento(dna);
            super.setIdade(0);
            System.out.println("Digite o número da mãe do animal: ");
            Scanner nm = new Scanner(System.in);
            int nma = nm.nextInt();
            super.setNumeroMae(nma);
            super.setNascidoPropriedade(true);
            super.setMortoPropriedade(false);
            super.setVendido(false);
            super.setDataObito("null      ");
            super.setDataVenda("null      ");
            this.setNumeroCrias(0);
            this.setBrucelose(false);
            BancoDeDados.cadastrarUmaVaca(this);//Método estático, pode ser acessado sem instanciar a classe.
            ArrayList<Vaca> vacas = Vaca.atualizarIdade();
            for (Vaca vaca : vacas) {
                if (vaca.getNumero() == nma) {
                    vaca.incrementarCrias();
                    Parto parto = new Parto(dna);
                    vaca.adicionarParto(parto);
                }
            }
            BancoDeDados.cadastrarTodasVacas(vacas);
        }
    }//Fim do método declararNascimento().

    /**
     * Grava os atributos de um animal por vez. Após, é necessário declarar a
     * vacinação contra brucelose.
     */
    @Override
    public void declararCompra() {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String dc = leitor.nextLine();
        if (dc.equalsIgnoreCase("s")) {
            System.out.println("Digite o número do animal: ");
            Scanner na = new Scanner(System.in);
            super.setNumero(na.nextInt());
            super.setSexo("fêmea");
            System.out.println("Digite a data de nascimento do animal: ");
            Scanner da = new Scanner(System.in);
            super.setDataNascimento(da.nextLine());
            super.setIdade(0);
            super.setNumeroMae(0);
            super.setNascidoPropriedade(false);
            super.setMortoPropriedade(false);
            super.setVendido(false);
            super.setDataObito("null      ");
            super.setDataVenda("null      ");
            this.setNumeroCrias(0);
            this.setBrucelose(false);
            BancoDeDados.cadastrarUmaVaca(this);
        }
    }//Fim do método declararCompra().

    /**
     * Declara o animal como morto, mantendo-o no ArrayList e calcula sua idade
     * até a data do óbito.
     */
    @Override
    public void declararMorte() {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String dm = leitor.nextLine();
        if (dm.equalsIgnoreCase("s")) {
            try {
                ArrayList<Vaca> vacas = Vaca.atualizarIdade();
                for (Vaca vaca : vacas) {
                    System.out.println("Animal nº " + vaca.getNumero() + ", " + vaca.getSexo() + ", nascido em " + vaca.getDataNascimento() + ", com " + vaca.getIdade() + " dias de idade, filho da vaca nº " + vaca.getNumeroMae() + ", nascido na propriedade? " + vaca.isNascidoPropriedade() + ", morto na propriedade? " + vaca.isMortoPropriedade() + ", vendido? " + vaca.isVendido() + ", data do óbito " + vaca.getDataObito() + ", data de venda " + vaca.getDataVenda() + ", número de crias " + vaca.getNumeroCrias() + ", brucelose " + vaca.isBrucelose() + ".");
                }
                System.out.println("\nQual o número do animal que deseja declarar a morte?\n");
                int numero = leitor.nextInt();
                for (Vaca vaca : vacas) {
                    if (vaca.getNumero() == numero) {
                        vaca.setMortoPropriedade(true);
                        System.out.println("Digite a data de óbito do animal: ");
                        Scanner leitura = new Scanner(System.in);
                        String doa = leitura.nextLine();
                        vaca.setDataObito(doa);
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date dn = df.parse(vaca.getDataNascimento());
                        Date dob = df.parse(vaca.getDataObito());
                        long dataNascimento = dn.getTime();
                        long dataObito = dob.getTime();
                        long idade = ((dataObito - dataNascimento) / (1000 * 60 * 60 * 24)) + 1;
                        int idadeInt = (int) idade;
                        vaca.setIdade(idadeInt);
                    }
                }
                BancoDeDados.cadastrarTodasVacas(vacas);
            } catch (IOException ex) {
                Logger.getLogger(Boi.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Boi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//Fim do método declararMorte().

    /**
     * Declara o animal como vendido, mantendo-o no ArrayList e calcula sua
     * idade até a data da venda.
     */
    @Override
    public void declararVenda() {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String dv = leitor.nextLine();
        if (dv.equalsIgnoreCase("s")) {
            try {
                ArrayList<Vaca> vacas = Vaca.atualizarIdade();
                for (Vaca vaca : vacas) {
                    System.out.println("Animal nº " + vaca.getNumero() + ", " + vaca.getSexo() + ", nascido em " + vaca.getDataNascimento() + ", com " + vaca.getIdade() + " dias de idade, filho da vaca nº " + vaca.getNumeroMae() + ", nascido na propriedade? " + vaca.isNascidoPropriedade() + ", morto na propriedade? " + vaca.isMortoPropriedade() + ", vendido? " + vaca.isVendido() + ", data do óbito " + vaca.getDataObito() + ", data de venda " + vaca.getDataVenda() + ", número de crias " + vaca.getNumeroCrias() + ", brucelose " + vaca.isBrucelose() + ".");
                }
                System.out.println("\nQual o número do animal que deseja declarar a venda?\n");
                int numero = leitor.nextInt();
                for (Vaca vaca : vacas) {
                    if (vaca.getNumero() == numero) {
                        vaca.setVendido(true);
                        System.out.println("Digite a data de venda do animal: ");
                        Scanner leitura = new Scanner(System.in);
                        String dva = leitura.nextLine();
                        vaca.setDataVenda(dva);
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date dn = df.parse(vaca.getDataNascimento());
                        Date dvn = df.parse(vaca.getDataVenda());
                        long dataNascimento = dn.getTime();
                        long dataVenda = dvn.getTime();
                        long idade = ((dataVenda - dataNascimento) / (1000 * 60 * 60 * 24)) + 1;
                        int idadeInt = (int) idade;
                        vaca.setIdade(idadeInt);
                    }
                }
                BancoDeDados.cadastrarTodasVacas(vacas);
            } catch (IOException ex) {
                Logger.getLogger(Vaca.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Vaca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//Fim do método declararVenda().

    public void declararBrucelose() throws IOException, ParseException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String db = leitor.nextLine();
        if (db.equalsIgnoreCase("s")) {
            ArrayList<Vaca> vacas = Vaca.atualizarIdade();
            System.out.println("As seguintes fêmeas não receberam vacina contra brucelose: ");
            int contador = 0;
            boolean pulo = false;
            for (Vaca vaca : vacas) {
                if (vaca.isBrucelose() == true) {
                    contador++;
                    if (contador == vacas.size()) {
                        pulo = true;
                    }
                } else if (vaca.isBrucelose() == false) {
                    System.out.println("Fêmea nº " + vaca.getNumero() + ".");
                }
            }
            if (pulo) {
                System.out.println("Todas as fêmeas receberam vacina contra brucelose!\n");
            } else {
                System.out.println("\nQual o número da fêmea que deseja declarar como vacinada?\n");
            }
            int numero = leitor.nextInt();
            for (Vaca vaca : vacas) {
                if (vaca.getNumero() == numero) {
                    vaca.setBrucelose(true);
                }
            }
            BancoDeDados.cadastrarTodasVacas(vacas);
        }
    }//Fim do método declararBrucelose().

    /**
     * Recebe um ArrayList de vacas, separa as que estão na propriedade e
     * calcula a idade de cada uma em dias. Usado nos métodos para pesquisar
     * vaca, declarar nascimento, venda e morte, listar mortos e vendidos,
     * atualizar animais e categorias.
     *
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static ArrayList<Vaca> atualizarIdade() throws IOException, ParseException {
        BancoDeDados bd = new BancoDeDados();
        ArrayList<Vaca> vacas = bd.recuperarVacas();
        for (Vaca vaca : vacas) {
            if (vaca.isMortoPropriedade() == false && vaca.isVendido() == false) {
                try {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date dn = df.parse(vaca.getDataNascimento());
                    long dataNascimento = dn.getTime();
                    Date date = new Date();
                    long dataAtual = date.getTime();
                    long idade = ((dataAtual - dataNascimento) / (1000 * 60 * 60 * 24)) + 1;
                    int idadeInt = (int) idade;
                    vaca.setIdade(idadeInt);
                    BancoDeDados.cadastrarTodasVacas(vacas);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }
        return vacas;
    }//Fim do método atualizarIdade().

    /**
     * Imprime todos os atributos de uma fêmea.
     *
     * @throws IOException
     * @throws ParseException
     */
    public void pesquisarVaca() throws IOException, ParseException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String pv = leitor.nextLine();
        if (pv.equalsIgnoreCase("s")) {
            System.out.println("Digite o número da fêmea que deseja pesquisar: ");
            int numero = leitor.nextInt();
            ArrayList<Vaca> vacas = Vaca.atualizarIdade();
            for (Vaca vaca : vacas) {
                if (numero == vaca.getNumero()) {
                    System.out.println("\nNº do animal\tSexo\t\tNascimento\tIdade em dias\tNº da mãe\tNascido na propriedade\tMorto na propriedade\tVendido\t\tÓbito\t\tVenda\t\tNº de crias\tBrucelose");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(vaca.getNumero() + "\t\t" + vaca.getSexo() + "\t\t" + vaca.getDataNascimento() + "\t" + vaca.getIdade() + "\t\t" + vaca.getNumeroMae() + "\t\t" + vaca.isNascidoPropriedade() + "\t\t\t\t" + vaca.isMortoPropriedade() + "\t\t\t" + vaca.isVendido() + "\t\t" + vaca.getDataObito() + "\t" + vaca.getDataVenda() + "\t" + vaca.getNumeroCrias() + "\t\t" + vaca.isBrucelose());
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
        }
    }//Fim do método pesquisarVaca().
    
    public static void exibirPartos() throws IOException, ParseException{
        System.out.println("\nNº da mãe\tNº da cria\tSexo da cria\tTotal de crias\tDatas dos partos\tIdade nos partos\tTempo entre partos");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        ArrayList<Vaca> vacas = Vaca.atualizarIdade();
        for(Vaca vaca: vacas){
            System.out.println(vaca.getNumeroMae()+"\t\t"+vaca.getNumero()+"\t\t"+vaca.getSexo()+"\t\t"+vaca.getNumeroCrias() );
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
        }
    }

}//Fim da classe Vaca.
