import java.io.*;
import java.util.HashMap;

public class ATM {
	public static void main(String[] args) throws IOException {
		OptionMenu optionMenu = new OptionMenu();
		Account acc = new Account();
		introduction();
		acc.loadAccounts();
		optionMenu.mainMenu();
	}

	public static void introduction() {
		System.out.println("\nWelcome to the ATM Project!\n");
	}
}
