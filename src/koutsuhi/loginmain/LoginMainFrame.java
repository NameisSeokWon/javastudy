package koutsuhi.loginmain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginMainFrame extends JFrame {
	SimpleDateFormat sdf;
	String today;
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JTextField t4 = new JTextField(10);
	JTextField t5 = new JTextField(10);
	JTextArea c2;

	public LoginMainFrame(String userName) {
		setTitle("로그인 메인화면");
		setSize(350,400);
		setLayout(new BorderLayout(10,10));
		showCenter();
		showButton();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		JLabel label = new JLabel("환영합니다." + userName + " 님");
		panel1.add(label);
		add(panel1, BorderLayout.NORTH);



		setVisible(true);
	}
	void showCenter() {
		JPanel panel2 = new JPanel(new GridLayout(6,2));
		JLabel l1 = new JLabel("오늘 날짜");
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		today = sdf.format(new Date());
		JTextArea c1 = new JTextArea(today,1,10);
		c1.setEditable(false);

		JLabel l2 = new JLabel("출발지");
		JLabel l3 = new JLabel("도착지");
		JLabel l4 = new JLabel("금액");
		JLabel l5 = new JLabel("출근일수");
		JLabel l6 = new JLabel("교통비");
		c2 = new JTextArea("",1,10);

		c2.setEditable(false);

		panel2.add(l1);	panel2.add(c1);
		panel2.add(l2);	panel2.add(t2);
		panel2.add(l3);	panel2.add(t3);
		panel2.add(l4);	panel2.add(t4);
		panel2.add(l5);	panel2.add(t5);
		panel2.add(l6);	panel2.add(c2);


		add(panel2, BorderLayout.CENTER);
	}
	void showButton() {

		JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		JButton b1 = new JButton("정산");
		JButton b2 = new JButton("저장");
		JButton b3 = new JButton("새로고침");

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(t4.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "금액을 입력하세요.");

				} else if(t5.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "출근일수를 입력하세요.");
				} else {
					String inputPrice = t4.getText();
					int price = Integer.parseInt(inputPrice);
					String inputWorkday = t5.getText();
					int workday = Integer.parseInt(inputWorkday);

					int sum = price * workday;

					c2.setText(String.valueOf(sum));
				}

			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				saveDataToFile();
			}
		});
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				c2.setText("");
			}
		});

		panel3.add(b1);
		panel3.add(b2);
		panel3.add(b3);
		add(panel3, BorderLayout.SOUTH);
	}
	void saveDataToFile() {
		String filename = "C:\\Users\\msi\\Desktop\\wevars study\\workspace\\swing\\src\\koutsuhi\\202402" + t2.getText() + "_" + today + ".txt";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
			writer.write(t2.getText() + ",");
			writer.write(t3.getText() + ",");
			writer.write(t4.getText() + ",");
			writer.write(t5.getText() + ",");
			writer.write(c2.getText() + "\n");
			JOptionPane.showMessageDialog(null, "데이터가 성공적으로 저장되었습니다.");
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.");
	            e.printStackTrace();
	        }
	}
}


