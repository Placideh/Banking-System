import java.util.Scanner;
import java.io.Console;
import java.sql.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
public class Login{
	public static void main(String[] args) {
		int anotherTransaction=1;
		while(anotherTransaction==1){
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
		//Here is Where there is the Welcome Design
		if (option==1) {
			
					Person.loginForm();
		}
		else if (option==2) {
					Person.signUpAndInsert();
		}
		/*THIS IS HELPIING TO HELP A USER TO START THE OPERATION OR LOG OUT IF THEY NEED TO 
		BY NO NEED OF TERMINATING THE PROGRAM QUICKLY*/
		anotherTransaction=0;
		while(anotherTransaction!=1&&anotherTransaction!=2){
			System.out.println();
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("DO YOU WANT TO START NEW OPERATION OR LOG OUT?");
			System.out.println("Press #1.Yes|#2.No");
			anotherTransaction=scan.nextInt();
		}
	}
	}
}	