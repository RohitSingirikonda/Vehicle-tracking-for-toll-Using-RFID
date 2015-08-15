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
import javax.swing.JComboBox;
import javax.swing.JLabel;
public class ViewPassVehicle extends JFrame
{
	JLabel l1;
	JComboBox c1;
	JButton b1;
	JPanel p1,p2;
	Font f1;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane jsp;
	
public ViewPassVehicle(){
	super("View Pass Vehicles");
	
	p1 = new JPanel();
	p1.setBackground(Color.white);
	f1 = new Font("Courier New",Font.PLAIN,14);
	
	l1 = new JLabel("Vehicle No");
	l1.setFont(f1);
	p1.add(l1);

	c1 = new JComboBox();
	c1.setFont(f1);
	p1.add(c1);

	b1 = new JButton("View");
	b1.setFont(f1);
	p1.add(b1);
	b1.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
			try{
				String vno = c1.getSelectedItem().toString().trim();
				String str[] = DBCon.getPass(vno);
				clear();
				for(String s1 : str){
					String row[] = s1.split("#");
					dtm.addRow(row);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	});
	

	p2 = new JPanel();
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
	dtm.addColumn("Vehicle No");
	dtm.addColumn("Owner Name");
	dtm.addColumn("Pass Time");
	dtm.addColumn("Amount");

	jsp = new JScrollPane(table);
	jsp.getViewport().setBackground(Color.white);
	p2.add(jsp,BorderLayout.CENTER);
	getContentPane().add(p1,BorderLayout.NORTH);
	getContentPane().add(p2,BorderLayout.CENTER);
}
public void clear(){
	for(int i=dtm.getRowCount()-1;i>=0;i--){
		dtm.removeRow(i);
	}
}
public void readVehicle(){
	try{
		String str[] = DBCon.getVehicleNo();
		c1.removeAllItems();
		for(String s1 : str){
			c1.addItem(s1.trim());
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}