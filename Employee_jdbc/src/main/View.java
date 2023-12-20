package main;

import java.sql.ResultSet;
import java.sql.SQLException;
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

			System.out.println("1.INSERT");
			System.out.println("2.DISPLAY");
			System.out.println("3.SEARCH");
			System.out.println("4.DELETE");
			System.out.println("5.UPDATE");
			System.out.println("0.EXIT");
			System.out.println();
			System.out.print("Enter your choice : ");
			ch = s.nextInt();
			switch (ch) {

			case 1: {
				System.out.print("Enter id : ");
				int id = s.nextInt();
				System.out.print("Enter Emp name : ");
				String name = s1.nextLine();
				System.out.print("Enter Emp email : ");
				String email = s1.nextLine();
				System.out.print("Enter Salary : ");
				int salary = s.nextInt();
				if (c.insert(new Employee(name, id, salary, email))) {
					System.out.println();
					System.out.println("record inserted succesfully");
				} else {
					System.out.println("unsuccesful insertion");
				}

				System.out.println("---------------------");
				break;
			}

			case 2: {
				System.out.println("enter sorting method: 1.NAME");
				System.out.println("enter sorting method: 2.ID");
				System.out.println("enter sorting method: 3.SALARY");
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
					System.out.println("enter a valid option");
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

					System.out.println("record deleted");
				} else {
					System.out.println("record not deleted");
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

						System.out.println("record not found");
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
					System.out.println("record updated");
				} else {
					System.out.println("record not found");
				}

				System.out.println("---------------------");
				break;
			}
			case 0: {
				break;
			}
			default: {
				System.out.println("enter a valid option ");
				System.out.println();

			}
			}
		} while (ch != 0);
		System.out.println();
		System.out.println("END");
	}
}
