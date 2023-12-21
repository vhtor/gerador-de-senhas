
public class Senha {
    String value;
    int tamanho;

    public static final int TIPO_MAIUSCULAS = 1;
    public static final int TIPO_MINUSCULAS = 2;
    public static final int TIPO_NUMEROS = 3;
    public static final int TIPO_SIMBOLOS = 4;

    private boolean contemMaiusculas = false;
    private boolean contemMinusculas = false;
    private boolean contemNumeros = false;
    private boolean contemSimbolos = false;
    private int tipo;

    public Senha(String value) {
        this.value = value;
        tamanho = value.length();
    }

    public int tipoCaractere(char c) {
        // Caractere é uma letra
        if (Character.isDigit(c)) {
            return TIPO_NUMEROS;
        } else {
            // Caractere é uma letra maiúscula
            if ((int) c >= 65 && (int) c <= 90)
                return TIPO_MAIUSCULAS;

            // Caractere é uma letra minúscula
            else if ((int) c >= 97 && (int) c <= 122) {
                return TIPO_MINUSCULAS;
            }

            // Se não for nenhum dos anteriores, é um símbolo
            return TIPO_SIMBOLOS;
        }
    }

    public int calcularScore() {
        String senha = this.value;

        int score = 0;

        for (int i = 0; i < senha.length(); i++) {
            char c = senha.charAt(i);
            this.tipo = this.tipoCaractere(c);

            if (this.tipo == TIPO_MAIUSCULAS) this.contemMaiusculas = true;
            else if (this.tipo == TIPO_MINUSCULAS) this.contemMinusculas = true;
            else if (this.tipo == TIPO_NUMEROS) this.contemNumeros = true;
            else if (this.tipo == TIPO_SIMBOLOS) this.contemSimbolos = true;
        }

        if (contemMaiusculas) score += 1;
        if (contemMinusculas) score += 1;
        if (contemNumeros) score += 1;
        if (contemSimbolos) score += 1;

        if (senha.length() >= 8) score += 1;
        if (senha.length() >= 16) score += 1;

        return score;
    }

    public String getScoreText(int score) {
        if (score >= 5) {
            return "OTIMA";
        } else if (score >= 4) {
            return "BOA";
        } else if (score >= 3) {
            return "MEDIANA";
        } else {
            return "FRACA";
        }
    }

    public boolean contemMaiusculas() {
        return contemMaiusculas;
    }

    public boolean contemMinusculas() {
        return contemMinusculas;
    }

    public boolean isContemNumeros() {
        return contemNumeros;
    }

    public boolean contemSimbolos() {
        return contemSimbolos;
    }

    @Override
    public String toString() {
        return value;
    }
}
