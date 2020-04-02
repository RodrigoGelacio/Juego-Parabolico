package juegoparabolico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Timer;

/**
 * @author Sergio Tapia
 * @author Rodrigo Torres
 */
public class Ball extends Item {

    private Game game;
    private int velocityX;
    private double velocityYInitial;
    private double velocityYFinal;
    private double gravity;
    private int barrier;
    private boolean control;
    private long initialTime;
    private boolean controlGravity;
    private Animation animationRotation1;

    public Ball(int x, int y, int width, int height, Game game, int counter) {
        super(x, y, width, height);
        this.game = game;
        velocityX = 0;
        barrier = 300;                  // The size of the "ready" area
        initialTime = 0;                // The time where the movement starts (usually 0)
        velocityYInitial = 0.0;         // double variable to store initial value of velocity to use it later on the formula
        velocityYFinal = 0.0;           // double variable to store final value of velocity to use it later on the formula
        gravity = .0981;                // Gravity value divided by 100
        control = false;
        controlGravity = false;
        this.animationRotation1 = new Animation(Assets.rotation1, 100);
    }

    /**
     * to set the barrier limit to a different value
     *
     * @param barrier
     */
    public void setBarrier(int barrier) {
        this.barrier = barrier;
    }

    /**
     * to set control to a different value
     *
     * @param control
     */
    public void setControl(boolean control) {
        this.control = control;
    }

    @Override
    public void tick() {

        // Detects if the proyectile is in x <= 300 and assigns the velocity values
        if (!game.getMouseManager().isIzquierdo()) {
            if (getX() <= barrier) {
                if (getX() <= 300) {
                    velocityX = (300 - getX()) / 50;
                }

                initialTime = System.nanoTime() - 1000000000;    // sets initial time of the movement  
                velocityYInitial = velocityX * (Math.tan(45));   // sets Y axis initial velocity with a 45 degree angle
                barrier = velocityX;
                control = true;

            }
            // Proyectile's animation stops until mouse button is pressed
            if (!game.getMouseManager().isIzquierdo()) {
                this.animationRotation1.tick();
            }
        }

        // Drags the item with the mouse until mouse button is released
        if (game.getMouseManager().isIzquierdo()) {
            setX(game.getMouseManager().getX() - 50);      // sets the item to the mouse position in X axis
            setY(game.getMouseManager().getY() - 50);        // sets the item to the mouse position in Y axis

        }

        // Use of the formula
        if (control) {
            setX(getX() + velocityX);

            velocityYFinal = velocityYInitial - gravity * ((System.nanoTime() - initialTime) / 1000000000);
            velocityYInitial = velocityYFinal;

            setY(getY() - (int) velocityYInitial);

            if (velocityYInitial < 1 && velocityYInitial > -7 && !controlGravity) {
                initialTime = System.nanoTime() - 1000000000;
                controlGravity = true;
            }

        }

        // if it hits a wall, it bounces in the opposite direction by pressing 
        //virtually a button.
        if (getX() + 40 >= game.getWidth()) {
            setX(game.getWidth() / 2 - (getWidth() / 2));   // Returns coin to its original position
            setY(game.getHeight() / 2);                   // Returns coin to its original position
            control = false;                            // Stops the gravity for the coin
            barrier = 300;
        } else if (getX() < 0) {
            setX(0);
        }

        // Setting boundaries and what happens if coin touches either the ceiling, floor or right wall
        if (getY() + 40 >= game.getHeight()) {
            setX(game.getWidth() / 2 - (getWidth() / 2));   // Returns coin to its original position
            setY(game.getHeight() / 2);                   // Returns coin to its original position
            control = false;                            // Stops the gravity for the coin
            barrier = 300;
            game.setCounterVidas(game.getCounterVidas() + 1); // Adds 1 to the counter of failed attempts
        } else if (getY() <= 0) {
            setX(game.getWidth() / 2 - (getWidth() / 2));   // Returns coin to its original position
            setY(game.getHeight() / 2);                   // Returns coin to its original position
            control = false;                            // Stops the gravity for the coin
            barrier = 300;
            game.setCounterVidas(game.getCounterVidas() + 1); // Adds 1 to the counter of failed attempts
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animationRotation1.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
    }
}
