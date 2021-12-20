package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.johnbryce.beans.Company;
import com.johnbryce.dao.CompaniesDAO;
import com.johnbryce.database.ConnectionPool;


public class CompaniesDBDAO implements CompaniesDAO {

	private ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public boolean isCompanyExists(String email, String password) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from companies where email=? and password=?");
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
	public void addCompany(Company company) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con
					.prepareStatement("insert into companies (id, name, email, password) values(?, ?, ?, ?)");
			pstmt.setInt(1, company.getId());
			pstmt.setString(2, company.getName());
			pstmt.setString(3, company.getEmail());
			pstmt.setString(4, company.getPassword());
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);
		}

	}

	@Override
	public void updateCompany(Company company) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con
					.prepareStatement("update companies set (name, email, password) values(?, ?, ?) where id=?");
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.setInt(4, company.getId());
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			cp.restoreConnection(con);
		}

	}

	@Override
	public void deleteCompany(int companyID) {
		Connection con = cp.getConnection();

		try {

			PreparedStatement pstmt = con.prepareStatement("delete from companies where id=?");
			pstmt.setInt(1, companyID);
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			cp.restoreConnection(con);

		}

	}

	@Override
	public ArrayList<Company> getAllCompanies() {
		Connection con = cp.getConnection();

		ArrayList<Company> companies = new ArrayList<Company>();

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from companies");
			ResultSet rset = pstmt.executeQuery();

			while (rset.next()) {
				companies.add(
						new Company(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), null));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);

		}

		return companies;
	}

	@Override
	public Company getOneCompany(int companyID) {
		Connection con = cp.getConnection();

		Company company = null;

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from companies where id=?");
			pstmt.setInt(1, companyID);
			ResultSet rset = pstmt.executeQuery();

			if (rset.next()) {
				company = new Company(rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4), null);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);

		}

		return company;
	}

	@Override
	public int getCompanyId(String email, String password) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from companies where email=? and password=?");
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

