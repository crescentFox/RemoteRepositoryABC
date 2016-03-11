package model;

import java.util.Arrays;

import model.dao.CustomerDAOJdbc;

public class CustomerService {
	CustomerDAO cDao = new CustomerDAOJdbc();

	public static void main(String[] arge) {
		CustomerService service = new CustomerService();
		CustomerBean cb = service.login("Alex", "A");
		System.out.println(cb);
		System.out.println(service.changePassword("Ellen", "EEE", "E"));

	}

	public CustomerBean login(String username, String password) {
		CustomerBean bean = cDao.select(username);
		if (bean != null) {
			byte[] pass = bean.getPassword();
			byte[] temp = password.getBytes();
			if (Arrays.equals(pass, temp)) {
				return bean;
			}
		}
		return null;
	}

	public boolean changePassword(String username, String oldPswd,
			String newPswd) {
		CustomerBean bean = this.login(username, oldPswd);
		if (bean != null) {
			if (newPswd != null && newPswd.length() != 0) {
				return cDao.update(newPswd.getBytes(), bean.getEmail(),bean.getBirth(), username);
			}
		}

		return false;
	}

}
