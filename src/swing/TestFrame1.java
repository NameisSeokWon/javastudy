package swing;

import java.awt.*;
import javax.swing.*;

public class TestFrame1 extends JFrame {
	TestFrame1(){
		setTitle("원 넓이 구하기");
		setLayout(new BorderLayout(10, 10));
		showNorth();
		showCalculation();
		showButton();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	void showNorth() {
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel panel = new JPanel(new GridLayout(2,0));

		JLabel l1 = new JLabel("원의 반지름");
		JLabel l2 = new JLabel("원의 넓이");

		JTextField t1 = new JTextField(10);
		JTextField t2 = new JTextField(10);

		p1.add(l1);
		p1.add(t1);
		p2.add(l2);
		p2.add(t2);

		panel.add(p1);
		panel.add(p2);

		add(panel, BorderLayout.NORTH);
	}
	void showCalculation() {

		JPanel panel = new JPanel();
		JTextArea c = new JTextArea("계산 결과가 나옵니다.",30,20);
//		c.setText("계산 결과가 나옵니다.");
		panel.add(c);
		c.setEditable(false);
		add(panel, BorderLayout.CENTER);
	}
	void showButton() {
		String[] color = {"red","green","blue"};
		JPanel panel = new JPanel(new GridLayout(0,3,10,10));
		JButton b1 = new JButton("계산");
		JComboBox<String> cb = new JComboBox<>(color);
		JButton b2 = new JButton("리셋");

		panel.add(b1);
		panel.add(cb);
		panel.add(b2);
		add(panel, BorderLayout.SOUTH);
	}


	public static void main(String[] args) {
		new TestFrame1();
	}
}
