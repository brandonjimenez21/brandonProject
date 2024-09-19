/**
 * Controls game logic and interaction with the game view
 * Handles user events such as entering letters, using help, and restarting the game
 */
package com.example.brandonProject.controllers;

import com.example.brandonProject.model.Player;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import com.example.brandonProject.model.Game;

/**
 * Game view controller
 *
 * @author Brandon Stiven Jimenez Romero
 */
public class GameController {

    //Referencias a los elementos visuales (FXML) de la interfaz grafica
    @FXML
    private Label wordLabel;
    @FXML
    private Label attemptsLabel;
    @FXML
    private TextField inputField;
    @FXML
    private Button restartButton;
    @FXML
    private Button helpButton; //Help Button
    @FXML
    private Canvas sunCanvas;

    //Objeto que controla el estado del juego
    private Game game;
    private int helpCount = 1; //Contador para limitar la cantidad de ayudas

    @FXML
    public void initialize() {
        //Inicializa el juego con la palabra secreta 'javafx'
        game = new Game("javafx");
        //Dibuja el sol al inicio del juego
        drawSun(0);
        //Actualiza la interfaz con el estado actual del juego
        updateView();
    }

    @FXML
    private void handleGuess() {
        //Logica para manejar el intento del jugador de adivinar una letra
        if (game.isGameOver()) {
            return;
        }

        String input = inputField.getText().trim();

        //Validar que solo se haya ingresado un caracter
        if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
            char guess = input.charAt(0);
            game.makeGuess(guess);
            updateView();
            inputField.clear();
        }else{
            attemptsLabel.setText("Ingresa solo un caracter");
        }
    }

    @FXML
    private void handleHelp() {
        //Logica para revelar una letra cuando el jugador pide ayuda
        if (helpCount > 0 && !game.isGameOver()) {
            char hint = game.revealLetter();  // Obtener una letra revelada
            helpCount--;  //Reducir el contador de ayudas
            helpButton.setDisable(true);  //Desactivar el boton de ayuda
            updateView();  //Actualizar la vista
        } else if (game.isGameOver()) {
            attemptsLabel.setText("El juego ha terminado. Reinicia para usar la ayuda.");
        }
    }

    @FXML
    private void handleRestart() {
        //Reiniciar el juego y restaura los valores iniciales
        game = new Game("javafx");
        helpCount = 1;
        updateView();
        inputField.setDisable(false);
        restartButton.setDisable(true);
        helpButton.setDisable(false); //Habilitar boton de ayuda nuevamente
        drawSun(0); //Redibujar el sol inicial
    }

    private void updateView() {
        //Actualiza la interfaz gráfica con el progreso actual del juego
        wordLabel.setText(game.getCurrentGuess()); //Actualiza el texto del label con la palabra adivinada
        attemptsLabel.setText("Intentos restantes: " + (5 - game.getAttempts()));

        int attempts = game.getAttempts();
        drawSun(attempts); //Dibujar el sol de acuerdo a los intentos

        if (game.isGameOver()) {
            if (game.getCurrentGuess().equals(game.getSecretWord())) {
                attemptsLabel.setText("¡Has ganado!");
            } else {
                attemptsLabel.setText("Has perdido. La palabra era: " + game.getSecretWord());
            }
            inputField.setDisable(true);
            restartButton.setDisable(false);
        }
    }

    /**
     * Draws a sun on the canvas, which gets darker as the player fails
     *
     * @param attempts Number of failed attempts by the player
     */
    private void drawSun(int attempts) {
        GraphicsContext gc = sunCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, sunCanvas.getWidth(), sunCanvas.getHeight());  // Limpiar el canvas

        double centerX = sunCanvas.getWidth() / 2;
        double centerY = sunCanvas.getHeight() / 2;
        double radius = 100;
        double pointSize = 5;
        double angleIncrement = 72; // Cada intento cubre 72 grados (1/5 del círculo)

        // Dibujar el sol con puntos blancos
        gc.setFill(javafx.scene.paint.Color.WHITE);
        drawPoints(gc, centerX, centerY, radius, pointSize, 0, 360); // Circulo completo

        // Dibujar el eclipse con puntos negros, segun la cantidad de errores
        gc.setFill(javafx.scene.paint.Color.BLACK);
        double startAngle = 0; // Angulo de inicio para los puntos negros

        for (int i = 0; i < attempts; i++) {
            drawPoints(gc, centerX, centerY, radius, pointSize, startAngle, angleIncrement);
            startAngle += angleIncrement; // Mover el angulo de inicio para el siguiente segmento
        }
    }

    /**
     *
     * @param gc Canvas graphic backdrop
     * @param centerX X coordinate of the center of the sun
     * @param centerY Y coordinate of the center of the sun
     * @param radius Radius of the circle of the sun
     * @param pointSize Size of drawn points
     * @param startAngle Initial angle to start drawing the points
     * @param angleRange Range of angles at which points are drawn
     */

    private void drawPoints(GraphicsContext gc, double centerX, double centerY, double radius, double pointSize, double startAngle, double angleRange) {
        double angleIncrement=9; // Incremento del angulo para los puntos

        for (double angle = startAngle; angle < startAngle + angleRange; angle += angleIncrement) {
            double x = centerX + radius * Math.cos(Math.toRadians(angle));
            double y = centerY + radius * Math.sin(Math.toRadians(angle));
            gc.fillOval(x - pointSize / 2, y - pointSize / 2, pointSize, pointSize);  // Dibujar el punto
        }
    }

    /**
     * Assign a player to the game controller
     *
     * @param player The player who is playing.
     */
    public void setPlayer(Player player) {
        //Logica para establecer el jugador
    }
}