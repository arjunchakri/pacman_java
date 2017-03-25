
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
//import java.awt.event.*;
import javax.swing.*;

public class pacman extends JPanel {

    int x = 250, y = 250, flag3 = 0;
    int xg1 = 100 , yg1 = 100;

    int[][] pointposition = new int[20][20];
    boolean pac_kill=false;
    int score=0;
    private static class Main1 {

        static int flag3 = 1;  //ghost temp movement
        static int flagg1 = 1; //ghost actual movement
        static int flag = 1;//pac temp movement
        static int flag1 = 1;//pac actual movement
    }
@Override
    public void paint(Graphics g) {
        super.paint(g);
        if(pac_kill){
            System.out.println("game over");
            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to Play again?");
            if(confirm==0){
                pacman pc = new pacman();
                
            }
            else if(confirm==1){
                System.exit(0);
            }
        }
        Graphics2D g2 = (Graphics2D) g;

        Image background = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\gridbg.png");
        Image pac1 = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\pac1.gif");
        Image pac2 = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\pac2.gif");
        Image pac3 = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\pac3.gif");
        Image pac4 = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\pac4.gif");

        Image ghost1 = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\ghost.png");

        Image point = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\point.png");

        Image top = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\bordertop.png");
        Image down = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\borderdown.png");
        Image left = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\borderleft.png");
        Image right = Toolkit.getDefaultToolkit().getImage("F:\\Softwares\\Java Proj\\pacman_java-master\\borderright.png");

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
            Thread.sleep(7);
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
    
        Random r = new Random();
int alter = r.nextInt(3);
//Main1.flag3 = r.nextInt(4);
        if(alter == 1){
        if(x>xg1){
                Main1.flag3 = 1;
            
        }
        else{
                Main1.flag3 = 3;
        }
        }
        else if(alter == 2){
        if(y>yg1){
                Main1.flag3 = 4;
            }
            else{
                Main1.flag3 = 2;
            }
        }
        if(alter == 3){
            Main1.flag3 = r.nextInt(4);
        }
        if (xg1 >= 450) {
            xg1 = 0;
        } else if (yg1 >= 450) {
            yg1 = 0;
        } else if (xg1 <= 0) {
            xg1 = 450;
        } else if (yg1 <= 0) {
            yg1 = 450;
        } else {
            if (xg1 % 50 == 0 && yg1 % 50 == 0 && Main1.flag3 != Main1.flagg1) {
                Main1.flagg1 = Main1.flag3;
            }
        }
        
        switch (Main1.flagg1) {
            case 1:
                g2.drawImage(ghost1, xg1++, yg1, null);
                break;
            case 2:
                g2.drawImage(ghost1, xg1, yg1--, null);
                break;
            case 3:
                g2.drawImage(ghost1, xg1--, yg1, null);
                break;
            case 4:
                g2.drawImage(ghost1, xg1, yg1++, null);
                break;
        }
        
        
        
        if(x==xg1 && y==yg1){
            pac_kill=true;
        }
        g2.setFont(new Font("Tahoma",Font.BOLD,30));
        g2.drawString("Score "+score, 50, 600);
        
        
        
        
        
        repaint();
    }

    public pacman(){
        JFrame f = new JFrame("pac");
        f.setSize(600, 600);
        //adds
        f.add(this, BorderLayout.CENTER);
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
    public static void main(String[] args) {
        new pacman();
    }
}
