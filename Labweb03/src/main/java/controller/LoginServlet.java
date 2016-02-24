package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerService;

@WebServlet("/secure/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMsg);

		// 1.接收form
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("username");
		String pwd = request.getParameter("password");
		// 2.parseInt
		// 3.檢查使用者輸入資料
		if (id == null || id.trim().length() == 0) {
			errorMsg.put("id", "帳號欄不可空白");
		}

		if (pwd == null || pwd.trim().length() == 0) {
			errorMsg.put("password", "密碼欄不可空白");
		}

		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/secure/login.jsp");
			rd.forward(request, response);
			return;
		}
		// 4.business login
		CustomerService cs = new CustomerService();
		String contextPath = getServletContext().getContextPath();
		if (cs.login(id, pwd) != null) {
			response.sendRedirect(response.encodeRedirectURL(contextPath
					+ "/index.jsp"));
			return;
		} else {
			errorMsg.put("exception", "帳號不存在或密碼不正確");
		}

		// 5.顯示view
		if (!errorMsg.isEmpty()) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/secure/login.jsp");
			rd.forward(request, response);
			return;
		}

	}

}
