
public class Main {

	
	public static void main(String args[]) {
		DBConnect connect = new DBConnect();
		//connect.getData();
		//connect.createUser("buddy@rogers.ca", "Will", "Smith", "password123");
		//System.out.println(connect.getUserID("roger@rogers.ca"));
		connect.createEvent("buddy@rogers.ca", "WLU Party3", 434, 100, "1979-01-01 00:00:01", "1979-01-01 00:00:01", "1235 Steet");
		System.out.println(connect.passwordChecker("buddy@rogers.ca", "password1233"));
	}
	
	
}
