package org.kpu.ticketbox.payment;

public class MovieTicket {

	public static final char SEAT_EMPTY_MARK = '-';
	public static final char SEAT_RESERVED_MARK = 'R';
	public static final char SEAT_PAY_COMPLETION_MARK = '$';
	
	private int nRow;
	private int nCol;
	private char cStatus;
	private int nReservedId;
	
	public MovieTicket(char cStatus) {
		this.cStatus = cStatus;
	}
	public int getnRow() {
		return nRow;
	}
	public int getnCol() {
		return nCol;
	}
	public char getcStatus() {
		return cStatus;
	}
	public void setcStatus(char cStatus) {
		this.cStatus = cStatus;
	}
	public void setSeat(int row, int col) {
		this.nRow = row;
		this.nCol = col;
	}
	public void setnReservedId(int id) {
		this.nReservedId = id;
	}
	
	//public void getnReservedId() {}
	 
	public int getnReservedId() {
		return nReservedId;
	}
	
}
