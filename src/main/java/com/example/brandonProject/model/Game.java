/**
 * Class that handles the logic of the game "The Eclipsed Sun"
 * Control the secret word, guessed letters and failed attempts
 *
 * @author Brandon Stiven Jimenez Romero
 */

package com.example.brandonProject.model;

import java.util.HashSet;
import java.util.Set;

public class Game {
    private final String secretWord;  // La palabra secreta que el jugador debe adivinar
    private final Set<Character> guessedLetters;  // Conjunto de letras adivinadas por el jugador
    private int attempts;  // Cantidad de intentos realizados por el jugador
    private String currentGuess;  // Estado actual de la palabra adivinada

    /**
     * Constructor that initializes the game with a secret word
     *
     * @param secretWord The secret word that the player must guess
     */

    public Game(String secretWord) {
        this.secretWord = secretWord.toLowerCase(); //Guarda la palabra secreta en minusculas
        this.guessedLetters = new HashSet<>(); //Conjunto para almacenar las letras adivinadas
        this.attempts = 0;                      //Inicializa los intentos en 0
        this.currentGuess = "_".repeat(secretWord.length()); //Genera los guiones bajos según la longitud de la palabra
    }

    /**
     * Take a player guess and update the game state
     *
     * @param letter The letter guessed by the player
     */

    public void makeGuess(char letter) {
        letter = Character.toLowerCase(letter);

        // Si la letra ya ha sido adivinada antes, no hacemos nada
        if (guessedLetters.contains(letter) || isGameOver()) {
            return;
        }

        guessedLetters.add(letter);

        // Si la letra esta en la palabra secreta, actualizamos la palabra adivinada
        if (secretWord.contains(String.valueOf(letter))) {
            StringBuilder newGuess = new StringBuilder(currentGuess);
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == letter) {
                    newGuess.setCharAt(i, letter);
                }
            }
            currentGuess = newGuess.toString();
        } else {
            // Incrementamos el contador de intentos si la letra no está en la palabra secreta
            attempts++;
        }
    }

    /**
     * Reveal an unguessed letter as a clue
     *
     * @return The letter revealed
     */

    public char revealLetter() {
        for (char letter : secretWord.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                guessedLetters.add(letter);
                currentGuess = updateCurrentGuess(letter);
                return letter;
            }
        }
        return '_'; // No deberia llegar aqui si hay letras no adivinadas
    }

    /**
     * Updates the current string of guessed letters with the given letter
     *
     * @param letter The letter was guessed correctly
     * @return The updated chain with the letters revealed
     */

    private String updateCurrentGuess(char letter) {
        StringBuilder updatedGuess = new StringBuilder(currentGuess);
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                updatedGuess.setCharAt(i, letter);
            }
        }
        return updatedGuess.toString();
    }

    /**
     * Check if the game is over
     *
     * @return {@code true} if the game is over, {@code false} otherwise
     */

    public boolean isGameOver() {
        return attempts >= 5 || currentGuess.equals(secretWord);
    }

    /**
     * Get the secret word
     *
     * @return The secret word
     */
    public String getSecretWord() {
        return secretWord;
    }

    /**
     * Gets the player's current progress as a string of underscores and guessed letters
     *
     * @return The current progress chain
     */
    public String getCurrentGuess() {
        return currentGuess;
    }

    /**
     * Gets the number of attempts made
     *
     * @return The number of attempts
     */
    public int getAttempts() {
        return attempts;
    }
}