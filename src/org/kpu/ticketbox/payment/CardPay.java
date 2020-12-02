package org.kpu.ticketbox.payment;

public class CardPay implements Pay{
	public static final double CARD_COMMISION = 0.15;

	@Override
	public Receipt charge(String product, double amount, String name, String number) {
		double totalAmount = amount + amount*CARD_COMMISION;
		Receipt receipt = new Receipt(name, product, "CardPay", number, amount, totalAmount);
		return receipt;
	}

	@Override
	public String payMethod() {
		return "Ä«µå";
	}

}
