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
 *
 * @author Rodrigo Torres
 */
public class Ball extends Item {

    private int direction;
    private Game game;
    private int counter;
    private int velocityX;
    private double velocityYInitial;
    private double velocityYFinal;
    private double gravity;
    private int barrier;
    private int frames;
    private int rotationNumber;
    private boolean control;
    private long initialTime;
    private long nowTime;
    private long trueTime;
    private boolean controlGravity;
    private int enter;
    private Animation animationRotation1;
    private Animation animationRotation2;
    private Animation animationRotation3;
    private Animation animationRotation4;
    private Animation animationRotation5;

    public Ball(int x, int y, int direction, int width, int height, Game game, int counter) {
        super(x, y, width, height);
        this.direction = direction;
        this.game = game;
        this.counter = counter;
        velocityX = 0;
        barrier = 300;
        initialTime = 0;
        velocityYInitial = 0.0;
        velocityYFinal = 0.0;
        gravity = .0981;
        nowTime = 0;
        trueTime = 0;
        control = false;
        controlGravity = false;
        enter = 0;
        frames = 0;
        rotationNumber = 1;
        this.animationRotation1 = new Animation(Assets.rotation1, 100);
        this.animationRotation2 = new Animation(Assets.rotation2, 100);
        this.animationRotation3 = new Animation(Assets.rotation3, 100);
        this.animationRotation4 = new Animation(Assets.rotation4, 100);
        this.animationRotation5 = new Animation(Assets.rotation4, 100);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void tick() {
        // moving player depending on flags and velocity
        this.animationRotation1.tick();
        this.animationRotation2.tick();
        this.animationRotation3.tick();
        this.animationRotation4.tick();
        this.animationRotation5.tick();
    
        System.out.println(frames);
        frames++;
        if(frames == 135){
        rotationNumber = 2;
    } /* else if (frames == 224){
        rotationNumber = 3;
    }  else if (frames == 336){
        rotationNumber = 4;
    } else if(frames == 448){
        rotationNumber = 5;
    } else if(frames == 560){
        rotationNumber = 1;
        frames = 0;
    } */
       
        
        if(!game.getMouseManager().isIzquierdo()){
            if(getX() <= barrier){
                if(getX() <= 300){
                    velocityX = (300 - getX())/50;
                }
                
                initialTime = System.nanoTime() - 1000000000;
                velocityYInitial = velocityX * (Math.tan(45));
                barrier = velocityX;
                control = true;
                
            }
        }
        
        
        if (game.getMouseManager().isIzquierdo()) {
            setX(game.getMouseManager().getX());
            setY(game.getMouseManager().getY());
            
        }
       
        
        if (control) {
            setX(getX() + velocityX);
            
            
//            System.out.println((System.nanoTime() - initialTime) / 1000000000);
            velocityYFinal = velocityYInitial - gravity* ((System.nanoTime() - initialTime)/1000000000);
            velocityYInitial = velocityYFinal;
            
            setY(getY() - (int)velocityYInitial);
            
            
            if(velocityYInitial < 1 && velocityYInitial >-7 && !controlGravity){
                initialTime = System.nanoTime() - 1000000000;
                controlGravity = true;
            }
            
        }
        

        // if it hits a wall, it bounces in the opposite direction by pressing 
        //virtually a button.
        if (getX() + 40 >= game.getWidth()) {
            setX(game.getWidth()/2 - (getWidth()/2));
            setY(game.getHeight()/2);
            control = false;
            barrier = 300;
        }
        else if(getX() < 0){
            setX(0);
        }
            
        if (getY() + 40 >= game.getHeight()) {
            setX(game.getWidth()/2 - (getWidth()/2));
            setY(game.getHeight()/2);
            control = false;
            barrier = 300;
        } else if (getY() <= 0) {
            setX(game.getWidth()/2 - (getWidth()/2));
            setY(game.getHeight()/2);
            control = false;
            barrier = 300;
        }
        
    }

    @Override
    public void render(Graphics g) {
        switch(rotationNumber){
            case 1:
                g.drawImage(animationRotation1.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 2:
                g.drawImage(animationRotation2.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 3:
                g.drawImage(animationRotation3.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 4:
                g.drawImage(animationRotation4.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 5:
                g.drawImage(animationRotation5.getCurrentFrame(), getX(), getY(), getWidth(), getHeight(), null);
                break;
        }
        
    }
}

