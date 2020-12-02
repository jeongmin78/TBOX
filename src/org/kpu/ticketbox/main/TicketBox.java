package org.kpu.ticketbox.main;

import java.util.Scanner;

import org.kpu.ticketbox.cinema.*;
import org.kpu.ticketbox.util.BackupWriter;
import org.kpu.ticketbox.util.Statistics;

public class TicketBox {
	private FamillyScreen famillyScreen;
	private AnimationScreen animationScreen;
	private PremiumScreen premiumScreen;
	public static final String ADMIN_PASSWORD = "admin";
	Scanner scan = new Scanner(System.in);
	
	public void initScreen() {
		famillyScreen = new FamillyScreen("노트북","17살, ‘노아’는 밝고 순수한 ‘앨리’를 보고 첫눈에 반한다.",8000,10,10);
		animationScreen = new AnimationScreen("레드슈즈", "사건 사고가 끊이지 않는 동화의 섬.", 10000, 10, 10);
		premiumScreen = new PremiumScreen("아바타", "지구 에너지 고갈 문제를 해결하기 위해 판도라 행성으로 향한 인류는 원주민 ‘나비족’과 대립하게 된다.", 25000, 5, 5);
	}

	public Screen selectScreen() {
		System.out.println("----------------------");
		System.out.println("-    상영관 선택 메인메뉴    -");
		System.out.println("----------------------");
		System.out.println(" 1. 가족 영화 1관");
		System.out.println(" 2. 애니메이션 영화 2관");
		System.out.println(" 3. 프리미엄 영화 3관 (커피, 케익 제공)");
		System.out.println(" 9. 관리자 메뉴");
		System.out.println(" 선택(1~3, 9)외 종료");
		System.out.print("\n상영관 선택 : ");
		int screenNumber = scan.nextInt();
		if (screenNumber == 9) 
			managerMode();
		if (screenNumber == 1) return famillyScreen;
		else if (screenNumber == 2)	return animationScreen;
		else if (screenNumber == 3) return premiumScreen;
		else return null;
	}
	
	void managerMode() {
		BackupWriter backupWriter = new BackupWriter();
		Screen family = famillyScreen;
		Screen animation = animationScreen;
		Screen premium = premiumScreen;
		System.out.print("암호 입력 : ");
		String password = scan.next();
		if (password.equals(ADMIN_PASSWORD)) {
			System.out.println("----------------------");
			System.out.println("----    관리자 기능    ----");
			System.out.println("----------------------");
			System.out.println("가족 영화관 결제 총액 : " + Statistics.sum(family.getHashMap()));
			System.out.println("애니메이션 영화관 결제 총액 : " + Statistics.sum(animation.getHashMap()));
			System.out.println("프리미엄 영화관 결제 총액 : " + Statistics.sum(premium.getHashMap()));
			System.out.println("영화관 총 티켓 판매량 : " + family.getTicketCount() + animation.getTicketCount() + premium.getTicketCount());
			System.out.println("c:\\\\temp\\\\tBoxReceipt.txt 백업 시작합니다.");
			backupWriter.backupFile("c:\\\\temp\\\\tBoxReceipt.txt", family.getHashMap());
			System.out.println("가족 영화관 매출 백업 완료");
			backupWriter.backupFile("c:\\\\temp\\\\tBoxReceipt.txt", animation.getHashMap());
			System.out.println("애니메이션 영화관 매출 백업 완료");
			backupWriter.backupFile("c:\\\\temp\\\\tBoxReceipt.txt", premium.getHashMap());
			System.out.println("프리미엄 영화관 매출 백업 완료\n");
		}
	}
}
