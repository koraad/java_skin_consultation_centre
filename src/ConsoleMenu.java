

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleMenu {


    public static void main(String[] args) throws IOException, ClassNotFoundException {

        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        int choice;
        Scanner scanner2 = new Scanner(System.in);

        try {

            do {
                //printing console menu
                manager.consoleMenuOptions();
                choice = scanner2.nextInt();

                switch (choice) {
                    case 1 -> manager.addDoctor();// adding a new doc
                    case 2 -> manager.printDoctor();// printing the list of docs
                    case 3 -> manager.removeDoctor();// removing a new doc
                    case 4 -> manager.sortDoctor();// sorting the list of docs
                    case 5 -> manager.saveToFile();// saving newly added docs to the file
                    case 6 -> {
                        manager.openGUI();

                        System.out.println("Graphical User Interface opening...\n");
                        System.out.println("Press 0. to go to Console Menu");
                        int choice2 = scanner2.nextInt(); // code snippet is just there to pause looping of while loop
                    }
                }

            }while (choice != 0);

            System.out.println("Console Menu closed: Bye Bye!");

        }catch (InputMismatchException e){System.out.println("Error: Invalid input");}

    }

}
