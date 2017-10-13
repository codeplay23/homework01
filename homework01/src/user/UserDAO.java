package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {

	// 필드생략-커넥션 풀
	

	// constructor
	public UserDAO() {

	}

	public void addUser(UserVO userVO) {// uservo에서 가져와야 하니깐

		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;// 지가 알아서 해도 명시적으로 쓸때는 지역에서 꼭 해줘야함

		try {

			// 1. connection

			// class.forname, Driver 생략-커넥션풀

			// 1번째 방법
			con=OracleConnectionPool.getInstance().getConnection();
			// 인스턴스를 받는 방법
					
			// 2번째 방법 datasource를 이용해서 커넥션 받는 방법

			//InitialContext ic = new InitialContext();
			//DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/ora");

			//con = ds.getConnection();

			// 2. statement
            
			StringBuffer QueryStr=new StringBuffer();
			QueryStr.append("insert into uservo values(?,?,?,?,?)");
			
			
			pStmt = con.prepareStatement(QueryStr.toString());
			pStmt.setString(1, userVO.getName());// uservo 클라이언트 정보 가져오기
			pStmt.setString(2, userVO.getSex());
			pStmt.setString(3, userVO.getBirth());
			pStmt.setString(4, userVO.getPhone());
			pStmt.setString(5, userVO.getAddress());

			// 3. result set

			rs = pStmt.executeQuery();

			// 4. db에서 확인할 조건문

			if (rs.next()) {
				System.out.println("db에서 확인한 name" + userVO.getName() + "sex" + userVO.getSex() + "birth"
						+ userVO.getBirth() + "phone" + userVO.getPhone() + "address" + userVO.getAddress());

				userVO.setActive(true);// 데이터가 다 들어가있으면...
			} else {
				System.out.println("db에서" + userVO.getName() + userVO.getSex() + userVO.getBirth() + userVO.getPhone()
						+ userVO.getAddress() + "에 해당하는 자료가 없습니다.");
			}

			// 5. Exception
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e1) {
				}
			} // rs

			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (Exception e2) {
				}
			} // pStmt

			if (con != null) {
				try {
					con.close();// 닫는게 아니라 반납의 의미
				} catch (Exception e3) {
				}
			} // con

		}
	}
}