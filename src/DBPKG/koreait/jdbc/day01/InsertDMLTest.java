package koreait.jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
// 학생 성적처리 프로그램 table 중에 새로운 학생을 등록(입력) 하는 기능을 만들어 봅시다.
//(테이블에 insert 실행)
public class InsertDMLTest {

	public static void main(String[] args) {

		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";		
	
		try(
				Connection conn = DriverManager.getConnection(url,user,password);
			){
			System.out.println("연결 상태 =" + conn);
			if(conn!=null) {
				System.out.println("오라클 데이터베이스 연결 성공!!");
			}
			else
				System.out.println("오라클 데이터베이스 연결 실패!!");
			//db연결 완료 후에 sql 실행하기.
			
			
			//insert SQL 작성 : 제약조건(기본키 stuno)위반 되지 않는 값으로 입력하기.
			String sql ="insert into TBL_STUDENT values ('2023003','김땡유',10,'갱기도')";		//insert SQL 작성
			
			
			//PreparedStatement 객체를 생성하면서 실행할 Sql을 설정합니다.
			
			//PreparedStatement 객체는 Connection 객체 메소드로 만듭니다.Connection 구현객체는 dbms 종류에 따라
			//생성되고 PreparedStatement 객체도 그에 따라 구현 객체가 결정됩니다.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt 객체의 구현 클래스 : " + pstmt.getClass().getName());
			
			
			//PreparedStatement() 메소드는 객체를 생성하면서 실행할 sql을 설정합니다.
		
			
			
			pstmt.execute();
			pstmt.close();
			
			
			
		}catch(Exception e) {
			System.out.println("ClassNotFoundExceptoin = 드라이버 경로가 잘못됐습니다.");
			System.out.println("SQLException = url 또는 user 또는 패스워드가 잘못됐습니다");
			System.out.println("오류 메시지 풀" + e.getMessage());
			e.printStackTrace();	//Exception 발생의 모든 원일을 cascade 형식으로 출력
		}
	}
}
/*
 * Statement 인터페이스는 sql 쿼리 처리와 과련된 방법을 정의합니다.
 * Statement 인터페이스의 객체는 SQL 쿼리문을 데이터베이스에 전송합니다. Connection 객체를 통해서 만듭니다.
 * 
 * PreparedStatement는 Statement의 자식 인터페이스.
 * 특징은 sql을 먼저 컴파일하고 sql 실행에 필요한 값은 실행할 때 매개변수로 전달하는 방식입니다.
 * 
 * 
 * 
 * */
 
 
 
 
 
 
 