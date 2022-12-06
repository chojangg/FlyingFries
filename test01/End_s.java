package test01;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class End_s extends JFrame {
	
	JPanel main_panel; // 버튼을 붙여질 메인 패널 선언

	JButton btn;
	
	public End_s() {
		JFrame fr = new JFrame("Flying Fries");
        ImageIcon icon = new ImageIcon("image/success.jpg");
        JPanel pn = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0, 0, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        
        FlowLayout fl = new FlowLayout();
        
        ImageIcon img = new ImageIcon("image/btn_restart.png");
    	ImageIcon img2 = new ImageIcon("image/btn_restart2.png");
    	ImageIcon img3 = new ImageIcon("image/btn_end.png");
    	ImageIcon img4 = new ImageIcon("image/btn_end2.png");
        
        pn.setLayout(fl);
        
        /* btn_restart: 재시작 버튼 */
        JButton btn_restart = new JButton(img);
        btn_restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new home();	// TO DO: 배경 흐르는 화면 전환
				pn.setVisible(false);	// 창 안보이게 하기
			}
		});
        btn_restart.setBorder(BorderFactory.createEmptyBorder(620 , 0, 0 , 10));
		btn_restart.setRolloverIcon(img2); // 버튼에 마우스가 올라갈떄 이미지 변환
		btn_restart.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_restart.setContentAreaFilled(false);
		
		
		/* btn_end: 게임종료 버튼 */
        JButton btn_end = new JButton(img3);
        btn_end.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 // 프레임 종료
			     dispose();
			     // 프로세스 종료
			     System.exit(0);
			}
		});
        btn_end.setBorder(BorderFactory.createEmptyBorder(620 , 0, 0 , 0));
		btn_end.setRolloverIcon(img4); // 버튼에 마우스가 올라갈떄 이미지 변환
		btn_end.setBorderPainted(false); // 버튼 테두리 설정해제
		btn_end.setContentAreaFilled(false);
		
        pn.add(btn_restart);
        pn.add(btn_end);
        
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