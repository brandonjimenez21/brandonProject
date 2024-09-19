/**
 * Handles the welcome screen, including user interaction to enter a name
 *
 * @author Brandon Stiven Jimenez Romero
 */

package com.example.brandonProject.controllers;

import com.example.brandonProject.model.Player;
import com.example.brandonProject.view.GameView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private TextField nicknameTextField;

    public WelcomeController() {
    }

    /**
     * Method that handles the event of pressing the submit button
     * Create a new player with the name entered and proceed to the game
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionSendButton(ActionEvent actionEvent) throws IOException {
        Player player = new Player();
        player.setId(1);
        player.setNickName(this.nicknameTextField.getText());
        GameView.getInstance().getGameController().setPlayer(player);
    }

    /**
     * Method that handles key events when the player enters his name
     *
     * @param event
     */
    @FXML
    public void onKeyPressedNicknameTextField(KeyEvent event) {
        //Lógica opcional para manejar eventos de teclas
    }
}