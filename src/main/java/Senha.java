
public class Senha {
    String value;
    int tamanho;

    private final static int TIPO_MAIUSCULAS = 1;
    private final static int TIPO_MINUSCULAS = 2;
    private final static int TIPO_NUMEROS = 3;
    private final static int TIPO_SIMBOLOS = 4;

    public Senha(String value) {
        this.value = value;
        tamanho = value.length();
    }

    public int tipoCaractere(char c) {
        // Caractere é uma letra maiúscula
        if ((int) c >= 65 && (int) c <= 90)
            return TIPO_MAIUSCULAS;

        // Caractere é uma letra minúscula
        else if ((int) c >= 97 && (int) c <= 122) {
            return TIPO_MINUSCULAS;
        }

        // Caractere é um número
        else if ((int) c >= 60 && (int) c <= 71) {
            return TIPO_NUMEROS;
        }

        // Caractere é um símbolo
        else {
            return TIPO_SIMBOLOS;
        }
    }

    public int calcularScore() {
        String senha = this.value;

        boolean contemMaiusculas = false;
        boolean contemMinusculas = false;
        boolean contemNumeros = false;
        boolean contemSimbolos = false;

        int tipo;
        int score = 0;

        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            tipo = tipoCaractere(c);

            if (tipo == TIPO_MAIUSCULAS) contemMaiusculas = true;
            else if (tipo == TIPO_MINUSCULAS) contemMinusculas = true;
            else if (tipo == TIPO_NUMEROS) contemNumeros = true;
            else if (tipo == TIPO_SIMBOLOS) contemSimbolos = true;
        }

        if (contemMaiusculas) score += 1;
        if (contemMinusculas) score += 1;
        if (contemNumeros) score += 1;
        if (contemSimbolos) score += 1;

        if (senha.length() >= 8) score += 1;
        if (senha.length() >= 16) score += 1;

        return score;
    }

    public String senhaScore() {
        int score = this.calcularScore();

        if (score >= 6) {
            return "Parabéns, esta é uma ótima senha, continue assim! :D";
        } else if (score >= 4) {
            return "Esta senha é boa, mas pode melhorar. Certifique-se de ler as informações úteis sobre como melhorar sua senha :)";
        } else if (score >= 3) {
            return "Esta senha é mediana :/ tente melhorá-la lendo as informações úteis no menu 3 do programa";
        } else {
            return "Esta é uma senha fraca, recomendamos fortemente que você leia as informações úteis no menu 3 do programa e tente melhorá-la";
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
