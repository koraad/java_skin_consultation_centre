import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    boolean fileEmpty = false;
    boolean added = false;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    ArrayList<Doctor> doctor_list = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    File file = new File("doctor.txt");
    Iterator<Doctor> iterator;


    //main functionality methods
    @Override
    public void addDoctor() throws IOException, ClassNotFoundException {

        int menu = 0;

        doctor_list = isFile();

        do {

            Doctor doctor;
            String name;
            String surname;
            String mobile_num;
            String license_num;
            String spec;
            Date date;
            boolean valid_date = false;
            boolean valid_mob = false;
            boolean valid_spec = false;
            String day, month, year;

            try {
                System.out.println("Enter required details to add a doctor----------------------------------------\n");
                System.out.println("Doctor's Name: ");
                name = scanner.nextLine();

                System.out.println("Doctor's Surname: ");
                surname = scanner.nextLine();

                //validating the name inputs
                if (name.length() < 3 | surname.length() < 3) {
                    System.out.println("A name cannot be less than 3 characters: try again");
                    continue;
                } else if (!isValidName(name) | !isValidName(surname)) {
                    System.out.println("Invalid Name or Surname: Try again");
                    continue;
                } else {

                    int birth_day = 0;
                    int birth_month = 0;
                    int birth_year = 0;
                    do {
                        try {
                            System.out.println("Doctor's Date of Birth DD/MM/YY: \n");
                            System.out.println("Day: ");
                            day = scanner.nextLine();

                            System.out.println("Month: ");
                            month = scanner.nextLine();

                            System.out.println("Year: ");
                            year = scanner.nextLine();

                            birth_day = Integer.parseInt(day);
                            birth_month = Integer.parseInt(month);
                            birth_year = Integer.parseInt(year);

                        }catch (NumberFormatException e){

                            System.out.println("Invalid birth day: Try again");
                            continue;
                        }


                        // validating the birthday
                        if (birth_day > 31 | birth_month > 12 | 2022 - birth_year > 105 | birth_year > 2022) {
                            System.out.println("Error: Invalid Birth Day: try again");
                        } else {
                            valid_date = true;
                        }

                    } while (!valid_date);

                    // creating a birthday
                    date = new Date(birth_day, birth_month, birth_year);

                    do {
                        System.out.println("Doctor's Mobile No: ");
                        mobile_num = scanner.next();

                        // validating the mobile number
                        if (isValid(mobile_num)) {
                            valid_mob = true;
                        } else {
                            System.out.println("Invalid phone number: Try again");
                        }
                    } while (!valid_mob);


                    System.out.println("Doctor's Medical License No: ");
                    license_num = scanner.next();

                    do {
                        System.out.println("Doctor's Area of Specialization:");
                        spec = scanner.next();

                        // validating speciality
                        if (spec.length() >= 4) {
                            valid_spec = true;
                        } else {
                            System.out.println("Specialization cannot be less than 4 characters: Try again");
                        }
                    } while (!valid_spec);
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input | Doctor adding failed");
                continue;
            }


            // formatting the inputs prior to object creation
            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            surname = surname.substring(0,1).toUpperCase() + surname.substring(1).toLowerCase();
            license_num = license_num.toUpperCase();
            spec = spec.substring(0,1).toUpperCase() + spec.substring(1).toLowerCase();


            // checking whether the doctor is already in the system
            boolean enlisted = false;

            for (Doctor doc: doctor_list) {
                if (doc.getMedical_licence_number().equals(license_num)) {
                    enlisted = true;
                    break;
                }
            }

            if (enlisted) {
                System.out.println("Action failed: Doctor with the Id "+license_num+" is already enlisted at the centre. Record not added!\n");
            } else {
                doctor = new Doctor(name, surname, mobile_num, license_num, spec);
                doctor.setDate_of_birth(date);

                doctor_list.add(doctor);
                setDoctor_list(doctor_list);

                System.out.println("Doctor successfully added to the system\n");
                added = true;

            }

            System.out.println("Press 1. to add another doctor, and 0. to exit to main menu: ");
            try {

                String menu_choice = scanner.nextLine();
                menu = Integer.parseInt(menu_choice);

            }catch (NumberFormatException e) {

                String menu_choice = scanner.nextLine();
                menu = Integer.parseInt(menu_choice);
            }

        }while (menu != 0);

    }
    @Override
    public void printDoctor() throws IOException, ClassNotFoundException {

        if (added) {
            doctor_list = getDoctor_list();
        } else {
            doctor_list = isFile();
        }

        //iterator = doctor_list.iterator();//additional code

        if (fileEmpty && doctor_list.size() == 0) {
            System.out.println("There are no doctor records stored in the file");
        } else {
            System.out.println("\n----------------------------------------------------------------------------------------------");
            System.out.println("Following are the list of doctors enlisted at the centre\n");
            printTable(doctor_list);
            //additional code
//            System.out.println("\nThere are currently "+doctor_list.size()+" doctors enlisted in the centre");
//            System.out.println("---------------------------------------------------------------------------------------");
//            System.out.println("---------------------------------------------------------------------------------------\n");
//
//            while (iterator.hasNext()) {
//                Doctor doctor = iterator.next();
//                System.out.println(doctor);
//            }
//            System.out.println("\n---------------------------------------------------------------------------------------");
//            System.out.println("---------------------------------------------------------------------------------------");
        }

    }
    @Override
    public void removeDoctor() throws IOException, ClassNotFoundException {

        if (added) {
            doctor_list = getDoctor_list();
        } else {
            doctor_list = isFile();
        }

        if (doctor_list.size() == 0) {
            System.out.println("Currently there are no doctors enlisted at the centre");
        } else {
            System.out.println("Enter Medical license number to remove a doctor: ");
            String doc_num = scanner.nextLine();

            boolean found = false;
            iterator = doctor_list.iterator();

            while (iterator.hasNext()) {
                Doctor doctor = iterator.next();
                if (doctor.getMedical_licence_number().equals(doc_num)) {
                    iterator.remove();
                    found = true;
                    System.out.println("\n----------------------------------------------------------------------------------------------");
                    System.out.println("Dr. "+doctor.getName()+" "+doctor.getSurname()+" removed from the system successfully");
                }
            }

            if (!found) {
                System.out.println("No doctor's record found with the license number "+doc_num);
            }else {
                System.out.println("Now there are "+doctor_list.size()+" doctors in total enlisted at the centre");

                oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(doctor_list);
                oos.close();
            }
        }

    }
    @Override
    public void sortDoctor() throws IOException, ClassNotFoundException {

        if (added) {
            doctor_list = getDoctor_list();
        } else {
            doctor_list = isFile();
        }

        if (doctor_list.size() == 0) {
            System.out.println("There are no doctor records stored in the file");
        }else {
            doctor_list.sort(Comparator.comparing(Person::getName));
            System.out.println("\n----------------------------------------------------------------------------------------------");
            System.out.println("\nDoctor list is sorted to the alphabetical order by first name as below");
            printTable(doctor_list);

            //additional code
//        for (Doctor doctor : doctor_list) {
//            System.out.println(doctor.getName() + " " + doctor.getSurname() + " " + doctor.getDate_of_birth() + " " + doctor.getMobile_number() + " " + doctor.getMedical_licence_number() + " " + doctor.getSpecialisation());
//        }

        }
    }
    @Override
    public void saveToFile() throws IOException {

        if (!added) {
            System.out.println("No new records found to be saved, please add new records first\n");
        } else {

            doctor_list = getDoctor_list();

            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(doctor_list);
            oos.close();

            System.out.println("All records are successfully saved to the file");
        }


    }
    @Override
    public void openGUI() {


        JFrame frame = new JFrame ("GUI | Skin Consultation Manger");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setBounds(200, 200, 752, 437);
        frame.getContentPane().add (new MainGUI());
        frame.pack();
        frame.setVisible (true);

        ImageIcon favicon = new ImageIcon("favicon.png");
        frame.setIconImage(favicon.getImage());

    }


    //supporting methods
    private boolean isValidName(String name) {

        //String regex = "^[A-Za-z]\\w{5,29}$";
        String regex = "^(?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$";

        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }
    private void printTable(ArrayList<Doctor> doctor_list){

        String leftAlignFormat = "| %-2s | %-10s | %-10s | %-10s | %-13s | %-15s | %-12s |%n";

        System.out.format("+----+------------+------------+------------+---------------+-----------------+--------------+%n");
        System.out.format("| No | Doc Id     | Name       | Surname    | Date of Birth | Specialization  | Mobile       |%n");
        System.out.format("+----+------------+------------+------------+---------------+-----------------+--------------+%n");
        for (Doctor doctor : doctor_list) {
            System.out.format(leftAlignFormat, doctor_list.indexOf(doctor)+1, doctor.getMedical_licence_number(), doctor.getName(), doctor.getSurname(), doctor.getDate_of_birth(), doctor.getSpecialisation(), doctor.getMobile_number());
        }
        System.out.format("+----+------------+------------+------------+---------------+-----------------+--------------+%n");
    }
    @Override
    public void consoleMenuOptions() {
        System.out.println("\n");
        System.out.println("Choose from the menu to operate");
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println("1. Add Doctor");
        System.out.println("2. Print Doctors");
        System.out.println("3. Remove Doctor");
        System.out.println("4. Sort Doctors");
        System.out.println("5. Save info in to file");
        System.out.println("6. Open GUI");
        System.out.println("0. End Program");
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println("Enter here: ");
    }
    private boolean isValid(String mob_num) {

        Pattern p = Pattern.compile(
                "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");

        Matcher m = p.matcher(mob_num);

        // Returns boolean value
        return (m.matches());
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Doctor> isFile() throws IOException, ClassNotFoundException {

        if (file.isFile()) {

            if (file.length() == 0L) {
                fileEmpty = true;
            } else {
                ois =new ObjectInputStream(new FileInputStream(file));
                doctor_list = (ArrayList<Doctor>) ois.readObject();
                ois.close();
            }
        }

        return doctor_list;
    }
    @Override
    public ArrayList<Doctor> getDoctor_list() {
        return doctor_list;
    }
    @Override
    public void setDoctor_list(ArrayList<Doctor> doctor_list) {
        this.doctor_list = doctor_list;
    }


}
