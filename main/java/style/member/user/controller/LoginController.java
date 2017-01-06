package style.member.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import style.member.user.model.MemberVO;
import style.member.user.service.LoginService;

@Controller
public class LoginController {
	// ���� ����
	private LoginService loginService;

	// ���� ����
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	//�ʱ� ������Ʈ �ּҸ� ġ��
	@RequestMapping("/loginForm.do")
	public String loginForm(){
		return "loginForm";
	}
	
	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public ModelAndView nolog(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if (session == null)
			System.out.println("�������� ���� ���� ����");

		return new ModelAndView("loginForm");
	}

	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(true);

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		/*if(session==null||session.getAttribute("loginID")==null || !session.getAttribute("loginID").equals(id)){
			//�������� ��ΰ��ƴ� ���������� ��û������!
			return new ModelAndView("test");
		}*/
		
		MemberVO member = loginService.getUserInfo(id);

		String db_id = member.getId();
		String db_pass = member.getPassword();

		// ���� �Ķ���� >> �� ����
		Map<String, Object> model = new HashMap<String, Object>();
		// ������ �𵨾� �� >> �Ķ���� ���� ��Ƽ� test������ ���ڿ�
		ModelAndView modelAndView = new ModelAndView();
		
		if (pass.equals(db_pass)) {
			// ������ ������ ���ǰ� : ���̵��ڿ� ȸ�� ��ü>>�ڹ��ڵ忡�� ����������� ����� ��
			session.setAttribute("loginID", member.getId());
			session.setAttribute("member", member);
			
			//�������� ���� �Ķ����
			model.put("id", member.getId());
			model.put("loginCheck", false);
		}else{
			//��й�ȣ ����ġ
			modelAndView.setViewName("loginForm");
			return modelAndView;
		}

		

		modelAndView.addAllObjects(model);
		modelAndView.setViewName("main");

		return modelAndView;

	}
}
