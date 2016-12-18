
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.awt.event.*;
import javax.swing.*;

public class pacman extends JPanel {

    int x = 250, y = 250, flag3 = 0;
    int[][] pointposition = new int[20][20];
    boolean pac_kill=false;
    int score=0;
    private static class Main1 {

        static int flag = 1;
        static int flag1 = 1;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(pac_kill){
            System.out.println("game over");
        }
        Graphics2D g2 = (Graphics2D) g;
        Image background = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\gridbg.png");
        Image pac1 = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\pac1.gif");
        Image pac2 = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\pac2.gif");
        Image pac3 = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\pac3.gif");
        Image pac4 = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\pac4.gif");

        Image ghost1 = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\ghost.png");

        Image point = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\point.png");

        Image top = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\bordertop.png");
        Image down = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\borderdown.png");
        Image left = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\borderleft.png");
        Image right = Toolkit.getDefaultToolkit().getImage("D:\\Exps\\Js\\pacman\\src\\pacman\\borderright.png");

        g2.drawImage(background, -5, -5, null);
        //pac movement
        if (x >= 450) {
            x = 0;
        } else if (y >= 450) {
            y = 0;
        } else if (x <= 0) {
            x = 450;
        } else if (y <= 0) {
            y = 450;
        } else {
            if (x % 50 == 0 && y % 50 == 0 && Main1.flag != Main1.flag1) {
                Main1.flag1 = Main1.flag;
            }
        }

        switch (Main1.flag1) {
            case 1:
                g2.drawImage(pac1, x++, y, null);
                break;
            case 2:
                g2.drawImage(pac2, x, y--, null);
                break;
            case 3:
                g2.drawImage(pac3, x--, y, null);
                break;
            case 4:
                g2.drawImage(pac4, x, y++, null);
                break;
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }

        g2.drawImage(right, 455, 0, null);
        g2.drawImage(left, 0, 0, null);
        g2.drawImage(top, 0, 0, null);
        g2.drawImage(down, 0, 455, null);

        //System.out.println(x/50+"  "+y/50);
        for (int l = 0; l < 8; l++) {
            for (int m = 0; m < 8; m++) {
                if (pointposition[l][m] == 0) {
                    g2.drawImage(point, 60 + 50 * l, 60 + 50 * m, null);
                    
                }
            }
        }
        if (x / 50 != 0 && y / 50 != 0) {
            if (Main1.flag1 == 1 || Main1.flag1 == 4) {
                pointposition[x / 50 - 1][y / 50 - 1] = 1;
                
            } else if (Main1.flag1 == 2) {
                pointposition[x / 50 - 1][y / 50] = 1;
            } else if (Main1.flag1 == 3) {
                pointposition[x / 50][y / 50 - 1] = 1;
            }
        }
        
        //--------------ghost
        g2.drawImage(ghost1, 100, 100,null);
        if(x/50==1 && y/50==1){
            pac_kill=true;
        }
        g2.setFont(new Font("Tahoma",Font.BOLD,30));
        g2.drawString("Score "+score, 50, 600);
        
        
        
        
        
        repaint();

    }

    public static void main(String[] args) {
        JFrame f = new JFrame("pac");
        f.setSize(600, 600);
        //adds
        f.add(new pacman(), BorderLayout.CENTER);
        f.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("right");
                    if (Main1.flag == 2 || Main1.flag == 4) {
                        Main1.flag = 1;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("up");
                    if (Main1.flag == 3 || Main1.flag == 1) {
                        Main1.flag = 2;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("left");
                    if (Main1.flag == 2 || Main1.flag == 4) {
                        Main1.flag = 3;
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("down");
                    if (Main1.flag == 1 || Main1.flag == 3) {
                        Main1.flag = 4;
                    }
                }
            }
        });
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
