package utilities;

public class HMS{
    public HMS() {
        StaticFunctions.Initialise();
    }
    public void MainView() throws Exception{
        int choice;

        do {
            System.out.println("\n\t\tMain Menu");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Demo Queries");
            System.out.println("4. Exit");
            do {
                System.out.print("Enter Choice (1-4): ");
                choice = StaticFunctions.nextInt();
                StaticFunctions.nextLine();
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid Choice");
                }
            } while (choice < 1 || choice > 4);
            switch (choice) {
                case 1:
                    Login ob = new Login();
                    ob.MainView();
                    break;
                case 2:
                    Signup ob2 = new Signup();
                    ob2.MainView();
                    break;
                case 3:
                    System.out.println("Coming Soon!");
                    break;
                case 4:
                    System.out.println("\tArrivederci!");
                    break;
            };
        }while(choice != 4);
    }

    public static void main(String[] args) throws Exception
    {
        HMS ob = new HMS();
        ob.MainView();
    }
}