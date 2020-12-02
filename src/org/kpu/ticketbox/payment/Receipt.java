package org.kpu.ticketbox.payment;

public class Receipt {
	String client;
	String productName;
	String payMethod;
	String payNumber;
	double subTotalAmount;
	double totalAmount;
	
	public Receipt(String client, String productName, String payMethod, String payNumber, double subTotalAmount, double totalAmount) {
		this.client = client;
		this.productName = productName;
		this.payMethod = payMethod;
		this.payNumber = payNumber;
		this.subTotalAmount = subTotalAmount;
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "[ Client :\t" + client + " ]\n" + "[ Product :\t" + productName + " ]\n"
				+ "[ PayMethod :\t" + payMethod + " ]\n" + "[ PayNumber :\t" + payNumber + " ]\n"
				+ "[ SubTotal :\t" + subTotalAmount + " ]\n" + "[ Total :\t" + totalAmount + " ]\n";
	}
	
	public String toBackupString() {
		return client + "," + productName + "," + payMethod
				 + "," + payNumber + "," + subTotalAmount + "," + totalAmount + "\n";
	}

	public double getTotalAmount() {
		return totalAmount;
	}
}
