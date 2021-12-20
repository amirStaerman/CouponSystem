package com.johnbryce.login;

import com.johnbryce.enums.ClientType;
import com.johnbryce.facade.AdminFacade;
import com.johnbryce.facade.ClientFacade;
import com.johnbryce.facade.CompanyFacade;
import com.johnbryce.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance;

	private LoginManager() {

	}

	// will it be more correct do do a "switch case" here?
	// In one of the tests, we converted an enum to a String array, however google
	// says this should work?

	public ClientFacade login(String email, String password, ClientType clientType) {

		if (clientType.equals(ClientType.COMPANY)) {
			CompanyFacade companyFacade = new CompanyFacade();

			if (!companyFacade.login(email, password)) {
				// throw exception
			} else {
				return companyFacade;
			}

		} else if (clientType.equals(ClientType.CUSTOMER)) {
			CustomerFacade customerFacade = new CustomerFacade();

			if (!customerFacade.login(email, password)) {
				// throw exception
			} else {
				return customerFacade;
			}
		} else if (clientType.equals(ClientType.ADMINISTRATOR)) {
			AdminFacade adminFacade = new AdminFacade();

			if (!adminFacade.login(email, password)) {
				// throw exception
			} else {
				return adminFacade;
			}
		}

		return null;
	}

	public static LoginManager getInstance() {

		if (instance == null) {
			synchronized (LoginManager.class) {

				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}

}
