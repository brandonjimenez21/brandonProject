/**
 * The Player class is defined, which stores the player information
 *
 * @author Brandon Stiven Jimenez Romero
 */

package com.example.brandonProject.model;

public class Player {
    private int id;
    private String nickName;

    public Player() {}

    /**
     * Assign an ID to the player
     *
     * @param id The player's unique identifier
     */

    public void setId(int id) {
        this.id = id; //Asigna un ID al jugador
    }

    /**
     * Get the player ID
     *
     * @return The player ID
     */
    public int getId() {
        return id; //Devuelve el ID del jugador
    }

    /**
     * Assign the player's nickname
     *
     * @param nickName The player's nickname
     */
    public void setNickName(String nickName) {
        this.nickName = nickName; //Asigna el apodo del jugador
    }

    /**
     * Gets the player's nickname
     *
     * @return The player's nickname
     */
    public String getNickName() {
        return nickName; //Devuelve el apodo del jugador
    }
}
