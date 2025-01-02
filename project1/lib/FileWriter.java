package project1.lib;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class FileWriter{
	
	// 데이터를 파일에 저장하는 메서드
	public static void saveContacts(String filePath, HashMap<String, Contact> contacts) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
			oos.writeObject(contacts);
			System.out.println("데이터가 파일에 성공적으로 저장되었습니다.");
		} catch(FileNotFoundException e){ // 파일이 존재하지 않을 경우 예외 처리
			System.out.println("파일이 존재하지 않습니다.");
		} catch (IOException e) { // 기타 예외 처리
			e.printStackTrace();
		}
	}
}
