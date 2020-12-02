package org.kpu.ticketbox.cinema;

import java.util.HashMap;
import java.util.Scanner;
import org.kpu.ticketbox.payment.BankTransfer;
import org.kpu.ticketbox.payment.CardPay;
import org.kpu.ticketbox.payment.MobilePay;
import org.kpu.ticketbox.payment.MovieTicket;
import org.kpu.ticketbox.payment.Pay;
import org.kpu.ticketbox.payment.Receipt;

public abstract class Screen {
	private int nCurrentReservedId = 100;
	private int ticketCount = 0;
	protected int nTicketPrice;
	protected int nRowMax;
	protected int nColMax;
	protected String strMovieName;
	protected String strMovieStory;
	protected MovieTicket[][] seatArray;
	public abstract void showMovieInfo(); //영화정보
	private HashMap<Integer, Receipt> receiptMap = new HashMap<Integer, Receipt>();
	Scanner scan = new Scanner(System.in);

	Screen(String name, String story, int price, int row, int col) {
		this.nTicketPrice = price;
		this.nRowMax = row;
		this.nColMax = col;
		this.strMovieName = name;
		this.strMovieStory = story;
		seatArray = new MovieTicket[row][col];
		initSeat();
	}
	public void initSeat() {
		for(int i=0; i<nRowMax; i++) {
			for(int j=0; j<nColMax; j++) {
				this.seatArray[i][j] = new MovieTicket(MovieTicket.SEAT_EMPTY_MARK);
			}
		}
	}
	public void showScreenMenu() {
		System.out.println("----------------------");
		System.out.println("영화 메뉴 - " + this.strMovieName);
		System.out.println("----------------------");
		System.out.println(" 1. 상영 영화 정보");
		System.out.println(" 2. 좌석 예약 현황");
		System.out.println(" 3. 좌석 예약 하기");
		System.out.println(" 4. 좌석 변경 하기");
		System.out.println(" 5. 좌석 결제 하기");
		System.out.println(" 6. 티켓 출력 하기");
		System.out.println(" 7. 메인 메뉴 이동");
	}
	public void showSeatMap() {
		System.out.println("\t[ 좌석 예약 현황 ]");
		for(int i=0; i<nRowMax+1; i++) {
			if(i>0) System.out.print("[" + i + "]");
			for(int j=0; j<nColMax; j++) {
				if(j==0) System.out.print("\t");
				if(i==0) System.out.print("[" + (j+1) + "] ");
				else System.out.print("[" + seatArray[i-1][j].getcStatus() + "] ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public void reserveTicket() {
		System.out.println("[ 좌석 예약 ]");
		System.out.print("좌석 행 번호 입력(1 - " + nRowMax+") : ");
		int seatRow = scan.nextInt();
		System.out.print("좌석 열 번호 입력(1 - " + nColMax+") : ");
		int seatCol = scan.nextInt();
		if(((seatRow>=1 && seatRow<=nRowMax) && (seatCol>=1 && seatCol<=nColMax))) {
			if(seatArray[seatRow-1][seatCol-1].getcStatus() == MovieTicket.SEAT_EMPTY_MARK) {
				seatArray[seatRow-1][seatCol-1].setSeat(seatRow, seatCol);
				seatArray[seatRow-1][seatCol-1].setnReservedId(nCurrentReservedId);
				seatArray[seatRow-1][seatCol-1].setcStatus(MovieTicket.SEAT_RESERVED_MARK);
				System.out.printf("행[%d] 열[%d] %d 예약 번호로 접수되었습니다.\n", seatRow,seatCol,nCurrentReservedId++);
			}
			else System.out.println("입력하신 좌석은 이미 예약된 좌석입니다.");
		}
		else System.out.println("입력하신 좌석은 존재하지 않습니다.");
		System.out.println();
	}
	private MovieTicket checkReservedId(int id) {
		int row=-1;
		int col=-1;
		for(int i=0; i<nRowMax; i++) {
			for(int j=0; j<nColMax; j++) {
				if(seatArray[i][j].getnReservedId() == id) {
					row=i; col=j;
				}
			}
		}
		if(row != -1 && col != -1) {
			return seatArray[row][col];
		}
		else return null;
	}
	public void changeTicket() {
		System.out.println("[ 좌석 변경 ]");
		System.out.print("좌석 예약 번호 입력 : ");
		int id = scan.nextInt();
		MovieTicket checkid = checkReservedId(id);
		if (checkid != null) {
			checkid.setcStatus(MovieTicket.SEAT_EMPTY_MARK);
			checkid.setnReservedId(0);
			System.out.print("좌석 행 번호 입력(1 - " + nRowMax+") : ");
			int seatRow = scan.nextInt();
			System.out.print("좌석 열 번호 입력(1 - " + nColMax+") : ");
			int seatCol = scan.nextInt();
			if(((seatRow>0 && seatRow<=nRowMax) && (seatCol>0 && seatCol<=nColMax))) {
				if(seatArray[seatRow-1][seatCol-1].getcStatus()==MovieTicket.SEAT_EMPTY_MARK) {
					seatArray[seatRow-1][seatCol-1].setSeat(seatRow, seatCol);
					seatArray[seatRow-1][seatCol-1].setnReservedId(id);
					seatArray[seatRow-1][seatCol-1].setcStatus(MovieTicket.SEAT_RESERVED_MARK);
					System.out.printf("예약번호 %d 행[%d] 열[%d] 좌석으로 변경되었습니다.\n", id,seatRow,seatCol);
				}
				else System.out.println("입력하신 좌석은 선택할 수 없습니다.");
			}
			else System.out.println("입력하신 좌석은 존재하지 않습니다.");
		}
		else System.out.println("입력하신 예약 번호는 존재하지 않습니다.");
	}
	public void payment() {
		System.out.println("[ 좌석 예약 결제 ]");
		System.out.print("예약 번호 입력 : ");
		int id = scan.nextInt();
		MovieTicket checkid = checkReservedId(id);
		if (checkid != null) {
			if (checkid.getcStatus() == MovieTicket.SEAT_RESERVED_MARK) {
				System.out.println("----------------------");
				System.out.println("	결제 방식 선택");
				System.out.println("----------------------");
				System.out.println(" 1. 은행 이체");
				System.out.println(" 2. 카드 결제");
				System.out.println(" 3. 모바일 결제");
				System.out.print("결제 방식 입력(1~3) : ");
				int payNumber = scan.nextInt();
				System.out.println();
	
				Pay pay = null;
				String name = null;
				String number = null;
				switch(payNumber) {
				case 1:
					System.out.print("[ 은행 이체 ]");
					System.out.print("이름 입력 : ");
					name = scan.next();
					System.out.print("은행 번호 입력(12자리수) : ");
					number = scan.next();
					BankTransfer bankTransfer = new BankTransfer();
					pay = bankTransfer;
					break;
				case 2:
					System.out.print("[ 카드 결제 ]");
					System.out.print("이름 입력 : ");
					name = scan.next();
					System.out.print("카드 번호 입력(12자리수) : ");
					number = scan.next();
					CardPay cardPay = new CardPay();
					pay = cardPay;
					break;
				case 3:
					System.out.print("[ 모바일 결제 ]");
					System.out.print("이름 입력 : ");
					name = scan.next();
					System.out.print("핸드폰 번호 입력(11자리수) : ");
					number = scan.next();
					MobilePay mobilePay = new MobilePay();
					pay = mobilePay;
					break;
				default:
					System.out.println("입력하신 결제 방식이 존재하지 않습니다.");
					break;
				}
				if (pay != null) {
					Receipt receipt = pay.charge(strMovieName, nTicketPrice, name, number);
					if (receipt != null) {
						System.out.println(pay.payMethod() + " 결제가 완료되었습니다. : " + receipt.getTotalAmount() + "원");
						System.out.println();
						checkReservedId(id).setcStatus(MovieTicket.SEAT_PAY_COMPLETION_MARK);
						receiptMap.put(id, receipt);
						ticketCount++;
					} 
				}
			}
			else System.out.println("이미 결제 완료된 예약 번호입니다.");
		}
		else System.out.println("입력하신 예약 번호는 존재하지 않습니다.");
	}
	public void printTicket() {
		System.out.println(" [ 좌석 티켓 출력 ]");
		System.out.print("예약 번호 입력 : ");
		int reserveNumber = scan.nextInt();
		if (checkReservedId(reserveNumber) != null) {
			if (checkReservedId(reserveNumber).getcStatus() == MovieTicket.SEAT_PAY_COMPLETION_MARK) {
				System.out.println("----------------------");
				System.out.println("--     Receipt      --");
				System.out.println("----------------------");
				Receipt receipt = receiptMap.get(reserveNumber);
				System.out.println(receipt.toString());
			}
			else System.out.println("입력하신 예약 번호는 결제가 완료되지 않았습니다.");
		}
		else System.out.println("입력하신 예약 번호는 존재하지 않습니다.");
	}
	public HashMap<Integer, Receipt> getHashMap() {
		return receiptMap;
	}
	public int getTicketCount() {
		return ticketCount;
	}
}