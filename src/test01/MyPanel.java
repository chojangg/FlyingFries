package test01;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	public MyPanel() {
		setFocusable(true);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					backX--;
					repaint();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(backImg, backX, 0, this);
	}
}
