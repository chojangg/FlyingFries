package test01;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class home extends JFrame{
	/*�����ִ� �̹����� �����ӿ� �׷��ٰ���.*/
	private Image background=new ImageIcon(home.class.getResource("../image/home.png")).getImage();//����̹���
	private Image logo = new ImageIcon(play.class.getResource("../image/Icon.png")).getImage();
	
	/*������*/
	public home() {
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
		new home();
		JFrame frame =new JFrame();
        JPanel panel = new JPanel(); 
		JButton btn_start = new JButton("���ӽ���");
		panel.setLayout(new BorderLayout());
		panel.add(btn_start, BorderLayout.CENTER);
		
	}
	
	
	
}
