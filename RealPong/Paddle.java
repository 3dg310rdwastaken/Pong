import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Paddle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Paddle extends Actor
{
    private int moveUp = -5;
    private int moveDown = 5;
    private int imageSet = 0;
    private String upKey;
    private String downKey;
    private String boostKey;
    private boolean hasBoosted;
    private GreenfootImage paddle;
    private GreenfootImage bluePaddle;
    private GreenfootImage blank;
    
    /**
     * Constructor for paddles.
     */
    public Paddle(String upKey, String downKey, String boostKey)
    {
        this.upKey = upKey;
        this.downKey = downKey;
        this.boostKey = boostKey;
        paddle = new GreenfootImage("Paddle.png");
        bluePaddle = new GreenfootImage("bluepaddle.png");
        blank = new GreenfootImage("blank.png");
        setImage(blank);
    }
    
    /**
     * Act - do whatever the Paddle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkForKeys();
        setPaddle();
    }
    
    /**
     * Sets the objects image to the paddle.
     */
    public void setPaddle()
    {
        if(imageSet == 0)
        {
            setImage(paddle);
            imageSet++;
        }
    }
    
    /**
     * Checks for assigned keys to allow both paddles to move up, move down,
     * or boost.
     */
    public void checkForKeys()
    {
        if(Greenfoot.isKeyDown(upKey))
        {
            if(getY() >= 50)
            {
                setLocation(getX(), getY() + moveUp);
            }
            moveUp = -5;
            moveDown = 5;
            hasBoosted = false;
            setImage(paddle);
        }
        
        if(Greenfoot.isKeyDown(downKey))
        {
            if(getY() <= getWorld().getHeight() - 50)
            {
                setLocation(getX(), getY() + moveDown);
            }
            moveUp = -5;
            moveDown = 5;
            hasBoosted = false;
            setImage(paddle);
        }
        
        if(Greenfoot.isKeyDown(boostKey) && !hasBoosted)
        {
            moveUp = moveUp - 30;
            moveDown = moveDown + 30;
            hasBoosted = true;
            setImage(bluePaddle);
        }
        
        if(!Greenfoot.isKeyDown(boostKey))
        {
            setImage(paddle);
        }
    }
}