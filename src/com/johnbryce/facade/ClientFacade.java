package com.johnbryce.facade;

import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.dbdao.CompaniesDBDAO;
import com.johnbryce.dbdao.CouponsDBDAO;
import com.johnbryce.dbdao.CustomersDBDAO;

public abstract class ClientFacade {

	protected CompaniesDAO companiesDAO = new CompaniesDBDAO();
	protected CustomersDAO customersDAO = new CustomersDBDAO();
	protected CouponsDAO couponsDAO = new CouponsDBDAO();

	protected abstract boolean login(String email, String password);

}
