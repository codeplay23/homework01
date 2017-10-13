package user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserDAO;
import user.UserVO;

public class AddUser extends HttpServlet {//implement

		public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
          req �ѱ� ���ڵ��ϴ°� �� ����� ��
		 res.setContentType("text/html;charset=EUC_KR");// �ѱ�ó��
		 PrintWriter out=res.getWriter();//Ŭ���̾�Ʈ�� �� HTML TEXT�� ������ ���� �޼ҵ�
		                                             //stream�� �����ϰ� �ν���Ʈ �����Ѱ� ĸ��ȭ! API�̿��ؼ� stream ����
		 
		 //Ŭ���̾�Ʈ ���� ��������
		 
		 String name=req.getParameter("name");
		 String sex=req.getParameter("sex");
	     String birth=req.getParameter("year") +req.getParameter("month")+req.getParameter("day");//������������ +�� ����.�Ķ���ʹ� 1���� �ۿ� ������
	     String phone=req.getParameter("number1")+req.getParameter("number2")+req.getParameter("number3");
	     String address=req.getParameter("address");
		 
		 //userVO �ν��Ͻ� ���� �ؼ� Ŭ���̾�Ʈ ���������� dbbean�� ���� 
		 //dependency? �ѹ� ���� ���Ŵϱ�
		 UserVO userVO=new UserVO();
		 userVO.setName(name);
		 userVO.setSex(sex);
	     userVO.setBirth(birth);
	     userVO.setPhone(phone);
	     userVO.setAddress(address);
		 
		 //dbbean�� �޼ҵ� �����ؼ� db�� �ִ� ����Ÿ �˻� ��: web.xml�� ���� �����ֱ�����
         UserDAO userDao=new UserDAO();
         userDao.addUser(userVO);
         
         out.println("<html>");
         out.println("<head></head>");
         out.println("<body>");
         out.println("<h2>���������� ȭ��<h2>");
         
         //HTML�� ȸ������ ����� �ƴ��� �����ִ� ���ǹ�(���ȭ��)
         if(userVO.isActive()){
        	out.println(name+"�� ���ԿϷ�");
       
         }else{
        	 out.println("ȸ������ ����");
        	 out.println("<p><p><a href='/homework01/addUser.html'>�ڷ�</a>");
         }
        
         out.println("</body>");
         out.println("</html>");
         
		}
}

