package project1.lib;

import java.io.Serializable;

//Contact 클래스 선언
public class Contact implements Serializable{
	private static final long serialVersionUID = -1522112818986156665L;
	private String name;
	private String number;
	private String address;
	private String relationship;

	// Contact 객체 초기화
	public Contact(String name, String number, String address, String relationship) {
		this.name = name;
		this.number = number;
		this.address = address;
		this.relationship = relationship;
	}
//	이름 반환받기
	public String getName() {
		return name;
	}
//	이름 설정하기
	public void setName(String name) {
		this.name = name;
	}
//	전화번호 반환받기
	public String getNumber() {
		return number;
	}
//	전화번호 설정하기
	public void setNumber(String number) {
		this.number = number;
	}
//	주소 반환받기
	public String getAddress() {
		return address;
	}
//	주소 설정하기
	public void setAddress(String address) {
		this.address = address;
	}
//	종류 반환받기
	public String getRelationship() {
		return relationship;
	}
//	종류 설정하기
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

//	문자열로 정보 반환하기
	@Override
	public String toString() {
		return "이름 = " + name + ", 전화번호 : " + number + ", 주소 : " + address + ", 종류 = " + relationship;
	}
}

