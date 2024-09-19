/**
 * Load game view using FXML file
 * Maneja la instancia unica de la vista
 *
 * @author Brandon Stiven Jimenez Romero
 */

package com.example.brandonProject.view;

import com.example.brandonProject.controllers.GameController;
import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameView extends Stage {

    private GameController gameController;

    /**
     * Constructor que carga la vista del juego desde el archivo FXML
     *
     * @throws IOException Si ocurre un error al cargar el archivo FXML
     */
    public GameView() throws IOException {
        //Carga el archivo FXMl de la vida del juego
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/brandonProject/hello-java-fx-view.fxml"));
        Parent root = loader.load();

        //Obtiene el controlador asociado al FXML
        this.gameController = loader.getController();

        //Configura la escene y la muestra
        this.setTitle("Eclipsed Sun");
        Scene scene = new Scene(root);
        this.getIcons().add(new Image(
                Objects.requireNonNull(getClass().getResourceAsStream("/com/example/brandonProject/images/icono.png"))
        ));
        this.setScene(scene);
        this.show();
    }

    /**
     * Obtiene el controlador del juego
     *
     * @return El controlador del juego
     */

    public GameController getGameController() {
        return this.gameController; //Devuelve el controlador del juego
    }

    /**
     * Obtiene la instancia única de la vista del juego
     *
     * @return La instancia de la vista del juego
     * @throws IOException Si ocurre un error al crear la instancia
     */
    public static GameView getInstance() throws IOException {
        return GameViewHolder.INSTANCE = new GameView(); //Crea una instancia unica de la vista del juego
    }

    private static class GameViewHolder {
        private static GameView INSTANCE;

        private GameViewHolder() {
        }
    }
}






















