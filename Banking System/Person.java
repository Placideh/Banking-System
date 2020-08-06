import java.util.Scanner;
import java.io.Console;
import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.InputMismatchException;
public class Person{
	

	public Person(){}
	public static void bankOperations(String input){
			
			String link="jdbc:mysql://localhost:3306/Bank";
			String user="root";
			String dbpassWord="toor";
			String getBalance="SELECT balance FROM account WHERE account_number='"+input+"'";
			String updateQuery="UPDATE account SET balance=? WHERE account_number='"+input+"'";

			int anotherTransaction=1;
			 int balance=0;

				Scanner scan=new Scanner(System.in);
			while (anotherTransaction==1) {
						System.out.println("What do you Want to do?");
						System.out.println("#1.CheckBalance|#2.Deposit|#3.Withdraw|#4.Transfer");
						int option=scan.nextInt();
						
						switch (option) {
							case 1:{ 
							try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
									

								PreparedStatement stat=conn.prepareStatement(getBalance);
								ResultSet resultBalance=stat.executeQuery();
								//this will take the current amount that was in the database for a specific person
								// int currentBalance=0;
								while(resultBalance.next()){
									int currentBalance=resultBalance.getInt(1);
									System.out.println(String.format("Your Balance:%d",currentBalance));
								}
							}catch (SQLException e) {
									System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
									
							}catch (Exception e) {
								e.printStackTrace();
							}
								break;
							}
							case 2:{
								System.out.println("How much do you want to deposit?");
								try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
									PreparedStatement statement=conn.prepareStatement(getBalance);
									PreparedStatement updateStatement=conn.prepareStatement(updateQuery);
									int deposit=scan.nextInt();
									if (deposit>0) {
										
												ResultSet resultBalance=statement.executeQuery();				
												int currentBalance=0;
												while(resultBalance.next()){
													currentBalance=resultBalance.getInt(1);
												}
												
												int balanceAccount=deposit+currentBalance;
												updateStatement.setInt(1,balanceAccount);
												int result2=updateStatement.executeUpdate();
												if (result2>0) {
													System.out.println("Update Made Successully");
													
												}

									
									System.out.println("You have deposited:"+deposit+" Successfully");
									}else{
										System.out.println("invalid Deposit Amount Can't be Negative:)");
									}
									
								}catch (SQLException e) {
									System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
									
								}catch (Exception e) {
									e.printStackTrace();
								}
									
								
								break;
							}
							case 3:{
								System.out.println("How much do you want to Withdraw?");

								try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
										int withdraw=scan.nextInt();

									PreparedStatement statement=conn.prepareStatement(getBalance);
									PreparedStatement updateStatement=conn.prepareStatement(updateQuery);

									ResultSet resultBalance=statement.executeQuery();				
									int currentBalance=0;
									while(resultBalance.next()){
											currentBalance=resultBalance.getInt(1);
										if (withdraw<=currentBalance) {
											if (withdraw%5==0) {
													int balanceAccount=currentBalance-withdraw;
													updateStatement.setInt(1,balanceAccount);
													int result2=updateStatement.executeUpdate();
													if (result2>0) {
														System.out.println("You have Withdrawn:"+withdraw+" Successfully");
													}
													
											}else{

												System.out.println("Money to Withdraw  has to be divisible by '5'");
											}
													
											
										}else{
												System.out.println("insufficient amount :)");

										}
												
									}
								}catch (SQLException e) {
									System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
									
								}catch (Exception e) {
									e.printStackTrace();
								}
								
								break;
							}
							case 4:{
								try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
									PreparedStatement statement=conn.prepareStatement(getBalance);
									PreparedStatement updateStatement=conn.prepareStatement(updateQuery);

									System.out.println("Which account Do you want to Transfer to?");
									System.out.println("follow this syntax:xxx-xxx-xxx-xx");
									String accountTransfer=scan.next();
									System.out.println("How much money You Want to Transfer?");
									int amountTransfer=scan.nextInt();

									ResultSet resultBalance=statement.executeQuery();				
									int currentBalance=0;
									int count=0;

									while(resultBalance.next()){
										currentBalance=resultBalance.getInt(1);
										if (amountTransfer<=currentBalance) {
											if (amountTransfer>=200) {

												String output="You will be Transferring To User #%d:%s";
												System.out.println(String.format(output,++count,accountTransfer));

												int balanceAccount=currentBalance-amountTransfer;
												updateStatement.setInt(1,balanceAccount);
												int result2=updateStatement.executeUpdate();
												if (result2>0) {
													System.out.println("You have Transfered:"+amountTransfer+" Successfully");
												}
												
											}else{

												System.out.println("Minimum Transfer that can be made if From:'200'and 'Above'");
												System.out.println("Try Encore!");
											}
											
										}else{
											System.out.println("insufficient amount :)");

										}
									}
								}catch (SQLException e) {
									System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
									
								}catch (Exception e) {
									e.printStackTrace();
								}
								
								break;
							}
							default:
								System.out.println("invalid transaction:)");
								break;
						}
						anotherTransaction=0;
						while(anotherTransaction!=1&&anotherTransaction!=2){
							System.out.println("Do you want another transaction?");
							System.out.println("Press #1.Yes|#2.No");
							anotherTransaction=scan.nextInt();
						}
			}
				
		
		
	}
	public static void loginForm(){
		Scanner scan=new Scanner(System.in);
		Console console=System.console();

		String link="jdbc:mysql://localhost:3306/Bank";
		String user="root";
		String dbpassWord="toor";
		System.out.print("Account #xxx-xxx-xxx-xx:");
		String input=scan.next().toLowerCase();
		String  pass=new String(console.readPassword("PassWord:"));
		System.out.println("Account:"+input+" | PassWord:"+pass);
		String QUERY="SELECT * FROM account WHERE account_number='" + input + "' AND password='" + pass + "'";
		if (!QUERY.contains(input)&&QUERY.contains(pass)) {
			return;
		}

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
					String output="User#%d:%s-%s-%s-%s-PassWord:%s-Balance:%d";
					System.out.println();
					System.out.println(String.format(output,++count,accountNumber,firstName,lastName,email,password,balance));
					// System.out.println("account is already exist Please Create other account");
					System.out.println();
					Person.bankOperations(input);
					
				}
				
				
		}	catch(SQLException e){
					System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
		}catch (Exception e) {
					e.printStackTrace();
			
		}
	
		
}
	public static void signUpAndInsert(){
		String link="jdbc:mysql://localhost:3306/Bank";
		String user="root";
		String dbpassWord="toor";
		Scanner scan=new Scanner(System.in);
		Console console=System.console();
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
		/*SO HERE WE ARE INPUTTING ALL THE DATA THAT WAS INSERTED SO THAT WE CAN STORE THEM IN THE DATABASE*/
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
		}catch (SQLException e) {

			System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
				
		}catch (Exception e) {
				e.printStackTrace();
				
		}
		
	}
	

}