import java.util.Scanner;

public class Gerador {
    Especificacao especificacao;

    public static Scanner teclado;

    public Gerador(Scanner scanner) {
        teclado = scanner;
    }

    public Gerador(boolean incluirMaiusculas, boolean incluirMinusculas, boolean incluirNumeros, boolean incluirSimbolos) {
        especificacao = new Especificacao(incluirMaiusculas, incluirMinusculas, incluirNumeros, incluirSimbolos);
    }

    public void mainLoop() {
        System.out.println("Bem vindo(a) ao gerador de senhas :)");
        imprimirMenu();

        String userInput = "-1";

        while (!userInput.equals("4")) {
            userInput = teclado.next();

            switch (userInput) {
                case "1" -> {
                    gerarSenha();
                    imprimirMenu();
                }
                case "2" -> {
                    analisarSenha();
                    imprimirMenu();
                }
                case "3" -> {
                    imprimirInformacoesUteis();
                    imprimirMenu();
                }
                case "4" -> imprimirEncerramento();
                default -> {
                    System.out.println();
                    System.out.println("Por favor, selecione apenas um dos comandos disponíveis no menu");
                    imprimirMenu();
                }
            }
        }
    }

    private Senha gerarSenha(int length) {
        final StringBuilder senha = new StringBuilder();

        final int tamanhoEspecificacao = especificacao.getEspecificacao().length();

        int max = tamanhoEspecificacao - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            senha.append(especificacao.getEspecificacao().charAt(index));
        }

        return new Senha(senha.toString());
    }

    private void imprimirInformacoesUteis() {
        System.out.println();
        System.out.println("- Use uma senha de pelo menos 08 caracteres, se possível");
        System.out.println("- Inclua caracteres minúsculos, maíusculos, números e símbolos sempre que possível");
        System.out.println("- Gere senhas aleatórias onde for viável");
        System.out.println("- Evite ao máximo usar a mesma senha para contas diferentes em diferentes sistemas");
        System.out.println("- Evite: letras repetidas, palavras conhecidas, sequências de letras ou números, nomes de animais de estimação e data de nascimento");
        System.out.println("- Evite colocar na senha informações que colegas ou conhecidos possam descobrir facilmente");
    }

    private void gerarSenha() {
        boolean incluirMaiusculas;
        boolean incluirMinusculas;
        boolean incluirNumeros;
        boolean incluirSimbolos;
        boolean parametrosVazios;

        imprimirDivisor();
        System.out.println("Responda as seguintes questões apenas com s ou n");

        do {
            parametrosVazios = false;

            incluirMinusculas = deveIncluirMinusculas();
            incluirMaiusculas = deveIncluirMaiusculas();
            incluirNumeros = deveIncluirNumeros();
            incluirSimbolos = deveIncluirSimbolos();

            // Se nenhum parâmetro foi escolhido
            if (!incluirMaiusculas && !incluirMinusculas && !incluirNumeros && !incluirSimbolos) {
                System.out.println("Por favor selecione pelo menos algum dos parâmetros para gerar uma senha");
                parametrosVazios = true;
            }

        } while (parametrosVazios);

        System.out.print("Ótimo! Agora digite o tamanho desejado para a senha: ");
        int length = tamanhoSenha();

        final Gerador gerador = new Gerador(incluirMaiusculas, incluirMinusculas, incluirNumeros, incluirSimbolos);
        final Senha senha = gerador.gerarSenha(length);

        System.err.println("Sua senha gerada é -> " + senha);
    }

    private boolean deveIncluirMinusculas() {
        String input;
        do {
            System.out.print("Você quer incluir letras minúsculas \"abcd...\" na senha? ");
            input = teclado.next();
        } while (inputRespostaInvalido(input));

        return incluir(input);
    }

    private boolean deveIncluirMaiusculas() {
        String input;
        do {
            System.out.print("Você quer incluir letras maiúsculas \"ABCD...\" na senha? ");
            input = teclado.next();
        } while (inputRespostaInvalido(input));

        return incluir(input);
    }

    private boolean deveIncluirNumeros() {
        String input;
        do {
            System.out.print("Você quer incluir números \"1234...\" na senha? ");
            input = teclado.next();
        } while (inputRespostaInvalido(input));

        return incluir(input);
    }

    private boolean deveIncluirSimbolos() {
        String input;
        do {
            System.out.print("Você quer incluir símbolos \"!@#$...\" na senha? ");
            input = teclado.next();
        } while (inputRespostaInvalido(input));

        return incluir(input);
    }

    private int tamanhoSenha() {
        int tamanho;
        do {
            tamanho = teclado.nextInt();
            if (tamanho < 6) {
                System.out.print("Por favor, digite um tamanho igual ou maior a 6 para a senha");
            }
        } while (tamanho < 6);

        return tamanho;
    }

    private boolean incluir(String input) {
        return input.equalsIgnoreCase("s");
    }

    private boolean inputRespostaInvalido(String input) {
        if (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n")) {
            System.out.println("Por favor, digite apenas s ou n como resposta\n");
            return true;
        }
        return false;
    }

    private void analisarSenha() {
        String input;

        System.out.print("Digite a senha a ser analisada: ");
        input = teclado.next();

        final Senha p = new Senha(input);

        System.out.println(p.senhaScore());
    }

    private void imprimirMenu() {
        System.out.println();
        System.out.println("1 - Gerador de Senhas");
        System.out.println("2 - Checar força de uma senha");
        System.out.println("3 - Informações úteis");
        System.out.println("4 - Encerrar");
        imprimirDivisor();
        System.out.print("Escolha uma opção: ");
    }

    private void imprimirDivisor() {
        System.out.println("--------------------------");
    }

    private void imprimirEncerramento() {
        System.out.println("Encerrando o programa...");
    }
}
