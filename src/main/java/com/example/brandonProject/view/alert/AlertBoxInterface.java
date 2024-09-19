/**
 * Define una interfaz para mostrar alertas
 * permite la flexibilidad si se quiere cambiar el tipo de alerta mas adelante
 */

package com.example.brandonProject.view.alert;

public interface AlertBoxInterface {
    //Define un metodo para mostrar una alerta
    public void showAlert(String title, String header, String message);
}
