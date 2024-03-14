package koutsuhi.list;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import koutsuhi.data.DataUtil;
import koutsuhi.login.LoginFrame;
import koutsuhi.modify.ModifyFrame;

public class ListFrame extends JFrame {
    LocalDate currentDate;
    JLabel l1;
    DefaultTableModel model;
    JTable table;
    JTextField t2;
    JButton b2;
    int selectedRow;
    

    public ListFrame() {
        setTitle("교통비정산 시스템 - 리스트");
        setSize(600,300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        currentDate = LocalDate.now();
        initializeUI();
        setVisible(true);
    }
    public void initializeUI() {
    	JPanel p2 = new JPanel(new FlowLayout());
    	JButton b1 = new JButton("뒤로가기");
    	b2 = new JButton("수정하기");
    	JLabel l2 = new JLabel("합계");
    	t2 = new JTextField("0엔");
    	t2.setEditable(false);
    	p2.add(b1);
    	p2.add(b2);
    	p2.add(l2);
    	p2.add(t2);
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
        model = new DefaultTableModel(columnNames, 0) {
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        table = new JTable(model);
        
        table.setDragEnabled(false);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedRow = table.getSelectedRow();
				b2.setEnabled(selectedRow != -1);
				
			}
		});
        
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
       


        add(p2, BorderLayout.SOUTH);
        b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                selectedRow = table.getSelectedRow();
                String date = model.getValueAt(selectedRow, 0).toString();
                String dStation = model.getValueAt(selectedRow, 1).toString();
                String aStation = model.getValueAt(selectedRow, 2).toString();
                String pare = model.getValueAt(selectedRow, 3).toString();
                String numberOfTime = model.getValueAt(selectedRow, 4).toString();
                

                // 클릭한 행의 정보를 ModifyFrame에 String 형태로 반환
                new ModifyFrame(date, dStation, aStation, pare, numberOfTime, currentDate);
            }
        });
        tabelData();   
        
    }

    public void date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        l1.setText(currentDate.format(formatter));
        tabelData();
    }

    public void tabelData() {
        DataUtil du = new DataUtil();
        String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        String[][] userInfoArr = du.loadUserTransInfo(formattedDate, LoginFrame.userId);

        model.setRowCount(0); // 기존 데이터 지움
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