package com.johnbryce.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	public static void createDB() {
		
		
		Connection con = ConnectionPool.getConnection();
		
		try {
			
			Statement stmt = con.createStatement();
			
			String companiesQuery = "create table if not exists "
									+ "COMPANIES (ID int PRIMARY KEY AUTO_INCREMENT,"
									+ "NAME varchar(255), "
									+ "EMAIL varchar(255), "
									+ "PASSWORD varchar(255))";	
						
			String customersQuery = "create table if not exists "
									+ "CUSTOMERS (ID int PRIMARY KEY AUTO_INCREMENT,"
									+ "FIRST_NAME varchar(255),"
									+ "LAST_NAME varchar(255),"
									+ "EMAIL varchar(255),"
									+ "PASSWORD varchar(255))";	
			
			String categoriesQuery = "create table if not exists "
									+ "CATEGORIES (ID int PRIMARY KEY AUTO_INCREMENT,"
									+ "NAME varchar(255))";
								
			String couponsQuery = "create table if not exists "
									+ "COUPONS (ID int PRIMARY KEY AUTO_INCREMENT,"
									+ "COMPANY_ID int, FOREIGN KEY (COMPANY_ID) REFERENCES COMPANIES (ID),"
									+ "CATEGORY_ID int, FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORIES (ID),"
									+ "TITLE varchar(255),"
									+ "DESCRIPTION varchar(255),"
									+ "START_DATE date,"
									+ "END_DATE date,"
									+ "AMOUNT Integer,"
									+ "PRICE double,"
									+ "IMAGE varchar(255))";
			
			//WHY DOESN'T IT WORK WITH THE PRIMARY KEY? COULDN'T FIGURE IT OUT
			
			String customersVScoupons = "create table if not exists "
									+ "CUSTOMERS_VS_COUPONS (CUSTOMER_ID int PRIMARY KEY,"
									+ "FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (ID),"
									+ "COUPON_ID int PRIMARY KEY,"
									+ "FOREIGN KEY (COUPON_ID) REFERENCES COUPONS (ID))";
					
			
			stmt.execute(companiesQuery);
			stmt.execute(customersQuery);
			stmt.execute(categoriesQuery);
			stmt.execute(couponsQuery);
			stmt.execute(customersVScoupons);	
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
