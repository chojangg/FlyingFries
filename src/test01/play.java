package test01;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class play extends JFrame{
	/*�����ִ� �̹����� �����ӿ� �׷��ٰ���.*/
	private Image background=new ImageIcon(play.class.getResource("../image/background.png")).getImage();//����̹���
	private Image logo = new ImageIcon(play.class.getResource("../image/Icon.png")).getImage();
	private Image power = new ImageIcon(play.class.getResource("../image/power.gif")).getImage();
	// �̹��� ������ ��ġ

	// private ImageIcon character = new IamgeIcon("../image/character.png");
	
	// ��ֹ� ������ ��ġ
	
	/*������*/
	public play() {
		homeframe();
	}
	public void homeframe() {
		setIconImage(logo);
		setTitle("Flying Fries");//Ÿ��Ʋ
		setSize(1300,800);//�������� ũ��
		setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);//â�� ��� ������
		setLayout(null);//���̾ƿ��� ������� ���������ϰ� ����.
		setVisible(true);//â�� ���̰�	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//JFrame�� ���������� ����ǰ�
	}
	public void paint(Graphics g) {//�׸��� �Լ�
		g.drawImage(background, 0, 0, null);//background�� �׷���
	}
	public static void main(String[] args){
		try {
			BufferedImage image1 = ImageIO.read(new File("C:/FlyingFries/FlyingFries/src/image/power.gif"));
			BufferedImage image2 = ImageIO.read(new File("C:/FlyingFries/FlyingFries/src/image/Icon.gif"));

			int width = Math.max(image1.getWidth(), image2.getWidth());
			int height = image1.getHeight() + image2.getHeight();

		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		new play();
	}
}