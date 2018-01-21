package br.com.casb;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Principal {

    //Constantes que correspondem às opções do menu.
    private static final int EXIBIR_AJUDA = 1;
    private static final int INCLUIR_USUARIO = 2;
    private static final int EXCLUIR_USUARIO = 3;
    private static final int PESQUISAR_USUARIO = 4;
    private static final int DECLARAR_NASCIMENTO = 5;
    private static final int DECLARAR_COMPRA = 6;
    private static final int DECLARAR_MORTE = 7;
    private static final int DECLARAR_VENDA = 8;
    private static final int DECLARAR_BRUCELOSE = 9;
    private static final int PESQUISAR_MACHO = 10;
    private static final int PESQUISAR_FEMEA = 11;
    private static final int REBANHO_ANIMAIS = 12;
    private static final int REBANHO_CATEGORIAS = 13;
    private static final int LISTAR_MORTOS = 14;
    private static final int LISTAR_VENDIDOS = 15;
    private static final int LISTAR_PARTOS = 16;
    private static final int SAIR = 17;

    /**
     * Exibe o menu principal e realiza transações.
     *
     * @throws IOException
     * @throws ParseException
     */
    public static void exibirMenu() throws IOException, ParseException {
        Boi boi = new Boi();
        Vaca vaca = new Vaca();
        Usuario usuario = new Usuario();
        System.out.println("\n      Escolha a opção: \n\n");
        System.out.println("1  -  Exibir ajuda.");
        System.out.println("2  -  Incluir usuário.");
        System.out.println("3  -  Excluir usuário.");
        System.out.println("4  -  Pesquisar usuário.");
        System.out.println("5  -  Declarar nascimento.");
        System.out.println("6  -  Declarar compra.");
        System.out.println("7  -  Declarar morte.");
        System.out.println("8  -  Declarar venda.");
        System.out.println("9  -  Declarar brucelose.");
        System.out.println("10 -  Pesquisar animal macho.");
        System.out.println("11 -  Pesquisar animal fêmea.");
        System.out.println("12 -  Exibir rebanho por animais.");
        System.out.println("13 -  Exibir rebanho por categorias.");
        System.out.println("14 -  Listar os animais mortos.");
        System.out.println("15 -  Listar os animais vendidos.");
        System.out.println("16 -  Listar partos.");
        System.out.println("17 -  Sair.\n");
        int opcao;
        Scanner leitor = new Scanner(System.in);
        do {
            opcao = leitor.nextInt();
            switch (opcao) {
                case EXIBIR_AJUDA:
                    System.out.println("Para uma melhor visualização no terminal, clicar com o botão direito do mouse na barra de título, em propriedades, layout,");
                    System.out.println("e configurar o tamanho do buffer de tela com largura 205 e altura 290.");
                    System.out.println("Para utilizar este sistema, o primeiro passo é cadastrar um usuário.");
                    System.out.println("O cpf deve ser digitado no formato xxx.xxx.xxx-xx.");
                    System.out.println("Para declarar nascimento, compra, morte ou venda, digitar m, M, f ou F para definir o sexo dos animais.");
                    System.out.println("Para declarar nascimento ou compra, utilize antes a opção 12 do menu para obter o próximo número da sequência de numeração dos animais.");
                    System.out.println("As datas devem ser digitadas no formato dd/mm/aaaa.");
                    System.out.println("Para declarar nascimento, a mãe do animal já deve estar cadastrada.");
                    System.out.println("Os animais provenientes de compra têm número da mãe igual a zero.");
                    break;
                case INCLUIR_USUARIO:
                    usuario.incluirUsuario();
                    break;
                case EXCLUIR_USUARIO:
                    usuario.excluirUsuario();
                    break;
                case PESQUISAR_USUARIO:
                    usuario.pesquisarUsuarios();
                    break;
                case DECLARAR_NASCIMENTO:
                    System.out.println("Digite M ou F para o sexo do animal: ");
                    String sexo1,
                     sexo2,
                     sexo3;
                    sexo1 = "M";
                    sexo2 = "F";
                    Scanner sn = new Scanner(System.in);
                    sexo3 = sn.nextLine();
                    if (sexo3.equalsIgnoreCase(sexo1)) {
                        boi.declararNascimento();
                    } else if (sexo3.equalsIgnoreCase(sexo2)) {
                        vaca.declararNascimento();
                    }
                    break;
                case DECLARAR_COMPRA:
                    System.out.println("Digite M ou F para o sexo do animal: ");
                    sexo1 = "M";
                    sexo2 = "F";
                    Scanner sc = new Scanner(System.in);
                    sexo3 = sc.nextLine();
                    if (sexo3.equalsIgnoreCase(sexo1)) {
                        boi.declararCompra();
                    } else if (sexo3.equalsIgnoreCase(sexo2)) {
                        vaca.declararCompra();
                    }
                    break;
                case DECLARAR_MORTE:
                    System.out.println("Digite M ou F para o sexo do animal: ");
                    sexo1 = "M";
                    sexo2 = "F";
                    Scanner sm = new Scanner(System.in);
                    sexo3 = sm.nextLine();
                    if (sexo3.equalsIgnoreCase(sexo1)) {
                        boi.declararMorte();
                    } else if (sexo3.equalsIgnoreCase(sexo2)) {
                        vaca.declararMorte();
                    }
                    break;
                case DECLARAR_VENDA:
                    System.out.println("Digite M ou F para o sexo do animal: ");
                    sexo1 = "M";
                    sexo2 = "F";
                    Scanner sv = new Scanner(System.in);
                    sexo3 = sv.nextLine();
                    if (sexo3.equalsIgnoreCase(sexo1)) {
                        boi.declararVenda();
                    } else if (sexo3.equalsIgnoreCase(sexo2)) {
                        vaca.declararVenda();
                    }
                    break;
                case DECLARAR_BRUCELOSE:
                    vaca.declararBrucelose();
                    break;
                case PESQUISAR_MACHO:
                    boi.pesquisarBoi();
                    break;
                case PESQUISAR_FEMEA:
                    vaca.pesquisarVaca();
                    break;
                case REBANHO_ANIMAIS:
                    Rebanho.atualizarAnimais();
                    break;
                case REBANHO_CATEGORIAS:
                    Rebanho.atualizarCategorias();
                    break;
                case LISTAR_MORTOS:
                    Rebanho.listarMortos();
                    break;
                case LISTAR_VENDIDOS:
                    Rebanho.listarVendidos();
                    break;
                case LISTAR_PARTOS:
                    Vaca.exibirPartos();
                    break;
                case SAIR:
                    System.out.println("Saindo...\n");
                    break;
                default:
                    System.err.println("Você não digitou um opção válida! Tente novamente.\n");

            }//Fim do switch().

        } while (opcao != 17);

    }//Fim do método exibirMenu().

}//Fim da classe Principal.
