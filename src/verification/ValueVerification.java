package verification;

import java.util.Scanner;

public class ValueVerification {

    Scanner scan = new Scanner(System.in);

    public int checkInt() {

        while (!scan.hasNextInt()) {
            System.out.println("Вы ввели значение не того типа");
            scan.next();
        }
        return scan.nextInt();
    }

    public String checkString() {
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNext()) {
            System.out.println("Вы ввели значение не того типа");
            scan.next();
        }
        return scan.nextLine();
    }
}
