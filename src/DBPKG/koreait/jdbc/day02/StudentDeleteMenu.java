package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDeleteMenu {

	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		//		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		System.out.println("::::::::::::::::::::학생 삭제 메뉴입니다.::::::::::::::::::::");
		System.out.println("<<지정된 학번에 대해 나이와 주소를 삭제할 수 있습니다.>>");
		//update문 테스트
		try (Connection conn = DriverManager.getConnection(url, user, password);) {
			//학생등록

			updateStudent(conn);

		} catch (Exception e) {
			System.out.println("오류 메시지 = " + e);
		}

	}
		
		//@SuppressWarnings("resource");
		private static void updateStudent(Connection connection) throws Exception {
			Scanner sc = new Scanner(System.in);
			String stuno,name,age,address;
			String sql = "delete TBL_STUDENT\r\n"
					+ "where stuno = ?";
			System.out.println("나이와 주소 삭제 프로그램입니다.");
			System.out.println("학생번호 입력시 0000입력은 삭제 취소 입니다.");
			System.out.print("학번을 입력하세요 >>> ");
			stuno = sc.nextLine();
			
			if (stuno.equals("0000")) {
				System.out.println("학생 정보 수정(입력)을 종료합니다.");
				sc.close();
				return;	//리턴에 값이 없을 때는 단순하게 메소드 종료로 실행됩니다.
			}
			
			//whlle() 
			//if (stuno.equals(ps.getClass().getName())) {
			try (
					PreparedStatement ps = connection.prepareStatement(sql);				
				){	//매개변수의 순서와 형식을 확인하고 전달하는 setXXX메소드 실행합니다.
				ps.setString(1, stuno);
//				ps.setString(2, address);
//				ps.execute();		//.execute는 insert,update,delete,select 모두 실행한다
				int count = ps.executeUpdate();			//리턴값은 반영된 행의 개수 -> 새로운 메소드 써보기
					//.executeUpdate() 는 insert,update,delete를 실행합니다.
				System.out.println("학생 정보 수정 " + count + "건이 완료되었습니다.");
			}catch(SQLException e) {
				System.out.println("데이터 수정에 문제가 생겼습니다." + e.getMessage());
			}		
		sc.close();
//		위의 수정된 코드에서는 ps.close(); 
//		문장이 try 블록에 포함되어 PreparedStatement 리소스가 자동으로 해제됩니다.
}}
