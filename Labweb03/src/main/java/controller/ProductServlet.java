package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductService;

@WebServlet("/pages/product.controller")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    String type = request.getParameter("prodaction");
		Map<String, String> errorMsg = new HashMap<>();
		request.setAttribute("ErrorMsg", errorMsg);
		// 1.接收form資料
		request.setCharacterEncoding("UTF-8");
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		String makeStr = request.getParameter("make");
		String expireStr = request.getParameter("expire");
		
		System.out.println(request.getMethod());
		System.out.println("id="+idStr+",name="+name);

		// 2.轉換資料型態
		int id = 0;
		if (idStr != null && idStr.trim().length() != 0) {
			try {
				id = Integer.parseInt(idStr.trim());
			} catch (NumberFormatException e2) {
				errorMsg.put("id", "ID格式錯誤，應為整數");
			}
		}
		double price = 0;
		if (priceStr != null && priceStr.trim().length() != 0) {
			try {
				price = Double.parseDouble(priceStr);
			} catch (NumberFormatException e1) {
				errorMsg.put("price", "Price格式錯誤，應為數字");
			}
		}
		java.util.Date make = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (makeStr != null && makeStr.trim().length() != 0) {
			try {
				make = df.parse(makeStr);
			} catch (Exception e) {
				errorMsg.put("make", "Make格式錯誤，應該為YYYY-MM-DD");
			}
		}
		int expire = 0;
		if (expireStr != null && expireStr.trim().length() != 0) {
			try {
				expire = Integer.parseInt(expireStr);
			} catch (NumberFormatException e) {
				errorMsg.put("expire", "Expire格式錯誤，應為整數");
			}
		}

		// 3.驗證資料
		if ("Insert".equals(type) || "Update".equals(type)
				|| "Delete".equals(type)) {
			if (idStr == null || idStr.trim().length() == 0) {
				errorMsg.put("id", "請輸入id以利執行" + type);
			}
		}

		if (!errorMsg.isEmpty()) {
			request.getRequestDispatcher("/pages/product.jsp").forward(request,
					response);
			return;
		}

		// 4.呼叫Model
		ProductBean pb = new ProductBean();
		pb.setId(id);
		pb.setName(name);
		pb.setPrice(price);
		pb.setMake(make);
		pb.setExpire(expire);
		ProductService ps = new ProductService();
		if ("Select".equals(type)) {
			List<ProductBean> beans = ps.select(pb);
			request.setAttribute("select", beans);
			request.getRequestDispatcher("/pages/display.jsp").forward(request,
					response);
		} else if ("Insert".equals(type)) {
			if (ps.insert(pb) != null) {
				request.setAttribute("success", type + "成功");
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
			} else {
				request.setAttribute("exception", "id已存在，請重新輸入");
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
			}
		} else if ("Update".equals(type)) {
			if (ps.update(pb) != null) {
				request.setAttribute("success", type + "成功");
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
			} else {
				request.setAttribute("exception", "id已存在，請重新輸入");
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
			}
		} else if ("Delete".equals(type)) {
			if (ps.delete(pb)) {
				request.setAttribute("success", type + "成功");
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
			} else {
				request.setAttribute("exception", "查無此id，請重新輸入");
				request.getRequestDispatcher("/pages/product.jsp").forward(
						request, response);
			}
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
