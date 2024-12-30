package project1.lib;

//파일을 읽고 저장하는 클래스 선언

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class FileReader{

	public static HashMap<String, Contact> loadContacts(String filePath) {
//   	key를 String, value를 Contact 클래스를 가지는 해시맵 선언
		HashMap<String, Contact> contacts = new HashMap<>();
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
			// 파일에서 읽은 데이터를 HashMap으로 변환
			contacts = (HashMap<String, Contact>) ois.readObject();
			System.out.println("파일에서 데이터를 성공적으로 불러왔습니다.");
		} catch (FileNotFoundException e) { // 파일이 없을 경우 예외처리
			System.out.println("파일이 존재하지 않습니다. 새로 생성합니다.");
		} catch (EOFException e) { // 파일이 비어 있을 경우 예외처리
			System.out.println("파일이 비어 있습니다. 새로 생성합니다.");
		} catch (IOException e) { // 기타 상황 예외 처리
			e.printStackTrace();
		} catch(ClassNotFoundException e) { // 클래스가 없을 경우 예외 처리
			e.printStackTrace();
		}
		return contacts;
	}

}
