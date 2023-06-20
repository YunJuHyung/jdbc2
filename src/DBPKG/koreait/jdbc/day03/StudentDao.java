package koreait.jdbc.day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;

//DAO : Data Access(접근-읽기와 쓰기) Object
//		ㄴ SQL 실행 메소드를 모아 놓은 클래스.

/*
 * StudentDao의 내용을 요약
 * insert,update 는 sql 파라미터에 전달한 데이터의 타입을 dto(원시형 또는 String)
 * delete는
 * delete sql 의 조건절 컬럼이 여러개 일때는 dto가 될 수 있습니다. map도 종종 사용합니다.
 * 
 * insert,update,delete 는 정수 리턴값으로 반영된 행의 개수를 전달.
 * 
 * selectOne : sql 파라미터에 전달할 데이터를 메소드 인자로 함.
 * selectAll : 파라미터 없으며 여러개의 행을 저장할 객체는 List 타입
 * 
 * */
public class StudentDao {


	//나중에 db를 `쉽게 코딩`하기 위한 `프레임워크`를 사용하면 Exception 처리 안해도 됩니다.
	public int insert(StudentDto student) throws SQLException {
		
		Connection connection = OracleUtility.getConnection();
		
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, student.getStuno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getAge());
		ps.setString(4, student.getAddress());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result;
	}
	
	
	public int update(StudentDto student) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "update TBL_STUDENT set age = ? , address = ? where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, student.getAge());
		ps.setString(2, student.getAddress());
		ps.setString(3, student.getStuno());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		
		return result;
	}
	
	
	//delete 메소드는 여러분이 만들어보세요. - 매개변수는 ? 
	public int delete(String stuno) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "delete from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, stuno);		//메소드 인자(매개변수)값을 sql 쿼리에 전달
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		
		return result;
	}
	
	//select 모두 조회
	public List<StudentDto> selectAll() throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_STUDENT";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		List<StudentDto> result = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			//열 인덱스에 해당하는 값을 가져옴
			//가져온 값을 이용하여 객체 생성
			//생성된 객체를 리스트StudentDao에 추가
			//이후 main 클래스에서 실행메소드에 반복문을 통해 출력문 작성
//			String stuno = rs.getString("stuno"); //stuno 열의 값을 가져옴
//			String name = rs.getString("name");
//			int age = rs.getInt("age");
//			String address = rs.getString("Address");
			
			StudentDto std = new StudentDto(rs.getString(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getString(4));
			result.add(std);
			
			
			
		}
		return result; //return null은 오류해결을 위한 코드
	}
	
	//select * from TBL_STUDENT where stuno ='2023002'; 실행을 위한 메소드
	//     ㄴ  selectOne 메소드 정의
	
	
	public StudentDto selectOne(String stuno) throws SQLException{
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, stuno);
		
		ResultSet rs = ps.executeQuery();
		StudentDto result = null;
		if(rs.next()) {
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String address = rs.getString(4);
			result = new StudentDto(stuno, name, age, address);
		}
		return result;
	}
}