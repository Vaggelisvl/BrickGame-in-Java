import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private  boolean play_status=false;
    private int score=0;
    private int totalBricks=2;
    private Timer time;
    private int delay=5;
    private  int PlayerX=310;
    private int ballPositionX=120;
    private int ballPositionY=350;
    private int balldirX=-1;
    private int balldirY=-2;
    private MapGenerator mapGenerator;
    public GamePlay(){
        mapGenerator=new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time=new Timer(delay,this);
        time.start();

    }
    public void paint(Graphics g){
        //add backgound
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);

        //drawing map
        mapGenerator.draw((Graphics2D)g);

        //filling borders
        g.setColor(Color.blue);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        //creating the paddle
        g.setColor(Color.orange);
        g.fillRect(PlayerX,550,100,8);

        //creating the ball
        g.setColor(Color.GREEN);
        g.fillOval(ballPositionX,ballPositionY,20,20);

        g.dispose();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if(play_status){
            if(new Rectangle(ballPositionX,ballPositionY,20,20).intersects(new Rectangle(PlayerX,550,100,8))){
                balldirY=-balldirY;
            }
            ballPositionX+=balldirX;
            ballPositionY+=balldirY;
            if(ballPositionX<0){
                balldirX=-balldirX;
            }
            if(ballPositionX>670){
                balldirX=-balldirX;
            }
            if(ballPositionY<0){
                balldirY=-balldirY;
            }
        }
        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(PlayerX>=600){
                PlayerX=600;
            }else {
                moveRight();
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(PlayerX<10){
                PlayerX=10;

            }else {
                moveLeft();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(PlayerX>=600){
                PlayerX=600;
            }else {
                moveRight();
            }
        }

        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(PlayerX<10){
                PlayerX=10;

            }else {
                moveLeft();
            }
        }

    }

    public void moveLeft() {
        play_status=true;
        PlayerX-=20;
    }

    public void moveRight() {
        play_status=true;
        PlayerX+=20;
    }
}
