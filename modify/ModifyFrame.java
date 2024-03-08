package koutsuhi.modify;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import koutsuhi.login.LoginFrame;

public class ModifyFrame extends JFrame {
    JTextField t1;
    JTextField t2;
    JTextField t3;
    JTextField t4;
    JTextField t5;
    JTextField t6;
 
    LocalDate currentDate;

    public ModifyFrame(String date, String dStation, String aStation, String pare, String numberOfTime, String finalPare, LocalDate currentDate) {
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
        t6 = new JTextField(finalPare, 10);
     

        p1.add(l1); p1.add(t1);
        p1.add(l2); p1.add(t2);
        p1.add(l3); p1.add(t3);
        p1.add(l4); p1.add(t4);
        p1.add(l5); p1.add(t5);
        p1.add(l6); p1.add(t6);

        panel2.add(p1);
        add(panel2, BorderLayout.CENTER);
    }
    void south () {
    	JPanel panel3 = new JPanel();
    	JButton b1 = new JButton("저장");
    	JButton b2 = new JButton("뒤로가기");
    	panel3.add(b1);
    	panel3.add(b2);
    	add(panel3, BorderLayout.SOUTH);
    	b1.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	        // 기존 파일 경로
    	        String folderPath = "src\\koutsuhi\\data\\" + currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM")) + "\\";
    	        String filename = folderPath + currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM")) + "_" + LoginFrame.userId + ".txt";

    	        try {
    	            // 기존 파일의 내용을 읽어옴
    	            List<String> lines = Files.readAllLines(Paths.get(filename));
    	            
    	            // 사용자가 수정한 데이터를 새로운 데이터로 대체 StringBuilder 클래스 사용. StringBuffer도 가능함
    	            StringBuilder newData = new StringBuilder(); 
    	            for (int i = 0; i < lines.size() - 1; i++) {
    	                newData.append(lines.get(i)).append("\n");
    	            }
    	            newData.append(t1.getText()).append(",").append(t2.getText()).append(",").append(t3.getText())
    	                    .append(",").append(t4.getText()).append(",").append(t5.getText()).append(",").append(t6.getText());
    	            
    	            // 대체된 내용을 기존 파일에 다시 씀
    	            Files.write(Paths.get(filename), newData.toString().getBytes());
    	            
    	            JOptionPane.showMessageDialog(null, "데이터가 성공적으로 저장되었습니다.");
    	        } catch (Exception ex) {
    	            ex.printStackTrace();
    	            JOptionPane.showMessageDialog(null, "데이터 저장 중 오류가 발생했습니다.");
    	        }
	       	        
    	    }
    	});

    	b2.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			dispose();
    		}
    	});    	
    }

}

