package DBPKG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day02.OracleUtility;
import koreait.jdbc.day04.MyPageBuy;


public class LoginDao {

	public int createId (List<LoginDto> custno) throws Exception{		
		Connection connection = HRD_0616.getConnection();
		String sql = "insert into member_tbl_02 \n"
				+ "(jbuy_seq.nextval,?,?,?,sysdate,?,?)";		//pk조회 : 결과 행 0 또는 1개
		
		int count = 0;
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);	//auto commit 설정 - false
			ps = connection.prepareStatement(sql);
			for(LoginDto b : custno) {
				ps.setInt(1, b.getCustno());
				ps.setString(2, b.getCustname());
				ps.setString(3, b.getPhone());
				ps.setString(4, b.getAddress());
				ps.setString(5, b.getGrade());
				ps.setString(6, b.getCity());
				count += ps.executeUpdate();
			}
			connection.commit(); //커밋		
		}catch(SQLException e) {
			System.out.println("로그인 오류 :" + e.getMessage());
			try {
				connection.rollback();
			}catch(SQLException e1) {
			}
		}
		ps.close();
		connection.close();
		return count;
	}
	
	public List<LoginDto> usercheck(int custno) throws Exception{	//06-19 여기서 부터 시작
		Connection connection = HRD_0616.getConnection();
		String sql = "select * from member_table_02";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, custno);
		ResultSet rs = ps.executeQuery();
		
		List<MyPageBuy> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new LoginDto(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getDate(5),
					rs.getString(6),
					rs.getString(7)));
		}
		ps.close();
		connection.close();
		return list;
	}
}
