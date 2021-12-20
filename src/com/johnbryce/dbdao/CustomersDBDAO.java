package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.johnbryce.beans.Customer;
import com.johnbryce.dao.CustomersDAO;
import com.johnbryce.database.ConnectionPool;

public class CustomersDBDAO implements CustomersDAO {

	private ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExists(String email, String password) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from customers where email=? and password=?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rset = pstmt.executeQuery();

			return rset.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);
		}

		return false;
	}

	@Override
	public void addCustomer(Customer customer) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement(
					"insert into customers (id, first_name, last_name, email, password) values(?, ?, ?, ?, ?)");
			pstmt.setInt(1, customer.getId());
			pstmt.setString(2, customer.getFirstName());
			pstmt.setString(3, customer.getLastName());
			pstmt.setString(4, customer.getEmail());
			pstmt.setString(5, customer.getPassword());
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);
		}

	}

	@Override
	public void updateCustomer(Customer customer) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement(
					"update customers set (first_name, last_name, email, password) values(?, ?, ?, ?) where id=?");
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.setInt(5, customer.getId());
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);
		}
	}

	@Override
	public void deleteCustomer(int cusomterID) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement("delete from customers where id=?");
			pstmt.setInt(1, cusomterID);
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);

		}
	}

	@Override
	public ArrayList<Customer> getAllCustomers() {
		Connection con = cp.getConnection();

		ArrayList<Customer> customers = new ArrayList<Customer>();

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from customers");
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				customers.add(new Customer(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), null));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);
		}
		return customers;
	}

	@Override
	public Customer getOneCustomer(int customerID) {
		Connection con = cp.getConnection();

		Customer customer = null;

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from custeomrs where id=?");
			pstmt.setInt(1, customerID);
			ResultSet rset = pstmt.executeQuery();

			if (rset.next()) {
				customer = new Customer(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), null);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);

		}
		return customer;
	}

	@Override
	public int getCustomerId(String email, String password) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from customers where email=? and password=?");
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rset = pstmt.executeQuery();

			if (rset.next()) {
				return rset.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			cp.restoreConnection(con);
		}

		return -99;
	}

}

