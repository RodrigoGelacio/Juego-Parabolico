package juegoparabolico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.image.BufferedImage;

/**
 *
 * @author antoniomejorado
 */
public class Assets {
    public static BufferedImage background; // to store background image
    public static BufferedImage player;     // to store the player image
    public static BufferedImage sprite;
    public static BufferedImage rotation1[];
    public static BufferedImage rotation2[];
    public static BufferedImage rotation3[];
    public static BufferedImage rotation4[];
    public static BufferedImage rotation5[];

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/space_background.jpg");
        player = ImageLoader.loadImage("/images/ball.png");
        sprite = ImageLoader.loadImage("/images/world.png");
        Spritesheet spritesheet = new Spritesheet(sprite);
        rotation1 = new BufferedImage[12]; // 54x58
        rotation2 = new BufferedImage[12];
        rotation3 = new BufferedImage[12];
        rotation4 = new BufferedImage[12];
        rotation5 = new BufferedImage[12];
        for(int i=0; i < 12; i++){
               
            rotation1[i] = spritesheet.crop(i * 54, 0, 54, 54);
            rotation2[i] = spritesheet.crop(i * 54, 58, 54, 54);
            rotation3[i] = spritesheet.crop(i * 54, 116, 54, 54);
            rotation4[i] = spritesheet.crop(i * 54, 174, 54, 54);
            rotation5[i] = spritesheet.crop(i * 54, 232, 54, 54);
        }
    }
    
}


