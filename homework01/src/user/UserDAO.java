package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {

	// �ʵ����-Ŀ�ؼ� Ǯ
	

	// constructor
	public UserDAO() {

	}

	public void addUser(UserVO userVO) {// uservo���� �����;� �ϴϱ�

		Connection con = null;
		PreparedStatement pStmt = null;
		ResultSet rs = null;// ���� �˾Ƽ� �ص� ��������� ������ �������� �� �������

		try {

			// 1. connection

			// class.forname, Driver ����-Ŀ�ؼ�Ǯ

			// 1��° ���
			con=OracleConnectionPool.getInstance().getConnection();
			// �ν��Ͻ��� �޴� ���
					
			// 2��° ��� datasource�� �̿��ؼ� Ŀ�ؼ� �޴� ���

			//InitialContext ic = new InitialContext();
			//DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/ora");

			//con = ds.getConnection();

			// 2. statement
            
			StringBuffer QueryStr=new StringBuffer();
			QueryStr.append("insert into uservo values(?,?,?,?,?)");
			
			
			pStmt = con.prepareStatement(QueryStr.toString());
			pStmt.setString(1, userVO.getName());// uservo Ŭ���̾�Ʈ ���� ��������
			pStmt.setString(2, userVO.getSex());
			pStmt.setString(3, userVO.getBirth());
			pStmt.setString(4, userVO.getPhone());
			pStmt.setString(5, userVO.getAddress());

			// 3. result set

			rs = pStmt.executeQuery();

			// 4. db���� Ȯ���� ���ǹ�

			if (rs.next()) {
				System.out.println("db���� Ȯ���� name" + userVO.getName() + "sex" + userVO.getSex() + "birth"
						+ userVO.getBirth() + "phone" + userVO.getPhone() + "address" + userVO.getAddress());

				userVO.setActive(true);// �����Ͱ� �� ��������...
			} else {
				System.out.println("db����" + userVO.getName() + userVO.getSex() + userVO.getBirth() + userVO.getPhone()
						+ userVO.getAddress() + "�� �ش��ϴ� �ڷᰡ �����ϴ�.");
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
					con.close();// �ݴ°� �ƴ϶� �ݳ��� �ǹ�
				} catch (Exception e3) {
				}
			} // con

		}
	}
}