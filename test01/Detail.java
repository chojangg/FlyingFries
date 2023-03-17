package test01;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Detail extends JFrame {
	
	JPanel main_panel; // ��ư�� �ٿ��� ���� �г� ����

	JButton btn;
	
	
	public Detail() {
		JFrame fr = new JFrame("Flying Fries");
        ImageIcon icon = new ImageIcon("image/detail.jpg");
        JPanel pn = new JPanel() {
            public void paintComponent(Graphics g) {
            	if (icon != null) {  
      		      g.drawImage(icon.getImage(), 0, 0, this);  
      		    } 
                setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
                super.paintComponent(g);
            }
        };
        
        BorderLayout fl = new BorderLayout();
        
        ImageIcon img = new ImageIcon("image/btn_start.png");
    	ImageIcon img2 = new ImageIcon("image/btn_start2.png");
    	
    	JLabel lbl = new JLabel();
        lbl.setBounds(30, 200, 274, 50);
        lbl.setText("���� ����");
        lbl.setHorizontalAlignment(JLabel.CENTER); // ���� ��� ����
        fr.getContentPane().add(lbl);
        
        pn.setLayout(fl);
        
        /*���ӽ��� ��ư*/
        JButton btn = new JButton(img);
        btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Bird();
				pn.setVisible(false);	// â �Ⱥ��̰� �ϱ�
			}
		});
        
		btn.setBounds(300, 300, 300, 300);
		btn.setRolloverIcon(img2); // ��ư�� ���콺�� �ö󰥋� �̹��� ��ȯ
		btn.setBorderPainted(false); // ��ư �׵θ� ��������
		btn.setContentAreaFilled(false);
		btn.setPreferredSize(new Dimension(330, 120)); // ��ư ũ�� ����

        pn.add(btn, BorderLayout.SOUTH);

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
	
	public static void main(String[] args) throws IOException {
		new Detail();
	}
}