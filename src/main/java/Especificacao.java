public class Especificacao {

    public static final String LETRAS_MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMEROS = "1234567890";
    public static final String SIMBOLOS = "!@#$%^&*()-_=+\\/~?";

    private final StringBuilder especificacao;

    public Especificacao(boolean incluirMaiusculas, boolean incluirMinusculas, boolean incluirNumeros, boolean incluirSimbolos) {
        especificacao = new StringBuilder();

        if (incluirMaiusculas) especificacao.append(LETRAS_MAIUSCULAS);
        if (incluirMinusculas) especificacao.append(LETRAS_MINUSCULAS);
        if (incluirNumeros) especificacao.append(NUMEROS);
        if (incluirSimbolos) especificacao.append(SIMBOLOS);
    }

    public String getEspecificacao() {
        return especificacao.toString();
    }
}
