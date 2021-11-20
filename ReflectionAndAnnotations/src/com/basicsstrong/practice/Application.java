package com.basicsstrong.practice;

import java.sql.SQLException;

public class Application {
	
	public static void main(String[] args) throws Exception {
		TransactionHistory sangeeta = new TransactionHistory(16781, "Sangeeta", "Credit", 1300);
		TransactionHistory neha = new TransactionHistory(16171, "Neha", "Debit", 1000);
		TransactionHistory mohit = new TransactionHistory(10437, "Mohit", "Debit", 2000);
		TransactionHistory josh = new TransactionHistory(18923, "Josh", "Credit", 3000);
		
		Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();
		
//		hibernate.write(sangeeta);
//		hibernate.write(neha);
//		hibernate.write(mohit);
//		hibernate.write(josh);
		
		TransactionHistory t = hibernate.read(TransactionHistory.class, 4L);
		System.out.println(t);
		
	}
}
