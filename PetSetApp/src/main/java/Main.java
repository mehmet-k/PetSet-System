import backend.models.User;
import backend.util.db.insert.insertToDataBase;

public class Main {
	public static void main(String[] args) {
		User user = new User("testUsername","Testname","testSurname","testadress");
		
		insertToDataBase.insertUser(user);
		
	}
}
