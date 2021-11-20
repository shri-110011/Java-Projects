package example1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	public static final int MAX_PASSWORD = 9999;
	public static void main(String[] args) {
		Random random = new Random();
		int vaultPassword = random.nextInt(MAX_PASSWORD);
		System.out.println(vaultPassword);
		Vault vault = new Vault(vaultPassword);
		List<Thread> threads = new ArrayList<>();
		threads.add(new AscendingHackerThread(vault));
		threads.add(new DescendingHackerThread(vault));
		threads.add(new PoliceThread());
		
		for(Thread thread:threads) {
			thread.start();
		}
	}
	
	private static class Vault {
		private int password;
		
		public Vault(int password) {
			this.password = password;
		}
		
		public boolean checkPassword(int guess) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return password == guess;
		}
	}
	
	private static abstract class HackerThread extends Thread {
		protected Vault vault;
		
		
		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(Thread.MAX_PRIORITY);
		}
		
		public void start() {
			System.out.println(this.getClass());
			super.start();
		}
	}
	
	private static class AscendingHackerThread extends HackerThread {

		public AscendingHackerThread(Vault vault) {
			super(vault);
		}
		
		public void run() {
			for(int guess=0; guess<MAX_PASSWORD; guess++) {
//				System.out.println(this.getName()+" "+guess);
				if(vault.checkPassword(guess)) {
					System.out.println(this.getName()+" guessed the password "+guess);
					System.exit(0);
				}
			}
		}
		
	}
	
	private static class DescendingHackerThread extends HackerThread {

		public DescendingHackerThread(Vault vault) {
			super(vault);
		}
		
		public void run() {
			for(int guess=MAX_PASSWORD; guess>0; guess--) {
//				System.out.println(this.getName()+" "+guess);
				if(vault.checkPassword(guess)) {
					System.out.println(this.getName()+" guessed the password "+guess);
					System.exit(0);
				}
			}
		}
		
	}
	
	private static class PoliceThread extends Thread {
		public void run() {
			for(int countDown=20; countDown>0; countDown--) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(countDown+"***************************************");
			}
			System.out.println("Game over for you hackers");
			System.exit(0);
		}
	}

}
