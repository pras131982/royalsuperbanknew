package sample.bank.data;

import java.util.HashMap;

import sample.bank.bean.CustomerBean;

public class Customers {

	private HashMap customers = new HashMap();

	public void addCustomers(String userId, CustomerBean customer) {
		try {
			customers.put(userId, customer);
			new CustomerMongoDB().addRecord(userId, customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean newUserId(String userId) {
		if (customers.containsKey(userId)) {
			System.out.println("contains yaar");
			return false;

		} else {

			CustomerBean cb = new CustomerMongoDB().getRecord(userId);
			if (cb != null) {
				return false;
			} else {
				System.out.println("doesnt contain");
				return true;
			}
		}
	}

	public CustomerBean getCustomers(String userId, String password) {
		CustomerBean customer;
		if ((customer = (CustomerBean) customers.get(userId)) != null) {

			if (customer.getPassword().equals(password)) {
				return customer;
			}
		} else {
			CustomerBean cb = new CustomerMongoDB().getRecord(userId);
			if (cb != null && cb.getPassword().equals(password)) {
				return cb;
			}

		}
		return null;
	}
}