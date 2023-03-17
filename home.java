package test01;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class home extends JFrame {
	
	public home() {
		JFrame fr = new JFrame("Flying Fries");
        ImageIcon icon = new ImageIcon("image/home.png");
        Image back = icon.getImage();
        JPanel pn = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(back, 0, 0, null);
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
                super.paintComponent(g);
            }
        };
        
        FlowLayout fl = new FlowLayout();
        
        ImageIcon img = new ImageIcon("image/btn_start.png");
    	ImageIcon img2 = new ImageIcon("image/btn_start2.png");
    	ImageIcon img3 = new ImageIcon("image/btn_how.png");
    	ImageIcon img4 = new ImageIcon("image/btn_how2.png");
        
        pn.setLayout(fl);
        
        
        /* btn_start: ���ӽ��� ��ư */
        JButton btn_start = new JButton(img);
        btn_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Bird();	// TO DO: ��� �帣�� ȭ�� ��ȯ
				pn.setVisible(false);	// â �Ⱥ��̰� �ϱ�
			}
		});
        btn_start.setBorder(BorderFactory.createEmptyBorder(620 , 0, 0 , 10));
		btn_start.setRolloverIcon(img2); // ��ư�� ���콺�� �ö󰥋� �̹��� ��ȯ
		btn_start.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_start.setContentAreaFilled(false);
		
		
		/* btn_how: ���ӹ�� ��ư */
        JButton btn_how = new JButton(img3);
        btn_how.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Detail();
				pn.setVisible(false);
			}
		});
        btn_how.setBorder(BorderFactory.createEmptyBorder(620 , 0, 0 , 0));
		btn_how.setRolloverIcon(img4); // ��ư�� ���콺�� �ö󰥋� �̹��� ��ȯ
		btn_how.setBorderPainted(false); // ��ư �׵θ� ��������
		btn_how.setContentAreaFilled(false);
		
        pn.add(btn_start);
        pn.add(btn_how);

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
	     	   File file = new File("bgm/Little-Samba-Quincas-Moreira.wav");
	            Clip clip = AudioSystem.getClip();
	            clip.open(AudioSystem.getAudioInputStream(file));
	            clip.start();
	        } catch (Exception e) {
	            System.err.println("Put the music.wav file in the sound folder if you want to play background music, only optional!");
	        }
		new home();
	}
	
	 
}