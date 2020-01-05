package spacegame.proje;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(1, this);

    private int gecen_sure = 0;

    private int harcanan_ates = 0;

    private BufferedImage image;

    private ArrayList<Fire> fires = new ArrayList<Fire>();

    private int atesdirY = 1;

    private int topX = 0;

    private int topDirX = 2;

    private int uzayGemisiX = 0;

    private int dirUzayX = 30;

    public boolean control() {
        for (Fire fire : fires) {
            if (new Rectangle(fire.getX(), fire.getY(), 10, 20).intersects(new Rectangle(topX, 0, 20, 20))) {
                return true;
            }
        }
        return false;
    }

    public Game() {
        try {
            image = ImageIO.read(new FileImageInputStream(new File("Spaceship.png")));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(Color.BLACK);

        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.red);
        g.fillOval(topX, 0, 20, 20);
        g.drawImage(image, uzayGemisiX, 490, image.getHeight() / 11, image.getWidth() / 6, this);

        for (Fire fire : fires) {
            if (fire.getY() <= 0) {
                fires.remove(fire);
            }
        }
        g.setColor(Color.CYAN);
        for (Fire fire : fires) {
            g.fillRect(fire.getX(), fire.getY(), 10, 20);
        }

        if (control()) {
            timer.stop();
            gecen_sure += 10;

            String message = "Kazandınız...\n"
                    + "Harcanan Ateş: " + harcanan_ates
                    + "\nGeçen Süre: " + gecen_sure / 1000.0 + " saniye ";

            JOptionPane.showMessageDialog(this, message);
            System.exit(0);

        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            if (uzayGemisiX <= 0) {
                uzayGemisiX = 0;
            } else {
                uzayGemisiX -= dirUzayX;
            }

        } else if (c == KeyEvent.VK_RIGHT) {
            if (uzayGemisiX >= 730) {
                uzayGemisiX = 730;
            } else {
                uzayGemisiX += dirUzayX;
            }
        } else if (c == KeyEvent.VK_SPACE) {
            fires.add(new Fire(uzayGemisiX + 30, 470));
            harcanan_ates++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Fire fire : fires) {
            fire.setY(fire.getY() - atesdirY);
        }

        topX += topDirX;
        if (topX >= 750) {
            topDirX = -topDirX;
        }

        if (topX <= 0) {
            topDirX = -topDirX;
        }
        repaint();
    }

}
