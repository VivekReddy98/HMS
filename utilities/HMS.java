package utilities;
public class HMS {
    public HMS() {
        StaticFunctions.Initialise();
    }
    public void MainView() throws Exception{
        int choice;
        do {
            System.out.println("\n\t\tMain Menu");
            System.out.println("1. Patient");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            do {
                System.out.print("Choose Role(1-3): ");
                choice = StaticFunctions.nextInt();
                StaticFunctions.nextLine();
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid Choice");
                }
            } while (choice < 1 || choice > 3);
            switch (choice) {
                case 1:
                    PatientHome ob = new PatientHome();
                    ob.MainView();
                    break;
                case 2:
                    //Staff Home
                    break;
                case 3:
                    System.out.println("\tArrivederci!");
                    break;
            };
        }while(choice != 3);
    }
    public static void main(String[] args) throws Exception
    {
        HMS ob = new HMS();
        ob.MainView();
    }
}