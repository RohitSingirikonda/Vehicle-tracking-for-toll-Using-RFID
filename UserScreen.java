package vehicle;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.sql.*;
public class UserScreen extends JFrame
{
	JButton b1,b2,b3,b4,b5,b6;
	JPanel p1,p2;
	Font f1;
	String user;
	Login login;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane jsp;
	LineBorder border;
	TitledBorder title;
	Serial serial;
public UserScreen(String usr,Login log){
	super("User Screen");
	user = usr;
	login = log;
	p1 = new JPanel();
	p1.setBackground(Color.white);
	f1 = new Font("Courier New",Font.PLAIN,14);
	
	p2 = new JPanel(); 
	b1 = new JButton("Register Vehicle");
	b1.setFont(f1);
	p1.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			Register r = new Register();
			r.setVisible(true);
			r.setSize(600,600);
			r.clear();
		}
	});
	b2 = new JButton("Start Reading");
	b2.setFont(f1);
	p1.add(b2);
	b2.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			serial = new Serial(dtm);
			serial.setPort("COM1");
			serial.setRate(9600);
			serial.initialize();
			JOptionPane.showMessageDialog(UserScreen.this,"Device Initialized");
		}
	});

	b3 = new JButton("Stop Reading");
	b3.setFont(f1);
	p1.add(b3);
	b3.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			if(serial != null){
				serial.close();
			}
			JOptionPane.showMessageDialog(UserScreen.this,"Device Closed");
		}
	});
             
	b4 = new JButton("View Pass Vehicle");
	b4.setFont(f1);
	p1.add(b4);
	b4.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			ViewPassVehicle vpv = new ViewPassVehicle();
			vpv.setVisible(true);
			vpv.setSize(600,400);
			vpv.readVehicle();
		}
	});
               b6 = new JButton("Check Balance");
	b6.setFont(f1);
	p1.add(b6);
	b6.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){


try{
Class.forName("com.mysql.jdbc.Driver");

Connection con = DriverManager.getConnection("jdbc:mysql://localhost/vehicle","root","root");
Statement stmt=con.createStatement();
  
ResultSet rs=stmt.executeQuery("select prepaid_amount from register");

while(rs.next())
{
System.out.println("Available Balance : "+rs.getDouble(1));
 
}
}
catch(Exception e)
{
System.err.println(e);
}
JOptionPane.showMessageDialog(UserScreen.this,"Please check available balance in Teminal");

		}
	});

	b5 = new JButton("Logout");
	b5.setFont(f1);
	p1.add(b5);
	b5.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			setVisible(false);
			login.clear();
			login.setVisible(true);
		}
	});

	border = new LineBorder(new Color(42,140,241),1,true);
	title = new TitledBorder (border,"Vehicle Transaction Status",TitledBorder.CENTER,TitledBorder.DEFAULT_POSITION, new Font("Courier New",Font.BOLD,14),Color.darkGray);
	p2 = new JPanel();
	p2.setBorder(title);
	p2.setBackground(Color.white);
	p2.setLayout(new BorderLayout());
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int r,int c){
			return false;
		}
	};
	table = new JTable(dtm);
	table.setRowHeight(30);
	table.setFont(f1);
	table.getTableHeader().setFont(new Font("Courier New",Font.BOLD,13));
	dtm.addColumn("Vehicle ID");
	dtm.addColumn("Owner Name");
	dtm.addColumn("Address");
	dtm.addColumn("Date & Time");
	dtm.addColumn("Transaction");
	jsp = new JScrollPane(table);
	jsp.getViewport().setBackground(Color.white);
	p2.add(jsp,BorderLayout.CENTER);
	getContentPane().add(p1,BorderLayout.NORTH);
	getContentPane().add(p2,BorderLayout.CENTER);
}

}