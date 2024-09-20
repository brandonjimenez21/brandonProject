/**
 * @author Brandon Stiven Jimenez Romero
 * @version 1.0
 * https://github.com/brandonjimenez21/brandonProject.git
 */

package com.example.brandonProject;

import com.example.brandonProject.view.WelcomeView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Initializes the first view (Welcome View) when starting the application
     *
     * @param primaryStage The main application window
     * @throws Exception If an error occurs while loading the view
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        WelcomeView.getInstance();
    }

    public static void main(String[] args) {
        /**
         * Launch the JavaFX application
         *
         * @param args Command line arguments
         */
        launch(args);
    }
}
