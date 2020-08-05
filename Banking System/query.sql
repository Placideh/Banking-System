-- CREATE DATABASE Bank;
-- USE Bank;
-- CREATE TABLE account (
-- 	account_number VARCHAR(100),
-- 	first_name VARCHAR(100),
-- 	last_name VARCHAR(100),
-- 	email VARCHAR(100),
-- 	PRIMARY KEY(account_number)

-- );
-- DESCRIBE account;
-- INSERT INTO account(account_number,first_name,last_name,email) VALUES('111-222-333-12','user','user','user@user.com');
-- SELECT * FROM account;
-- ALTER TABLE account ADD COLUMN password VARCHAR(255);
-- SELECT * FROM account;
-- UPDATE account SET password="123456" WHERE account_number="111-222-333-12";
-- SELECT * FROM account;
-- SELECT * FROM account WHERE account_number='111-222-333-12' AND password='123456';
-- SELECT account_number FROM account WHERE account_number='111-222-333-12';
-- SELECT account_number FROM account;
-- ALTER TABLE account ADD COLUMN balance INT(10);
-- SELECT * FROM account;
-- ALTER TABLE account DROP COLUMN balance;
-- SELECT * FROM account;
-- ALTER TABLE account ADD COLUMN balance INT(10) NOT NULL;
SELECT * FROM account;
-- public static void transfer(){
-- 		String link="jdbc:mysql://localhost:3306/Bank";
-- 		String user="root";
-- 		String dbpassWord="toor";
-- 		Scanner scan=new Scanner(System.in);
-- 		Console console=System.console();

-- 			System.out.println("Which account Do you want to Transfer to?");
-- 			System.out.println("follow this syntax:xxx-xxx-xxx-xx");
-- 			String accountTransfer=scan.next();
-- 		String checkquery="SELECT account_number FROM account WHERE account_number='"+accountTransfer+"'";
-- 		String updateQuery="UPDATE account SET balance=? WHERE account_number='"+accountTransfer+"'";
-- 		String checkquery2="SELECT balance FROM account WHERE account_number='"+accountTransfer+"'";

-- 			System.out.println("How much money You Want to Transfer?");
-- 			int amountTransfer=scan.nextInt();
-- 			balanceAccount-=amountTransfer;

-- 		try(Connection conn=DriverManager.getConnection(link,user,dbpassWord)){
-- 			PreparedStatement statement=conn.prepareStatement(checkquery);
-- 			PreparedStatement statement2=conn.prepareStatement(checkquery2);
-- 			PreparedStatement statUpdate=conn.prepareStatement(updateQuery);
-- 			ResultSet result=statement.executeQuery();
-- 			ResultSet result2=statement2.executeQuery();
-- 			int count=0;
-- 			int amountAdded=0;
-- 			while(result.next()&&result2.next()){
-- 				String accountNumber=result.getString(1);//accountnumber
-- 				String output="You will be Transferring To User#%d:%s";
-- 				System.out.println(String.format(output,++count,accountNumber));
-- 				int currentBalance=result2.getInt(1);
-- 				amountAdded+=currentBalance;
-- 				// int amount=result2.getInt(1);


				
-- 			}

-- 			// int bal=Person.bankOperations();
-- 			 int transferedAmount=amountAdded+amountTransfer;
-- 			statUpdate.setInt(1,transferedAmount);
-- 			int amountAddedResult=statUpdate.executeUpdate();
-- 			if (amountAddedResult>0) {
-- 				System.out.println("Transferring Made Successfully!!");
				
-- 			}

-- 		}catch (SQLException e) {
-- 			System.err.format("SQLSTATE:%s\n%s",e.getSQLState(),e.getMessage());
			
-- 		}catch (Exception e) {
-- 			e.printStackTrace();
-- 		}


-- 	} 

