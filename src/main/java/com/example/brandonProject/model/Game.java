/**
 * Class that handles the logic of the game "The Eclipsed Sun"
 * Control the secret word, guessed letters and failed attempts
 *
 * @author Brandon Stiven Jimenez Romero
 */

package com.example.brandonProject.model;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.Normalizer;

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
     * Takes a player's guess and updates the game state.
     * <p>
     * This method processes the player's guessed letter by normalizing it (removing any accents) and checking
     * if it exists in the secret word. If the guessed letter is in the secret word, the current guess
     * is updated. If the letter is incorrect, the number of attempts is incremented.
     * <p>
     * The method ignores repeated guesses and stops processing if the game is already over.
     *
     * @param letter The letter guessed by the player. Accents will be removed, so 'a' and 'á' are treated as equivalent.
     */

    public void makeGuess(char letter) {
        letter = Character.toLowerCase(letter);

        //Normalizar la letra ingresada por el usuario (eliminar tildes)
        letter = normalizeCharacter(letter);

        //Si la letra ya ha sido adivinada antes, no hacemos nada
        if (guessedLetters.contains(letter) || isGameOver()) {
            return;
        }

        guessedLetters.add(letter);

        // Si la letra esta en la palabra secreta, actualizamos la palabra adivinada
        if (normalizeString(secretWord).contains(String.valueOf(letter))) {
            StringBuilder newGuess = new StringBuilder(currentGuess);
            for (int i = 0; i < secretWord.length(); i++) {
                //Se compara la version normalizada de la letra en la palabra
                if (normalizeCharacter(secretWord.charAt(i)) == letter) {
                    newGuess.setCharAt(i, secretWord.charAt(i)); //Manten la letra original (con tilde o no)
                }
            }
            currentGuess = newGuess.toString();
        } else {
            // Incrementamos el contador de intentos si la letra no está en la palabra secreta
            attempts++;
        }
    }

    /**
     * Normalize a character by removing accents (tildes)
     *
     * @param c The character eliminating accents (tildes)
     * @return The character without accents
     */
    private char normalizeCharacter(char c) {
        // Normaliza y elimina tildes
        String normalized = Normalizer.normalize(String.valueOf(c), Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "").charAt(0);
    }

    /**
     * Normalizes an entire string by removing accents (tildes)
     *
     * @param input The string that you want to normalize
     * @return The string without accents
     */
    private String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    /**
     * Reveal a random unguessed letter from the secret word as a clue
     *
     * This method selects a random letter from the secret word that the player has not yet guessed
     * updates the current guess with the revealed letter, and returns the letter to the caller
     *
     * @return The revealed letter, or '_' if there are no more unrevealed letters
     */

    public char revealLetter() {
        //Lista para almacenar las letras no adivinadas
        List<Character> unrevealedLetters = new ArrayList<>();

        //Recorrer la palabra secreta y añadir las letras no adivinadas a la lista
        for (char letter : secretWord.toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                unrevealedLetters.add(letter); //Añadir las letras no adivinadas
            }
        }

        //Verificar si hay letras no adivinadas
        if (!unrevealedLetters.isEmpty()){
            //Seleccionar una letra aleatoria de las no adivinadas
            Random rand = new Random();
            char randomLetter = unrevealedLetters.get(rand.nextInt(unrevealedLetters.size()));

            //Añadir la letra revelada a las letras adivinadas
            guessedLetters.add(randomLetter);

            //Actualizar el estado de la palabra adivinada
            currentGuess = updateCurrentGuess(randomLetter);

            //Devolver la letra revelada
            return randomLetter;
        }
        return '_'; //Si no quedan letras por revelar, devolver un valor por defecto
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