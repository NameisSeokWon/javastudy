package koutsuhi.login;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import koutsuhi.signup.SignUpFrame;

public class LoginFrame extends JFrame {

	JButton b1 = new JButton("로그인");
	JButton b2 = new JButton("회원가입");

	public LoginFrame() {
		setTitle("교통비정산 시스템 - 로그인");
		setSize(300,200);
		setLocationRelativeTo(null);

		setLayout(new GridLayout(2,1));

		// 1번판낼
		JPanel panel1 = new JPanel(new GridLayout(2, 2));
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("ID");
		JTextField t1 = new JTextField(10);
		p1.add(l1);
		p1.add(t1);
		JPanel p2 = new JPanel();
		JLabel l2 = new JLabel("PW");
		JTextField t2 = new JTextField(10);
		p2.add(l2);
		p2.add(t2);
		panel1.add(p1);
		panel1.add(p2);

		JPanel panel2 = new JPanel();

		panel2.add(b1);
		panel2.add(b2);

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new SignUpFrame();
			}
		});

		add(panel1);
		add(panel2);

		setVisible(true);
	}

	public static void main(String[] args) {
		new LoginFrame();
	}

}
