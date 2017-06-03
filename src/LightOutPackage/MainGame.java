package LightOutPackage;

/**
 *
 * @author Kakon
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * this class contains the whole game logic 
 * it also handles all the mouse and keyboard events
 */

public class MainGame implements KeyListener, MouseListener, MouseMotionListener {

    public static void main(String args[]) {

        MainGame b = new MainGame();
        b.run();

    }

    ScreenManager s;
    Image bg, bg2, bg3, bg4, bg6, bg7, bg8, bg9, bg10, bg11, bg12, bg13, bg14, bg15, bg16, bg17, bg18, bg19, bg20, bg21, bg22, bg23, bg24, bg25, bg26, bg27;
    boolean gameRunning = false;
    boolean showapple = true;
    boolean startPlay = false;
    boolean startPlay2 = false;
    boolean startPlay3 = false;
    boolean startPlay4 = false;
    boolean startPlay5 = false;
    boolean os = true;
    boolean menu = true;
    boolean instruction = false;
    int x, y;
    int countinstructionimages = 0;
    int chances = 3;
    private int flagclickpos1 = 0, flagclickpos2 = 0, flagclickpos3 = 0, flagclickpos4 = 0, flagclickpos5 = 0, flagclickpos6 = 0, flagclickpos7 = 0;
    private int clickCount = 0;
    int randx[] = new int[10];
    int randy[] = new int[10];
    int count = 0;
    int flag = 0;
    int score = 0;
    int gameovertracker = 0;
    boolean removeRandomPics = false;
    private static final DisplayMode modes1[] = {
        new DisplayMode(800, 600, 16, 0)
    };

    private static final DisplayMode modes2[] = {
        new DisplayMode(800, 600, 32, 0)
    };
    private boolean win7 = false;
    private boolean win8 = false;

    public void init() {
        bg = new ImageIcon("image\\Start1.PNG").getImage();
        bg2 = new ImageIcon("image\\Play2.PNG").getImage();
        bg3 = new ImageIcon("image\\Play.PNG").getImage();
        bg4 = new ImageIcon("image\\Square_rong.PNG").getImage();
        bg6 = new ImageIcon("image\\Discolor.PNG").getImage();
        bg7 = new ImageIcon("image\\Instruction.PNG").getImage();
        bg8 = new ImageIcon("image\\Discolo.PNG").getImage();
        bg9 = new ImageIcon("image\\Discol.PNG").getImage();
        bg10 = new ImageIcon("image\\Disco.PNG").getImage();
        bg11 = new ImageIcon("image\\Disc.PNG").getImage();
        bg12 = new ImageIcon("image\\LevelFailedHorrible_0.PNG").getImage();
        bg13 = new ImageIcon("image\\LevelFailedHopeless_1.PNG").getImage();
        bg14 = new ImageIcon("image\\LevelFailedAverage_2.PNG").getImage();
        bg15 = new ImageIcon("image\\LevelFailedReliable_3.PNG").getImage();
        bg16 = new ImageIcon("image\\LevelFailedRemarkable_4.PNG").getImage();
        bg17 = new ImageIcon("image\\GameOverProdigious_5.PNG").getImage();
        bg18 = new ImageIcon("image\\Instructions_1.PNG").getImage();
        bg19 = new ImageIcon("image\\Instructions_2.PNG").getImage();
        bg20 = new ImageIcon("image\\Instructions_3.PNG").getImage();
        bg21 = new ImageIcon("image\\Instructions_4.PNG").getImage();
        bg22 = new ImageIcon("image\\Instructions_5.PNG").getImage();
        bg23 = new ImageIcon("image\\Exit.PNG").getImage();
        bg24 = new ImageIcon("image\\Back.PNG").getImage();
        bg25 = new ImageIcon("image\\OS_Page.PNG").getImage();
        bg26 = new ImageIcon("image\\Windows_7.PNG").getImage();
        bg27 = new ImageIcon("image\\Windows_8.PNG").getImage();

        flagclickpos1 = 0;
        flagclickpos2 = 0;
        flagclickpos3 = 0;
        flagclickpos4 = 0;
        flagclickpos5 = 0;
        flagclickpos6 = 0;
        flagclickpos7 = 0;
    }

    public void run() {

        s = new ScreenManager();

        try {
            DisplayMode dm = s.findFirstCompatibleMode(modes2);
            s.setFullScreen(dm);
            Window w = s.getFullScreenWindow();
            w.addKeyListener(this);
            w.addMouseListener(this);
            w.addMouseMotionListener(this);
            init();
            Graphics2D g;

            while (os == true) {
                g = s.getGraphics();
                paintOsScreen(g);
                s.update();
                g.dispose();
            }

            s.restoreScreen();

            if (win7 == true) {
                dm = s.findFirstCompatibleMode(modes1);
                s.setFullScreen(dm);
                w = s.getFullScreenWindow();
                w.addKeyListener(this);
                w.addMouseListener(this);
                w.addMouseMotionListener(this);
            } else if (win8 == true) {
                dm = s.findFirstCompatibleMode(modes2);
                s.setFullScreen(dm);
                w = s.getFullScreenWindow();
                w.addKeyListener(this);
                w.addMouseListener(this);
                w.addMouseMotionListener(this);

            }

            long startTime = System.currentTimeMillis();
            long cumTime = startTime;
            long timePassed2 = 0;
            flag = 0;
            while (gameRunning == true) {
                while (menu == true && gameRunning == true) {
                    g = s.getGraphics();
                    paintOpeningScreen(g);
                    s.update();
                }
                while (instruction == true && gameRunning == true) {
                    g = s.getGraphics();
                    paintInstructionScreen(g);
                    s.update();
                }

                while (startPlay == true && gameRunning == true) {
                    if (flag == 0) {
                        cumTime = System.currentTimeMillis();
                        flag = 1;
                    }
                    if (gameovertracker == 1) {
                        startPlay = false;

                        g = s.getGraphics();
                        paintGameOver(g);
                        g.dispose();
                        s.update();
                        try {
                            Thread.sleep(4000);
                        } catch (Exception e) {
                        }
                        menu = true;
                    }

                    g = s.getGraphics();
                    mainGamePaint(g);
                    g.dispose();
                    s.update();
                    int ax[] = new int[10];
                    int ay[] = new int[10];
                    for (int i = 0; i < 3; i++) {
                        ax[i] = 308 + 63 * i;
                    }
                    for (int i = 0; i < 3; i++) {
                        ay[i] = 240 + 63 * i;
                    }
                    Random r = new Random();
                    if (count < 3) {
                        int indx = r.nextInt(3);
                        int indy = r.nextInt(3);
                        randx[count] = ax[indx];
                        randy[count++] = ay[indy];
                    }
                    int seconds = 0;
                    long timePassed = System.currentTimeMillis() - cumTime;
                    timePassed2 += timePassed;
                    if (timePassed2 >= 950000) {
                        removeRandomPics = true;
                        timePassed2 = 0;
                    }

                    if (score == 30) {
                        startPlay = false;
                        startPlay2 = true;
                        clickCount = 0;
                        gameovertracker = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        init();
                    } else if (clickCount == 3) {
                        System.out.println(clickCount);
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        score = 0;
                        init();
                    }
                }

                while (startPlay2 == true && gameRunning == true) {
                    menu = false;
                    instruction = false;
                    if (flag == 0) {
                        cumTime = System.currentTimeMillis();
                        flag = 1;
                    }
                    if (gameovertracker == 1) {
                        startPlay2 = false;

                        g = s.getGraphics();
                        paintGameOver(g);
                        g.dispose();
                        s.update();
                        try {
                            Thread.sleep(4000);
                        } catch (Exception e) {
                        }
                        menu = true;
                    }

                    g = s.getGraphics();
                    mainGamePaint2(g);
                    g.dispose();
                    s.update();
                    int ax[] = new int[10];
                    int ay[] = new int[10];
                    for (int i = 0; i < 4; i++) {
                        ax[i] = 278 + 63 * i;
                    }
                    for (int i = 0; i < 4; i++) {
                        ay[i] = 210 + 63 * i;
                    }
                    Random r = new Random();
                    if (count < 4) {
                        int indx = r.nextInt(4);
                        int indy = r.nextInt(4);
                        randx[count] = ax[indx];
                        randy[count++] = ay[indy];
                    }
                    int seconds = 0;
                    long timePassed = System.currentTimeMillis() - cumTime;
                    timePassed2 += timePassed;
                    if (timePassed2 >= 950000) {
                        removeRandomPics = true;
                        timePassed2 = 0;
                    }

                    if (score == 70) {
                        startPlay2 = false;
                        startPlay3 = true;
                        gameovertracker = 0;
                        menu = true;
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        init();
                    } else if (clickCount == 4) {
                        System.out.println(clickCount);
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        score = 30;
                        init();
                    }
                }

                while (startPlay3 == true && gameRunning == true) {
                    menu = false;
                    instruction = false;
                    if (flag == 0) {
                        cumTime = System.currentTimeMillis();
                        flag = 1;
                    }
                    if (gameovertracker == 1) {
                        startPlay3 = false;

                        g = s.getGraphics();
                        paintGameOver(g);
                        g.dispose();
                        s.update();
                        try {
                            Thread.sleep(4000);
                        } catch (Exception e) {
                        }
                        menu = true;
                    }

                    g = s.getGraphics();
                    mainGamePaint3(g);
                    g.dispose();
                    s.update();
                    int ax[] = new int[10];
                    int ay[] = new int[10];
                    for (int i = 0; i < 5; i++) {
                        ax[i] = 248 + 63 * i;
                    }
                    for (int i = 0; i < 5; i++) {
                        ay[i] = 180 + 63 * i;
                    }
                    Random r = new Random();
                    if (count < 5) {
                        int indx = r.nextInt(5);
                        int indy = r.nextInt(5);
                        randx[count] = ax[indx];
                        randy[count++] = ay[indy];
                    }
                    int seconds = 0;
                    long timePassed = System.currentTimeMillis() - cumTime;
                    timePassed2 += timePassed;
                    if (timePassed2 >= 950000) {
                        removeRandomPics = true;
                        timePassed2 = 0;
                    }

                    if (score == 120) {
                        startPlay3 = false;
                        startPlay4 = true;
                        gameovertracker = 0;
                        menu = true;
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        init();
                    } else if (clickCount == 5) {
                        System.out.println(clickCount);
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        score = 70;
                        init();
                    }
                }

                while (startPlay4 == true && gameRunning == true) {
                    menu = false;
                    instruction = false;
                    if (flag == 0) {
                        cumTime = System.currentTimeMillis();
                        flag = 1;
                    }
                    if (gameovertracker == 1) {
                        startPlay4 = false;

                        g = s.getGraphics();
                        paintGameOver(g);
                        g.dispose();
                        s.update();
                        try {
                            Thread.sleep(4000);
                        } catch (Exception e) {
                        }
                        menu = true;
                    }

                    g = s.getGraphics();
                    mainGamePaint4(g);
                    g.dispose();
                    s.update();
                    int ax[] = new int[10];
                    int ay[] = new int[10];
                    for (int i = 0; i < 6; i++) {
                        ax[i] = 218 + 63 * i;
                    }
                    for (int i = 0; i < 6; i++) {
                        ay[i] = 150 + 63 * i;
                    }
                    Random r = new Random();
                    if (count < 6) {
                        int indx = r.nextInt(6);
                        int indy = r.nextInt(6);
                        randx[count] = ax[indx];
                        randy[count++] = ay[indy];
                    }
                    int seconds = 0;
                    long timePassed = System.currentTimeMillis() - cumTime;
                    timePassed2 += timePassed;
                    if (timePassed2 >= 950000) {
                        removeRandomPics = true;
                        timePassed2 = 0;
                    }

                    if (score == 180) {
                        startPlay4 = false;
                        startPlay5 = true;
                        gameovertracker = 0;
                        menu = true;
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        init();
                    } else if (clickCount == 6) {
                        System.out.println(clickCount);
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        score = 120;
                        init();
                    }
                }

                while (startPlay5 == true && gameRunning == true) {
                    menu = false;
                    instruction = false;
                    if (flag == 0) {
                        cumTime = System.currentTimeMillis();
                        flag = 1;
                    }
                    if (gameovertracker == 1) {
                        startPlay5 = false;

                        g = s.getGraphics();
                        paintGameOver(g);
                        g.dispose();
                        s.update();
                        try {
                            Thread.sleep(4000);
                        } catch (Exception e) {
                        }
                        menu = true;
                    }

                    g = s.getGraphics();
                    mainGamePaint5(g);
                    g.dispose();
                    s.update();
                    int ax[] = new int[10];
                    int ay[] = new int[10];
                    for (int i = 0; i < 7; i++) {
                        ax[i] = 188 + 63 * i;
                    }
                    for (int i = 0; i < 7; i++) {
                        ay[i] = 120 + 63 * i;
                    }
                    Random r = new Random();
                    if (count < 7) {
                        int indx = r.nextInt(7);
                        int indy = r.nextInt(7);
                        randx[count] = ax[indx];
                        randy[count++] = ay[indy];
                    }
                    int seconds = 0;
                    long timePassed = System.currentTimeMillis() - cumTime;
                    timePassed2 += timePassed;
                    if (timePassed2 >= 950000) {
                        removeRandomPics = true;
                        timePassed2 = 0;
                    }

                    if (score == 250) {
                        startPlay5 = false;
                        g = s.getGraphics();
                        PaintGameFinished(g);
                        g.dispose();
                        s.update();
                        try {
                            Thread.sleep(5000);
                        } catch (Exception e) {
                        }
                        gameovertracker = 0;
                        menu = true;
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        init();
                    } else if (clickCount == 7) {
                        System.out.println(clickCount);
                        clickCount = 0;
                        count = 0;
                        removeRandomPics = false;
                        flag = 0;
                        score = 180;
                        init();
                    }
                }

            }
        } finally {
            s.restoreScreen();
        }
    }
    
    /**
     * method to draw Opening Screen of game
     * @param g : Graphics2D object
     */
    public void paintOpeningScreen(Graphics2D g) {
        g.drawImage(bg, 0, 0, null);
        if (showapple == true) {
            g.setColor(Color.red);
        }
        g.drawImage(bg7, 336, 230, null);
        g.drawImage(bg2, 147, 230, null);
        g.drawImage(bg23, 529, 230, null);

    }
    
    /**
     * method to draw for game level one
     * @param g : Graphics2D object
     */
    public void mainGamePaint(Graphics2D g) {
        g.drawImage(bg3, 0, 0, null);
        g.drawImage(bg6, 287, 219, null);
        if (count >= 3) {
            g.drawImage(bg4, randx[0], randy[0], null);
            g.drawImage(bg4, randx[1], randy[1], null);
            g.drawImage(bg4, randx[2], randy[2], null);
        }
        if (removeRandomPics == true) {
            g.drawImage(bg6, 287, 220, null);
        }
        Font f = new Font("Cooper Black", Font.BOLD, 20);
        g.setFont(f);
        String ss = Integer.toString(score);
        g.drawString(ss, 546, 22);
        g.drawString("1", 412, 22);
        ss = Integer.toString(clickCount);
        g.drawString(ss, 289, 22);
    }
    
    /**
     * method to draw for game level two
     * @param g : Graphics2D object
     */
    public void mainGamePaint2(Graphics2D g) {
        g.drawImage(bg3, 0, 0, null);
        g.drawImage(bg8, 258, 189, null);
        if (count >= 4) {
            g.drawImage(bg4, randx[0], randy[0], null);
            g.drawImage(bg4, randx[1], randy[1], null);
            g.drawImage(bg4, randx[2], randy[2], null);
            g.drawImage(bg4, randx[3], randy[3], null);
        }
        if (removeRandomPics == true) {
            g.drawImage(bg8, 258, 189, null);
        }
        Font f = new Font("Cooper Black", Font.BOLD, 20);
        g.setFont(f);
        String ss = Integer.toString(score);
        g.drawString(ss, 546, 22);
        g.drawString("2", 412, 22);
        ss = Integer.toString(clickCount);
        g.drawString(ss, 289, 22);
    }
    
    /**
     * method to draw for game level three
     * @param g : Graphics2D object
     */
    public void mainGamePaint3(Graphics2D g) {
        g.drawImage(bg3, 0, 0, null);
        g.drawImage(bg9, 228, 159, null);
        if (count >= 5) {
            g.drawImage(bg4, randx[0], randy[0], null);
            g.drawImage(bg4, randx[1], randy[1], null);
            g.drawImage(bg4, randx[2], randy[2], null);
            g.drawImage(bg4, randx[3], randy[3], null);
            g.drawImage(bg4, randx[4], randy[4], null);
        }
        if (removeRandomPics == true) {
            g.drawImage(bg9, 228, 159, null);
        }
        Font f = new Font("Cooper Black", Font.BOLD, 20);
        g.setFont(f);
        String ss = Integer.toString(score);
        g.drawString(ss, 546, 22);
        g.drawString("3", 412, 22);
        ss = Integer.toString(clickCount);
        g.drawString(ss, 289, 22);
    }
    
    /**
     * method to draw for game level four
     * @param g : Graphics2D object
     */
    public void mainGamePaint4(Graphics2D g) {
        g.drawImage(bg3, 0, 0, null);
        g.drawImage(bg10, 198, 129, null);
        if (count >= 6) {
            g.drawImage(bg4, randx[0], randy[0], null);
            g.drawImage(bg4, randx[1], randy[1], null);
            g.drawImage(bg4, randx[2], randy[2], null);
            g.drawImage(bg4, randx[3], randy[3], null);
            g.drawImage(bg4, randx[4], randy[4], null);
            g.drawImage(bg4, randx[5], randy[5], null);
        }
        if (removeRandomPics == true) {
            g.drawImage(bg10, 198, 129, null);
        }
        Font f = new Font("Cooper Black", Font.BOLD, 20);
        g.setFont(f);
        String ss = Integer.toString(score);
        g.drawString(ss, 546, 22);
        g.drawString("4", 412, 22);
        ss = Integer.toString(clickCount);
        g.drawString(ss, 289, 22);
    }
    
    /**
     * method to draw for game level five
     * @param g : Graphics2D object
     */
    public void mainGamePaint5(Graphics2D g) {
        g.drawImage(bg3, 0, 0, null);
        g.drawImage(bg11, 168, 100, null);
        if (count >= 7) {
            g.drawImage(bg4, randx[0], randy[0], null);
            g.drawImage(bg4, randx[1], randy[1], null);
            g.drawImage(bg4, randx[2], randy[2], null);
            g.drawImage(bg4, randx[3], randy[3], null);
            g.drawImage(bg4, randx[4], randy[4], null);
            g.drawImage(bg4, randx[5], randy[5], null);
            g.drawImage(bg4, randx[6], randy[6], null);
        }
        if (removeRandomPics == true) {
            g.drawImage(bg11, 168, 100, null);
        }
        Font f = new Font("Cooper Black", Font.BOLD, 20);
        g.setFont(f);
        String ss = Integer.toString(score);
        g.drawString(ss, 546, 22);
        g.drawString("5", 412, 22);
        ss = Integer.toString(clickCount);
        g.drawString(ss, 289, 22);

    }
    
    /**
     * method to draw for game level after five
     * @param g : Graphics2D object
     */
    public void PaintGameFinished(Graphics2D g) {
        g.drawImage(bg17, 0, 0, null);
    }
    
    /**
     * method to draw for game instructions
     * @param g : Graphics2D object
     */
    public void paintInstructionScreen(Graphics2D g) {
        if (countinstructionimages == 0) {
            g.drawImage(bg18, 0, 0, null);
        }
        if (countinstructionimages == 1) {
            g.drawImage(bg19, 0, 0, null);
        }
        if (countinstructionimages == 2) {
            g.drawImage(bg20, 0, 0, null);
        }
        if (countinstructionimages == 3) {
            g.drawImage(bg21, 0, 0, null);
        }
        if (countinstructionimages == 4) {
            g.drawImage(bg22, 0, 0, null);
        }

        g.drawImage(bg24, 580, 480, null);
    }
    
    /**
     * method to draw for different tiles in game
     * @param g : Graphics2D object
     */
    public void paintGameOver(Graphics2D g) {
        if (score == 0) {
            g.drawImage(bg12, 0, 0, null);
        }
        if (score == 30) {
            g.drawImage(bg13, 0, 0, null);
        }
        if (score == 70) {
            g.drawImage(bg14, 0, 0, null);
        }
        if (score == 120) {
            g.drawImage(bg15, 0, 0, null);
        }
        if (score == 180) {
            g.drawImage(bg16, 0, 0, null);
        }
    }
    
    /**
     * method draw to OS selection for running the game
     * @param g : Graphics2D object
     */
    private void paintOsScreen(Graphics2D g) {
        g.drawImage(bg25, 0, 0, null);
        g.drawImage(bg26, 250, 250, null);
        g.drawImage(bg27, 250, 350, null);
    }

    public void keyTyped(KeyEvent e) {

    }
    
    /**
         * method to detect which key is pressed by the user 
         * also taking appropriate actions when that key is pressed
         * @param e KeyEvent
         */
    public void keyPressed(KeyEvent e) {
        /*if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gameRunning = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

        }*/
    }
    
    /**
         * method to detect which key is released by the user 
         * also taking appropriate actions when that key is pressed
         * @param e KeyEvent
         */
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (countinstructionimages < 4) {
                ++countinstructionimages;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (countinstructionimages > 0) {
                --countinstructionimages;
            }

        }

    }
    
    /**
         * method to detect which mouse is clicked by the user 
         * also taking appropriate actions when the mouse is clicked
         * @param e Mouse Event
         */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (startPlay == true) {
            if (e.getX() > 287 && e.getX() < 287 + 63 * 3 && e.getY() > 219 && e.getY() < 219 + 63 * 3) {
                ++clickCount;
                if (clickCount == 3) {
                    ++gameovertracker;
                }
            }

        }
        if (startPlay2 == true) {
            if (e.getX() > 257 && e.getX() < 257 + 63 * 4 && e.getY() > 189 && e.getY() < 189 + 63 * 4) {
                ++clickCount;
                if (clickCount == 4) {
                    ++gameovertracker;
                }
            }

        }

        if (startPlay3 == true) {
            if (e.getX() > 227 && e.getX() < 227 + 63 * 5 && e.getY() > 159 && e.getY() < 159 + 63 * 5) {
                ++clickCount;
                if (clickCount == 5) {
                    ++gameovertracker;
                }
            }

        }
        if (startPlay4 == true) {
            if (e.getX() > 197 && e.getX() < 197 + 63 * 6 && e.getY() > 129 && e.getY() < 129 + 63 * 6) {
                ++clickCount;
                if (clickCount == 6) {
                    ++gameovertracker;
                }
            }

        }
        if (startPlay5 == true) {
            if (e.getX() > 167 && e.getX() < 167 + 63 * 7 && e.getY() > 99 && e.getY() < 99 + 63 * 7) {
                ++clickCount;
                if (clickCount == 7) {
                    ++gameovertracker;
                }
            }

        }

        if ((gameRunning == true && menu == true)) {
            x = e.getX();
            y = e.getY();
            if (x > 153 && x < 280) {
                if (y > 234 && y < 280) {
                    startPlay = true;
                    menu = false;
                    gameovertracker = 0;
                    clickCount = 0;
                    count = 0;
                    removeRandomPics = false;
                    flag = 0;
                    score = 0;
                    init();
                }
            }
            if (x > 335 && x < 479 && y > 234 && y < 280) {
                if (instruction == false) {
                    instruction = true;
                    menu = false;
                }
            }

        }

        if (gameRunning == true && instruction == true) {
            if (e.getX() > 585 && e.getX() < 712 && e.getY() > 483 && e.getY() < 532) {//Back Button
                instruction = false;
                menu = true;
            }
        }

        if (gameRunning == true && menu == true) {
            if (e.getX() > 535 && e.getX() < 661 && e.getY() > 234 && e.getY() < 280) {//For Exit Button
                gameRunning = false;
                menu = false;

            }
        }
        System.out.println(x + " " + y);
        if (startPlay == true) {
            if (count >= 3) {
                if (e.getX() > randx[0] && e.getX() < randx[0] + 63 && flagclickpos1 == 0) {
                    if (e.getY() > randy[0] && e.getY() < randy[0] + 63) {
                        flagclickpos1 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[1] && e.getX() < randx[1] + 63 && flagclickpos2 == 0) {
                    if (e.getY() > randy[1] && e.getY() < randy[1] + 63) {
                        flagclickpos2 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[2] && e.getX() < randx[2] + 63 && flagclickpos3 == 0) {
                    if (e.getY() > randy[2] && e.getY() < randy[2] + 63) {
                        flagclickpos3 = 1;
                        score += 10;
                    }
                }

            }
        }

        if (startPlay2 == true) {
            if (count >= 4) {
                if (e.getX() > randx[0] && e.getX() < randx[0] + 63 && flagclickpos1 == 0) {
                    if (e.getY() > randy[0] && e.getY() < randy[0] + 63) {
                        flagclickpos1 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[1] && e.getX() < randx[1] + 63 && flagclickpos2 == 0) {
                    if (e.getY() > randy[1] && e.getY() < randy[1] + 63) {
                        flagclickpos2 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[2] && e.getX() < randx[2] + 63 && flagclickpos3 == 0) {
                    if (e.getY() > randy[2] && e.getY() < randy[2] + 63) {
                        flagclickpos3 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[3] && e.getX() < randx[3] + 63 && flagclickpos4 == 0) {
                    if (e.getY() > randy[3] && e.getY() < randy[3] + 63) {
                        flagclickpos4 = 1;
                        score += 10;
                    }
                }

            }
        }

        if (startPlay3 == true) {
            if (count >= 5) {
                if (e.getX() > randx[0] && e.getX() < randx[0] + 63 && flagclickpos1 == 0) {
                    if (e.getY() > randy[0] && e.getY() < randy[0] + 63) {
                        flagclickpos1 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[1] && e.getX() < randx[1] + 63 && flagclickpos2 == 0) {
                    if (e.getY() > randy[1] && e.getY() < randy[1] + 63) {
                        flagclickpos2 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[2] && e.getX() < randx[2] + 63 && flagclickpos3 == 0) {
                    if (e.getY() > randy[2] && e.getY() < randy[2] + 63) {
                        flagclickpos3 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[3] && e.getX() < randx[3] + 63 && flagclickpos4 == 0) {
                    if (e.getY() > randy[3] && e.getY() < randy[3] + 63) {
                        flagclickpos4 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[4] && e.getX() < randx[4] + 63 && flagclickpos5 == 0) {
                    if (e.getY() > randy[4] && e.getY() < randy[4] + 63) {
                        flagclickpos5 = 1;
                        score += 10;
                    }
                }

            }
        }

        if (startPlay4 == true) {
            if (count >= 6) {
                if (e.getX() > randx[0] && e.getX() < randx[0] + 63 && flagclickpos1 == 0) {
                    if (e.getY() > randy[0] && e.getY() < randy[0] + 63) {
                        flagclickpos1 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[1] && e.getX() < randx[1] + 63 && flagclickpos2 == 0) {
                    if (e.getY() > randy[1] && e.getY() < randy[1] + 63) {
                        flagclickpos2 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[2] && e.getX() < randx[2] + 63 && flagclickpos3 == 0) {
                    if (e.getY() > randy[2] && e.getY() < randy[2] + 63) {
                        flagclickpos3 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[3] && e.getX() < randx[3] + 63 && flagclickpos4 == 0) {
                    if (e.getY() > randy[3] && e.getY() < randy[3] + 63) {
                        flagclickpos4 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[4] && e.getX() < randx[4] + 63 && flagclickpos5 == 0) {
                    if (e.getY() > randy[4] && e.getY() < randy[4] + 63) {
                        flagclickpos5 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[5] && e.getX() < randx[5] + 63 && flagclickpos6 == 0) {
                    if (e.getY() > randy[5] && e.getY() < randy[5] + 63) {
                        flagclickpos6 = 1;
                        score += 10;
                    }
                }

            }
        }

        if (startPlay5 == true) {
            if (count >= 7) {
                if (e.getX() > randx[0] && e.getX() < randx[0] + 63 && flagclickpos1 == 0) {
                    if (e.getY() > randy[0] && e.getY() < randy[0] + 63) {
                        flagclickpos1 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[1] && e.getX() < randx[1] + 63 && flagclickpos2 == 0) {
                    if (e.getY() > randy[1] && e.getY() < randy[1] + 63) {
                        flagclickpos2 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[2] && e.getX() < randx[2] + 63 && flagclickpos3 == 0) {
                    if (e.getY() > randy[2] && e.getY() < randy[2] + 63) {
                        flagclickpos3 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[3] && e.getX() < randx[3] + 63 && flagclickpos4 == 0) {
                    if (e.getY() > randy[3] && e.getY() < randy[3] + 63) {
                        flagclickpos4 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[4] && e.getX() < randx[4] + 63 && flagclickpos5 == 0) {
                    if (e.getY() > randy[4] && e.getY() < randy[4] + 63) {
                        flagclickpos5 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[5] && e.getX() < randx[5] + 63 && flagclickpos6 == 0) {
                    if (e.getY() > randy[5] && e.getY() < randy[5] + 63) {
                        flagclickpos6 = 1;
                        score += 10;
                    }
                }
                if (e.getX() > randx[6] && e.getX() < randx[6] + 63 && flagclickpos7 == 0) {
                    if (e.getY() > randy[6] && e.getY() < randy[6] + 63) {
                        flagclickpos7 = 1;
                        score += 10;
                    }
                }

            }
        }

        if (os == true && gameRunning == false) {
            if (e.getX() > 298 && e.getX() < 543 && e.getY() > 250 && e.getY() < 279) {
                win7 = true;
                os = false;
                gameRunning = true;
            } else if (e.getX() > 298 && e.getX() < 543 && e.getY() > 350 && e.getY() < 379) {
                win8 = true;
                os = false;
                gameRunning = true;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
