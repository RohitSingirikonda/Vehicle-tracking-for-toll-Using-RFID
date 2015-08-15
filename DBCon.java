package vehicle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
public class DBCon{
    private static Connection con;
	
public static Connection getCon()throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection("jdbc:mysql://localhost/vehicle","root","root");
     return con;
}
public static String register(String[] input,double prepaid,double price)throws Exception{
    String msg="fail";
    con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select vehicle_no from register where vehicle_no='"+input[4]+"'");
    if(rs.next()){
        msg = "Vehicle no already exist";
    }
    else{
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp time = new java.sql.Timestamp(date.getTime()); 
		PreparedStatement stat=con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?,?)");
		stat.setString(1,input[0]);
		stat.setString(2,input[1]);
		stat.setString(3,input[2]);
		stat.setString(4,input[3]);
		stat.setString(5,input[4]);
		stat.setString(6,input[5]);
		stat.setDouble(7,prepaid);
		stat.setDouble(8,price);
		stat.setTimestamp(9,time);
		int i=stat.executeUpdate();
		if(i > 0){
			msg = "success";
		}
    }
    return msg;
}
public static String[] getVehicleNo()throws Exception{
	StringBuilder sb = new StringBuilder();
	con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select vehicle_no from register");
	while(rs.next()){
		sb.append(rs.getString(1)+",");
	}
	if(sb.length() > 0)
		sb.deleteCharAt(sb.length()-1);
	return sb.toString().split(",");
	
}
public static String[] getPass(String vno)throws Exception{
	StringBuilder sb = new StringBuilder();
	con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select * from vehicle_pass where vehicle_no='"+vno+"'");
	while(rs.next()){
		String vehicle = rs.getString(1);
		String owner = rs.getString(2);
		String time = rs.getString(3);
		String amount = rs.getString(4);
		sb.append(vehicle+"#"+owner+"#"+time+"#"+amount+"\n");
	}
	if(sb.length() > 0)
		sb.deleteCharAt(sb.length()-1);
	return sb.toString().split("\n");
}
public static String getDetails(String vno)throws Exception{
	StringBuilder sb = new StringBuilder();
	String status = "";
	double remaining = 0;
	con = getCon();
    Statement stmt=con.createStatement();
    ResultSet rs=stmt.executeQuery("select owner_name,address,prepaid_amount,toll_price from register where vehicle_no='"+vno+"'");
	if(rs.next()){
		String owner = rs.getString(1);
		String address = rs.getString(2);
		double prepaid = rs.getDouble(3);
		double price = rs.getDouble(4);
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp time = new java.sql.Timestamp(date.getTime());
		if(prepaid > price){
			remaining = prepaid - price;
			status = "Transaction Successfull";
			PreparedStatement stat = con.prepareStatement("update register set prepaid_amount=? where vehicle_no=?");
			stat.setDouble(1,remaining);
			stat.setString(2,vno);
			stat.executeUpdate();
			stat = con.prepareStatement("insert into vehicle_pass values(?,?,?,?)");
			stat.setString(1,vno);
			stat.setString(2,owner);
			stat.setTimestamp(3,time);
			stat.setDouble(4,price);
			int i = stat.executeUpdate();
			if(i > 0)
				sb.append(vno+"#"+owner+"#"+address+"#"+time.toString()+"#"+status);
		}else{
			status = "Transaction Failed";
			sb.append(vno+"#"+owner+"#"+address+"#"+time.toString()+"#"+status);
		}
	}
	if(sb.length() == 0)
		sb.append("none");
	return sb.toString();
}
}
