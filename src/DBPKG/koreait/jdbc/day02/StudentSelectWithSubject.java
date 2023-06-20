package koreait.jdbc.day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectWithSubject {
	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println("::::::::성적 조회메뉴 ::::::::");
		System.out.println("과목명을 입력하여 조회 합니다.");
		//selectManyScore(conn);
		ScoreSelectWithSubject(conn);
		

		OracleUtility.close(conn);

	}

	private static void selectManyScore(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String sql = "select stuno,jumsu,term,teacher \r\n"
				+ "from TBL_SCORE "
				+ "where subject = ?";
		System.out.println("조회할 과목명 입력 >>>>");
		String subject = sc.nextLine();
		int count = 0;
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, subject);

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				count++;
				System.out.println(String.format("%10s %10d %10s %20s",
						rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4)
						));
				System.out.println("\t몇번째 학생인가요?" + count);

			}
		} catch (SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다." + e.getMessage());
		}
		sc.close();
	}
	private static void selectCount(Connection conn,String subject) {
		String sql ="select count(*) \r\n"
				+ "from TBL_SCORE \r\n"
				+ "where subject = ?";
		//count 와  같은 함수 결과는 행 1개, 컬럼 1개
		try (
				PreparedStatement ps = conn.prepareStatement(sql);
			){
			ps.setString(1, subject);
			
			ResultSet rs = ps.executeQuery();
			int count = 0;
			if(rs.next()) {
				count = rs.getInt(1);
			}
			//참고 : 입력한 과목의 건(행) 수를 조회할 수 있습니다,.
			System.out.println("과목 << " + subject + " >> " + count + "건이 조회되었습니다." );
	}catch(SQLException e) {
		System.out.println("데이터 조회에 문제가 생겼습니다." + e.getMessage());
	}
}

private static void ScoreSelectWithSubject(Connection conn) {
	Scanner sc = new Scanner(System.in);
	String sql = "select * from TBL_SCORE where subject = ? and jumsu > ?";
	System.out.println("조회할 과목 입력 >>>>");
	String subject = sc.nextLine();
	System.out.println("몇 점 초과로 조회할까요? >>>>");
	int jumsu = sc.nextInt();
	
	try (
			PreparedStatement ps = conn.prepareStatement(sql);
		){
		ps.setString(1, subject);
		ps.setInt(2, jumsu);
		int count = 0;
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			count++;
			System.out.printf("학번 : " + rs.getString(1));
			System.out.printf("\t과목 : " + rs.getString(2));
			System.out.printf("\t점수 : " + rs.getInt(3));
			System.out.printf("\t강사 : " + rs.getString(4));
			System.out.printf("\t기간 : " + rs.getString(5)+"\n");
			
		}
		System.out.println("몇명이나 있나요?" + count + "명 있네요");
		selectCount(conn, subject);
	}catch(SQLException e) {
		System.out.println(e);
		System.out.println("데이터 조회에 문제가 생겼습니다." + e.getMessage());
	}
	sc.close();
		
		
	
}
}
/*
 * 모든 학생 조회하는 StudentSelectAllMenu 클래스 : 1줄에 1개 행을 출력하세요. 과목명을 입력하면 해당 과목을 조회하는
 * ScoreSelectWithSubject 클래스
 */
