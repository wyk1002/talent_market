package biubiu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController
{
	@RequestMapping(value = "/login", method = { RequestMethod.POST, RequestMethod.GET })
	public String login(HttpServletRequest request, HttpSession session)
	{
		if (request.getParameter("username").compareTo("123")!=0||request.getParameter("password").compareTo("123")!=0)
		{
			session.setAttribute("wronglogin",true);
			return "login";
		}
		return "main";
	}
}
