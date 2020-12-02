package org.kpu.ticketbox.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.kpu.ticketbox.cinema.Screen;

public class TicketBoxMain {

	public static void main(String[] args) {

		TicketBox ticketBox = new TicketBox();
		Scanner scan = new Scanner(System.in);
		Screen screen = null;
		boolean bMainMenu = true;
		ticketBox.initScreen();
		
		while(true) {
			if(bMainMenu) {
				screen = ticketBox.selectScreen(); //상영관 선택
				if(screen == null) {
					System.out.print("안녕히 가세요 !");
					scan.close();
					System.exit(0);
				}
				bMainMenu = false;
			}
			screen.showScreenMenu(); //상영관 선택 메인메뉴
			System.out.print("메뉴를 선택하세요 >> ");
			try {
				int select = scan.nextInt();
				switch(select) {
					case 1: //스크린 상영 영화 정보 보기
						screen.showMovieInfo();
						break;
					case 2:
						screen.showSeatMap();
						break;
					case 3:
						screen.reserveTicket();
						break;
					case 4:
						screen.changeTicket();
						break;
					case 5:
						screen.payment();
						break;
					case 6:
						screen.printTicket();
						break;
					case 7:
						bMainMenu = true;
						break;
					default:
						System.out.println("메뉴(1~7)을 선택해주세요.");
						break;	
				}
			}catch (InputMismatchException e) {
				System.out.println("잘못된 입력입니다.");
				scan.nextLine();
				continue;
			}
		}
	}
}
