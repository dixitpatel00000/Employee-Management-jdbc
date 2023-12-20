package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

public class Controller {

	static Connection con;

	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String user = "root";
			String pass = "Wjdxo0703";
			String path = "jdbc:mysql://localhost:3306/employee";

			con = DriverManager.getConnection(path, user, pass);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}

	public boolean insert(Employee e) {
		try {
			Connection con = createConnection();
			String query = "insert into emp(empid,empname,empemail,empsal) values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getEmail());
			ps.setInt(4, e.getSalary());

			ps.executeUpdate();
			return true;

		} catch (Exception ex) {

			ex.printStackTrace();
			return false;
		}

	}

	public ResultSet display(int key) {
		ResultSet set = null;
		String query = null;
		try {
			Connection con = createConnection();
			switch (key) {
			case 1: {
				query = "select * from emp order by empname";
			}
			case 2: {
				query = "select * from emp order by empid";
			}
			case 3: {
				query = "select * from emp order by empsal";
			}

			}

			PreparedStatement ps = con.prepareStatement(query);

			set = ps.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return set;

	}

	public ResultSet search(int id) {
		ResultSet set = null;
		try {
			Connection con = createConnection();
			String query = "select * from emp where empid=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			set = ps.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return set;

	}

	public boolean delete(int id) {
		try {
			Connection con = createConnection();
			String query = "delete from emp where empid=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ps.executeUpdate();
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public boolean update(int id, Employee e) {
		try {
			Connection con = createConnection();
			String query = "update emp set empid=?,empname=?,empemail=?,empsal=? where empid=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, e.getId());
			ps.setString(2, e.getName());
			ps.setString(3, e.getEmail());
			ps.setInt(4, e.getSalary());
			ps.setInt(5, id);

			ps.executeUpdate();
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

}
