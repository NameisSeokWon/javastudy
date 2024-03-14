package swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestFrame1 extends JFrame {
	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextArea c = new JTextArea("계산 결과가 나옵니다.",30,20);
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
//		c.setText("계산 결과가 나옵니다.");
		panel.add(c);
		c.setEditable(false);
		add(panel, BorderLayout.CENTER);
	}
	void showButton() {
		String[] color = {"red","green","blue"};
		JPanel panel = new JPanel(new GridLayout(0,3,10,10));
		JButton b1 = new JButton("계산");
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(t1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "반지름을 입력하세요. ");
					
				} else {
					String inputStr = t1.getText();
					int banji = Integer.parseInt(inputStr);
					
					float sum = banji * banji * 3.14f;
					t2.setText(String.valueOf(sum));
					c.setText(inputStr + "*" + inputStr + "*" + "3.14 =" + sum);
				}
				
			}
		});
		
		JComboBox<String> cb = new JComboBox<>(color);
		JButton b2 = new JButton("리셋");
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				c.setText("계산 결과가 나옵니다.");
				t2.setText("");
				t1.setText("");
			}
		});
		

		panel.add(b1);
		panel.add(cb);
		panel.add(b2);
		add(panel, BorderLayout.SOUTH);
	}


	public static void main(String[] args) {
		new TestFrame1();
	}
}
