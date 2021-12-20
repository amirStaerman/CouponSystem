package com.johnbryce.dao;

import java.util.ArrayList;

import com.johnbryce.beans.Customer;

public interface CustomersDAO {

	public boolean isCustomerExists(String email, String password);

	public void addCustomer(Customer customer);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(int cusomterID);

	public ArrayList<Customer> getAllCustomers();

	public Customer getOneCustomer(int customerID);

	public int getCustomerId(String email, String password);

}
