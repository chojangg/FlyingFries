package test01;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class End_s extends JFrame {
	
	JPanel main_panel; // ��ư�� �ٿ��� ���� �г� ����

	JButton btn;
	
	public End_s() {
		JFrame fr = new JFrame("Flying Fries");
        ImageIcon icon = new ImageIcon("image/success.jpg");
        JPanel pn = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
                super.paintComponent(g);
            }
        };
        
        FlowLayout fl = new FlowLayout();
        
        ImageIcon img = new ImageIcon("image/btn_restart.png");
    	ImageIcon img2 = new ImageIcon("image/btn_restart2.png");
    	ImageIcon img3 = new ImageIcon("image/btn_end.png");
    	ImageIcon img4 = new ImageIcon("image/btn_end2.png");
        
        pn.setLayout(fl);
        
        /* btn_restart: ����� ��ư */
        JButton btn_restart = new JButton(img);
        btn_restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new home();	// TO DO: ��� �帣�� ȭ�� ��ȯ
				pn.setVisible(false);	// â �Ⱥ��̰� �ϱ�
			}
		});
        btn_restart.setBorder(BorderFactory.createEmptyBorder(620 , 0, 0 , 10));
		btn_restart.setRolloverIcon(img2); // ��ư�� ���콺�� �ö󰥋� �̹��� ��ȯ
		btn_restart.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_restart.setContentAreaFilled(false);
		
		
		/* btn_end: �������� ��ư */
        JButton btn_end = new JButton(img3);
        btn_end.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 // ������ ����
			     dispose();
			     // ���μ��� ����
			     System.exit(0);
			}
		});
        btn_end.setBorder(BorderFactory.createEmptyBorder(620 , 0, 0 , 0));
		btn_end.setRolloverIcon(img4); // ��ư�� ���콺�� �ö󰥋� �̹��� ��ȯ
		btn_end.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_end.setContentAreaFilled(false);
		
        pn.add(btn_restart);
        pn.add(btn_end);
        
        fr.setContentPane(pn);

        fr.setSize(1300, 850);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������(�ڹ� ȭ��) ũ��
     	Dimension frameSize = fr.getSize();
     	// ����� ũ��
     	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        fr.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        
        fr.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
	     	   File file = new File("bgm/Desert-Planet-Quincas-Moreira.wav");
	            Clip clip = AudioSystem.getClip();
	            clip.open(AudioSystem.getAudioInputStream(file));
	            clip.start();
	        } catch (Exception e) {
	            System.err.println("Put the music.wav file in the sound folder if you want to play background music, only optional!");
	        }
		new End_s();
	}
}