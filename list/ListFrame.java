package koutsuhi.list;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import koutsuhi.data.DataUtil;
import koutsuhi.login.LoginFrame;
import koutsuhi.loginmain.LoginMainFrame;
import koutsuhi.modify.ModifyFrame;

public class ListFrame extends JFrame {
    LocalDate currentDate;
    JLabel l1;
    DefaultTableModel model;
    JTable table;
    JTextField t2;

    public ListFrame() {
        setTitle("교통비정산 시스템 - 리스트");
        setSize(600,300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        currentDate = LocalDate.now();
        initializeUI();
        setVisible(true);
    }
    void initializeUI() {
        JButton a1 = new JButton("<");
        JButton a2 = new JButton(">");
        JPanel p1 = new JPanel();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = currentDate.format(formatter);
        l1 = new JLabel(formattedDate);

        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.minusMonths(1);
                date();
            }
        });

        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentDate = currentDate.plusMonths(1);
                date();
            }
        });

        p1.add(a1);
        p1.add(l1);
        p1.add(a2);
        add(p1, BorderLayout.NORTH);

        String[] columnNames = {"DATE", "D_STATION", "A_STATION", "PARE","NUMBEROFTIME","FINALLPARE"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        

        JPanel p2 = new JPanel(new FlowLayout());
        JButton b1 = new JButton("뒤로가기");
        JButton b2 = new JButton("수정하기");
        JLabel l2 = new JLabel("합계");
        t2 = new JTextField("0엔");
        t2.setEditable(false);
        p2.add(b1);
        p2.add(b2);
        p2.add(l2);
        p2.add(t2);

        add(p2, BorderLayout.SOUTH);
        b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginMainFrame(LoginFrame.userName);
				
			}
		});
        b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ModifyFrame();
				
			}
		});

        TableData();
    }

    void date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        l1.setText(currentDate.format(formatter));
        TableData();
    }

    void TableData() {
        DataUtil du = new DataUtil();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String[][] userInfoArr = du.loadUserTransInfo(formattedDate, LoginFrame.userId);

        model.setRowCount(0); // Clear existing data
        int sum = 0;
        for(String[] row : userInfoArr) {
            model.addRow(row);
            sum += Integer.parseInt(row[5]);
        }
        t2.setText(sum + "엔");
    }

/*    public static void main(String[] args) {
        new ListFrame();
    }*/
}