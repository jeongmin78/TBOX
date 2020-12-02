package org.kpu.ticketbox.payment;

public class MobilePay implements Pay{
	public static final double MOBILE_COMMISION = 0.2;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {
		double totalAmount = amount + amount*MOBILE_COMMISION;
		Receipt receipt = new Receipt(name, product, "MobilePay", number, amount, totalAmount);
		return receipt;
	}

	@Override
	public String payMethod() {
		return "¸ð¹ÙÀÏ";
	}

}
