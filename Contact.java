package project1.lib;

import java.io.Serializable;

//Contact 클래스 선언
public class Contact implements Serializable{
	private static final long serialVersionUID = -1522112818986156665L;
	private String name;
	private String number;
	private String address;
	private String relationship;

	// 객체 초기화
	public Contact(String name, String number, String address, String relationship) {
		this.name = name;
		this.number = number;
		this.address = address;
		this.relationship = relationship;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@Override
	public String toString() {
		return "이름 = " + name + ", 전화번호 : " + number + ", 주소 : " + address + ", 종류 = " + relationship;
	}
}

