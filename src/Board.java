import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener {

    int heroX;
    int heroY;
    PositionedImage hero;
    PositionedImage floor;
    PositionedImage wall;
    String orientation = "img/hero-down.png";


    public Board() {
        heroX = 0;
        heroY = 0;

        // set the size of your draw board
        setPreferredSize(new Dimension(720, 720));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        // graphics.fillRect(testBoxX, testBoxY, 100, 100);
        // here I have a 720x720 canvas
        // you can create and draw an image using the class below e.g.
            hero = new PositionedImage(orientation, heroX, heroY);
            floor = new PositionedImage("img/floor.png", 0,0);
        wall = new PositionedImage("img/wall.png", 72,144);


        for (int i = 0; i < 720; i+=72) {
            floor.posY = i;
            for (int j = 0; j < 720; j+=72) {
                floor.posX = j;
                floor.draw(graphics);
            }
        }
        wall.draw(graphics);
        hero.draw(graphics);
    }

    public static void main(String[] args) {
        // Here is how you set up a new window and adding our board to it
        JFrame frame = new JFrame("RPG Game");
        Board board = new Board();
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        // Here is how you can add a key event listener
        // The board object will be notified when hitting any key
        // with the system calling one of the below 3 methods
        frame.addKeyListener(board);
        // Notice (at the top) that we can only do this
        // because this Board class (the type of the board object) is also a KeyListener
    }

    // To be a KeyListener the class needs to have these 3 methods in it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    // But actually we can use just this one for our goals here
    @Override
    public void keyReleased(KeyEvent e) {
        // When the up or down keys hit, we change the position of our box
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (heroY == 0){
            } else if (wall.posY == heroY - 72 && wall.posX == heroX) {
            } else {
                heroY -= 72;
                orientation = "img/hero-up.png";
            }
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (heroY == 648){
            } else if (wall.posY == heroY + 72 && wall.posX == heroX) {
            } else {
                heroY += 72;
                orientation = "img/hero-down.png";
            }
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (heroX == 648){
            }else if (wall.posY == heroY && wall.posX == heroX + 72) {
            } else {
                heroX += 72;
                orientation = "img/hero-right.png";
            }
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (heroX == 0){
            }else if (wall.posY == heroY && wall.posX == heroX - 72) {
            } else {
                heroX -= 72;
                orientation = "img/hero-left.png";
            }
        }
        // and redraw to have a new picture with the new coordinates
        repaint();

    }

}
