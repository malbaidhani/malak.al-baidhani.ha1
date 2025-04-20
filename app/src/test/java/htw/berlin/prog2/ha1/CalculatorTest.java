package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


    //TODO hier weitere Tests erstellen
    //Teilaufgabe 1
    @Test
    @DisplayName("should switch the sign when pressing the negative key")
    void testToggleNegativeSign() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(4);        // Ziffer eingeben, Bildschirm zeigt dann 4

        
        calc.pressNegativeKey();      // +/- Taste drücken, Bildschirm zeigt dann -4
        assertEquals("-4", calc.readScreen());

        calc.pressNegativeKey();      // Negative Taste erneut drücken, Bildschirm zeigt: 4
        assertEquals("4", calc.readScreen());
    }

    //Teilaufgabe 2
    @Test
    @DisplayName("should keep the number when pressing equals toearly/without second number")
    void testEqualsWithoutSecondNumber() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("+");
        calc.pressEqualsKey(); // nichts hingeschrieben (ohne eine zweite Zahl)

        String expected = "2"; // sollte gleich bleiben
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(9);  // eine Ziffer eingeben
        calc.pressBinaryOperationKey("/");  // dividieren
        calc.pressDigitKey(0);  // 0 eingeben
        calc.pressEqualsKey();  // Aufgabe ausrechnen lassen

        String expected = "Error";  // Falsches Ergebnis
        String actual = calc.readScreen();  // Tatsächliches Ergebnis

        assertEquals(expected, actual);  // wird überprüft, ob Fehler angezeigt wird
    }

        //Teilaufgabe 3
        //testEqualsWithoutSecondNumber() aus Teilaufgabe 2
        public void pressEqualsKey() {  //soll prüfen, ob eine zweite Zahl überhaupt eingegeben wurde
            if (latestOperation.isEmpty() || waitingForSecondOperand) { //kein Ergebnis, wenn keine zweite Zahl eingegeben wurde
            waitingForSecondOperand = false;
            return;
        }

        //testDivisionByZero() aus Teilaufgabe 2
            // Division durch Null
            if (latestOperation.equals("/") && currentValue == 0) {
                screen = "Error";
                return;
            }

        }
}

