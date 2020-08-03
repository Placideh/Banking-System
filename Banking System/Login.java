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
					String output="User#%d:%s-%s-%s-%s-%s";
					System.out.println();
					System.out.println(String.format(output,++count,accountNumber,firstName,lastName,email,password));
					// System.out.println("account is already exist Please Create other account");
					System.out.println();
					System.out.println("What do you want to do ?:");
					System.out.println("#1.Balance,#2.Deposit,#3.Withdraw,#4.Transfer");
					int choices=scan.nextInt();
					switch(choices){
						case 1:
							//call balance function
						break;

						case 2:
							//call Deposit function
						break;

						case 3:
							//call withdraw function
						break;

						case 4:
							//call Transfer function
						break;

						default:
						System.out.println("invalid Inputs");
						break;
					}

				}
			}catch(SQLException e){
				System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		else if (option==2) {
			String QUERY="INSERT INTO account(account_number,first_name,last_name,email,password) VALUES (?,?,?,?,?)";
			System.out.print("Account #xxx-xxx-xxx-xx:");
			String account=scan.next();
			System.out.print("First Name:");
			String firstName=scan.next().toUpperCase();
			System.out.print("Last Name:");
			String lName=scan.next();
			String lastName=lName.substring(0,1).toUpperCase()+lName.substring(1);
			System.out.print("Email:");
			String email=scan.next();
			System.out.print("PassWord:");
			String passWord=scan.next();
			System.out.println("Account:"+account+" |FistName:"+firstName+"|LastName:"+lastName+" |Email:"+email+" |PassWord:"+passWord);

			// public void insertItem(String account,String firstName,String lastName,String email,String passWord){

				try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
					PreparedStatement statement=conn.prepareStatement(QUERY);
					statement.setString(1,account);
					statement.setString(2,firstName);
					statement.setString(3,lastName);
					statement.setString(4,email);
					statement.setString(5,passWord);
					int result=statement.executeUpdate();
					if (result>0) {
						System.out.println("Account Created ");
						
					}else{
						System.out.println("Account already existed");
					}


				
				}catch (SQLException e) {
					System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
					
				}catch (Exception e) {
					e.printStackTrace();
					
				}
			// }
		}
	}
}	