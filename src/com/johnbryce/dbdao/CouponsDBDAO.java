package com.johnbryce.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.johnbryce.beans.Company;
import com.johnbryce.beans.Coupon;
import com.johnbryce.dao.CouponsDAO;
import com.johnbryce.database.ConnectionPool;
import com.johnbryce.enums.Category;

public class CouponsDBDAO implements CouponsDAO {

	private ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) {
		Connection con = cp.getConnection();

		try {
			// when creating the sql db, I am entering int as category ID, while creating
			// the Coupon bean,
			// I am entering an ENUM, how is this synchronizing?

			PreparedStatement pstmt = con.prepareStatement(
					"insert into coupons (id, company_id, category_id, title, description, start_date, end_date, amount, price, image) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, coupon.getID());
			pstmt.setInt(2, coupon.getCompanyID());
			pstmt.setString(3, coupon.getCategory().name()); // is this correct?
			pstmt.setString(4, coupon.getTitle());
			pstmt.setString(5, coupon.getDescription());
			pstmt.setDate(6, coupon.getStartDate());
			pstmt.setDate(7, coupon.getEndDate());
			pstmt.setInt(8, coupon.getAmount());
			pstmt.setDouble(9, coupon.getPrice());
			pstmt.setString(10, coupon.getImage());
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement(
					"update coupons set (title, description, start_date, end_date, amount, price, image) values(?, ?, ?, ?, ?, ?, ?) where id=?");

			pstmt.setString(1, coupon.getTitle());
			pstmt.setString(2, coupon.getDescription());
			pstmt.setDate(3, coupon.getStartDate());
			pstmt.setDate(4, coupon.getEndDate());
			pstmt.setInt(5, coupon.getAmount());
			pstmt.setDouble(6, coupon.getPrice());
			pstmt.setString(7, coupon.getImage());
			pstmt.setInt(8, coupon.getID());
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);

		}

	}

	@Override
	public void deleteCoupon(int couponID) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement("delete from coupons where id=?");
			pstmt.setInt(1, couponID);
			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);

		}

	}

	@Override
	public ArrayList<Coupon> getAllCoupons() {
		Connection con = cp.getConnection();

		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from coupons");
			ResultSet rset = pstmt.executeQuery();

			// Trying to get an ENUM. Is getObject and casting correct? or should I convert
			// my enums to string?

			while (rset.next()) {
				coupons.add(new Coupon(rset.getInt(1), rset.getInt(2), (Category) rset.getObject(3), rset.getString(4),
						rset.getString(5), rset.getDate(6), rset.getDate(7), rset.getInt(8), rset.getDouble(9),
						rset.getString(10)));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);

		}

		return coupons;
	}

	@Override
	public Coupon getOneCoupon(int couponID) {
		Connection con = cp.getConnection();

		Coupon coupon = null;

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from coupons where id=?");
			pstmt.setInt(1, couponID);
			ResultSet rset = pstmt.executeQuery();

			if (rset.next()) {
				coupon = new Coupon(rset.getInt(1), rset.getInt(2), (Category) rset.getObject(3), rset.getString(4),
						rset.getString(5), rset.getDate(6), rset.getDate(7), rset.getInt(8), rset.getDouble(9),
						rset.getString(10));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cp.restoreConnection(con);

		}

		return coupon;
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getCouponId(String email, String password) {
		Connection con = cp.getConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement("select * from coupons where email=? and password=?");
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