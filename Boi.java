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

public class Boi extends Bovino {

    public Boi() {
    }

    public Boi(int numero, String sexo, String dataNascimento, int idade, int numeroMae, boolean nascidoPropriedade, boolean mortoPropriedade, boolean vendido, String dataObito, String dataVenda) {
        super(numero, sexo, dataNascimento, idade, numeroMae, nascidoPropriedade, mortoPropriedade, vendido, dataObito, dataVenda);
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
            super.setSexo("macho");
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
            BancoDeDados.cadastrarUmBoi(this);//Método estático, pode ser acessado sem instanciar a classe.
            ArrayList<Vaca> vacas = Vaca.atualizarIdade();
            for (Vaca vaca : vacas) {
                if (vaca.getNumero() == nma) {
                    vaca.incrementarCrias();
                    Parto parto = new Parto(dna);
                    vaca.adicionarParto(parto);
                }
                BancoDeDados.cadastrarTodasVacas(vacas);
            }
        }
    }//Fim do método declararNascimento.

    /**
     * Grava os atributos de um animal por vez.
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
            super.setSexo("macho");
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
            BancoDeDados.cadastrarUmBoi(this);
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
                ArrayList<Boi> bois = Boi.atualizarIdade();
                for (Boi boi : bois) {
                    System.out.println("Animal nº " + boi.getNumero() + ", " + boi.getSexo() + ", nascido em " + boi.getDataNascimento() + ", com " + boi.getIdade() + " dias de idade, filho da vaca nº " + boi.getNumeroMae() + ", nascido na propriedade? " + boi.isNascidoPropriedade() + ", morto na propriedade? " + boi.isMortoPropriedade() + ", vendido? " + boi.isVendido() + ", data do óbito " + boi.getDataObito() + ", data de venda " + boi.getDataVenda() + ".");
                }
                System.out.println("\nQual o número do animal que deseja declarar a morte?\n");
                int numero = leitor.nextInt();
                for (Boi boi : bois) {
                    if (boi.getNumero() == numero) {
                        boi.setMortoPropriedade(true);
                        System.out.println("Digite a data de óbito do animal: ");
                        Scanner leitura = new Scanner(System.in);
                        String doa = leitura.nextLine();
                        boi.setDataObito(doa);
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        Date dn = df.parse(boi.getDataNascimento());
                        Date dob = df.parse(boi.getDataObito());
                        long dataNascimento = dn.getTime();
                        long dataObito = dob.getTime();
                        long idade = ((dataObito - dataNascimento) / (1000 * 60 * 60 * 24)) + 1;
                        int idadeInt = (int) idade;
                        boi.setIdade(idadeInt);
                    }
                }
                BancoDeDados.cadastrarTodosBois(bois);
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
                ArrayList<Boi> bois = Boi.atualizarIdade();
                for (Boi boi : bois) {
                    System.out.println("Animal nº " + boi.getNumero() + ", " + boi.getSexo() + ", nascido em " + boi.getDataNascimento() + ", com " + boi.getIdade() + " dias de idade, filho da vaca nº " + boi.getNumeroMae() + ", nascido na propriedade? " + boi.isNascidoPropriedade() + ", morto na propriedade? " + boi.isMortoPropriedade() + ", vendido? " + boi.isVendido() + ", data do óbito " + boi.getDataObito() + ", data de venda " + boi.getDataVenda() + ".");
                }
                System.out.println("\nQual o número do animal que deseja declarar a venda?\n");
                int numero = leitor.nextInt();
                for (Boi boi : bois) {
                    if (boi.getNumero() == numero) {
                        boi.setVendido(true);
                    }
                    System.out.println("Digite a data de venda do animal: ");
                    Scanner leitura = new Scanner(System.in);
                    String dva = leitura.nextLine();
                    boi.setDataVenda(dva);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date dn = df.parse(boi.getDataNascimento());
                    Date dvn = df.parse(boi.getDataVenda());
                    long dataNascimento = dn.getTime();
                    long dataVenda = dvn.getTime();
                    long idade = ((dataVenda - dataNascimento) / (1000 * 60 * 60 * 24)) + 1;
                    int idadeInt = (int) idade;
                    boi.setIdade(idadeInt);
                }
                BancoDeDados.cadastrarTodosBois(bois);
            } catch (IOException ex) {
                Logger.getLogger(Boi.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Boi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//Fim do método declararVenda().

    /**
     * Recebe um ArrayList de bois, separa os que estão na propriedade e calcula
     * a idade de cada um em dias. Usado nos métodos para pesquisar boi,
     * declarar nascimento, venda e morte, listar mortos e vendidos, atualizar
     * animais e categorias.
     *
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static ArrayList<Boi> atualizarIdade() throws IOException, ParseException {
        BancoDeDados bd = new BancoDeDados();
        ArrayList<Boi> bois = bd.recuperarBois();
        for (Boi boi : bois) {
            if (boi.isMortoPropriedade() == false && boi.isVendido() == false) {
                try {
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Date dn = df.parse(boi.getDataNascimento());
                    long dataNascimento = dn.getTime();
                    Date date = new Date();
                    long dataAtual = date.getTime();
                    long idade = ((dataAtual - dataNascimento) / (1000 * 60 * 60 * 24)) + 1;
                    int idadeInt = (int) idade;
                    boi.setIdade(idadeInt);
                    BancoDeDados.cadastrarTodosBois(bois);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        }
        return bois;
    }//Fim do método atualizarIdade().

    /**
     * Imprime todos os atributos de um macho.
     *
     * @throws IOException
     * @throws ParseException
     */
    public void pesquisarBoi() throws IOException, ParseException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String pb = leitor.nextLine();
        if (pb.equalsIgnoreCase("s")) {
            System.out.println("Digite o número do macho que deseja pesquisar: ");
            int numero = leitor.nextInt();
            ArrayList<Boi> bois = Boi.atualizarIdade();
            for (Boi boi : bois) {
                if (numero == boi.getNumero()) {
                    System.out.println("\nNº do animal\tSexo\t\tNascimento\tIdade em dias\tNº da mãe\tNascido na propriedade\tMorto na propriedade\tVendido\t\tÓbito\t\tVenda\t\tNº de crias\tBrucelose");
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println(boi.getNumero() + "\t\t" + boi.getSexo() + "\t\t" + boi.getDataNascimento() + "\t" + boi.getIdade() + "\t\t" + boi.getNumeroMae() + "\t\t" + boi.isNascidoPropriedade() + "\t\t\t\t" + boi.isMortoPropriedade() + "\t\t\t" + boi.isVendido() + "\t\t" + boi.getDataObito() + "\t" + boi.getDataVenda());
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
        }
    }//Fim do método pesquisarBoi().

}//Fim da classe Boi.
