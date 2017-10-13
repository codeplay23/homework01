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
		
          req 한글 인코딩하는거 꼭 써줘야 함
		 res.setContentType("text/html;charset=EUC_KR");// 한글처리
		 PrintWriter out=res.getWriter();//클라이언트가 볼 HTML TEXT에 전송을 위한 메소드
		                                             //stream을 생성하고 인스턴트 생성한걸 캡슐화! API이용해서 stream 생성
		 
		 //클라이언트 정보 가져오기
		 
		 String name=req.getParameter("name");
		 String sex=req.getParameter("sex");
	     String birth=req.getParameter("year") +req.getParameter("month")+req.getParameter("day");//여러개받을때 +로 연결.파라미터는 1개씩 밖에 못받음
	     String phone=req.getParameter("number1")+req.getParameter("number2")+req.getParameter("number3");
	     String address=req.getParameter("address");
		 
		 //userVO 인스턴스 생성 해서 클라이언트 정보묶음을 dbbean에 전달 
		 //dependency? 한번 쓰고 말거니깐
		 UserVO userVO=new UserVO();
		 userVO.setName(name);
		 userVO.setSex(sex);
	     userVO.setBirth(birth);
	     userVO.setPhone(phone);
	     userVO.setAddress(address);
		 
		 //dbbean의 메소드 접근해서 db에 있는 데이타 검색 비교: web.xml에 정보 보내주기위해
         UserDAO userDao=new UserDAO();
         userDao.addUser(userVO);
         
         out.println("<html>");
         out.println("<head></head>");
         out.println("<body>");
         out.println("<h2>내정보보기 화면<h2>");
         
         //HTML에 회원가입 제대로 됐는지 보여주는 조건문(결과화면)
         if(userVO.isActive()){
        	out.println(name+"님 가입완료");
       
         }else{
        	 out.println("회원가입 실패");
        	 out.println("<p><p><a href='/homework01/addUser.html'>뒤로</a>");
         }
        
         out.println("</body>");
         out.println("</html>");
         
		}
}

