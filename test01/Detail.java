package test01;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Detail extends JFrame {
	
	JPanel main_panel; // 버튼을 붙여질 메인 패널 선언

	JButton btn;
	
	
	public Detail() {
		JFrame fr = new JFrame("Flying Fries");
        ImageIcon icon = new ImageIcon("image/detail.jpg");
        JPanel pn = new JPanel() {
            public void paintComponent(Graphics g) {
            	if (icon != null) {  
      		      g.drawImage(icon.getImage(), 0, 0, this);  
      		    } 
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
        
        BorderLayout fl = new BorderLayout();
        
        ImageIcon img = new ImageIcon("image/btn_start.png");
    	ImageIcon img2 = new ImageIcon("image/btn_start2.png");
    	
    	JLabel lbl = new JLabel();
        lbl.setBounds(30, 200, 274, 50);
        lbl.setText("게임 설명");
        lbl.setHorizontalAlignment(JLabel.CENTER); // 수평 가운데 정렬
        fr.getContentPane().add(lbl);
        
        pn.setLayout(fl);
        
        /*게임시작 버튼*/
        JButton btn = new JButton(img);
        btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Bird();
				pn.setVisible(false);	// 창 안보이게 하기
			}
		});
        
		btn.setBounds(300, 300, 300, 300);
		btn.setRolloverIcon(img2); // 버튼에 마우스가 올라갈떄 이미지 변환
		btn.setBorderPainted(false); // 버튼 테두리 설정해제
		btn.setContentAreaFilled(false);
		btn.setPreferredSize(new Dimension(330, 120)); // 버튼 크기 지정

        pn.add(btn, BorderLayout.SOUTH);

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
	
	public static void main(String[] args) throws IOException {
		new Detail();
	}
}