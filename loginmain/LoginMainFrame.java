package koutsuhi.loginmain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import koutsuhi.list.ListFrame;
import koutsuhi.login.LoginFrame;

public class LoginMainFrame extends JFrame {

	String date;
	String userName;

	JTextField t1 = new JTextField(10);
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JTextField t4 = new JTextField(10);
	JTextField t5 = new JTextField(10);
	JTextArea c2;

	public LoginMainFrame(String userName) {
		this.userName = userName;
		setTitle("로그인 메인화면");
		setSize(450,500);
		setLayout(new BorderLayout(10,10));
		showCenter();
		showButton();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel1 = new JPanel();
		JLabel label = new JLabel("환영합니다. " + userName + " 님");
		panel1.add(label);
		add(panel1, BorderLayout.NORTH);

		setVisible(true);
	}
	void showCenter() {
		JPanel panel2 = new JPanel(new GridLayout(6,2));
		JLabel l1 = new JLabel("오늘 날짜");
		JLabel l2 = new JLabel("출발지");
		JLabel l3 = new JLabel("도착지");
		JLabel l4 = new JLabel("금액");
		JLabel l5 = new JLabel("횟수");
		JLabel l6 = new JLabel("교통비");

		c2 = new JTextArea("",1,10);
		c2.setEditable(false);



		panel2.add(l1);	panel2.add(t1);
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
		JButton b4 = new JButton("조회하기");

		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t4.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "금액을 입력하세요.");
				} else if(t5.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "횟수를 입력하세요.");
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
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate localDate = LocalDate.parse(t1.getText(), formatter);
					date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));


					saveDataToFile();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				t5.setText("");
				c2.setText("");
			}
		});


		b4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ListFrame();
			}
		});


		panel3.add(b1);
		panel3.add(b2);
		panel3.add(b4);
		panel3.add(b3);
		add(panel3, BorderLayout.SOUTH);
	}
	void saveDataToFile() throws IOException {
		String folderPath = "src\\koutsuhi\\data\\" + date + "\\";
		File folder = new File(folderPath);
		folder.mkdir();
	    String filename = folderPath + date + "_" + LoginFrame.userId + ".txt";
	    String newData = getCurrentData();

	    if (fileExists(filename) && fileContainsData(filename, newData)) {
	        int option = JOptionPane.showConfirmDialog(null, "이미 저장된 동일한 데이터가 있습니다. 덮어쓰시겠습니까?", "데이터 저장", JOptionPane.YES_NO_OPTION);
	        if (option == JOptionPane.YES_OPTION) {
	            saveToFile(filename, newData);
	        }
	    } else {
	        saveToFile(filename, newData);
	    }
	}
	boolean fileExists(String filename) {
	    File file = new File(filename);
	    return file.exists();
	}

	void saveToFile(String filename, String data) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
	    	writer.write(data);
	    	if (fileExists(filename)) {
	    		writer.newLine();
	    	}
	        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 저장되었습니다.");
	    }
	}
	boolean fileContainsData(String filename, String data) throws IOException {
	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.equals(data)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}


    String getCurrentData() {
        return t1.getText() + "," + t2.getText() + "," + t3.getText() + "," + t4.getText() + "," + t5.getText() + "," + c2.getText();
    }

}