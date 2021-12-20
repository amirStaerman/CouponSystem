package com.johnbryce.facade;

import java.util.ArrayList;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Customer;

public class AdminFacade extends ClientFacade {

	@Override
	public boolean login(String email, String password) {

		if (email.equals("admin@admin.com") && password.equals("admin")) {
			return true;

		} else {
			// throw exception
		}
		return false;
	}

	public void addCompany(Company company) {

		ArrayList<Company> companies = companiesDAO.getAllCompanies();

		for (int i = 0; i < companies.size(); i++) {
			if (companies.get(i).getName().equals(company.getName())
					|| companies.get(i).getEmail().equals(company.getEmail())) {
				// throw exception this company already exists
			} else {
				companiesDAO.addCompany(company);
			}
		}

	}

	public void updateCompany(Company company) {

		ArrayList<Company> companies = companiesDAO.getAllCompanies();

		for (int i = 0; i < companies.size(); i++) {
			if (companies.get(i).getName().equals(company.getName())
					|| companies.get(i).getEmail().equals(company.getEmail())) {
				// throw exception this company already exists
			} else {
				companiesDAO.updateCompany(company);
			}
		}

	}

	public void deleteCompany(int companyID) {

	}

	public ArrayList<Company> getAllCompanies() {

		ArrayList<Company> companies = companiesDAO.getAllCompanies();

		return companies;

	}

	public Company getOneCompany(int companyID) {

		return companiesDAO.getOneCompany(companyID);
		// throw exception if ID doesn't exist

	}

	public void addCustomer(Customer customer) {

		ArrayList<Customer> customers = customersDAO.getAllCustomers();

		for (Customer c : customers) {
			if (c.getEmail().equals(customer.getEmail())) {
				// throw this user already exists exception
			} else {
				customersDAO.addCustomer(customer);
			}
		}

	}

	public void updateCustomer(Customer customer) {

		ArrayList<Customer> customers = customersDAO.getAllCustomers();

		for (Customer c : customers) {
			if (c.getEmail().equals(customer.getEmail())) {
				// throw this user already exists exception
			} else {
				customersDAO.updateCustomer(customer);
			}
		}

	}

	public void deleteCustomer(int customerID) {

		customersDAO.deleteCustomer(customerID);
		// throw exception of ID doesn't exist

		// delete history of purchases as well

	}

	public ArrayList<Customer> getAllCustomers() {

		ArrayList<Customer> customers = customersDAO.getAllCustomers();

		return customers;

	}

	public Customer getOneCustomer(int customerID) {

		Customer customer = customersDAO.getOneCustomer(customerID);
		// throw user doesn't exist exception

		return customer;

	}

}

