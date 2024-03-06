package koutsuhi.modify;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ModifyFrame extends JFrame {
	
	public ModifyFrame() {
		setTitle("수정화면");		
		setLayout(new BorderLayout(10,10));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300,300);
		center();
		setLocationRelativeTo(null);
		setVisible(true);
		JPanel panel1 = new JPanel();
		JLabel label = new JLabel("수정해주세요");
		panel1.add(label);
		add(panel1,BorderLayout.NORTH);
	}
	void center() {
		JPanel panel2 = new JPanel();
		JPanel p1 = new JPanel(new GridLayout(6,2));
		
		JLabel l1 = new JLabel("DATE");
		JLabel l2 = new JLabel("D_STATION");
		JLabel l3 = new JLabel("A_STATION");
		JLabel l4 = new JLabel("PARE");
		JLabel l5 = new JLabel("NUMBEROFTIME");
		JLabel l6 = new JLabel("FINALLPARE");
		JTextField t1 = new JTextField(10);
		JTextField t2 = new JTextField(10);
		JTextField t3 = new JTextField(10);
		JTextField t4 = new JTextField(10);
		JTextField t5 = new JTextField(10);
		
		JTextArea a = new JTextArea("",1,10);
		
		p1.add(l1); p1.add(t1);
		p1.add(l2); p1.add(t2);
		p1.add(l3); p1.add(t3);
		p1.add(l4); p1.add(t4);
		p1.add(l5); p1.add(t5);
		p1.add(l6); p1.add(a);
		
		panel2.add(p1);
		add(panel2,BorderLayout.CENTER);		
		
	}
	void south() {
		JPanel panel3 = new JPanel();
		/*JPanel p3 = new JPanel(FlowLayout(new FlowLayout));*/
		JButton b1 = new JButton();
		JButton b2 = new JButton();
		
	}


    /*public static void main(String[] args) {
        new ModifyFrame();
    }*/
}

	



	
	
	

