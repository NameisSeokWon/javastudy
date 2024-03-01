package swing;

import java.awt.*;
import javax.swing.*;

public class Calculator extends JFrame {
		Calculator(){
			setTitle("계산기");
			setLayout(new BorderLayout(10, 10));
			showNorth();
			showCalculation();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(300,350);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		void showNorth() {
			JPanel p = new JPanel();
			JPanel panel = new JPanel();
			JTextField t = new JTextField(20);
			p.add(t);

			panel.add(p);
			add(panel, BorderLayout.NORTH);
		}
		void showCalculation() {

			JPanel panel = new JPanel();
			JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
			JPanel p2 = new JPanel(new GridLayout(4,4,10,10));
			JButton b1 = new JButton("on");
			JButton b2 = new JButton("off");
			JButton b3 = new JButton("7");
			JButton b4 = new JButton("8");
			JButton b5 = new JButton("9");
			JButton b6 = new JButton("+");
			JButton b7 = new JButton("4");
			JButton b8 = new JButton("5");
			JButton b9 = new JButton("6");
			JButton b10 = new JButton("-");
			JButton b11 = new JButton("1");
		
			JButton b12 = new JButton("2");
			JButton b13 = new JButton("3");
			JButton b14 = new JButton("*");
			JButton b15 = new JButton("0");
			JButton b16 = new JButton("·");
			JButton b17 = new JButton("=");
			JButton b18 = new JButton("÷");

			p1.add(b1);	p1.add(b2);
			p2.add(b3); p2.add(b4); p2.add(b5); p2.add(b6);
			p2.add(b7); p2.add(b8); p2.add(b9); p2.add(b10);
			p2.add(b11); p2.add(b12); p2.add(b13); p2.add(b14);
			p2.add(b15); p2.add(b16); p2.add(b17); p2.add(b18);

			panel.add(p1);
			panel.add(p2);

			add(panel,BorderLayout.CENTER);
		}

		public static void main(String[] args) {
			new Calculator();
		}
}



