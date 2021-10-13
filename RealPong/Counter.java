import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    private int score;
    
    /**
     * Constructor for Counter class objects.
     */
    public Counter()
    {
        score = 0;
        setImage (new GreenfootImage(200, 30));
        update();
    }
    
    /**
     * Adds 1 to the "score" variable.
     */
    public void addScore()
    {
        score++;
        update();
    }
    
    /**
     * Sets the score to zero after somebody wins.
     */
    public void resetScore()
    {
        score = 0;
    }
    
    /**
     * Sets the counter's image to keep up to date with the internal score.
     */
    public void update()
    {
        GreenfootImage img = getImage();
        img.clear();
        img.setColor(Color.BLACK);
        img.drawString("Score: " + score, 4, 20);
    }
}
