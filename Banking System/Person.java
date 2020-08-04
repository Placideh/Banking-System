import java.util.Scanner;
public class Person{
	private String accountNumber;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	public Person(){}
	
	public static void main(String[] args) {
		// Person person=new Person();
		// person.bankOperations();
		// bankOperations();
		int bal=bankOperations();
		System.out.println("Your Balance Is:"+bal);
	}
	public static int bankOperations(){
		int anotherTransaction=1;
		int balance=0;

		Scanner scan=new Scanner(System.in);
		while (anotherTransaction==1) {
				System.out.println("What do you Want to do?");
				System.out.println("#1.CheckBalance|#2.Deposit|#3.Withdraw|#4.Transfer");
				int option=scan.nextInt();
				switch (option) {
					case 1:
						System.out.println("your balance is:"+balance);
						break;
					case 2:{
						System.out.println("How much do you want to deposit?");
						int deposit=scan.nextInt();
						balance+=deposit;
						System.out.println("You have deposited:"+deposit+" Successfully");
						break;
					}
					case 3:{
						System.out.println("How much do you want to Withdraw?");
						int withdraw=scan.nextInt();
						balance-=withdraw;
						System.out.println("You have Withdrawn:"+withdraw+" Successfully");
						break;
					}
					case 4:{
						System.out.println("Which account Do you want to Transfer to?");
						System.out.println("follow this syntax:xxx-xxx-xxx-xx");
						String accountTransfer=scan.next();
						System.out.println("How much money You Want to Transfer?");
						int amountTransfer=scan.nextInt();
						balance-=amountTransfer;
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
		return balance;
}
}