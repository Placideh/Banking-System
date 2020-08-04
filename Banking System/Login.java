import java.util.Scanner;
import java.io.Console;
import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
public class Login{
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		Console console=System.console();
		System.out.println();
		System.out.println("				 ________________________________");
		System.out.println("				|				 |");

	System.out.println("				|Hello And Welcome to EAD BANK!:)|");
		System.out.println("				|________________________________|");
		System.out.println();

		System.out.println("	Login | Sign Up");
		System.out.println("	================");
		System.out.println("	#1.Login |#2.SignUp");
		int option=scan.nextInt();
		String link="jdbc:mysql://localhost:3306/Bank";
		String user="root";
		String dbpassWord="toor";
		// String z= option==1||option==2? "Please Specify Your Email and PassWord" : "Please Specify Your whole Identity";
		// System.out.println(z);
		if (option==1) {
			
			System.out.print("Account #xxx-xxx-xxx-xx:");
			String input=scan.next().toLowerCase();
			String  pass=new String(console.readPassword("PassWord:"));
			System.out.println("Account:"+input+" | PassWord:"+pass);
			
			String QUERY="SELECT * FROM account WHERE account_number='" + input + "' AND password='" + pass + "'";
			try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
				PreparedStatement statement=conn.prepareStatement(QUERY);
				ResultSet result=statement.executeQuery();
				int count=0;
				while(result.next()){
					String accountNumber=result.getString(1);//accountnumber
					String firstName=result.getString(2);//firstname
					String lastName=result.getString(3);//lastname
					String email=result.getString(4);//email
					String password=result.getString(5);//password
					int balance=result.getInt(6);
					String output="User#%d:%s-%s-%s-%s-%s-%d";
					System.out.println();
					System.out.println(String.format(output,++count,accountNumber,firstName,lastName,email,password,balance));
					// System.out.println("account is already exist Please Create other account");
					System.out.println();

					
					

				}
			}catch(SQLException e){
				System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				
			}
			int balanceAccount=0;
			
			String updateQuery="UPDATE account SET balance=? WHERE account_number='"+input+"'";
			String getBalance="SELECT balance FROM account WHERE account_number='"+input+"'";


			try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
				PreparedStatement statement=conn.prepareStatement(updateQuery);
				PreparedStatement stat=conn.prepareStatement(getBalance);
				ResultSet resultBalance=stat.executeQuery();
				int all=0;
				while(resultBalance.next()){
					all=resultBalance.getInt(1);
				}
				int bal=Person.bankOperations();
				balanceAccount=bal+all;
				statement.setInt(1,balanceAccount);
				int result=statement.executeUpdate();
				if (result>0) {
					System.out.println("Update Made Successully");
					
				}
			}catch(SQLException e){
				System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		else if (option==2) {
			String QUERY="INSERT INTO account(account_number,first_name,last_name,email,password,balance) VALUES (?,?,?,?,?,?)";
			System.out.println("To create Account Please Refer to the Syntax");
			System.out.print("Account #xxx-xxx-xxx-xx:");
			String account=scan.next();
			/*THI CODES DOWN IT FIRST CHECK IF THE ACCOUNT IS NOT ALREADY IN THE SYSTEM IF YES IT WILL TERMINATE 
			OR CONTINUE FURTHER PROCESS IF IT IS FALSE*/
			String checkquery="SELECT account_number FROM account WHERE account_number='"+account+"'";
			try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
							PreparedStatement statement=conn.prepareStatement(checkquery);
							ResultSet result=statement.executeQuery();
							int count=0;
							while(result.next()){
								String accountNumber=result.getString(1);//accountnumber
								String output="User#%d:%s";
								// System.out.println(String.format(output,++count,accountNumber));
								System.out.println("Account Exist Please Create Another One");
								return;
							}
			}catch (SQLException e) {
				System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
				
			}catch (Exception e) {
				e.printStackTrace();
				
			}
			/* THE INSERT WILL CONTINUE BY HERE IF EVERYTHING IS OKAY BUT 
			THIS CODE IS NOT EFFICIECT IT IS NOT CLEAN BUT I WILL COME TO THAT LATER*/
			System.out.print("First Name:");
			String firstName=scan.next().toUpperCase();
			System.out.print("Last Name:");
			String lName=scan.next();
			String lastName=lName.substring(0,1).toUpperCase()+lName.substring(1);
			System.out.print("Email:");
			String email=scan.next();
			String  passWord=new String(console.readPassword("PassWord:"));
			int balance=0;
			System.out.println("Account:"+account+" |FistName:"+firstName+"|LastName:"+lastName+" |Email:"+email+" |PassWord:"+passWord+"|Balance:"+balance);

			// public void insertItem(String account,String firstName,String lastName,String email,String passWord){

				try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
					PreparedStatement statement=conn.prepareStatement(QUERY);
					statement.setString(1,account);
					statement.setString(2,firstName);
					statement.setString(3,lastName);
					statement.setString(4,email);
					statement.setString(5,passWord);
					statement.setInt(6,balance);
					int result=statement.executeUpdate();
					if (result>0)
						System.out.println("Account Created ");
					}catch (SQLException e) 
					{
					System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
					
					}catch (Exception e) {
					e.printStackTrace();
					
				}
			
		}
	}
}	