package com.globalin.domain;

public class TicKet {
	
	private int tno;
	private String Owner;
	private String grade;
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getOwner() {
		return Owner;
	}
	public void setOwner(String owner) {
		Owner = owner;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "TicKet [tno=" + tno + ", Owner=" + Owner + ", grade=" + grade + "]";
	}
	
	

}
