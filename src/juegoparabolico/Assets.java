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

    public static BufferedImage background;     // to store background image
    public static BufferedImage player;         // to store the player image
    public static BufferedImage sprite;         // to store coin spritesheet
    public static BufferedImage pause;          // to store pause image
    public static BufferedImage gameOver;       // to store game over image
    public static BufferedImage spriteBasket;   // to store fountain sprite sheet
    public static BufferedImage rotation1[];    // to store each image of the coin sprite sheet
    public static SoundClip score;              // to store the score sound
    public static SoundClip music;              // to store the background music sound
    public static BufferedImage portal[];       // to store each image of the fountain sprite sheet
    public static BufferedImage building;

    /**
     * initializing the images of the game
     */
    public static void init() {
        music = new SoundClip("/sounds/guitarMusic.wav");
        building = ImageLoader.loadImage("/images/building.png");
        score = new SoundClip("/sounds/score.wav");
        background = ImageLoader.loadImage("/images/park.png");
        pause = ImageLoader.loadImage("/images/pause.jpg");
        gameOver = ImageLoader.loadImage("/images/gameover.jpg");
        sprite = ImageLoader.loadImage("/images/coin1.png");
        spriteBasket = ImageLoader.loadImage("/images/fountain.png");
        Spritesheet spritesheet = new Spritesheet(sprite);
        Spritesheet spritesheet2 = new Spritesheet(spriteBasket);
        rotation1 = new BufferedImage[6];
        portal = new BufferedImage[4];
        for (int i = 0; i < 6; i++) {
            rotation1[i] = spritesheet.crop(i * 116, 0, 116, 146);  // crops images for the coin animation
        }
        for (int i = 0; i < 4; i++) {
            portal[i] = spritesheet2.crop(i * 170, 0, 170, 130);    // crops images for the fountain animation
        }
    }

}
