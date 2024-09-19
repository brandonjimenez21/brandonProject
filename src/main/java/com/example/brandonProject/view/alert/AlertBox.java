/**
 * Esta clase muestra una alerta informativa en la interfaz gráfica
 */
package com.example.brandonProject.view.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBox implements AlertBoxInterface {
    @Override
    public void showAlert(String title, String header, String message) {
        //Muestra una alerta de información con el titulo, encabezado y mensajes proporcionados
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait(); //Espera hasta que el usuario cierre la alerta
    }
}
