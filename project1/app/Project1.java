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
    private static final String FILE_PATH = "C:\\Temp\\contacts.bin"; // 연락처 데이터를 저장할 파일 경로

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 키보드 입력을 위한 Scanner 객체 생성
        HashMap<String, Contact> contacts = FileReader.loadContacts(FILE_PATH); // 파일에서 HashMap으로 연락처 로드
        ArrayList<Contact> matchedContacts = new ArrayList<>(); // 특정 조건에 맞는 연락처를 저장하는 리스트

        while (true) { // 프로그램 종료 전까지 반복 실행
            // 메뉴 출력
            System.out.println();
            System.out.println("============================");
            System.out.println("   다음 메뉴 중 하나를 선택하세요.   ");
            System.out.println("============================");
            System.out.println("1. 회원 추가");
            System.out.println("2. 회원 목록 보기");
            System.out.println("3. 회원 정보 수정하기");
            System.out.println("4. 회원 삭제");
            System.out.println("5. 종료");

            // 사용자로부터 메뉴 선택 입력받기
            String choice = scanner.nextLine();

            try {
                // 사용자 선택에 따른 분기 처리
                switch (choice) {
//                	회원 추가
                    case "1":
                        System.out.println("등록할 회원의 정보를 입력하세요.");
                        System.out.print("이름: ");
                        String name = scanner.nextLine();
                        System.out.print("전화번호(ex: 01012345678): ");
                        String number = scanner.nextLine();

                        // 중복된 전화번호인지 확인
                        if (contacts.containsKey(number)) {
                            System.out.println("이미 등록된 전화번호입니다.");
                            // 중복된 경우 메뉴 선택으로 돌아감
                            continue;
                        }

                        // 추가 정보 입력받기
                        System.out.print("주소: ");
                        String address = scanner.nextLine();
                        System.out.print("종류(ex.가족, 친구, 기타): ");
                        String relationship = scanner.nextLine();

                        // 새로운 연락처를 HashMap에 추가
                        contacts.put(number, new Contact(name, number, address, relationship));
                        System.out.println("회원이 추가되었습니다.");
                        break;

//                  회원 목록 보기  
                    case "2":
                        System.out.println("총 " + contacts.size() + "명의 회원이 저장되어 있습니다.");
                        // 저장된 모든 연락처 출력
                        for (Contact contact : contacts.values()) {
                            System.out.println("회원정보 : " + contact);
                        }
                        break;

//                  회원 정보 수정
                    case "3":
                        System.out.println("수정할 회원의 이름을 입력하세요.");
                        System.out.print("이름 : ");
                        String modifyName = scanner.nextLine();

                        // 이름이 일치하는 회원 찾기
                        for (Contact contact : contacts.values()) {
                            if (contact.getName().equals(modifyName)) {
                                matchedContacts.add(contact); // ArrayList에 추가
                            }
                        }

                        if (matchedContacts.isEmpty()) { // 해당 이름이 없는 경우
                            System.out.println("해당 이름의 회원 정보가 없습니다.");
                        } else { // 해당 이름의 회원이 여러 명일 경우 선택
                            for (int i = 0; i < matchedContacts.size(); i++) {
                                System.out.println((i + 1) + ". " + matchedContacts.get(i));
                            }
                            System.out.print("수정할 회원 번호를 선택하세요: ");
                            int selected = scanner.nextInt();
                            scanner.nextLine(); // 버퍼 초기화

                            if (selected > 0 && selected <= matchedContacts.size()) {
                                // 선택된 연락처 수정
                                Contact contact = matchedContacts.get(selected - 1);
                                System.out.print("이름: ");
                                String newName = scanner.nextLine();
                                System.out.print("전화번호: ");
                                String newNumber = scanner.nextLine();

                                // 새 전화번호가 이미 존재하는 경우 처리
                                if (!contact.getNumber().equals(newNumber) && contacts.containsKey(newNumber)) {
                                    System.out.println("이미 등록된 전화번호입니다.");
                                    // 중복된 경우 메뉴 선택으로 돌아감
                                    continue;
                                }

                                // 수정된 정보 입력받기
                                System.out.print("주소: ");
                                String newAddress = scanner.nextLine();
                                System.out.print("종류(ex.가족, 친구, 기타): ");
                                String newRelationship = scanner.nextLine();

                                // 기존 연락처 삭제 후 수정된 정보 저장
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
                        
//                  회원 삭제
                    case "4":
                        System.out.println("삭제할 회원의 이름을 입력하세요.");
                        System.out.print("이름 : ");
                        String deleteName = scanner.nextLine();

                        // 삭제 대상 검색
                        for (Contact contact : contacts.values()) {
                            if (contact.getName().equals(deleteName)) {
                                matchedContacts.add(contact);
                            }
                        }

                        if (matchedContacts.isEmpty()) { // 해당 이름이 없는 경우
                            System.out.println("해당 이름의 회원 정보가 없습니다.");
                        } else { // 삭제 대상 선택
                            for (int i = 0; i < matchedContacts.size(); i++) {
                                System.out.println((i + 1) + ". " + matchedContacts.get(i));
                            }
                            System.out.print("삭제할 회원 번호를 선택하세요: ");
                            int selected = scanner.nextInt();
                            scanner.nextLine(); // 버퍼 초기화

                            if (selected > 0 && selected <= matchedContacts.size()) {
                                // 선택된 연락처 삭제
                                Contact contact = matchedContacts.get(selected - 1);
                                contacts.remove(contact.getNumber());
                                System.out.println("삭제가 완료되었습니다.");
                            } else {
                                System.out.println("잘못된 번호를 선택했습니다.");
                            }
                        }
                        break;

//                  종료
                    case "5":
                        FileWriter.saveContacts(FILE_PATH, contacts); // 연락처 저장
                        System.out.println("종료되었습니다.");
                        return; // 프로그램 종료

//                  잘못된 메뉴 선택 처리
                    default:
                        System.out.println("1~5의 정수를 입력해주세요.");
                }
            } catch (InputMismatchException e) { // 잘못된 입력 처리
                System.out.println("올바른 형식을 입력해주세요.");
                scanner.nextLine(); // 입력 버퍼 초기화
            } catch (Exception e) { // 기타 예외 처리
                System.out.println("알 수 없는 오류가 발생했습니다. 다시 시도해주세요.");
                e.printStackTrace();
            }
        }
    }
}
