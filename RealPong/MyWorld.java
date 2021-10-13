import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Counter counter;
    private Counter counter2;
    Ball ball;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        ball = new Ball();
        
        counter = new Counter();
        addObject (counter, 145, 23);
        
        counter2 = new Counter();
        addObject (counter2, 600, 23);
        
        Paddle leftpaddle = new Paddle("w", "s", "`");
        Paddle rightpaddle = new Paddle("up", "down", "enter");
        
        addObject(ball, getWidth()/2, getHeight()/2);
        
        addObject(leftpaddle, 25, getHeight()/2);
        addObject(rightpaddle, getWidth()-25, getHeight()/2);
    }
    
    /**
     * Tells Player 1's counter to add 1 to its score display.
     */
    public void p1Score()
    {
        counter2.addScore();
    }
    
    /**
     * Tells Player 2's counter to add 1 to its score display.
     */
    public void p2Score()
    {
        counter.addScore();
    }
    
    /**
     * Resets displayed scores after someone wins.
     */
    public void resetScores()
    {
        counter.resetScore();
        counter2.resetScore();
        counter.update();
        counter2.update();
    }
    
    /**
     * Stops the music when the game is paused.
     */
    public void stopped()
    {
        ball.pauseMusic();
    }
}
