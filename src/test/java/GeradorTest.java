import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GeradorTest {

    public Gerador getGeradorComScanner() {
        return new Gerador(new Scanner(System.in));
    }

    public Gerador getGeradorComEspecificacao(boolean incluirMaiusculas, boolean incluirMinusculas, boolean incluirNumeros, boolean incluirSimbolos) {
        return new Gerador(incluirMaiusculas, incluirMinusculas, incluirNumeros, incluirSimbolos);
    }

    @Test
    public void isInputRespostaInvalido_DeveRetornarTrueQuandoInputForInvalido() {
        // Arrange
        final var gerador = this.getGeradorComScanner();
        String inputInvalidoLetra = "a";
        String inputInvalidoNumero = "1";
        String inputInvalidoSimbolo = "!";
        String inputInvalidoVazio = "";
        String inputInvalidoSim = "sim";
        String inputInvalidoNao = "nao";

        // Act
        boolean resultado01 = gerador.isInputRespostaInvalido(inputInvalidoLetra);
        boolean resultado02 = gerador.isInputRespostaInvalido(inputInvalidoNumero);
        boolean resultado03 = gerador.isInputRespostaInvalido(inputInvalidoSimbolo);
        boolean resultado04 = gerador.isInputRespostaInvalido(inputInvalidoVazio);
        boolean resultado05 = gerador.isInputRespostaInvalido(inputInvalidoSim);
        boolean resultado06 = gerador.isInputRespostaInvalido(inputInvalidoNao);

        // Assert
        assertTrue(resultado01);
        assertTrue(resultado02);
        assertTrue(resultado03);
        assertTrue(resultado04);
        assertTrue(resultado05);
        assertTrue(resultado06);
    }

    @Test
    public void isInputRespostaInvalido_DeveRetornarFalseQuandoInputForValido() {
        // Arrange
        final var gerador = this.getGeradorComScanner();
        String inputValido01 = "s";
        String inputValido02 = "n";
        String inputValido03 = "S";
        String inputValido04 = "N";

        // Act
        boolean resultado01 = gerador.isInputRespostaInvalido(inputValido01);
        boolean resultado02 = gerador.isInputRespostaInvalido(inputValido02);
        boolean resultado03 = gerador.isInputRespostaInvalido(inputValido03);
        boolean resultado04 = gerador.isInputRespostaInvalido(inputValido04);

        // Assert
        assertFalse(resultado01);
        assertFalse(resultado02);
        assertFalse(resultado03);
        assertFalse(resultado04);
    }

    @Test
    public void gerarSenha_DeveRetornarSenhaComTamanhoCorreto() {
        // Arrange
        final var gerador = this.getGeradorComEspecificacao(true, true, true, true);
        int tamanho = 10;

        // Act
        final var senha = gerador.gerarSenha(tamanho);

        // Assert
        assertSame(senha.value.length(), tamanho);
    }

    @Test
    public void gerarSenha_DeveRetornarSenhaComLetrasMaiusculasQuandoEspecificacaoIncluirMaiusculas() {
        // Arrange
        final var gerador = this.getGeradorComEspecificacao(true, false, false, false);
        int tamanho = 10;

        // Act
        final var senha = gerador.gerarSenha(tamanho);

        // Assert
        assertTrue(senha.value.matches("^[A-Z]+$"));
    }

    @Test
    public void gerarSenha_DeveRetornarSenhaComLetrasMinusculasQuandoEspecificacaoIncluirMinusculas() {
        // Arrange
        final var gerador = this.getGeradorComEspecificacao(false, true, false, false);
        int tamanho = 10;

        // Act
        final var senha = gerador.gerarSenha(tamanho);

        // Assert
        assertTrue(senha.value.matches("^[a-z]+$"));
    }

    @Test
    public void gerarSenha_DeveRetornarSenhaComNumerosQuandoEspecificacaoIncluirNumeros() {
        // Arrange
        final var gerador = this.getGeradorComEspecificacao(false, false, true, false);
        int tamanho = 10;

        // Act
        final var senha = gerador.gerarSenha(tamanho);

        // Assert
        assertTrue(senha.value.matches("^[0-9]+$"));
    }

    @Test
    public void gerarSenha_DeveRetornarSenhaComSimbolosQuandoEspecificacaoIncluirSimbolos() {
        // Arrange
        final var gerador = this.getGeradorComEspecificacao(false, false, false, true);
        int tamanho = 10;

        // Act
        final var senha = gerador.gerarSenha(tamanho);

        // Assert
        assertTrue(senha.value.matches("^[!@#$%^&*()-_=+\\\\/~?]+$"));
    }

    @ParameterizedTest(name = "{2}")
    @CsvFileSource(resources = "senhas.csv", delimiter = ';')
    public void analisarSenha_DeveRetornarScoreCorreto(String senhaValue, int scoreEsperado, String nomeCaso) {
        // Arrange
        final var senha = new Senha(senhaValue);

        // Act
        final var score = senha.calcularScore();

        // Assert
        assertSame(scoreEsperado, score);
    }
}
