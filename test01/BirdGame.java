package test01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.*;

class Bird extends JFrame implements KeyListener, Runnable {
    int width=1300;    //������ ����
    int height=850;    //������ ����
    int x=100, y=350;  //�÷��̾� ��ǥ ����
    int score=0;    //���� ����
    int life=5;     //���� ����

    Bird() {
        getimg();  //���� �ҷ�����
        gogo();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ â ���� �� ���α׷� ����
        setTitle("FlyingFries");  //������ �̸�
        setSize(width, height);  //������ ũ��
        setResizable(false);
        setVisible(true);
        
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                    (windowSize.height - frameSize.height) / 2); 

    }

    Image player_img;       //�÷��̾� �̹���
    Image background_img;   //���ȭ�� �̹���
    Image explosion_img;    //���� �̹���
    Image arrow_img;        //���� �̹���
    Image alien_img;        //���� �̹���
    Image wordBG;           //���� ��� �� �̹���
    Image story_img;      //���丮 �̹���
    
    public void getimg() {   // �̹��� ��������
        try {
            wordBG = new ImageIcon("image/���ڹ��.png").getImage();
            arrow_img = new ImageIcon("image/feather.png").getImage();        //���� �̹���
            alien_img = new ImageIcon("image/pepper.gif").getImage();      //���� �̹���
            player_img = new ImageIcon("image/bird.gif").getImage();       //�÷��̾� �̹���
            background_img = new ImageIcon("image/background.png").getImage();   //��� �̹���
            explosion_img = new ImageIcon("image/exploding.png").getImage();    //���� �̹���
            story_img = new ImageIcon("image/story.gif").getImage();     //���丮 �̹���
        } catch (Exception e){
            System.out.println("������ �� �� �����ϴ�. ");
        }
    }

    Thread th;  //������ ����
    public void gogo() {
       
        addKeyListener(this);       //Ű���� �̺�Ʈ ����
        th = new Thread(this);  //������ ����
        th.start();  //������ ����
    }

    Arrow arrow;           //���� Ŭ����
    Alien alien;           //���� Ŭ����
    Explosion explosion;   //���� Ŭ����
    class Arrow {
        int x;  //���� ��ǥ
        int y;

        Arrow(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move() {
            x += 11;   //���� 11��ŭ ���������� ���
        }
    }

    class Alien {
        int x;  //���� ��ǥ
        int y;

        Alien(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move() {
            x -= alien_speed;     //��ֹ� 7��ŭ �������� �����̱�
        }
    }

    class Explosion {
        int x;  //���� ȿ�� ��ǥ
        int y;
        int maintain_int; //���� ȿ���� �����Ǵ� �ð��� ���� ����
        int situation;    //������ �Ͼ�� ��Ȳ
        //0:��ֹ��� ���п� �¾��� ��, 1:�÷��̾�� ��ֹ��� �浹���� ��

        Explosion(int x, int y, int situation) {
            this.x = x;
            this.y = y;
            this.situation = situation;
            maintain_int = 0;
        }

        public void maintain() {
            maintain_int++;     //������ Ÿ�� ���� ī��Ʈ
        }
    }

    int alien_speed=7;
    int round=1;
    int shoot=0;    //�������� �߻� �����ϱ� ���� ī��Ʈ ����
    int appear=0;   //��ֹ� ���� ���� ī��Ʈ ����
    int ch_break=0;
    public void run() {  //������ ���ѷ����Ǵ� �κ�
        try {   //���ܿɼ� �������� ���� ����
            while (true) {  //���ѷ���
            	 if(ch_break==1){
                     break;
                 }
                 if(score==100){     //���� ������ �ɶ����� ���̵� ���̱� (���� �ӵ� ������ �ϱ�)
                     round=2;
                     alien_speed=12;
                 }
                 else if(score==250){
                     round=3;
                     alien_speed=15;
                 }
                 else if(score==350){
                     round=4;
                     alien_speed=17;
                 }
                 else if(score==450){   //���̵��� 5�ܰ������
                     round=5;
                     alien_speed=19;
                 }
                 else if(score>=100) {
                 	new End_s();
                 	setVisible(false);
                 	break;
                 }
                KeyWok();           //Ű���� �Է����� x, y����
                WorkGame();         //���� ���� �޼ҵ�
                repaint();          //���ŵ� ������ �̹��� ���� �׸���
                Thread.sleep(20);  //20mill sec�� �ӵ��� ������ ������
                shoot++;    //���� �߻� ���� Ƚ�� ī��Ʈ
                appear++;   //��ֹ� ���� ���� ���� Ƚ�� ī��Ʈ
            }
        } catch (Exception e) {
            System.out.println("������ �߻��Ͽ����ϴ�. ");
        }
    }

    ArrayList arr_arrow = new ArrayList();      //���� �迭
    ArrayList arr_alien = new ArrayList();      //��ֹ� �迭
    ArrayList arr_explosion = new ArrayList();  //���� �迭
    public void WorkGame() {
        //���� ����
        for (int i = 0; i < arr_alien.size(); ++i) {
            alien = (Alien) (arr_alien.get(i));   //�迭�� ��ֹ��� ����������� �� �ش�Ǵ� ����
            alien.move();  //�ش� ���� �����̱�

            if (Crash_check(x, y, alien.x, alien.y, player_img, alien_img)==1) {    //�÷��̾ ��ֹ��� �浹���� ��
                life--;    //���� �ϳ� �ٱ�
                arr_alien.remove(i);   //�ش� ��ֹ� ����

                explosion = new Explosion(alien.x + alien_img.getWidth(null) / 2, alien.y + alien_img.getHeight(null) / 2, 0);
                //���� ���� �߽� ��ǥ�� ���� ��Ȳ "0" �ޱ�
                arr_explosion.add(explosion);   //�浹�� ���� ��ġ�� ���� ȿ�� �ֱ�
                explosion = new Explosion(x, y, 1); //�÷��̾��� ���� ��ǥ�� ���� ��Ȳ "1" �ޱ�
                arr_explosion.add(explosion);   //�浹�� �÷��̾��� ��ġ�� ���� ȿ�� �ֱ�
            }
        }
        if (appear == 150) {   //���� ���� 150���� ��ֹ� ����
        	 alien = new Alien(width + 100, 100);
             arr_alien.add(alien);   //�� ��ǥ�� ���� �� �迭�� �߰�
             alien = new Alien(width + 100, 300);
             arr_alien.add(alien);
             alien = new Alien(width + 100, 500);
             arr_alien.add(alien);
             alien = new Alien(width + 100, 700);
             arr_alien.add(alien);
            appear=0;   //appear �ʱ�ȭ
        }

        //���н��
        if (Space) {   //�����̽��ٰ� ��������
            if (shoot > 15) {   //���� ���� �߻� ���� ����  //������ 15�� ������ ���߰� �߻�
                arrow = new Arrow(x + 150, y + 30);
                arr_arrow.add(arrow);   //���� ���� �߰�
                shoot = 0;  //shoot �ʱ�ȭ
            }
        }

        for (int i = 0; i < arr_arrow.size(); ++i) {
            arrow = (Arrow) arr_arrow.get(i);
            arrow.move();
            for (int j = 0; j < arr_alien.size(); ++j) {
                alien = (Alien) arr_alien.get(j);
                if (Crash_check(arrow.x, arrow.y, alien.x, alien.y, arrow_img, alien_img)==1) {
                    //��ֹ��� ���п� ���� ��� ��ֹ��� ���� �� �� ȭ�鿡�� �����
                    arr_arrow.remove(i);
                    arr_alien.remove(j);
                    score += 10;  //���� +10
                    explosion = new Explosion(alien.x + alien_img.getWidth(null) / 2, alien.y + alien_img.getHeight(null) / 2, 0);
                    //���� ���� �߽� ��ǥ�� ���� ��Ȳ "0"�ޱ� (��ֹ��� Ȱ�� ���� ���)
                    arr_explosion.add(explosion);   //��ֹ��� ����� ��ġ�� ���� ȿ�� �߰�
                }
            }
        }

        //���� ȿ��
        for (int i = 0; i < arr_explosion.size(); ++i) {
            explosion = (Explosion) arr_explosion.get(i);
            explosion.maintain();
        }
    }

    public int Crash_check(int x1, int y1, int x2, int y2, Image img1, Image img2) {  //�浹 �޼ҵ�
        //�� �簢�� �̹����� �浹 ���� Ȯ��
        //x1+img1.getWidth(null)/2 �� img1�� �߽� ��ǥ
        //x2+img2.getWidth(null)/2 �� img2�� �߽� ��ǥ
        //�� ���� ���� �� �߽���ǥ ���� �Ÿ� ���
        //img1.getWidth(null)/2, img2.getWidth(null)/2
        //�� �̹����� ���� ������ ���� ������ ���ؼ� �� �̹����� ������������ �̹��� �߽���ǥ ���� �Ÿ� ���
        //���� �� �̹����� �������ִ� �Ÿ� < �� �̹����� �������� �� �߽���ǥ ���� �Ÿ� => ���� => true ����
        //�ƴϸ� false ����
        int check;
        if (Math.abs((x1 + img1.getWidth(null) / 2) - (x2 + img2.getWidth(null) / 2)) < (img2.getWidth(null) / 2 + img1.getWidth(null) / 2)
                && Math.abs((y1 + img1.getHeight(null) / 2) - (y2 + img2.getHeight(null) / 2)) < (img2.getHeight(null) / 2 + img1.getHeight(null) / 2)) {
            check = 1;
        } else {
            check = 0;
        }
        return check;
    }

    Image bufferimg;    //���� ���۸�
    Graphics bufferg;   //���� ���۸�
    public void paint(Graphics g) {
        bufferimg = createImage(width, height); //������۸� ���� ũ�⸦ ȭ�� ũ��� ���� ����
        bufferg = bufferimg.getGraphics();    //������ �׷��� ��ü ���
        update(g);
    }

    public void update(Graphics g) {
        Print_Background(); //��� �̹��� �׸���
        bufferg.drawImage(player_img, x, y, this);   //�÷��̾� �׸���
        Print_Alien();      //���� ��� �޼ҵ� ����
        Print_Arrow();      //���� ��� �޼ҵ� ����
        Print_Explosion();  //���� ȿ�� ��� �޼ҵ� ����
        Print_Text();       //���� �����Ȳ ǥ�� �ؽ�Ʈ ��� �޼ҵ� ����
        if(life==0){        //������ �� ���� ��� ���� ���� â ���
            Print_GAMEOVER();
        }
        g.drawImage(bufferimg, 0, 0, this);   //ȭ�鿡 ���ۿ� �׸� �׸��� ������ �׸���
    }

    int move_background = 0;   //��� �����̴� ȿ�� ���� ����
    public void Print_Background() {    //��� �̹��� ���
       
        bufferg.clearRect(0, 0, width, height);  //ȭ�� �����
        if (move_background > -700) {   //���� 0�� move_background ������ -700���� ũ�� ����
            bufferg.drawImage(background_img, move_background, 0, this);
            move_background -= 1;   //move_background ������ 0���� -1��ŭ ��� �ٿ��� ��� �������� -1�� �̵�
        } else {
            move_background = 0;    //0���� �ʱ�ȭ
        }
    }

    public void Print_Arrow() {    //���� �̹��� ���
        for (int i = 0; i < arr_arrow.size(); ++i) {
            arrow = (Arrow) (arr_arrow.get(i));
            bufferg.drawImage(arrow_img, arrow.x, arrow.y, this);
        }
    }

    public void Print_Alien() {  //���� �̹��� ���
        for (int i = 0; i < arr_alien.size(); ++i) {
            alien = (Alien) (arr_alien.get(i));
            bufferg.drawImage(alien_img, alien.x, alien.y-35, this);
        }
    }

    public void Print_Explosion() { //���� ȿ�� ���
        for (int i = 0; i < arr_explosion.size(); ++i) {
            explosion = (Explosion) arr_explosion.get(i);
            if (explosion.situation == 0) {    //���� ��Ȳ "0"�̸�
                if (explosion.maintain_int < 10) {  //10�� �ݺ��� ���� ���� ȿ�� ������Ű��
                    bufferg.drawImage(explosion_img, explosion.x - explosion_img.getWidth(null) / 2, explosion.y - explosion_img.getHeight(null) / 2, this);
                }
            } else {    //���� ��Ȳ "1"�̸�
                if (explosion.maintain_int < 8) {   //8�� �ݺ��� ���� ���� ȿ�� ������Ű��
                    bufferg.drawImage(explosion_img, explosion.x + 120, explosion.y + 15, this);
                }
            }
        }
    }

    public void Print_GAMEOVER(){   //���ӿ���
    	new End_F1();
        setVisible(false);
    }

    public void Print_Text() {  //���� ���� ��Ȳ �����ֱ�
    	bufferg.setFont(new Font("Rockwell", Font.BOLD, 30));  //��Ʈ ����
        bufferg.drawString("<"+round+" ROUND>", 1050,120);    //���� ǥ��
        bufferg.setFont(new Font("Rockwell", Font.BOLD, 23));  //��Ʈ ����
        bufferg.drawString("SCORE : " + score, 1050, 145);    //���� ǥ��
        bufferg.drawString("LIFE : " + life, 1050, 170);      //���� ���� ǥ��
    }

    ////Ű���� �Է�
    boolean Up = false;  //Ű���� �Է� ó���� ���� ����
    boolean Down = false;
    boolean Left = false;
    boolean Right = false;
    boolean Space = false;
    public void KeyWok() {
        //Ű���� �Է� �������� �÷��̾� 5�� �̵�
        if (Up == true) {
            y -= 5;   //ȭ�� ������ �������� �ϱ�
        }
        if (Down == true) {
            y += 5;
        }
        if (Left == true) {
            x -= 5;
        }
        if (Right == true) {
            x += 5;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { } //Ű���尡 Ÿ���ε� �� �̺�Ʈ ó���ϴ� ��

    public void keyPressed(KeyEvent e) {
        //Ű���尡 ���������� �̺�Ʈ ó���ϴ� ��
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Up = true;
                break;
            case KeyEvent.VK_DOWN:
                Down = true;
                break;
            case KeyEvent.VK_LEFT:
                Left = true;
                break;
            case KeyEvent.VK_RIGHT:
                Right = true;
                break;
            case KeyEvent.VK_SPACE:
                Space = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        //Ű���尡 �������ٰ� ���������� �̺�Ʈ ó���ϴ� ��
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Up = false;
                break;
            case KeyEvent.VK_DOWN:
                Down = false;
                break;
            case KeyEvent.VK_LEFT:
                Left = false;
                break;
            case KeyEvent.VK_RIGHT:
                Right = false;
                break;
            case KeyEvent.VK_SPACE:
                Space = false;
                break;
        }
    }
}

public class BirdGame {
    public static void main(String[] args) {
       
        Bird game = new Bird();
    }
}