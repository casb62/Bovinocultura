package br.com.casb;

import java.io.IOException;
import java.text.ParseException;

/**
 * Programa para gerenciamento de criação de bovinos.
 *
 * @author Cassio A. S. Baptistussi
 */
public class Executar {

    public static void main(String[] args) throws IOException, ParseException {
        Usuario usuario = new Usuario();
        Principal principal = new Principal();
        while (true) {
            while (!usuario.isUsuarioAutenticado()) {//Loop enquanto usuário ainda não está autenticado.
                System.out.println("Sistema de Gestão de Criação de Bovinos\n");
                System.out.println("Este sistema possui um usuário padrão já cadastrado.\nUtilize-o para o seu primeiro acesso. Em seguida, cadastre-se.\nCPF: 123.456.789-10 Senha: boiada\n");
                usuario.setUsuarioAutenticado(usuario.autenticarUsuario());
            }//Fim do while interno.
            //Agora o usuário está autenticado.
            Principal.exibirMenu();
            usuario.setUsuarioAutenticado(false);//Reinicializa para outro usuário.
            System.out.println("Obrigado por usar este sistema!\n");
        }//Fim do while externo.

    }//Fim do main().

}//Fim da classe Executar.
