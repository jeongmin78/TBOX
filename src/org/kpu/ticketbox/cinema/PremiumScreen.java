package org.kpu.ticketbox.cinema;

public class PremiumScreen extends Screen {
	public PremiumScreen(String name, String story, int price, int row, int col) {
		super(name, story, price, row, col);
	}

	@Override
	public void showMovieInfo() {
		System.out.println("----------------------");
		System.out.println(this.strMovieName + " 소개");
		System.out.println("----------------------");
		System.out.println("영화관 : 프리미엄 영화 3관 (커피,케익 제공)");
		System.out.println("줄거리 : " + this.strMovieStory);
		System.out.println("가격 : " + this.nTicketPrice + "원");
	}
}
