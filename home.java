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
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        
        FlowLayout fl = new FlowLayout();
        
        ImageIcon img = new ImageIcon("image/btn_start.png");
    	ImageIcon img2 = new ImageIcon("image/btn_start2.png");
    	ImageIcon img3 = new ImageIcon("image/btn_how.png");
    	ImageIcon img4 = new ImageIcon("image/btn_how2.png");
        
        pn.setLayout(fl);
        
        
        /* btn_start: 게임시작 버튼 */
        JButton btn_start = new JButton(img);
        btn_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Bird();	// TO DO: 배경 흐르는 화면 전환
				pn.setVisible(false);	// 창 안보이게 하기
			}
		});
        btn_start.setBorder(BorderFactory.createEmptyBorder(620 , 0, 0 , 10));
		btn_start.setRolloverIcon(img2); // 버튼에 마우스가 올라갈떄 이미지 변환
		btn_start.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_start.setContentAreaFilled(false);
		
		
		/* btn_how: 게임방법 버튼 */
        JButton btn_how = new JButton(img3);
        btn_how.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Detail();
				pn.setVisible(false);
			}
		});
        btn_how.setBorder(BorderFactory.createEmptyBorder(620 , 0, 0 , 0));
		btn_how.setRolloverIcon(img4); // 버튼에 마우스가 올라갈떄 이미지 변환
		btn_how.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_how.setContentAreaFilled(false);
		
        pn.add(btn_start);
        pn.add(btn_how);

        fr.setContentPane(pn);

        fr.setSize(1300, 850);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 프레임(자바 화면) 크기
     	Dimension frameSize = fr.getSize();
     	// 모니터 크기
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