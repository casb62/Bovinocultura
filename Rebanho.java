package br.com.casb;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rebanho {

    private int m0a12;
    private int m13a24;
    private int m25a36;
    private int m37a60;
    private int mmais60;
    private int f0a2;
    private int f3a8;
    private int f9a12;
    private int f13a24;
    private int f25a36;
    private int f37a60;
    private int fmais60;
    private int totalMachos;
    private int totalFemeas;
    private int totalGeral;

    public Rebanho() {
    }

    public Rebanho(int m0a12, int m13a24, int m25a36, int m37a60, int mmais60, int f0a2, int f3a8, int f9a12, int f13a24, int f25a36, int f37a60, int fmais60, int totalMachos, int totalFemeas, int totalGeral) {
        this.m0a12 = m0a12;
        this.m13a24 = m13a24;
        this.m25a36 = m25a36;
        this.m37a60 = m37a60;
        this.mmais60 = mmais60;
        this.f0a2 = f0a2;
        this.f3a8 = f3a8;
        this.f9a12 = f9a12;
        this.f13a24 = f13a24;
        this.f25a36 = f25a36;
        this.f37a60 = f37a60;
        this.fmais60 = fmais60;
        this.totalMachos = totalMachos;
        this.totalFemeas = totalFemeas;
        this.totalGeral = totalGeral;
    }

    /**
     * Recebe dois ArrayList, de machos e de fêmeas que estão na propriedade,
     * separa por faixas etárias e exibe os totais por faixas etárias, por sexo
     * e o total geral, e o percentual de cada faixa em relação ao total de
     * animais presentes na propriedade.
     *
     * @throws IOException
     * @throws ParseException
     */
    public static void atualizarCategorias() throws IOException, ParseException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String ac = leitor.nextLine();
        if (ac.equalsIgnoreCase("s")) {
            Rebanho rebanho = new Rebanho(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            ArrayList<Boi> bois = Boi.atualizarIdade();
            double contadorBoi = 0;
            for (Boi boi : bois) {
                if ((boi.isMortoPropriedade() == false) && (boi.isVendido() == false)) {
                    contadorBoi++;
                    if ((boi.getIdade() > 0) && (boi.getIdade() <= 360)) {
                        rebanho.m0a12++;
                    } else if ((boi.getIdade() > 360) && (boi.getIdade() <= 720)) {
                        rebanho.m13a24++;
                    } else if ((boi.getIdade() > 720) && (boi.getIdade() <= 1080)) {
                        rebanho.m25a36++;
                    } else if ((boi.getIdade() > 1080) && (boi.getIdade() <= 1800)) {
                        rebanho.m37a60++;
                    } else {//if((boi.getIdade() > 1800))
                        rebanho.mmais60++;
                    }
                }
            }
            ArrayList<Vaca> vacas = Vaca.atualizarIdade();
            double contadorVaca = 0;
            for (Vaca vaca : vacas) {
                if ((vaca.isMortoPropriedade() == false) && (vaca.isVendido() == false)) {
                    contadorVaca++;
                    if ((vaca.getIdade() > 0) && (vaca.getIdade() <= 89)) {
                        rebanho.f0a2++;
                    } else if ((vaca.getIdade() > 89) && (vaca.getIdade() <= 240)) {
                        rebanho.f3a8++;
                    } else if ((vaca.getIdade() > 240) && (vaca.getIdade() <= 360)) {
                        rebanho.f9a12++;
                    } else if ((vaca.getIdade() > 360) && (vaca.getIdade() <= 720)) {
                        rebanho.f13a24++;
                    } else if ((vaca.getIdade() > 720) && (vaca.getIdade() <= 1080)) {
                        rebanho.f25a36++;
                    } else if ((vaca.getIdade() > 1080) && (vaca.getIdade() <= 1800)) {
                        rebanho.f37a60++;
                    } else {//if((vaca.getIdade() > 1800))
                        rebanho.fmais60++;
                    }
                }
            }
            System.out.printf("\nMachos de 0 a 12 meses: %d\t%.2f%s\n", rebanho.m0a12, (rebanho.m0a12 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Machos de 13 a 24 meses: %d\t%.2f%s\n", rebanho.m13a24, (rebanho.m13a24 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Machos de 25 a 36 meses: %d\t%.2f%s\n", rebanho.m25a36, (rebanho.m25a36 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Machos de 37 a 60 meses: %d\t%.2f%s\n", rebanho.m37a60, (rebanho.m37a60 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Machos acima de 60 meses: %d\t%.2f%s\n", rebanho.mmais60, (rebanho.mmais60 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("\nFêmeas de 0 a 2 meses: %d\t%.2f%s\n", rebanho.f0a2, (rebanho.f0a2 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Fêmeas de 3 a 8 meses. Em idade para vacinar contra brucelose: %d\t%.2f%s\n", rebanho.f3a8, (rebanho.f3a8 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Fêmeas de 9 a 12 meses: %d\t%.2f%s\n", rebanho.f9a12, (rebanho.f9a12 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Fêmeas de 13 a 24 meses: %d\t%.2f%s\n", rebanho.f13a24, (rebanho.f13a24 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Fêmeas de 25 a 36 meses: %d\t%.2f%s\n", rebanho.f25a36, (rebanho.f25a36 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Fêmeas de 37 a 60 meses: %d\t%.2f%s\n", rebanho.f37a60, (rebanho.f37a60 * 100) / (contadorBoi + contadorVaca), "%");
            System.out.printf("Fêmeas acima de 60 meses: %d\t%.2f%s\n", rebanho.fmais60, (rebanho.fmais60 * 100) / (contadorBoi + contadorVaca), "%");
            rebanho.totalMachos = rebanho.m0a12 + rebanho.m13a24 + rebanho.m25a36 + rebanho.m37a60 + rebanho.mmais60;
            System.out.printf("\nTotal de machos: %d\t%.2f%s\n", rebanho.totalMachos, (rebanho.totalMachos * 100) / (contadorBoi + contadorVaca), "%");
            rebanho.totalFemeas = rebanho.f0a2 + rebanho.f3a8 + rebanho.f9a12 + rebanho.f13a24 + rebanho.f25a36 + rebanho.f37a60 + rebanho.fmais60;
            System.out.printf("Total de fêmeas: %d\t%.2f%s\n", rebanho.totalFemeas, (rebanho.totalFemeas * 100) / (contadorBoi + contadorVaca), "%");
            rebanho.totalGeral = rebanho.totalMachos + rebanho.totalFemeas;
            System.out.printf("Total geral: %d\t\t%.2f%s\n\n", rebanho.totalGeral, (rebanho.totalGeral * 100) / (contadorBoi + contadorVaca), "%");
        }
    }//Fim do método atualizarCategorias().

    /**
     * Recebe dois ArrayList, de machos e de fêmeas que estão na propriedade, e
     * exibe todos os atributos de todos animais, um por um.
     *
     * @throws IOException
     * @throws ParseException
     */
    public static void atualizarAnimais() throws IOException, ParseException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String aa = leitor.nextLine();
        if (aa.equalsIgnoreCase("s")) {
            System.out.println("\nNº do animal\tSexo\t\tNascimento\tIdade em dias\tNº da mãe\tNascido na propriedade\tMorto na propriedade\tVendido\t\tÓbito\t\tVenda\t\tNº de crias\tBrucelose");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ArrayList<Boi> bois = Boi.atualizarIdade();
            for (Boi boi : bois) {
                if ((boi.isMortoPropriedade() == false) && (boi.isVendido() == false)) {
                    System.out.println(boi.getNumero() + "\t\t" + boi.getSexo() + "\t\t" + boi.getDataNascimento() + "\t" + boi.getIdade() + "\t\t" + boi.getNumeroMae() + "\t\t" + boi.isNascidoPropriedade() + "\t\t\t\t" + boi.isMortoPropriedade() + "\t\t\t" + boi.isVendido() + "\t\t" + boi.getDataObito() + "\t" + boi.getDataVenda());
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            ArrayList<Vaca> vacas = Vaca.atualizarIdade();
            for (Vaca vaca : vacas) {
                if ((vaca.isMortoPropriedade() == false) && (vaca.isVendido() == false)) {
                    System.out.println(vaca.getNumero() + "\t\t" + vaca.getSexo() + "\t\t" + vaca.getDataNascimento() + "\t" + vaca.getIdade() + "\t\t" + vaca.getNumeroMae() + "\t\t" + vaca.isNascidoPropriedade() + "\t\t\t\t" + vaca.isMortoPropriedade() + "\t\t\t" + vaca.isVendido() + "\t\t" + vaca.getDataObito() + "\t" + vaca.getDataVenda() + "\t" + vaca.getNumeroCrias() + "\t\t" + vaca.isBrucelose());
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            System.out.println("\n");
        }
    }//Fim do método atualizarAnimais().

    /**
     * Recebe dois ArrayList, de machos e de fêmeas, e exibe os que morreram e
     * seu percentual em relação ao total de animais que estão ou que já
     * passaram pela propriedade.
     *
     * @throws IOException
     * @throws ParseException
     */
    public static void listarMortos() throws IOException, ParseException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String lm = leitor.nextLine();
        if (lm.equalsIgnoreCase("s")) {
            System.out.println("\nNº do animal\tSexo\t\tNascimento\tIdade em dias\tNº da mãe\tNascido na propriedade\tMorto na propriedade\tVendido\t\tÓbito\t\tVenda\t\tNº de crias\tBrucelose");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ArrayList<Boi> bois = Boi.atualizarIdade();
            double contadorBoi = 0;
            for (Boi boi : bois) {
                if (boi.isMortoPropriedade() == true) {
                    contadorBoi++;
                    System.out.println(boi.getNumero() + "\t\t" + boi.getSexo() + "\t\t" + boi.getDataNascimento() + "\t" + boi.getIdade() + "\t\t" + boi.getNumeroMae() + "\t\t" + boi.isNascidoPropriedade() + "\t\t\t\t" + boi.isMortoPropriedade() + "\t\t\t" + boi.isVendido() + "\t\t" + boi.getDataObito() + "\t" + boi.getDataVenda());
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            ArrayList<Vaca> vacas = Vaca.atualizarIdade();
            double contadorVaca = 0;
            for (Vaca vaca : vacas) {
                if (vaca.isMortoPropriedade() == true) {
                    contadorVaca++;
                    System.out.println(vaca.getNumero() + "\t\t" + vaca.getSexo() + "\t\t" + vaca.getDataNascimento() + "\t" + vaca.getIdade() + "\t\t" + vaca.getNumeroMae() + "\t\t" + vaca.isNascidoPropriedade() + "\t\t\t\t" + vaca.isMortoPropriedade() + "\t\t\t" + vaca.isVendido() + "\t\t" + vaca.getDataObito() + "\t" + vaca.getDataVenda() + "\t" + vaca.getNumeroCrias() + "\t\t" + vaca.isBrucelose());
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            System.out.printf("\nTotal de animais mortos: %.0f\t%.2f%s\n", (contadorBoi + contadorVaca), ((contadorBoi + contadorVaca) * 100) / (vacas.size() + bois.size()), "%");
        }
    }//Fim do método listarMortos().

    /**
     * Recebe dois ArrayList, de machos e de fêmeas, e exibe os que foram
     * vendidos e seu percentual em relação ao total de animais que estão ou que
     * já passaram pela propriedade.
     *
     * @throws IOException
     * @throws ParseException
     */
    public static void listarVendidos() throws IOException, ParseException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String lv = leitor.nextLine();
        if (lv.equalsIgnoreCase("s")) {
            System.out.println("\nNº do animal\tSexo\t\tNascimento\tIdade em dias\tNº da mãe\tNascido na propriedade\tMorto na propriedade\tVendido\t\tÓbito\t\tVenda\t\tNº de crias\tBrucelose");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            ArrayList<Boi> bois = Boi.atualizarIdade();
            double contadorBoi = 0;
            for (Boi boi : bois) {
                if (boi.isVendido() == true) {
                    contadorBoi++;
                    System.out.println(boi.getNumero() + "\t\t" + boi.getSexo() + "\t\t" + boi.getDataNascimento() + "\t" + boi.getIdade() + "\t\t" + boi.getNumeroMae() + "\t\t" + boi.isNascidoPropriedade() + "\t\t\t\t" + boi.isMortoPropriedade() + "\t\t\t" + boi.isVendido() + "\t\t" + boi.getDataObito() + "\t" + boi.getDataVenda());
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            ArrayList<Vaca> vacas = Vaca.atualizarIdade();
            double contadorVaca = 0;
            for (Vaca vaca : vacas) {
                if (vaca.isVendido() == true) {
                    contadorVaca++;
                    System.out.println(vaca.getNumero() + "\t\t" + vaca.getSexo() + "\t\t" + vaca.getDataNascimento() + "\t" + vaca.getIdade() + "\t\t" + vaca.getNumeroMae() + "\t\t" + vaca.isNascidoPropriedade() + "\t\t\t\t" + vaca.isMortoPropriedade() + "\t\t\t" + vaca.isVendido() + "\t\t" + vaca.getDataObito() + "\t" + vaca.getDataVenda() + "\t" + vaca.getNumeroCrias() + "\t\t" + vaca.isBrucelose());
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            System.out.printf("\nTotal de animais vendidos: %.0f\t%.2f%s\n", (contadorBoi + contadorVaca), ((contadorBoi + contadorVaca) * 100) / (vacas.size() + bois.size()), "%");
        }
    }//Fim do método listarVendidos.

}//Fim da classe Rebanho.
