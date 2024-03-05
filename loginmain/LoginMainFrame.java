package koutsuhi.loginmain;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginMainFrame extends JFrame {
	SimpleDateFormat sdf;
	String today;
	String userName;
	JTextField t2 = new JTextField(10);
	JTextField t3 = new JTextField(10);
	JTextField t4 = new JTextField(10);
	JTextField t5 = new JTextField(10);
	JTextArea c2;

	public LoginMainFrame(String userName) {
		this.userName = userName;
		setTitle("로그인 메인화면");
		setSize(350,400);
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
		JButton b4 = new JButton("조회하기");

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

					int sum = price * workday * 2;

					c2.setText(String.valueOf(sum));
				}

			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveDataToFile();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
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
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					showData();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

			}
		});

		panel3.add(b4);
		panel3.add(b1);
		panel3.add(b2);
		panel3.add(b3);
		add(panel3, BorderLayout.SOUTH);
	}
	void saveDataToFile() throws IOException {
        String filename = "C:\\Users\\msi\\Desktop\\wevars study\\workspace\\swing\\src\\koutsuhi\\202402" + userName + "_" + today + ".txt";
        File file = new File(filename);

        if (file.exists() && fileContainsData(file, getCurrentData())) {
        	//이미 저장된 동일한 데이터가 있는지 확인하고, 있는 경우 사용자에게 옵션을 제공하여 데이터를 추가하거나 덮어쓰거나 취소할 수 있도록 함
        	Object[] options = { "추가", "덮어쓰기", "취소" };
            int option = JOptionPane.showOptionDialog(null, "이미 저장된 동일한 데이터가 있습니다. 새로운 데이터로 추가하시겠습니까?", "데이터 저장", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
            if (option == JOptionPane.YES_OPTION) {
                saveToFile(filename, true); // 파일에 데이터 추가
            } else if (option == JOptionPane.NO_OPTION) {
                saveToFile(filename, false); // 파일에 데이터 덮어쓰기
            }
            // 취소는 아무 동작 수행 안함
        } else {
            saveToFile(filename, false); // 파일에 추가할 필요 없음, 파일이 존재하지 않거나 동일한 데이터가 없음
        }
    }

    void saveToFile(String filename, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, append))) {
            if (append) writer.newLine(); // 데이터를 추가할 경우 새로운 줄로 이동함
            writer.write(getCurrentData());
            JOptionPane.showMessageDialog(null, "데이터가 성공적으로 저장되었습니다.");
        }
    }

    boolean fileContainsData(File file, String data) throws IOException {
        String currentDate = data.split(",")[0]; // 현재 입력된 데이터에서 날짜 부분만 추출
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String fileDate = line.split(",")[0]; // 파일 내의 데이터에서 날짜 부분만 추출
                if (fileDate.equals(currentDate)) {
                    return true; // 동일날짜O
                }
            }
        }
        return false; // 동일 날짜X
    }

    String getCurrentData() {
        return today + "," + t2.getText() + "," + t3.getText() + "," + t4.getText() + "," + t5.getText() + "," + c2.getText();
    }
    void showData() throws IOException {
        String filename = "C:\\Users\\msi\\Desktop\\wevars study\\workspace\\swing\\src\\koutsuhi\\202402" + userName + "_" + today + ".txt";
        File file = new File(filename);

        if (file.exists()) {
            StringBuilder data = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line).append("\n");
                }
            }
            JTextArea textArea = new JTextArea(10, 20);
            textArea.setText(data.toString());
            textArea.setEditable(false);

            JScrollPane scrollPane = new JScrollPane(textArea);
            JOptionPane.showMessageDialog(null, scrollPane, "데이터 조회", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "저장된 데이터가 없습니다.", "데이터 조회", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}