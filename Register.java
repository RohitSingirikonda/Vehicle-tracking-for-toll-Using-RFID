package vehicle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
public class Register extends JFrame{
	CustomPanel p1;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField tf1,tf2,tf3,tf4,tf5,tf6,tf7,tf8;
	JButton b1;
	Font f1;
	JTextArea area;
public Register(){
	super("Vehicle Registration Screen");
	p1 = new CustomPanel();
	p1.setTitle("    Vehicle Registration Screen");
	p1.setLayout(null);
	
	JPanel main = new JPanel();
	main.setLayout(new BorderLayout());

	f1 = new Font("Microsoft Sanserif",Font.BOLD,11);
	
	l1 = new JLabel("Owner Name");
	l1.setForeground(Color.white);
	l1.setFont(f1);
	l1.setBounds(50,50,100,30);
	p1.add(l1);
	tf1 = new JTextField();
	tf1.setFont(f1);
	tf1.setBounds(150,50,120,30);
	p1.add(tf1);

	l2 = new JLabel("Contact No");
	l2.setForeground(Color.white);
	l2.setFont(f1);
	l2.setBounds(50,100,100,30);
	p1.add(l2);
	tf2 = new JTextField();
	tf2.setFont(f1);
	tf2.setBounds(150,100,120,30);
	p1.add(tf2);

	l3 = new JLabel("Address");
	l3.setForeground(Color.white);
	l3.setFont(f1);
	l3.setBounds(50,150,100,30);
	p1.add(l3);
	tf3 = new JTextField();
	tf3.setFont(f1);
	tf3.setBounds(150,150,220,30);
	p1.add(tf3);

	l4 = new JLabel("License No");
	l4.setForeground(Color.white);
	l4.setFont(f1);
	l4.setBounds(50,200,100,30);
	p1.add(l4);
	tf4 = new JTextField();
	tf4.setFont(f1);
	tf4.setBounds(150,200,120,30);
	p1.add(tf4);

	l5 = new JLabel("RFID Tag No");
	l5.setForeground(Color.white);
	l5.setFont(f1);
	l5.setBounds(50,250,100,30);
	p1.add(l5);
	tf5 = new JTextField();
	tf5.setFont(f1);
	tf5.setBounds(150,250,120,30);
	p1.add(tf5);

	l6 = new JLabel("Vehicle Details");
	l6.setForeground(Color.white);
	l6.setFont(f1);
	l6.setBounds(50,300,100,30);
	p1.add(l6);
	tf6 = new JTextField();
	tf6.setFont(f1);
	tf6.setBounds(150,300,220,30);
	p1.add(tf6);
	
	l7 = new JLabel("Prepaid Amount");
	l7.setForeground(Color.white);
	l7.setFont(f1);
	l7.setBounds(50,350,100,30);
	p1.add(l7);
	tf7 = new JTextField();
	tf7.setFont(f1);
	tf7.setBounds(150,350,120,30);
	p1.add(tf7);

	l8 = new JLabel("Toll Price");
	l8.setForeground(Color.white);
	l8.setFont(f1);
	l8.setBounds(50,400,100,30);
	p1.add(l8);
	tf8 = new JTextField();
	tf8.setFont(f1);
	tf8.setBounds(150,400,120,30);
	p1.add(tf8);

	b1 = new JButton("Register");
	b1.setFont(f1);
	b1.setBounds(70,450,100,30);
	p1.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			register();
		}
	});

	getContentPane().add(p1,BorderLayout.CENTER);
}
public void clear(){
	tf1.setText("");
	tf2.setText("");
	tf3.setText("");
	tf4.setText("");
	tf5.setText("");
	tf6.setText("");
	tf7.setText("");
	tf8.setText("");
}
public void register(){
	String owner = tf1.getText();
	String contact = tf2.getText();
	String address = tf3.getText();
	String license = tf4.getText();
	String vehicle_no = tf5.getText();
	String vehicle_details = tf6.getText();
	String prepaid = tf7.getText();
	String price = tf8.getText();
	if(owner == null || owner.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Owner rname must be enter");
		tf1.requestFocus();
		return;
	}
	if(contact == null || contact.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Contact no must be enter");
		tf2.requestFocus();
		return;
	}
	if(address == null || address.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Address must be enter");
		tf3.requestFocus();
		return;
	}
	if(license == null || license.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"License no must be enter");
		tf4.requestFocus();
		return;
	}
	if(vehicle_no == null || vehicle_no.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Vehicle no must be enter");
		tf5.requestFocus();
		return;
	}
	if(vehicle_details == null || vehicle_details.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Vehicle details must be enter");
		tf6.requestFocus();
		return;
	}
	if(prepaid == null || prepaid.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Prepaid amount must be enter");
		tf7.requestFocus();
		return;
	}
	if(price == null || price.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Price per toll booth visit must be enter");
		tf8.requestFocus();
		return;
	}
	double prepaid_amt = 0;
	double price_per_visit = 0;
	try{
		prepaid_amt = Double.parseDouble(prepaid.trim());
	}catch(NumberFormatException nfe){
		JOptionPane.showMessageDialog(this,"Prepaid amount must be numeric only");
		tf7.requestFocus();
		return;
	}
	try{
		price_per_visit = Double.parseDouble(price.trim());
	}catch(NumberFormatException nfe){
		JOptionPane.showMessageDialog(this,"Price per toll booth visit must be numeric only");
		tf8.requestFocus();
		return;
	}
	try{
		String input[] = {owner,contact,address,license,vehicle_no,vehicle_details};
		String msg = DBCon.register(input,prepaid_amt,price_per_visit);
		if(msg.equals("success")){
			JOptionPane.showMessageDialog(this,"Vehicle details added");
			clear();
		}
		else
			JOptionPane.showMessageDialog(this,"Error in adding vehicle details");
	}catch(Exception e){
		e.printStackTrace();
	}
}
}