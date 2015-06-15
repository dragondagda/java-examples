package de.hsbremen;

import de.hsbremen.DBConnection;

/**
 * Hello world!
 *
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("## Begin of main()");
		System.out.println();
		
		final DBConnection connection = new DBConnection();
		
		System.out.println("# Statement");
		System.out.println("3rd party Authentications available: " + connection.getAllAuthentications());
		System.out.println("-> add authentication 'facebook'");
		connection.addAuthentication("facebook");
		System.out.println("3rd party Authentications available: " + connection.getAllAuthentications());
		System.out.println("-> remove authentication 'facebook'");
		connection.deleteAuthentication("facebook");
		System.out.println("3rd party Authentications available: " + connection.getAllAuthentications());
		System.out.println();
		
		System.out.println("# Prepared statements");
		System.out.println("Login for (peter,pass12345) is correct: " + connection.login("peter", "pass12345"));
		System.out.println("Login for (elli,NULL) is correct: " + connection.login("elli", null));
		System.out.println("Login for (elli,12345) is correct: " + connection.login("elli", "12345"));
		System.out.println();
		
		System.out.println("# Close Database Connection");
		connection.close();
		System.out.println();
		
		System.out.println("## End of main()");
	}
}
