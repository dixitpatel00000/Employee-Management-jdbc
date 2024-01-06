package edu.jsp.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class View {

	Controller c = new Controller();

	public static void main(String[] args) {

		View v = new View();

		v.menu();

	}

	private void menu() {

		Scanner s = new Scanner(System.in);
		Scanner s1 = new Scanner(System.in);

		int ch;
		do {

			System.out.println("1.INSERT BY BATCH");
			System.out.println("2.DISPLAY & SORT");
			System.out.println("3.SEARCH");
			System.out.println("4.DELETE");
			System.out.println("5.UPDATE");
			System.out.println("6.DELETE ALL");
			System.out.println("7.COUNT NO OF SAL GREATER THAN");
			System.out.println("8.INSERT BY CALL");
			System.out.println("0.EXIT");
			System.out.println();
			System.out.print("Enter your choice : ");
			ch = s.nextInt();
			switch (ch) {

			case 1: {
				int count = 0;

				while (true) {
					System.out.print("Enter id : ");
					int id = s.nextInt();
					System.out.print("Enter Emp name : ");
					String name = s1.nextLine();
					System.out.print("Enter Emp email : ");
					String email = s1.nextLine();
					System.out.print("Enter Salary : ");
					int salary = s.nextInt();
					count += c.insert(new Employee(name, id, salary, email));
					System.out.println("Do you want to enter more records (Y/N)");
					String flag = s1.nextLine().toUpperCase();
					if (flag.equals("N")) {
						break;
					}

				}
				System.out.println(count + " no of records inserted");
				break;

			}

			case 2: {
				System.out.println("enter sorting method: 1.ID");
				System.out.println("enter sorting method: 2.SALARY");
				System.out.println("enter sorting method: 3.NAME");
				int option = s.nextInt();
				if (option < 4 && option > 0) {
					ResultSet set = c.display(option);
					try {
						while (set.next()) {

							System.out.println("++++++++++++++++++++");
							System.out.println();
							System.out.println("Name: " + set.getString("empname"));
							System.out.println("id: " + set.getInt("empid"));
							System.out.println("email: " + set.getString("empemail"));
							System.out.println("sal: " + set.getInt("empsal"));
							System.out.println();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}

				} else {
					System.err.println("enter a valid option");
				}
				System.out.println("---------------------");
				break;
			}

			case 3: {
				System.out.print("Enter id to Search : ");
				ResultSet set = c.search(s.nextInt());

				try {
					if (!set.isBeforeFirst()) {

						System.out.println("record not found");
					} else {
						while (set.next()) {
							System.out.println("++++++++++++++++++++");
							System.out.println();
							System.out.println("Name: " + set.getString("empname"));
							System.out.println("id: " + set.getInt("empid"));
							System.out.println("email: " + set.getString("empemail"));
							System.out.println("sal: " + set.getInt("empsal"));
							System.out.println();
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();

				}

				System.out.println("---------------------");
				break;
			}
			case 4: {

				System.out.print("Enter id to Delete : ");
				int id = s.nextInt();
				if (c.delete(id)) {

					System.err.println("record deleted");
				} else {
					System.err.println("record not deleted");
				}
				System.out.println("---------------------");
				break;
			}

			case 5: {

				System.out.print("Enter id to Update : ");
				int originalid = s.nextInt();

				ResultSet set = c.search(originalid);
				try {
					if (!set.isBeforeFirst()) {

						System.err.println("record not found");
						break;
					}
				} catch (SQLException e) {

					e.printStackTrace();
				}

				System.out.println("Enter Updated name: ");
				String name = (s1.nextLine());
				System.out.println("Enter Updated email: ");
				String email = (s1.nextLine());
				System.out.println("Enter Updated id: ");
				int id = (s.nextInt());
				System.out.println("Enter Updated salary: ");
				int salary = (s.nextInt());

				if (c.update(originalid, new Employee(name, id, salary, email))) {
					System.err.println("record updated");
				} else {
					System.err.println("record not found");
				}

				System.out.println("---------------------");
				break;
			}

			case 6: {
				if (c.deleteAll()) {
					System.err.println("all records deleted succesfully");
				} else {
					System.err.println("error");
				}
				break;

			}

			case 7: {
				System.out.println("no of salaries greater than:");

				c.countsal(s.nextInt());
				break;

			}

			case 8: {
				int count = 0;

				while (true) {
					System.out.print("Enter id : ");
					int id = s.nextInt();
					System.out.print("Enter Emp name : ");
					String name = s1.nextLine();
					System.out.print("Enter Emp email : ");
					String email = s1.nextLine();
					System.out.print("Enter Salary : ");
					int salary = s.nextInt();
					count += c.insertbycall(new Employee(name, id, salary, email));
					System.out.println("Do you want to enter more records (Y/N)");
					String flag = s1.nextLine().toUpperCase();
					if (flag.equals("N")) {
						break;
					}

				}
				System.out.println(count + " no of records inserted");
				break;

			}

			case 0: {
				break;
			}
			default: {
				System.err.println("enter a valid option ");
				System.out.println();

			}
			}
		} while (ch != 0);
		System.out.println();
		System.err.println("END");
	}
}
