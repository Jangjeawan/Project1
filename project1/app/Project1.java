package project1.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

import project1.lib.Contact;
import project1.lib.FileReader;
import project1.lib.FileWriter;


// 메인 실행 클래스
public class Project1 {
    private static final String FILE_PATH = "C:\\Temp\\contacts.bin";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Contact> contacts = FileReader.loadContacts(FILE_PATH);
        ArrayList<Contact> matchedContacts = new ArrayList<>();	
        
        while (true) {
            System.out.println();
            System.out.println("============================");
            System.out.println("   다음 메뉴 중 하나를 선택하세요.   ");
            System.out.println("============================");
            System.out.println("1. 회원 추가");
            System.out.println("2. 회원 목록 보기");
            System.out.println("3. 회원 정보 수정하기");
            System.out.println("4. 회원 삭제");
            System.out.println("5. 종료");

            String choice = scanner.nextLine();
            
            try {
            	switch(choice) {
            	case "1":
            		System.out.println("등록할 회원의 정보를 입력하세요.");
            		System.out.print("이름: ");
            		String name = scanner.nextLine();
            		System.out.print("전화번호(ex: 01012345678): ");
            		String number = scanner.nextLine();

            		if (contacts.containsKey(number)) {
            			System.out.println("이미 등록된 전화번호입니다.");
            			continue;
            		}
            		System.out.print("주소: ");
            		String address = scanner.nextLine();
            		System.out.print("종류(ex.가족, 친구, 기타): ");
            		String relationship = scanner.nextLine();

            		contacts.put(number, new Contact(name, number, address, relationship));
            		System.out.println("회원이 추가되었습니다.");
            		break;
                
            	case "2":
            		System.out.println("총 " + contacts.size() + "명의 회원이 저장되어 있습니다.");
            		for (Contact contact : contacts.values()) {
            			System.out.println("회원정보 : " + contact);
            		}
            		break;
                
            	case "3":
            		System.out.println("수정할 회원의 이름을 입력하세요.");
                	System.out.print("이름 : ");
                	String modifyName = scanner.nextLine();

                	for (Contact contact : contacts.values()) {
                		if (contact.getName().equals(modifyName)) {
                			matchedContacts.add(contact);
                		}
                	}
                	if (matchedContacts.isEmpty()) {
                		System.out.println("해당 이름의 회원 정보가 없습니다.");
                	} else {
                		for (int i = 0; i < matchedContacts.size(); i++) {
                			System.out.println((i + 1) + ". " + matchedContacts.get(i));
                		}
                		System.out.print("수정할 회원 번호를 선택하세요: ");
                		int selected = scanner.nextInt();
                		scanner.nextLine();

                		if (selected > 0 && selected <= matchedContacts.size()) {
                			Contact contact = matchedContacts.get(selected - 1);
                			System.out.print("이름: ");
                			String newName = scanner.nextLine();
                			System.out.print("전화번호: ");
                			String newNumber = scanner.nextLine();

                			if (!contact.getNumber().equals(newNumber) && contacts.containsKey(newNumber)) {
                				System.out.println("이미 등록된 전화번호입니다.");
                				continue;
                			}
                			System.out.print("주소: ");
                			String newAddress = scanner.nextLine();
                			System.out.print("종류(ex.가족, 친구, 기타): ");
                			String newRelationship = scanner.nextLine();

                			contacts.remove(contact.getNumber());
                			contact.setName(newName);
                			contact.setNumber(newNumber);
                			contact.setAddress(newAddress);
                        	contact.setRelationship(newRelationship);
                        	contacts.put(newNumber, contact);

                        	System.out.println("수정이 완료되었습니다.");
                		} else {
                			System.out.println("잘못된 번호를 선택했습니다.");
                		}
                	}
                	break;
                
            	case "4":
            		System.out.println("삭제할 회원의 이름을 입력하세요.");
            		System.out.print("이름 : ");
            		String deleteName = scanner.nextLine();

            		for (Contact contact : contacts.values()) {
            			if (contact.getName().equals(deleteName)) {
                        matchedContacts.add(contact);
            			}
            		}
            		if (matchedContacts.isEmpty()) {
            			System.out.println("해당 이름의 회원 정보가 없습니다.");
                	} else {
                    	for (int i = 0; i < matchedContacts.size(); i++) {
                    		System.out.println((i + 1) + ". " + matchedContacts.get(i));
                    	}
                    	System.out.print("삭제할 회원 번호를 선택하세요: ");
                    	int selected = scanner.nextInt();
                    	scanner.nextLine();

                    	if (selected > 0 && selected <= matchedContacts.size()) {
                    		Contact contact = matchedContacts.get(selected - 1);
                        	contacts.remove(contact.getNumber());
                        	System.out.println("삭제가 완료되었습니다.");
                    	} else {
                    		System.out.println("잘못된 번호를 선택했습니다.");
                    	}
                	}
            		break;
                
            	case "5":
            		FileWriter.saveContacts(FILE_PATH, contacts);
            		System.out.println("종료되었습니다.");
            		break;
                
            	default:
            		System.out.println("1~5의 정수를 입력해주세요.");
            	}
            } catch(InputMismatchException e) {
            	System.out.println("올바른 형식을 입력해주세요.");
            	scanner.nextLine();
            } catch(Exception e) {
            	System.out.println("알 수 없는 오류가 발생했습니다. 다시 시도해주세요.");
            	e.printStackTrace();
            }
        }
    }
}
