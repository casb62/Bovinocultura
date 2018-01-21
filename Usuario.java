package br.com.casb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Usuario {

    private String cpf;
    private String senha;
    private String nome;
    private boolean usuarioAutenticado;
    private String inscricaoEstadual;

    public Usuario() {
    }

    public Usuario(String cpf, String senha, String nome, boolean usuarioAutenticado, String inscricaoEstadual) {
        this.cpf = cpf;
        this.senha = senha;
        this.nome = nome;
        this.usuarioAutenticado = usuarioAutenticado;
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(boolean usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    /**
     * Inclui um usuário por vez para operar o sistema.
     */
    public void incluirUsuario() {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String iu = leitor.nextLine();
        if (iu.equalsIgnoreCase("s")) {
            System.out.println("Digite o cpf do usuário: ");
            cpf = leitor.nextLine();
            System.out.println("Digite a senha do usuário: ");
            senha = leitor.nextLine();
            System.out.println("Digite o nome do usuário: ");
            nome = leitor.nextLine();
            usuarioAutenticado = false;
            System.out.println("Digite a inscrição estadual: ");
            inscricaoEstadual = leitor.nextLine();
            BancoDeDados.cadastrarUmUsuario(this);
        }
    }//Fim do método incluirUsuario().

    /**
     * Exclui um usuário por vez pela posição que ele ocupa no ArrayList.
     *
     * @throws IOException
     */
    public void excluirUsuario() throws IOException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String eu = leitor.nextLine();
        if (eu.equalsIgnoreCase("s")) {
            BancoDeDados bd = new BancoDeDados();
            ArrayList<Usuario> usuarios = bd.recuperarUsuarios();
            for (Usuario usuario : usuarios) {
                System.out.println("Posição: " + usuarios.indexOf(usuario) + " cpf " + usuario.getCpf() + " nome " + usuario.getNome() + ".");
            }
            System.out.println("\nQual a posição do usuário que deseja excluir?\n");
            int posicao = leitor.nextInt();
            usuarios.remove(posicao);
            BancoDeDados.cadastrarTodosUsuarios(usuarios);
        }
    }//Fim do método excluirUsuario().

    /**
     * Exibe o CPF, o nome e a inscrição estadual de todos os usuários
     * cadastrados.
     *
     * @throws IOException
     */
    public void pesquisarUsuarios() throws IOException {
        System.out.println("Deseja continuar? Digite s ou n.");
        Scanner leitor = new Scanner(System.in);
        String pu = leitor.nextLine();
        if (pu.equalsIgnoreCase("s")) {
            BancoDeDados bd = new BancoDeDados();
            ArrayList<Usuario> usuarios = bd.recuperarUsuarios();
            System.out.println("CPF:\t\t\tNome:\t\t\t\t\tInscrição Estadual:");
            System.out.println("-----------------------------------------------------------------------------------");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getCpf() + "\t\t" + usuario.getNome() + "\t\t" + usuario.getInscricaoEstadual());
                System.out.println("-----------------------------------------------------------------------------------");
            }
        }
    }//Fim do método pesquisarUsuarios().

    /**
     * Autentica o usuário, dá-lhe as boas vindas e alerta para a vacinação
     * contra aftosa nos meses de maio e novembro.
     *
     * @return
     * @throws IOException
     */
    public boolean autenticarUsuario() throws IOException {
        System.out.println("Digite o número do seu cpf: ");
        Scanner leitor = new Scanner(System.in);
        String cpfDigitado = leitor.nextLine();
        System.out.println("Digite a sua senha: ");
        String senhaDigitada = leitor.nextLine();
        BancoDeDados bd = new BancoDeDados();
        ArrayList<Usuario> usuarios = bd.recuperarUsuarios();
        boolean usuarioAutenticado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equalsIgnoreCase(cpfDigitado) && usuario.getSenha().equalsIgnoreCase(senhaDigitada)) {
                usuarioAutenticado = true;
                Calendar c = Calendar.getInstance();
                int hora = c.get(Calendar.HOUR_OF_DAY);
                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH) + 1;
                int ano = c.get(Calendar.YEAR);
                if (hora >= 6 && hora < 12) {
                    System.out.println("\nBom dia " + usuario.getNome() + ", seja bem vindo! Hoje é dia " + dia + "/" + mes + "/" + ano + ".\n");
                } else if (hora >= 12 && hora < 18) {
                    System.out.println("\nBoa tarde " + usuario.getNome() + ", seja bem vindo! Hoje é dia " + dia + "/" + mes + "/" + ano + ".\n");
                } else {
                    System.out.println("\nBoa noite " + usuario.getNome() + "seja bem vindo! Hoje é dia " + dia + "/" + mes + "/" + ano + ".\n");
                }
                if (mes == 5 || mes == 11) {
                    System.out.println("Não esquecer a vacina contra febre aftosa este mês!\n");
                }
            }
        }

        return usuarioAutenticado;

    }//Fim do método autenticarUsuario().

}//Fim da classe Usuario.
