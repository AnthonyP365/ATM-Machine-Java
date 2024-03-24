import java.io.*;
import java.util.HashMap;

public class ATM {
	HashMap<Integer, Account> data = new HashMap<Integer, Account>();
	public static final String dataFile = "/Users/anthony/Documents/Projects/ATM-Machine-Java/AccountData";
	public static final String logFile = "/Users/anthony/Documents/Projects/ATM-Machine-Java/TransactionLog";

	public static void main(String[] args) throws IOException {
		OptionMenu optionMenu = new OptionMenu();
		introduction();
		optionMenu.mainMenu();
	}

	public static void introduction() {
		System.out.println("Welcome to the ATM Project!");
	}

	public void loadAccounts() {
		try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] fileData = line.split(",");
				int customerNumber = Integer.parseInt(fileData[0]);
				int pinNumber = Integer.parseInt(fileData[1]);
				double checkingBalance = Double.parseDouble(fileData[2]);
				double savingBalance = Double.parseDouble(fileData[3]);
				Account acc = new Account(customerNumber, pinNumber);
				acc.calcCheckingDeposit(checkingBalance);
				acc.calcSavingDeposit(savingBalance);
				data.put(customerNumber, acc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveAccounts() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
			for (Account acc : data.values()) {
				String line = acc.getCustomerNumber() + "," + acc.getPinNumber() + "," +
						acc.getCheckingBalance() + "," + acc.getSavingBalance();
				writer.write(line);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logTransaction(String transaction) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
			writer.write(transaction);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
