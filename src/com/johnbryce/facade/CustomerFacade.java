package com.johnbryce.facade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.beans.Customer;
import com.johnbryce.enums.Category;

public class CustomerFacade extends ClientFacade {

	private int customerID;

	@Override
	public boolean login(String email, String password) {

		customerID = customersDAO.getCustomerId(email, password);

		if (customerID < 1) {
			return false;
		}

		return true;
	}

	public void purchaseCoupon(Coupon coupon) {

		LocalDateTime now = LocalDateTime.now(); // is this correct?

		Customer customer = customersDAO.getOneCustomer(customerID);

		ArrayList<Coupon> customerCoupons = customer.getCoupons();

		// Can you please refer me to google to look for a way to create several
		// conditions in one "if" and still being able to throw the relevant exception?
		// Thanks!

		if (customerCoupons.contains(coupon)) {
			// throw coupon has already been purchased exception
		} else if (coupon.getAmount() < 1) {
			// throw out of stock exception

			// Where can I solve this? can I use Calendar instead?
		} else if (coupon.getEndDate().before(null)) {
			// throw coupon has expired exception
		} else {
			customerCoupons.add(coupon);
			// how come coupon.getAmount()-- doens't work?
			coupon.setAmount(coupon.getAmount() - 1);
		}

	}

	public ArrayList<Coupon> getCustomerCoupons() {

		Customer customer = customersDAO.getOneCustomer(customerID);

		if (customer.getCoupons().isEmpty()) {
			System.out.println("There are no current coupons");
		}

		return customer.getCoupons();

	}

	public ArrayList<Coupon> getCustomerCoupons(Category category) {

		Customer customer = customersDAO.getOneCustomer(customerID);

		ArrayList<Coupon> customerCoupons = customer.getCoupons();
		customerCoupons.stream().filter(c -> c.getCategory() != category);

		return customerCoupons;

	}

	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) {

		Customer customer = customersDAO.getOneCustomer(customerID);

		ArrayList<Coupon> customerCoupons = customer.getCoupons();
		customerCoupons.stream().filter(c -> c.getPrice() <= maxPrice);

		return customerCoupons;
	}

	public Customer getCustomerDetails() {

		Customer customer = customersDAO.getOneCustomer(customerID);

		if (customer == null) {
			// throw exception
		}

		return customer;
	}
}

