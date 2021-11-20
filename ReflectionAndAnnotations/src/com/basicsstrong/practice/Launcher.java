package com.basicsstrong.practice;

import java.sql.SQLException;

import org.h2.tools.Server;

public class Launcher {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Server.main();
		
//		Class.forName("org.h2.Driver");
//		Connection conn = DriverManager.getConnection("jdbc:h2:D:\\Java EE Workspace\\ReflectionAndAnnotations\\database\\practice1", "sa", "");
//		System.out.println("Database created!");
		

	}

}
//D:\Java EE Workspace\ReflectionAndAnnotations\database\practice1
//select * from TransactionHistory;
//insert into TransactionHistory (transactionId,accountNumber,name,transactionType,amount) values (1001, 16781, 'Sangeeta', 'Credit', 1300);
//drop table TransactionHistory;
/*create table TransactionHistory(
transactionId long primary key,
accountNumber int,
name varchar(30),
transactionType varchar(15),
amount double
);*/

/*
 drop table TransactionHistory;
create table TransactionHistory(
transactionId long primary key,
accountNumber int,
name varchar(30),
transactionType varchar(15),
amount double
);
select * from TransactionHistory;
 */
