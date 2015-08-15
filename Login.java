package vehicle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
public class Login extends JFrame
{
	CustomPanel p1;
	JLabel l1,l2;
	JTextField tf1,tf2;
	JButton b1,b2;
	Font f1;
public Login(){
	super("Login Screen");
	p1 = new CustomPanel();
	p1.setTitle("    Login Screen");
	p1.setLayout(null);
	
	JPanel main = new JPanel();
	main.setLayout(new BorderLayout());

	f1 = new Font("Courier New",Font.BOLD,11);
	JPanel pan1 = new JPanel(); 
	l1 = new JLabel("Username");
	l1.setForeground(Color.white);
	l1.setFont(f1);
	pan1.add(l1);
	tf1 = new JTextField(15);
	tf1.setFont(f1);
	pan1.add(tf1);
	
	JPanel pan2 = new JPanel(); 
	l2 = new JLabel("Password");
	l2.setForeground(Color.white);
	l2.setFont(f1);
	pan2.add(l2);
	tf2 = new JPasswordField(15);
	tf2.setFont(f1);
	pan2.add(tf2);

	JPanel pan3 = new JPanel(); 
	b1 = new JButton("Login");
	b1.setFont(f1);
	pan3.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			login();
		}
	});
	
	b2 = new JButton("Reset");
	b2.setFont(f1);
	pan3.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			tf1.setText("");
			tf2.setText("");
		}
	});

	main.setBackground(new Color(128, 128, 128));
	pan1.setBackground(new Color(128, 128, 128));
	pan2.setBackground(new Color(128, 128, 128));
	pan3.setBackground(new Color(128, 128, 128));
	main.add(pan1,BorderLayout.NORTH);
	main.add(pan2,BorderLayout.CENTER);
	main.add(pan3,BorderLayout.SOUTH);
	main.setBounds(50,80,300,100);
	p1.add(main);
	getContentPane().add(p1,BorderLayout.CENTER);
}
public static void main(String a[])throws Exception{
	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	Login login = new Login();
	login.setVisible(true);
	login.setSize(400,300);
	login.setLocationRelativeTo(null);
	login.setResizable(false);
}
public void clear(){
	tf1.setText("");
	tf2.setText("");
}
public void login(){
	String user = tf1.getText();
	String pass = tf2.getText();
	
	if(user == null || user.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Username must be enter");
		tf1.requestFocus();
		return;
	}
	if(pass == null || pass.trim().length() <= 0){
		JOptionPane.showMessageDialog(this,"Password must be enter");
		tf2.requestFocus();
		return;
	}
	try{
		if(user.equals("admin") && pass.equals("admin")){
			setVisible(false);
			UserScreen us = new UserScreen(user,this);
			us.setVisible(true);
			us.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}else{
			JOptionPane.showMessageDialog(this,"Error in login");
		}
		
	
		
	}catch(Exception e){
		e.printStackTrace();
	}
}
}