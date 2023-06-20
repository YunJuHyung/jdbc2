package DBPKG;
import java.util.Date;
import java.util.List;

import koreait.jdbc.day04.MyPageBuy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
	int custno;
	String custname;
	String phone;
	String address;
	Date joindate;
	String grade;
	String city;


//select * from mypage_buy where customid = 'twice';
	public List<LoginDto> LoginDto(List<LoginDto> custno){
		return null;
	}
//	//select sum(total) from mypage_buy where customid='twice';
//	public long myMoney(String customid) {
//		return 0;
//	}
}