package br.com.casb;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BancoDeDados {

    private final Path path1 = Paths.get("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/bois.txt");
    private final Charset utf8a = StandardCharsets.UTF_8;
    private final Path path2 = Paths.get("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/usuarios.txt");
    private final Charset utf8b = StandardCharsets.UTF_8;
    private final Path path3 = Paths.get("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/vacas.txt");
    private final Charset utf8c = StandardCharsets.UTF_8;
    private final Path path4 = Paths.get("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/partos.txt");
    private final Charset utf8d = StandardCharsets.UTF_8;

    /**
     * Grava os atributos de uma vaca por vez no arquivo vacas.txt. Usado para
     * declarar nascimento e compra de fêmeas.
     *
     * @param vaca
     */
    public static void cadastrarUmaVaca(Vaca vaca) {
        try {
            File arquivo = new File("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/vacas.txt");
            try (FileWriter fw = new FileWriter(arquivo, true); PrintWriter pw = new PrintWriter(fw)) {
                pw.println(vaca.getNumero() + ";" + vaca.getSexo() + ";" + vaca.getDataNascimento() + ";" + vaca.getIdade() + ";" + vaca.getNumeroMae() + ";" + vaca.isNascidoPropriedade() + ";" + vaca.isMortoPropriedade() + ";" + vaca.isVendido() + ";" + vaca.getDataObito() + ";" + vaca.getDataVenda() + ";" + vaca.getNumeroCrias() + ";" + vaca.isBrucelose() + ";" + vaca.obterPartos() + ";");
                pw.flush();
                pw.close();
                fw.close();
            }
        } catch (IOException e) {
        }
    }//Fim do método cadastrarUmaVaca().

    /**
     * Grava os atributos de um boi por vez no arquivo bois.txt. Usado para
     * declarar nascimento e compra de machos.
     *
     * @param boi
     */
    public static void cadastrarUmBoi(Boi boi) {
        try {
            File arquivo = new File("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/bois.txt");
            try (FileWriter fw = new FileWriter(arquivo, true); PrintWriter pw = new PrintWriter(fw)) {
                pw.println(boi.getNumero() + ";" + boi.getSexo() + ";" + boi.getDataNascimento() + ";" + boi.getIdade() + ";" + boi.getNumeroMae() + ";" + boi.isNascidoPropriedade() + ";" + boi.isMortoPropriedade() + ";" + boi.isVendido() + ";" + boi.getDataObito() + ";" + boi.getDataVenda() + ";");
                pw.flush();
                pw.close();
                fw.close();
            }
        } catch (IOException e) {

        }
    }//Fim do método cadastrarUmBoi().

    /**
     * Grava os atributos de um usuário por vez no arquivo usuários.txt. Usado
     * para incluir um usuário por vez para operar o sistema.
     *
     * @param usuario
     */
    public static void cadastrarUmUsuario(Usuario usuario) {
        try {
            File arquivo = new File("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/usuarios.txt");
            try (FileWriter fw = new FileWriter(arquivo, true); PrintWriter pw = new PrintWriter(fw)) {
                pw.println(usuario.getCpf() + ";" + usuario.getSenha() + ";" + usuario.getNome() + ";" + usuario.isUsuarioAutenticado() + ";" + usuario.getInscricaoEstadual() + ";");
                pw.flush();
                pw.close();
                fw.close();
            }
        } catch (IOException e) {

        }
    }//Fim do método cadastrarUmUsuario().

    /**
     * Faz a leitura do arquivo vacas.txt retornando um ArrayList com todas as
     * fêmeas cadastradas. Usado no método para atualizar idade das fêmeas.
     *
     * @return
     * @throws IOException
     */
    public ArrayList<Vaca> recuperarVacas() throws IOException {
        ArrayList<Vaca> vacas = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path3, utf8c)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] t = line.split(";");
                ArrayList<Parto> partos = new ArrayList<>();
                Vaca vaca = new Vaca(Integer.parseInt(t[0]), t[1], t[2], Integer.parseInt(t[3]), Integer.parseInt(t[4]), Boolean.parseBoolean(t[5]), Boolean.parseBoolean(t[6]), Boolean.parseBoolean(t[7]), t[8], t[9], Integer.parseInt(t[10]), Boolean.parseBoolean(t[11]), partos );
                vacas.add(vaca);
            }
        }
        return vacas;
    }//Fim do método recuperarVacas().

    /**
     * Faz a leitura do arquivo bois.txt retornando um ArrayList com todos os
     * machos cadastrados. Usado no método para atualizar idade dos machos.
     *
     * @return
     * @throws IOException
     */
    public ArrayList<Boi> recuperarBois() throws IOException {
        ArrayList<Boi> bois = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path1, utf8a)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] t = line.split(";");
                Boi boi = new Boi(Integer.parseInt(t[0]), t[1], t[2], Integer.parseInt(t[3]), Integer.parseInt(t[4]), Boolean.parseBoolean(t[5]), Boolean.parseBoolean(t[6]), Boolean.parseBoolean(t[7]), t[8], t[9]);
                bois.add(boi);
            }
        }
        return bois;
    }//Fim do método recuperarBois().

    /**
     * Faz a leitura do arquivo usuarios.txt retornando um ArrayList com todos
     * os usuários cadastrados. Usado nos métodos para excluir, pesquisar e
     * autenticar usuários.
     *
     * @return
     * @throws IOException
     */
    public ArrayList<Usuario> recuperarUsuarios() throws IOException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path2, utf8b)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] t = line.split(";");
                Usuario usuario = new Usuario(t[0], t[1], t[2], Boolean.parseBoolean(t[3]), t[4]);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }//Fim do método recuperarUsuarios().
    

    /**
     * Recebe um ArrayList de vacas e grava todos os seus atributos no arquivo
     * vacas.txt. Usado nos métodos para declarar nascimento, morte, venda,
     * atualizar idade e brucelose das fêmeas.
     *
     * @param vacas
     * @throws IOException
     */
    public static void cadastrarTodasVacas(ArrayList<Vaca> vacas) throws IOException {
        Path path = Paths.get("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/vacas.txt");
        Charset utf8 = StandardCharsets.UTF_8;
        try (BufferedWriter writer = Files.newBufferedWriter(path, utf8)) {
            for (Vaca vaca : vacas) {
                writer.write(vaca.getNumero() + ";" + vaca.getSexo() + ";" + vaca.getDataNascimento() + ";" + vaca.getIdade() + ";" + vaca.getNumeroMae() + ";" + vaca.isNascidoPropriedade() + ";" + vaca.isMortoPropriedade() + ";" + vaca.isVendido() + ";" + vaca.getDataObito() + ";" + vaca.getDataVenda() + ";" + vaca.getNumeroCrias() + ";" + vaca.isBrucelose() + ";" + vaca.obterPartos() + ";\n");
            }
        }
    }//Fim do método cadastrarTodasVacas().

    /**
     * Recebe um ArrayList de bois e grava todos os seus atributos no arquivo
     * bois.txt. Usado nos métodos para declarar morte, venda e atualizar idade
     * dos machos.
     *
     * @param bois
     * @throws IOException
     */
    public static void cadastrarTodosBois(ArrayList<Boi> bois) throws IOException {
        Path path = Paths.get("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/bois.txt");
        Charset utf8 = StandardCharsets.UTF_8;
        try (BufferedWriter writer = Files.newBufferedWriter(path, utf8)) {
            for (Boi boi : bois) {
                writer.write(boi.getNumero() + ";" + boi.getSexo() + ";" + boi.getDataNascimento() + ";" + boi.getIdade() + ";" + boi.getNumeroMae() + ";" + boi.isNascidoPropriedade() + ";" + boi.isMortoPropriedade() + ";" + boi.isVendido() + ";" + boi.getDataObito() + ";" + boi.getDataVenda() + ";\n");
            }
        }
    }//Fim do método cadastrarTodosBois().

    /**
     * Recebe um ArrayList de usuários e grava todos os seus atributos no
     * arquivo usuarios.txt. Usado no método para excluir usuários.
     *
     * @param usuarios
     * @throws IOException
     */
    public static void cadastrarTodosUsuarios(ArrayList<Usuario> usuarios) throws IOException {
        Path path = Paths.get("C:/Users/CassioBattistuzzo/Documents/NetBeansProjects/Bovinocultura/src/br/com/casb/usuarios.txt");
        Charset utf8 = StandardCharsets.UTF_8;
        try (BufferedWriter writer = Files.newBufferedWriter(path, utf8)) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getCpf() + ";" + usuario.getSenha() + ";" + usuario.getNome() + ";" + usuario.isUsuarioAutenticado() + usuario.getInscricaoEstadual() + ";\n");
            }
        }
    }//Fim do método cadastrarTodosUsuarios().

}//Fim da classe BancoDeDados.
