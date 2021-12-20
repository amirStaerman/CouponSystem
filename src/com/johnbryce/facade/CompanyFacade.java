package com.johnbryce.facade;

import java.util.ArrayList;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.enums.Category;

public class CompanyFacade extends ClientFacade {

	private int companyID;
	
	// Am I supposed to enter the login method into a constructor here? 
	// According to the UML, I am supposed to create a constructor in the classes that extend ClientFacade, although ClientFacade doesn't have a constructor.
	// Otherwise, I can't see what prevents anyone from access to everything.

	@Override
	public boolean login(String email, String password) {

		companyID = companiesDAO.getCompanyId(email, password);

		// why would the ID be lower than 1 if it's auto incremented?

		if (companyID < 1) {
			return false;
		}
		return true;
	}

	// Let's say a company has signed in. What's preventing one company (Coca Cola)
	// from adding/updating/deleting a different company's coupons? (Pepsi)
	// They can still go to the deleteCoupon method and throw some random coupon ID
	// numbers that may exist in the database. Am I wrong?
	//

	public void addCoupon(Coupon coupon) {
		Company company = companiesDAO.getOneCompany(companyID);

		for (Coupon coupons : company.getCoupons()) {
			if (coupon.getTitle().equals(coupons.getTitle())) {
				// throw exception
			} else {
				couponsDAO.addCoupon(coupon);
			}

		}

	}

	public void updateCoupon(Coupon coupon) {
		Company company = companiesDAO.getOneCompany(companyID);

		for (Coupon coupons : company.getCoupons()) {
			if (coupon.getTitle().equals(coupons.getTitle())) {
				// throw exception
			} else {
				couponsDAO.updateCoupon(coupon);
			}

		}

	}

	public void deleteCoupon(int couponID) {
		couponsDAO.deleteCoupon(couponID);
		// throw exceptions in case ID doesn't exist

		// delete the purchase history as well

	}

	public ArrayList<Coupon> getCompanyCoupons() {
		Company company = companiesDAO.getOneCompany(companyID);

		ArrayList<Coupon> coupons = company.getCoupons();

		ArrayList<Coupon> coupons1 = couponsDAO.getAllCoupons();

		coupons1.stream().filter(c -> c.getCompanyID() != companyID); // is this correct? googled it...

		return coupons;

		// In case I want to use the method you used in class, what is supposed to
		// replace the "item" here? couldn't get it.

		// ArrayList<Coupon> coupons = coupons.getAllCoupons()
		// coupons.stream().filter(item.getCompanyId() != companyID)
	}

	public ArrayList<Coupon> getCompanyCoupons(Category category) {

		// This is what we did in class. How is this going to show only the coupons of
		// the company that has signed in?
		ArrayList<Coupon> coupons1 = couponsDAO.getAllCoupons();
		coupons1.stream().filter(c -> c.getCategory() != category);

		// this is how I thought of doing it...?

		Company company = companiesDAO.getOneCompany(companyID);
		ArrayList<Coupon> coupons = company.getCoupons();
		coupons.stream().filter(c -> c.getCategory() != category);

		return coupons;

	}

	public ArrayList<Coupon> getCompanyCoupons(double maxPrice) {
		Company company = companiesDAO.getOneCompany(companyID);

		ArrayList<Coupon> coupons = company.getCoupons();
		coupons.stream().filter(c -> c.getPrice() <= maxPrice);

		return coupons;

	}

	public Company getCompanyDetails() {
		Company company = companiesDAO.getOneCompany(companyID);
		
		// The only reason I can think of a company being null, is if someone got lazy and created it with an empty constructor.
		// if there another reason that I can't think of?

		if (company == null) {
			// throw exception
		}

		return company;
	}

}
