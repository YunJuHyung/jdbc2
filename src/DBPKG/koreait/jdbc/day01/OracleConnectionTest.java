package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
//다른 DBMS 클라이언트 프로그램과 같이 DB를 사용할 수 있는 동작을 구현.
//제일 먼저 해야 하는 것은 `데이터베이스 연결` 입니다.
public class OracleConnectionTest {

	public static void main(String[] args) {
		//0. COnnetcion은 인터페이스로 직접 객체를 생성하지 않고
		//구현 클래스
		
		//1. 아래 4개의 필수 연결 정보를 설정합니다.
		Connection conn = null;
		//접속하고자 하는 서버의 주소
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		//oracle.jdbc.driver.OracleDriver ojdbc6.jar 에 포함된 패키지이름
		//OracleDriver 는 오라클 드라이버 클래스 이름
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";
		
		try {
			
			//2. 드라이버 클래스를 메모리에 로드(올리기)
			//     ㄴ 연결 객체를 만들어주는 역할을 하나.
			//		ㄴClass.forName(driver);
			//이때 2번에서 만든 객체 즉 dbms 에 다라 구현객체가 만들어집니다.
				Class.forName(driver);
			
			//3. DriverManager 클래스는 연결객체를 만듭니다. -2번의 객체를 동작시킵니다.
			//	이때 2번에서 만듣 객체 즉 dbms 에 따라 구현객체가 만들어집니다.
				conn = DriverManager.getConnection(url,user,password);
			
			//4. 3번의 결과로 오라클 db에 맞는 연결객체가 생성됩니다.
			System.out.println("연결 상태 =" + conn);
			if(conn!=null) {
				System.out.println("오라클 데이터베이스 연결 성공!!");
			}
			else
				System.out.println("오라클 데이터베이스 연결 실패!!");
			
		}catch(Exception e) {
			System.out.println("ClassNotFoundExceptoin = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 패스워드가 잘못됐습니다");
			System.out.println("오류 메시지 풀" + e.getMessage());
			e.printStackTrace();	//Exception 발생의 모든 원일을 cascade 형식으로 출력
		}finally {
			try {
				if(conn != null)
					conn.close();
			}catch(Exception e) {}
		}//명시적으로 자원을 close => 실행안해도 오류는 안납니다.
	}
}

/*
 * API: Apllication Programing Interface
 * 		: 서로 다른 소프트웨어 시스템 간의 연결을 위한 방식(라이브러리로 제공)
 * 
 * 라이브러리 : 자바 라이브러리와 같이 특정 기능을 제공하는 클래스들의 집합. 확장자는 압축형태.jar
 * 
 * jdbc : 자바와 dbms를 연결하는 api. 오라클은 ojdbcX.jar 이고 X는 오라클 jdbc 버전 표시.
 * 
 * */
