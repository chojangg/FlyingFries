//package test01;
//
//import java.awt.Graphics;
//
//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//public class ImageExam01 extends JFrame implements Runnable{
//
//	private ImageIcon bg = null;
//	private ImageIcon bg1 = null;
//
//	public void ImageExam01() {
//
//	}
//
//	public void run() {
//		try {
//		JFrame frame = new JFrame();
//		frame.setSize(350,450);
//		frame.setLayout(null);
//		bg = new ImageIcon("../heart_empty");
//		bg1 = new ImageIcon("../heart_full");
//		JPanel Panel1 = new JPanel() {
//			public void paintComponent(Graphics g) {
//				g.drawImage(bg.getImage(), 0, 0, 350, 450, null);
//			}
//		};
//		Panel1.setBounds(0,0, 350, 450);
//		JPanel Panel2 = new JPanel(){
//			public void paintComponent(Graphics g) {
//				g.drawImage(bg.getImage(), 0, 0, 190, 290, null);
//			}
//		};
//		Panel2.setBounds(10,10,200,300);
//		frame.add(Panel2);
//		frame.add(Panel1);
//		frame.setVisible(true);
//		}catch (Exception e) {
//
//		}
//		public static void main (String[] a) {
//			Thread t = new Thread(new ImageExam01());
//			t.start();
//		}
//}
//
//// https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=wisthood00&logNo=140161932162
