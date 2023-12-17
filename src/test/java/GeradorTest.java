import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class GeradorTest {

    Gerador gerador = new Gerador(new Scanner(System.in));

    @Test
    public void isInputRespostaInvalido_DeveRetornarTrueQuandoInputForInvalido() {
        // Arrange
        String inputInvalidoLetra = "a";
        String inputInvalidoNumero = "1";
        String inputInvalidoSimbolo = "!";
        String inputInvalidoVazio = "";
        String inputInvalidoSim = "sim";
        String inputInvalidoNao = "nao";

        // Act
        boolean resultado01 = this.gerador.isInputRespostaInvalido(inputInvalidoLetra);
        boolean resultado02 = this.gerador.isInputRespostaInvalido(inputInvalidoNumero);
        boolean resultado03 = this.gerador.isInputRespostaInvalido(inputInvalidoSimbolo);
        boolean resultado04 = this.gerador.isInputRespostaInvalido(inputInvalidoVazio);
        boolean resultado05 = this.gerador.isInputRespostaInvalido(inputInvalidoSim);
        boolean resultado06 = this.gerador.isInputRespostaInvalido(inputInvalidoNao);

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
        String inputValido01 = "s";
        String inputValido02 = "n";
        String inputValido03 = "S";
        String inputValido04 = "N";

        // Act
        boolean resultado01 = this.gerador.isInputRespostaInvalido(inputValido01);
        boolean resultado02 = this.gerador.isInputRespostaInvalido(inputValido02);
        boolean resultado03 = this.gerador.isInputRespostaInvalido(inputValido03);
        boolean resultado04 = this.gerador.isInputRespostaInvalido(inputValido04);

        // Assert
        assertFalse(resultado01);
        assertFalse(resultado02);
        assertFalse(resultado03);
        assertFalse(resultado04);
    }
}
