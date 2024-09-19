/**
 * Load welcome view using FXML file
 * Handles the single instance of the view
 */

package com.example.brandonProject.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WelcomeView extends Stage {

    public WelcomeView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/brandonProject/welcome-view.fxml"));
        Parent root = loader.load();
        this.setTitle("Eclipsed Sun");
        Scene scene = new Scene(root);
        this.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/brandonProject/images/icono.png"))));
        this.setScene(scene);
        this.show();
    }

    public static WelcomeView getInstance() throws IOException {
        return WelcomeViewHolder.INSTANCE = new WelcomeView();
    }

    private static class WelcomeViewHolder {
        private static WelcomeView INSTANCE;

        private WelcomeViewHolder() {
        }
    }
}






















