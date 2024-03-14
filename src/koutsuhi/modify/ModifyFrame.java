package koutsuhi.modify;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import koutsuhi.login.LoginFrame;

public class ModifyFrame extends JFrame {
    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;
    JTextField t5;
    JTextField t6;
    JTextArea c;

    LocalDate currentDate;

    public ModifyFrame(String date, String dStation, String aStation, String pare, String numberOfTime, LocalDate currentDate) {
    	this.currentDate = currentDate;
    	setTitle("수정화면");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setVisible(true);
        south();
        

        JPanel panel1 = new JPanel();
        JLabel label = new JLabel("수정해주세요");
        panel1.add(label);
        add(panel1, BorderLayout.NORTH);


        JPanel panel2 = new JPanel();
        JPanel p1 = new JPanel(new GridLayout(6, 2));

        JLabel l1 = new JLabel("DATE");
        JLabel l2 = new JLabel("D_STATION");
        JLabel l3 = new JLabel("A_STATION");
        JLabel l4 = new JLabel("PARE");
        JLabel l5 = new JLabel("NUMBEROFTIME");
        JLabel l6 = new JLabel("FINALLPARE");
        t1 = new JTextField(date, 10);
        t2 = new JTextField(dStation, 10);
        t3 = new JTextField(aStation, 10);
        t4 = new JTextField(pare, 10);
        t5 = new JTextField(numberOfTime, 10);
        c = new JTextArea("",1,10);
        c.setEditable(false);
        


        p1.add(l1); p1.add(t1);
        p1.add(l2); p1.add(t2);
        p1.add(l3); p1.add(t3);
        p1.add(l4); p1.add(t4);
        p1.add(l5); p1.add(t5);
        p1.add(l6); p1.add(c);

        panel2.add(p1);
        add(panel2, BorderLayout.CENTER);
    }    
    public void south () {
    	JPanel panel3 = new JPanel();

    	add(panel3, BorderLayout.SOUTH);
    	JButton b1 = new JButton("수정");
    	panel3.add(b1);
    	b1.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // 입력 데이터 검증
    	        if(checkInputData()) {
    	            try {
    	                // pare와 numberOfTime을 정수로 변환
    	                int pare = Integer.parseInt(t4.getText());
    	                int numberOfTime = Integer.parseInt(t5.getText());
    	               
    	                int finalPare = pare * numberOfTime;

    	                // 결과를 JTextArea c에 String으로 변환하여 삽입
    	                c.setText(String.valueOf(finalPare));
    	                
    	                int modifyQ = JOptionPane.showConfirmDialog(null, "수정하시겠습니까 ?");
    	                if(modifyQ == JOptionPane.YES_OPTION) {
    	                    modifyOrDeleteData(false); // 여기서 실제 파일 수정 로직을 호출
    	                }
    	            } catch (NumberFormatException ex) {
    	                // 정수 변환 실패 시 사용자에게 알림
    	                JOptionPane.showMessageDialog(null, "금액과 횟수는 정수로 입력해야 합니다.");
    	            }
    	        }
    	    }
    	});
    	
    	JButton b2 = new JButton("삭제");
    	panel3.add(b2);
    	b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
                int deleteQ = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까 ?");
                if (deleteQ == JOptionPane.YES_OPTION) {
                    modifyOrDeleteData(true);
                }
            }
        });
    	JButton b4 = new JButton("뒤로가기");
    	panel3.add(b4);
    	b4.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			dispose();    			
    		}
    	});
    }
    public void modifyOrDeleteData(boolean delete) {
        String folderPath = "src\\koutsuhi\\data\\" + currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM")) + "\\";
        String filename = folderPath + currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM")) + "_" + LoginFrame.userId + ".csv";

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            List<String> updatedLines = new ArrayList<>();

            if (!delete) {
                // 수정하는 로직
                for (int i = 0; i < lines.size() - 1; i++) {
                    updatedLines.add(lines.get(i));
                }
                updatedLines.add(t1.getText() + "," + 
                		t2.getText() + "," + 
                		t3.getText() + "," + 
                		t4.getText() + "," + 
                		t5.getText() + "," + 
                		c.getText());
            } else {
                // 삭제하는 로직
                String toDelete = t1.getText() + "," + 
                		t2.getText() + "," + 
                		t3.getText() + "," + 
                		t4.getText() + "," + 
                		t5.getText() + "," + 
                		c.getText();
                for (String line : lines) {
                    if (!line.equals(toDelete)) {
                        updatedLines.add(line);
                    }
                }
            }

            // 업데이트 된 라인을 파일에 다시 덮어쓰기
            Files.write(Paths.get(filename), updatedLines);
            String message = delete ? "데이터가 성공적으로 삭제되었습니다." : "데이터가 성공적으로 저장되었습니다.";
            JOptionPane.showMessageDialog(null, message);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "데이터 처리 중 오류가 발생했습니다.");
        }
        dispose();
    }
    public boolean checkInputData() {
		// yyyy-MM-dd
		String regex = "^\\d{4}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])$";

        // 정규식 패턴을 컴파일
        Pattern pattern = Pattern.compile(regex);
 
        // 입력된 날짜를 체크한다.
        Matcher matcher = pattern.matcher(t1.getText());

        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "날짜는 yyyy-mm-dd 형식으로 입력해주세요.");
			return false;
        }
		String regex1 = "^[가-힣]+$";
		pattern = Pattern.compile(regex1);
		matcher = pattern.matcher(t2.getText());
        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "출발역은 한글로만 입력가능합니다.");
			return false;
        }
		matcher = pattern.matcher(t3.getText());
        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "도착역은 한글로만 입력가능합니다.");
			return false;
        }
		String regex2 = "^[1-9]\\d*$";
		pattern = Pattern.compile(regex2);
		matcher = pattern.matcher(t4.getText());

        // 일치 여부 반환
        if(!matcher.matches()) {
			JOptionPane.showMessageDialog(null, "금액은 정수로만 입력 가능합니다.");
			return false;
        }
        String regex3 = "^[1-9]\\d*$";       
        pattern = Pattern.compile(regex3);
        matcher = pattern.matcher(t5.getText());
        // 일치 여부 반환
        if(!matcher.matches()) {
        	JOptionPane.showMessageDialog(null, "횟수는 정수로만 입력 가능합니다.");
        	return false;
        }
        return true;

	}
}