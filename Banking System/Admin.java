import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.Console;
public class Admin{ 
	public Admin(){}
	private static String link="jdbc:mysql://localhost:3306/Bank";
	private static String admin="jdbc:mysql://localhost:3306/Admin";
	private static String user="root";
	private static String dbpassWord="toor";
	static Scanner scan=new Scanner(System.in);
	static Console console=System.console();

	public static void admin() {
		System.out.print("Admin Name:");
		String name=scan.next();
		String  pass=new String(console.readPassword("PassWord:"));
		String QUERY="SELECT * FROM admin WHERE name='" + name + "' AND password='" + pass + "'";
		try(Connection conn=DriverManager.getConnection(admin,user,dbpassWord)){
			PreparedStatement adminStatement=conn.prepareStatement(QUERY);
			ResultSet adminResult=adminStatement.executeQuery();
			while(adminResult.next()){
					String adminName=adminResult.getString(1);
					String password=adminResult.getString(2);
					String output="#adminName:%s,PassWord:%s";
					System.out.println(String.format(output,adminName,password));

				System.out.println("What Do You Want to do?");
				System.out.println("#1.insertUser|#2.updateUser|#3.deleteUser|#4.getAllUsers");
				int option=scan.nextInt();
				switch (option) {
					case 1:{

						insertUser();
						break;
					}
					case 2:{
						updateUser();
						break;
					}
					case 3:{
						deleteUser();
						break;
					}
					case 4:{
						selectAll();
						break;	
					}
					default:
					System.out.println("Invalid Operation!!:)");
				}
			}

		}catch (SQLException e) {
			System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static  void insertUser(){
		String query="INSERT INTO account(account_number,first_name,last_name,email,password,balance)VALUES(?,?,?,?,?,?)";

		System.out.print("Account #xxx-xxx-xxx-xx:");
		String input=scan.next().toLowerCase();
		String  pass=new String(console.readPassword("PassWord:"));
		System.out.println("Account:"+input+" | PassWord:"+pass);
		/* Inserting the Inputs*/
		System.out.print("First Name:");
		String firstName=scan.next().toUpperCase();
		System.out.print("Last Name:");
		String lName=scan.next();
		String lastName=lName.substring(0,1).toUpperCase()+lName.substring(1);
		System.out.print("Email:");
		String email=scan.next();
		if(!email.contains("@")||!email.contains(".")){System.out.println("Please write a valid email!");return;}
		String  passWord=new String(console.readPassword("PassWord:"));
		int balance=0;
		System.out.println("Account:"+input+" |FistName:"+firstName+"|LastName:"+lastName+" |Email:"+email+" |PassWord:"+passWord+"|Balance:"+balance);

		/* INSETING ITEM INTO THE DATABASE*/
		try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
			PreparedStatement statement=conn.prepareStatement(query);
				statement.setString(1,input);
				statement.setString(2,firstName);
				statement.setString(3,lastName);
				statement.setString(4,email);
				statement.setString(5,passWord);
				statement.setInt(6,balance);
				int result=statement.executeUpdate();
				if (result>0)
					System.out.println("Account Created ");

		}catch (SQLException e) {
			System.err.format("MYSQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateUser(){
		
		try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
			System.out.println("Which Column Do you want to Update?");
			System.out.println("$1.account_number,$2.first_name,$3.last_name,$4.email,$5.passWord,$6.balance");
			int option=scan.nextInt();
			switch(option){
					case 1:{ 
							System.out.print("Your Old Account #xxx-xxx-xxx-xx:");
							String input=scan.next().toLowerCase();
							System.out.print("Your New Account #xxx-xxx-xxx-xx:");
							String inputUpdate=scan.next().toLowerCase();
							String query1="UPDATE account SET account_number=? WHERE account_number=?";
							PreparedStatement statement=conn.prepareStatement(query1);
							statement.setString(1,inputUpdate);
							statement.setString(2,input);
							int result=statement.executeUpdate();
							if (result>0) 
								System.out.println("Account Updated !!");
			
							break;
					}
					case 2:{ 
							System.out.print("Account #xxx-xxx-xxx-xx:");
							String input=scan.next().toLowerCase();
							System.out.print("First Name:");
							String firstName=scan.next().toUpperCase();
							String query2="UPDATE account SET first_name=? WHERE account_number=?";
							PreparedStatement statement=conn.prepareStatement(query2);
							statement.setString(1,firstName);
							statement.setString(2,input);
							int result=statement.executeUpdate();
							if (result>0) 
								System.out.println("Account Updated !!");
			
							break;
					}
					case 3:{ 
							System.out.print("Account #xxx-xxx-xxx-xx:");
							String input=scan.next().toLowerCase();
							System.out.print("Last Name:");
							String lName=scan.next();
							String lastName=lName.substring(0,1).toUpperCase()+lName.substring(1);
							String query3="UPDATE account SET last_name=? WHERE account_number=?";
							PreparedStatement statement=conn.prepareStatement(query3);
							statement.setString(1,lastName);
							statement.setString(2,input);
							int result=statement.executeUpdate();
							if (result>0) 
								System.out.println("Account Updated !!");
			
							break;
					}
					case 4:{ 
							System.out.print("Account #xxx-xxx-xxx-xx:");
							String input=scan.next().toLowerCase();
							System.out.print("Email:");
							String email=scan.next();
							String query4="UPDATE account SET email=? WHERE account_number=?";
							PreparedStatement statement=conn.prepareStatement(query4);
							statement.setString(1,email);
							statement.setString(2,input);
							int result=statement.executeUpdate();
							if (result>0) 
								System.out.println("Account Updated !!");
			
							break;
					}
					case 5:{ 
							System.out.print("Account #xxx-xxx-xxx-xx:");
							String input=scan.next().toLowerCase();
							String  passWord=new String(console.readPassword("PassWord:"));
							String query5="UPDATE account SET password=? WHERE account_number=?";
							PreparedStatement statement=conn.prepareStatement(query5);
							statement.setString(1,passWord);
							statement.setString(2,input);
							int result=statement.executeUpdate();
							if (result>0) 
								System.out.println("Account Updated !!");
			
							break;
					}
					case 6:{ 
							System.out.print("Account #xxx-xxx-xxx-xx:");
							String input=scan.next().toLowerCase();
							System.out.print("Balance:");
							int balance=scan.nextInt();
							String query6="UPDATE account SET balance=? WHERE account_number=?";
							PreparedStatement statement=conn.prepareStatement(query6);
							statement.setInt(1,balance);
							statement.setString(2,input);
							int result=statement.executeUpdate();
							if (result>0) 
								System.out.println("Account Updated !!");
			
							break;
					}
					default:
						System.out.println("Invalid Operation");
						break;
			}

		}catch (SQLException e) {
			System.err.format("MYSQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			
		}catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Please Enter Valid Inputs!!");
		}
	}
	public static void deleteUser(){
		System.out.print("Account #xxx-xxx-xxx-xx:");
		String input=scan.next().toLowerCase();
		String dropQuery="DELETE FROM account WHERE account_number='"+input+"'";
		try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
			PreparedStatement statement=conn.prepareStatement(dropQuery);
			int result=statement.executeUpdate();
			if (result>0) {
				System.out.println("Account Deleted !!");
				
			}
		}catch (SQLException e) {
			System.err.format("MYSQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			
		}catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Please Enter Valid Inputs!!");
		}
	}
	public static void selectAll(){
		String selectQuery="SELECT * FROM account";
		try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
			PreparedStatement statement=conn.prepareStatement(selectQuery);
			ResultSet result=statement.executeQuery();
			int count=0;
			while(result.next()){
				String accountNumber=result.getString(1);//accountnumber
				String firstName=result.getString(2);//firstname
				String lastName=result.getString(3);//lastname
				String email=result.getString(4);//email
				String password=result.getString(5);//password
				int balance=result.getInt(6);//balance
				String output="User#%d:%s-%s-%s-%s-PassWord:%s-Balance:%d";
				System.out.println();
				System.out.println(String.format(output,++count,accountNumber,firstName,lastName,email,password,balance));
				System.out.println();
			}

		}catch (SQLException e) {
			System.err.format("MYSQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
	}
}