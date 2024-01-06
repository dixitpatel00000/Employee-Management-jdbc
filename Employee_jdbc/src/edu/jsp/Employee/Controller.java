package edu.jsp.Employee;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import edu.jsp.connectionPool.ConnectionPool;

public class Controller {

	public int insert(Employee... e) {
		try {
			int count = 0;
			Connection con = ConnectionPool.getConnection();
			String query = "insert into emp(empid,empname,empemail,empsal) values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			for (Employee emp : e) {

				ps.setInt(1, emp.getId());
				ps.setString(2, emp.getName());
				ps.setString(3, emp.getEmail());
				ps.setInt(4, emp.getSalary());
				ps.addBatch();
				count++;
			}

			ps.executeBatch();
			ps.close();
			ConnectionPool.recieveConnection(con);
			return count;

		} catch (Exception ex) {

			ex.printStackTrace();
			return -1;
		}

	}

	public ResultSet display(int key) {
		ResultSet set = null;
		String query = null;
		try {
			Connection con = ConnectionPool.getConnection();
			switch (key) {
			case 1: {
				query = "select * from emp order by empid";
				break;
			}
			case 2: {
				query = "select * from emp order by empsal";
				break;
			}
			case 3: {
				query = "select * from emp order by empname";
				break;
			}

			}

			PreparedStatement ps = con.prepareStatement(query);

			set = ps.executeQuery();

			ConnectionPool.recieveConnection(con);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return set;

	}

	public ResultSet search(int id) {
		ResultSet set = null;
		try {
			Connection con = ConnectionPool.getConnection();
			String query = "select * from emp where empid=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			set = ps.executeQuery();

			ConnectionPool.recieveConnection(con);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return set;

	}

	public boolean delete(int id) {
		try {
			Connection con = ConnectionPool.getConnection();
			String query = "delete from emp where empid=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ps.executeUpdate();

			ConnectionPool.recieveConnection(con);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public boolean deleteAll() {
		try {
			Connection con = ConnectionPool.getConnection();
			String query = "delete from emp";

			PreparedStatement ps = con.prepareStatement(query);

			ps.executeUpdate();

			ConnectionPool.recieveConnection(con);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public boolean update(int id, Employee e) {
		try {
			Connection con = ConnectionPool.getConnection();
			String query = "update emp set empid=?,empname=?,empemail=?,empsal=? where empid=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getEmail());
			ps.setInt(4, e.getSalary());
			ps.setInt(5, id);

			ps.executeUpdate();

			ConnectionPool.recieveConnection(con);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public int insertbycall(Employee... e) {

		try {
			Connection con = ConnectionPool.getConnection();
			String sql = "call emp_insert(?,?,?,?)";
			CallableStatement stmt = con.prepareCall(sql);
			int count = 0;
			for (Employee emp : e) {

				stmt.setInt(1, emp.getId());
				stmt.setString(2, emp.getName());
				stmt.setString(3, emp.getEmail());
				stmt.setInt(4, emp.getSalary());
				count += stmt.executeUpdate();

			}

			stmt.close();
			ConnectionPool.recieveConnection(con);
			return count;

		} catch (

		Exception ex) {
			ex.printStackTrace();
			return -1;
		}

	}

	public void countsal(int sal) {
		try {
			Connection con = ConnectionPool.getConnection();
			String query = "select count_by_sal(?)";

			CallableStatement ps = con.prepareCall(query);
			ps.setInt(1, sal);

			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			System.out.println(count);

			ConnectionPool.recieveConnection(con);

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}
}
