import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("hello world");

				Scanner scanner = new Scanner();

				System.out.println("please enter your lastName");
				String lastName = scanner.nextLine();
				System.out.println("please enter your firstName");
				String firstName = scanner.nextLine();

				System.out.println("Your name is : " + firstName + " " + lastName);

    }
}
