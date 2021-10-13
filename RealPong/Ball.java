import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends Actor
{
    private int dX = 4;
    private int dY = 2;
    private int fixX = 4;
    private int fixY = 2;
    private int randomNumber;
    private int p1Score = 0;
    private int p2Score = 0;
    private GreenfootImage player1Win;
    private GreenfootImage player2Win;
    private GreenfootImage ball;
    private GreenfootImage splashScreen;
    private int startDelay = 0;
    private boolean hasStarted;
    private GreenfootSound music = new GreenfootSound("music.mp3");
    
    /**
     * Constructor method for the ball.
     */
    public Ball()
    {
        player1Win = new GreenfootImage("p1win.png");
        player2Win = new GreenfootImage("p2win.png");
        ball = new GreenfootImage("blue-draught.png");
        splashScreen = new GreenfootImage("splash-screen.png");
        setImage(splashScreen);
    }
    
    /**
     * Act - do whatever the Ball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        playStart();
        playMusic();
        startDelay++;
        setToBall();
        move();
        checkForBounce();
        checkForReset();
        checkForPaddle();
        checkForWin();
    }
    
    /**
     * Checks if the music has already been played, and if it hasn't, the 
     * method plays the music.
     */
    public void playMusic()
    {
        if(!music.isPlaying())
        {
            music.playLoop();
        }
    }
    
    /**
     * Checks if the start sound effect has already been played,
     * and if it hasn't, the method plays the sound effect.
     */
    public void playStart()
    {
        if(hasStarted == false)
        {
            Greenfoot.playSound("game-start.wav");
            hasStarted = true;
        }
    }
    
    /**
     * Guarentees the ball will have its image set to the ball image when 
     * game is running.
     */
    public void setToBall()
    {
        if(getImage() != ball)
        {
            setImage(ball);
        }
    }
    
    /**
     * Allows the ball to move dynamically instead of using the basic
     * greenfoot move method.
     */
    public void move()
    {
        if(startDelay >= 20)
        {
            setLocation(getX() + dX, getY() + dY);
        }
    }
    
    /**
     * Makes sure the ball does not get stuck in the paddle.
     */
    public void paddleFix()
    {
        setLocation(getX() + fixX, getY() + fixY);
    }
    
    /**
     * Checks if the ball hits the top or bottom of the screen,
     * and makes it bounce if it has.
     */
    public void checkForBounce()
    {
        if (getY() <= 0 || getY() >= getWorld().getHeight()-1)
        {
            dY = -dY;
            Greenfoot.playSound("bounce.wav");
        }
    }
    
    /**
     * Resets the ball if it hits either horizontal edge of the screen.
     */
    public void checkForReset()
    {
        if(getX() >= getWorld().getWidth()-1)
        {
            setLocation(300, 200);
            p1Score++;
            Greenfoot.playSound("goal.wav");
            dX = 4;
            randomY();
            randomX();
            startDelay = 0;
            MyWorld myWorld = (MyWorld) getWorld();
            myWorld.p2Score();
        }
        if(getX() <= 0)
        {
            setLocation(300, 200);
            p2Score++;
            Greenfoot.playSound("goal.wav");
            dX = 4;
            randomY();
            randomX();
            startDelay = 0;
            MyWorld myWorld = (MyWorld) getWorld();
            myWorld.p1Score();
        }
    }
    
    /**
     * Randomizes the direction of movement on the Y axis when the ball is 
     * reset.
     */
    public void randomY()
    {
        randomNumber = Greenfoot.getRandomNumber(2 + 1);
        if(randomNumber == 1)
        {
            dY = -dY;
        }
    }
    
    /**
     * Randomizes the direction of movement on the X axis when the ball is 
     * reset.
     */
    public void randomX()
    {
        randomNumber = Greenfoot.getRandomNumber(2 + 1);
        if(randomNumber == 1)
        {
            dX = -dX;
        }
    }
    
    /**
     * Checks if the ball is touching a paddle, and makes it bounce if it is.
     */
    public void checkForPaddle()
    {
        if(isTouching(Paddle.class))
        {
            randomNumber = Greenfoot.getRandomNumber(2 + 1);
            dX = -dX;
            if(getX() >= 500)
            {
                fixX = -10;
                paddleFix();
            }
            if(getX() <= 100)
            {
                fixX = 10;
                paddleFix();
            }
            Greenfoot.playSound("bounce.wav");
            if(randomNumber == 1)
            {
                if(dX <= 0)
                {
                    dX--;
                }
                if(dX >= 0)
                {
                    dX++;
                }
            }
        }
    }
    
    /**
     * Pauses the music.
     */
    public void pauseMusic()
    {
        music.pause();
    }
    
    /**
     * Checks if either player has reached 8 points, and plays the necessary
     * win effects if they have.
     */
    public void checkForWin()
    {
        if(p1Score >= 8)
        {
            p1Score = 0;
            p2Score = 0;
            hasStarted = false;
            Greenfoot.playSound("win.wav");
            setImage(player1Win);
            MyWorld myWorld = (MyWorld) getWorld();
            myWorld.resetScores();
            music.stop();
            Greenfoot.stop();
        }
        if(p2Score >= 8)
        {
            p2Score = 0;
            p1Score = 0;
            hasStarted = false;
            Greenfoot.playSound("win.wav");
            setImage(player2Win);
            MyWorld myWorld = (MyWorld) getWorld();
            myWorld.resetScores();
            music.stop();
            Greenfoot.stop();
        }
    }
}