package main;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	static Connection con;

	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String user = "postgres";
			String pass = "admin@123";
			String path = "jdbc:postgresql://localhost/postgres";

			con = DriverManager.getConnection(path, user, pass);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;

	}

	public boolean insert(ArrayList<Employee> list) {
		try {
			Connection con = createConnection();
			String query = "insert into emp(empid,empname,empemail,empsal) values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			for (int i = 0; i < list.size(); i++) {

				ps.setInt(1, list.get(i).getId());
				ps.setString(2, list.get(i).getName());
				ps.setString(3, list.get(i).getEmail());
				ps.setInt(4, list.get(i).getSalary());
				ps.addBatch();
			}

			ps.executeBatch();

			con.close();
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

			con.close();
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

			con.close();
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

			con.close();
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public boolean deleteAll() {
		try {
			Connection con = createConnection();
			String query = "delete from emp";

			PreparedStatement ps = con.prepareStatement(query);

			ps.executeUpdate();

			con.close();
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

			con.close();
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public void insertbycall() {
		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);
		try {
			Connection con = createConnection();
			String sql = "call emp_insert(?,?,?,?)";
			CallableStatement stmt = con.prepareCall(sql);

			while (true) {
				System.out.print("Enter id : ");
				stmt.setInt(1, s.nextInt());
				System.out.print("Enter Emp name : ");
				stmt.setString(2, s1.nextLine());
				System.out.print("Enter Emp email : ");
				stmt.setString(3, s1.nextLine());
				System.out.print("Enter Salary : ");
				stmt.setInt(4, s.nextInt());
				stmt.executeUpdate();
				System.out.println("Do you want to enter more records (Y/N)");
				String flag = s1.nextLine().toUpperCase();
				if (flag.equals("N")) {
					break;
				}
			}

			stmt.close();
			con.close();

		} catch (Exception e) {
		
		}

	}

	public void countsal(int sal) {
		try {
			Connection con = createConnection();
			String query = "select count_by_sal(?)";

			CallableStatement ps = con.prepareCall(query);
			ps.setInt(1, sal);

			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			System.out.println(count);

			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}
}
