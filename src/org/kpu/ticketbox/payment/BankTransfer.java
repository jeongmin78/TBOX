package org.kpu.ticketbox.payment;

public class BankTransfer implements Pay{
	public static final double BANK_TRANSFER_COMMISION = 0.1;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {
		double totalAmount = amount + amount*BANK_TRANSFER_COMMISION;
		Receipt receipt = new Receipt(name, product, "BankTransfer",number, amount, totalAmount);
		return receipt;
	}

	@Override
	public String payMethod() {
		return "АєЗа";
	}
}
