package juegoparabolico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Rodrigo Torres
 */
public class KeyManager implements KeyListener {

    public boolean up;      // flag to move up the player
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public int verticalSpeed; // velocity for the vertical buttons
    public int horizontalSpeed; // velocity for the horizontal button
    public boolean paused; // flag pause the game

    private boolean keys[];  // to store all the flags for every key

    public KeyManager() {
        keys = new boolean[256];
        verticalSpeed = 0;
        horizontalSpeed = 0;
        paused = true;
    }

    public boolean isPaused() {
        return paused;
    }

    
    @Override

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Function: when a key is pressed the other ones are set to false.
     * Function: It also copies the speed if there is a change between hor and
     * vertical speed.
     *
     * @param key object
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

    }

    /**
     * function: this is a function that serves to virtually click a button.
     *
     * @param char
     */

    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released
         keys[e.getKeyCode()] = false;
         if(e.getKeyCode() == KeyEvent.VK_P){
             paused = !paused;
         }
    }

    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        
    }
}
