package world;

public class Main {

    static DAO management = new DAO();

    public static void main(String[] args) {

        while (true) {

            System.out.println("=========================");
            System.out.println("1. Search for a City");
            System.out.println("2. Search for a Country");
            System.out.println("3. Create new Country");
            System.out.println("4. Update Country");
            System.out.println("5. NUKE Country");
            System.out.println("0. Exit");
            System.out.println("=========================");
            System.out.print("Input: ");

            int choice = management.scanInt();
            String search;

            if (choice == 1) {

                System.out.print("Enter Search Parameters: ");

                search = management.input.nextLine();
                management.findCity(search);

            } else if (choice == 2) {

                System.out.print("Enter Search Parameters: ");

                search = management.input.nextLine();
                management.findCountry(search);

            } else if (choice == 3) {

                management.createCountry();

            } else if (choice == 4) {

                management.updateCountry();

            } else if (choice == 5) {

                management.deleteCountry();
            } else if (choice == 0) {
                System.exit(0);

            } else {
                System.out.println("<Invalid Choice!>");
            }

        }
    }

}
