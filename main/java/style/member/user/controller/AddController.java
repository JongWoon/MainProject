package style.member.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import style.member.user.model.MemberVO;
import style.member.user.service.AddService;

@Controller
public class AddController {
	//���� ����
	private AddService addService;
	//���� ����
	public void setAddService(AddService addService) {
		this.addService = addService;
	}
	
	
	@RequestMapping(value="register.do", method=RequestMethod.GET)
	public String setView(){
		return "registerForm";
	}
	
	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public ModelAndView register(HttpServletRequest request, MemberVO memberVo)throws Exception{
	
		//JSP ��ũ��Ʈ�� ������ �����͸� 
		//DB CUSTOMER���̺� ȸ������ ���
		//������������ �α���������
		//ModelAndView �Ϸ���. �ָӴ� //�������̸� ����(loginForm)
		
		request.setCharacterEncoding("UTF-8");
		
		int check = addService.addUserInfo(memberVo);
		
		return null;
		
	}
		
}
