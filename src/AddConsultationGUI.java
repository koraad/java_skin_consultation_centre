import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddConsultationGUI extends JPanel implements ActionListener, Serializable{
    private final JButton reset, addNew, getBack, addImage;
    private final JComboBox<String> docIdList, duration, con_time;
    private final JComboBox<Integer> dob_day, dob_month, dob_year, con_day, con_month;
    private final JComboBox<Year> con_year;
    private final JTextField firstNameText, surnameLabelText, mobText, patIdText, imageFilePath;
    private final JTextArea notes;
    private final JLabel chosen_image_name, chosen_image;
    Year this_year = Year.now();
    String imageString;// image that has converted to a string
   
    public AddConsultationGUI() throws IOException, ClassNotFoundException {

        //adjust size and set layout
        setPreferredSize (new Dimension (752, 437));
        setLayout (null);

        //construct preComponents
        String[] doctorIds = getDocIds();
        Integer[] days = getBirthDays(31);
        Integer[] months = getBirthDays(12);
        Integer[] years = getBirthYears();
        String[] con_durations = {"1 Hrs"};
        String[] start_times = {"0800", "0900","1000","1100","1200","1300","1400","1500","1600","1700","1800","1900","2000"};
        Year[] currentYear = {this_year};

        //construct components
        //Jlabels
        JLabel patDetailsLabel = new JLabel("Patient's Details");
        JLabel consultationDetailsLabel = new JLabel("Consultation Details");
        JLabel date = new JLabel("Date");
        JLabel docIdLabel = new JLabel("Doctor Id");
        JLabel dobLabel = new JLabel("Date of Birth");
        JLabel firstNameLabel = new JLabel("First Name");
        JLabel surnameLabel = new JLabel("Surname");
        JLabel mobileLabel = new JLabel("Mobile No");
        JLabel patIdLabel = new JLabel("Patient Id");
        JLabel notesLabel = new JLabel("Notes");
        JLabel startTimeLabel = new JLabel("Start Time");
        JLabel endTimeLabel = new JLabel("Duration");
        JLabel chosen_image_label = new JLabel("Chosen Image:");
        chosen_image_name = new JLabel();
        chosen_image = new JLabel();


        //Jbuttons
        reset = new JButton ("Reset");
        addNew = new JButton ("Add");
        getBack = new JButton ("Go Back");
        addImage = new JButton("Upload Image");

        //Jcombos

        docIdList = new JComboBox<>(doctorIds);
        dob_day = new JComboBox<> (days);
        dob_month = new JComboBox<> (months);
        dob_year = new JComboBox<> (years);
        con_day = new JComboBox<> (days);
        con_month = new JComboBox<> (months);
        con_year = new JComboBox<> (currentYear);
        duration = new JComboBox<>(con_durations);
        con_time = new JComboBox<>(start_times);

        dob_year.setEditable(true);
        dob_month.setEditable(true);
        dob_day.setEditable(true);
        con_day.setEditable(true);
        con_month.setEditable(true);
        docIdList.setEditable(true);

        //Jtexts
        firstNameText = new JTextField (5);
        surnameLabelText = new JTextField (5);
        mobText = new JTextField (5);
        patIdText = new JTextField (5);
        imageFilePath = new JTextField(5);
        notes = new JTextArea();

        //add components
        add (reset);
        add (addNew);
        add (docIdLabel);
        add (docIdList);
        add (dobLabel);
        add (firstNameLabel);
        add (firstNameText);
        add (surnameLabel);
        add (mobileLabel);
        add (patIdLabel);
        add (surnameLabelText);
        add (mobText);
        add (patIdText);
        add (patDetailsLabel);
        add (notesLabel);
        add(con_time);
        add (startTimeLabel);
        add(endTimeLabel);
        add (getBack);
        add(addImage);
        add(dob_day);
        add(dob_month);
        add(dob_year);
        add(notes);
        add(consultationDetailsLabel);
        add(date);
        add(con_day);
        add(con_month);
        add(con_year);
        add(duration);
        add(imageFilePath);
        add(chosen_image_label);
        add(chosen_image_name);
        add(chosen_image);

        //set component bounds (only needed by Absolute Positioning)

        //left components
        dobLabel.setBounds (20, 155, 100, 25);
        dob_day.setBounds(115, 160, 60, 35);
        dob_month.setBounds(195, 160, 60, 35);
        dob_year.setBounds(275, 160, 80, 35);
        firstNameLabel.setBounds (20, 65, 100, 25);
        firstNameText.setBounds (115, 70, 345, 35);
        surnameLabel.setBounds (20, 110, 100, 25);
        mobileLabel.setBounds (20, 200, 100, 25);
        patIdLabel.setBounds (20, 245, 100, 25);
        surnameLabelText.setBounds (115, 115, 345, 35);
        mobText.setBounds (115, 205, 345, 35);
        patIdText.setBounds (115, 250, 345, 35);
        patDetailsLabel.setBounds (20, 25, 100, 25);
        notesLabel.setBounds (20, 290, 100, 25);
        notes.setBounds (115, 295, 345, 65);
        addImage.setBounds (20, 410, 120, 35);
        imageFilePath.setBounds(150, 410, 310, 35);

        //right components
        consultationDetailsLabel.setBounds (565, 25, 150, 25);
        reset.setBounds (855, 410, 100, 35);
        addNew.setBounds (965, 410, 100, 35);
        docIdList.setBounds (635, 70, 100, 35);
        getBack.setBounds (745, 410, 100, 35);
        docIdLabel.setBounds (565, 75, 85, 25);
        con_time.setBounds (635, 115, 100, 35);
        startTimeLabel.setBounds (565, 115, 100, 25);
        endTimeLabel.setBounds(755, 115, 100, 25);
        duration.setBounds (825, 115, 100, 35);
        date.setBounds(755, 75, 100, 25);
        con_day.setBounds(825, 70, 60, 35);
        con_month.setBounds(905, 70, 60, 35);
        con_year.setBounds(985, 70, 80, 35);
        chosen_image_label.setBounds(565, 170, 100, 25);
        chosen_image_name.setBounds(565, 200, 150, 25);
        chosen_image.setBounds(690, 180, 373, 220);


        //removing button focus
        getBack.setFocusable(false);
        addImage.setFocusable(false);
        reset.setFocusable(false);
        addNew.setFocusable(false);

        // Action listeners
        getBack.addActionListener(this);
        addNew.addActionListener(this);
        docIdList.addActionListener(this);
        reset.addActionListener(this);
        addImage.addActionListener(this);
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == getBack) {

            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();

            (new WestminsterSkinConsultationManager()).openGUI();

        } else if (e.getSource() == addNew) { //adding a new consultation to the file

            formValidation(e);

        } else if (e.getSource() == addImage) {

            try {
                uploadImage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        } else if (e.getSource() == reset) {
            //resetting text fields
            reset();
        }

    }

    private void uploadImage() throws IOException {

        JFileChooser upload = new JFileChooser();

        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg", "gif");
        upload.addChoosableFileFilter(fnef);


        int response = upload.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            String file_path = upload.getSelectedFile().getAbsolutePath();

            //setting the test field string as file path
            imageFilePath.setText(file_path);

            //setting label name as file name
            chosen_image_name.setText(upload.getSelectedFile().getName());
            ImageIcon image = new ImageIcon(file_path);

            Image image1 = image.getImage();
            Image image2 = image1.getScaledInstance(chosen_image.getWidth(), chosen_image.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon newIcon = new ImageIcon(image2);
            chosen_image.setIcon(newIcon);

            //encoding the image to a string
            EncodeDecode encoder = new EncodeDecode();
            imageString = encoder.encodeImage(file_path);

        }
    }

    private void formValidation(ActionEvent e) {

        if (firstNameText.getText().equals("") | imageFilePath.getText().equals("") | surnameLabelText.getText().equals("") | mobText.getText().equals("") | patIdText.getText().equals("") | notes.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "All fields must be filled", "Error", JOptionPane.ERROR_MESSAGE);

        } else if (firstNameText.getText().length() < 3 |surnameLabelText.getText().length() < 3) {
            JOptionPane.showMessageDialog(null, "A name cannot be less than 3 characters", "Error", JOptionPane.ERROR_MESSAGE);

        } else if (!isValidName(firstNameText.getText()) || !isValidName(surnameLabelText.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid Name or Surname: Try again", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            
            if (isValid(mobText.getText())) {
                try {
                    addNew(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                //resetting text boxes
                reset();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);

            }

        }
    }

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
    private boolean isValid(String mob_num) {

        Pattern p = Pattern.compile(
                "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");

        Matcher m = p.matcher(mob_num);

        // Returns boolean value
        return (m.matches());
    }

    private void reset() {
        docIdList.getSelectedItem();
        firstNameText.setText("");
        surnameLabelText.setText("");
        mobText.setText("");
        notes.setText("");
        patIdText.setText("");
        imageFilePath.setText("");
    }

    @SuppressWarnings("unchecked")
    private void addNew(ActionEvent e) throws IOException {

        File file = new File("consultations.txt");
        ArrayList<Consultation> consultations = new ArrayList<>();

        try {

            if (file.isFile()) {

                ObjectInputStream ois =new ObjectInputStream(new FileInputStream(file));
                consultations = (ArrayList<Consultation>) ois.readObject();
                ois.close();
            }

            saveToFile(consultations, file, e); // saving the added record to the file


        } catch (IOException ex) {

            saveToFile(consultations, file, e);

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    // checking if the Date Doctor and Time of the consultation are the same
    private boolean isBooked(ArrayList<Consultation> consultations, int day, int month, int year) {


        Date list_date;
        Date new_date = new Date(day, month, year);
        String new_date_string = new_date.toString();
        String list_docId;
        String new_docId = Objects.requireNonNull(docIdList.getSelectedItem()).toString();
        String list_time;
        String new_time = Objects.requireNonNull(con_time.getSelectedItem()).toString();

        boolean isBooked = false;

        for (Consultation consultation : consultations) {

            list_docId = consultation.getDoctor().getMedical_licence_number();
            list_time = consultation.getStart_time();
            list_date = consultation.getDate();

            String list_date_string = list_date.toString();

            if (list_docId.equals(new_docId) && list_time.equals(new_time) && new_date_string.equals(list_date_string)) {
                isBooked = true;

            }
        }

        return isBooked;
    }

    private int getRandomValue(int range_size) {

        return ThreadLocalRandom
                .current()
                .nextInt(0, range_size);
    }

    private String getDocId(ArrayList<Doctor> doctor_list, String removingId) {
        String randomId;
        String[] allarrayId = new String[doctor_list.size()];

        for (int i = 0; i < doctor_list.size(); i++) {
            allarrayId[i] = doctor_list.get(i).getMedical_licence_number();
        }

        String[] arr_new = new String[doctor_list.size()-1];

        for(int i=0, k=0;i< arr_new.length;i++){
            if(!Objects.equals(allarrayId[i], removingId)){
                arr_new[k]=allarrayId[i];
                k++;
            }
        }

        int index = getRandomValue(doctor_list.size()-1);

        randomId = arr_new[index];
        return randomId;
    }

    @SuppressWarnings("unchecked")
    private void saveToFile(ArrayList<Consultation> consultations, File file, ActionEvent e) throws IOException {


        Random rnd = new Random();
        int number = rnd.nextInt(9999);

        //generating random consultation id
        String id = "SKN#" + String.format("%04d", number);

        //converting variable data to be set to the constructor
        String pat_name = firstNameText.getText().substring(0, 1).toUpperCase() + firstNameText.getText().substring(1).toLowerCase();
        String pat_surname = surnameLabelText.getText().substring(0, 1).toUpperCase() + surnameLabelText.getText().substring(1).toLowerCase();
        String patId = patIdText.getText().toUpperCase();
        String note = notes.getText().substring(0, 1).toUpperCase() + notes.getText().substring(1).toLowerCase();

        int day = Integer.parseInt(Objects.requireNonNull(dob_day.getSelectedItem()).toString());
        int month = Integer.parseInt(Objects.requireNonNull(dob_month.getSelectedItem()).toString());
        int year = Integer.parseInt(Objects.requireNonNull(dob_year.getSelectedItem()).toString());

        int dayConsult = Integer.parseInt(Objects.requireNonNull(con_day.getSelectedItem()).toString());
        int monthConsult = Integer.parseInt(Objects.requireNonNull(con_month.getSelectedItem()).toString());
        int yearConsult = Integer.parseInt(Objects.requireNonNull(con_year.getSelectedItem()).toString());

        String con_duration = Objects.requireNonNull(duration.getSelectedItem()).toString();
        String consult_time = Objects.requireNonNull(con_time.getSelectedItem()).toString();

        //creating a new patient
        Patient patient = new Patient(pat_name, pat_surname, mobText.getText(), patId);
        patient.setDate_of_birth(new Date(day, month, year));

        int standard_consultation_charge = 15; //initial cost if new patient


        //searching whether the patient is an old customer
        File filePatient = new File("patient.txt");
        ArrayList<Patient> patients_list = new ArrayList<>();
        boolean new_patient = false;

        try {

            if (file.isFile()) {

                if (file.length() != 0) {
                    ObjectInputStream oispat = new ObjectInputStream(new FileInputStream(filePatient));
                    patients_list = (ArrayList<Patient>) oispat.readObject();
                    oispat.close();


                    for (Patient value : patients_list) {
                        if (value.getPatient_id().equals(patient.getPatient_id())) {
                            standard_consultation_charge = 25;
                            break;
                        } else { // only adding a patient to the patients records if patients isn't an old customer

                            new_patient = true;
                        }
                    }

                    if (new_patient) {
                        patients_list.add(patient);

                        ObjectOutputStream oospat = new ObjectOutputStream(new FileOutputStream(filePatient));
                        oospat.writeObject(patients_list);
                        oospat.close();
                    }
                }
            }


        } catch (IOException | ClassNotFoundException | NullPointerException ex) {
            patients_list.add(patient);

            ObjectOutputStream oospat = new ObjectOutputStream(new FileOutputStream(filePatient));
            oospat.writeObject(patients_list);
            oospat.close();
        }

        //creating a new consultation date
        Date selectedDate = new Date(dayConsult, monthConsult, yearConsult);


        //int totalCost = standard_consultation_charge * con_duration;
        String con_cost = "£" + standard_consultation_charge;


        //searching for the selected doctor
        Doctor doctor = null;
        File fileDoctor = new File("doctor.txt");
        ArrayList<Doctor> doctor_list = null;

        try {

            if (file.isFile()) {

                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDoctor));
                doctor_list = (ArrayList<Doctor>) ois.readObject();
                ois.close();
            }

        } catch (IOException | ClassNotFoundException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "No doctors are enlisted at the centre at this moment");

        }

        // adding a new doctor is the doctor is already occupied with another consultation in the same date and time
        boolean newlyAdded = false;
        String newDocId = "";

        if (isBooked(consultations, dayConsult, monthConsult, yearConsult)) {

            assert doctor_list != null;
            newDocId = getDocId(doctor_list, Objects.requireNonNull(docIdList.getSelectedItem()).toString());

            for (Doctor value : doctor_list) {
                if (value.getMedical_licence_number().equals(newDocId)) {
                    doctor = value;
                }
            }

            newlyAdded = true;

        } else {
            //referring to the same doctor in the file
            assert doctor_list != null;
            doctor = doctor_list.get(docIdList.getSelectedIndex());
        }


        // creating a consultation and writing to the file
        Consultation consultation = new Consultation(id, doctor, patient, consult_time, con_duration, con_cost, note, imageString);
        consultation.setDate(selectedDate);

        consultations.add(consultation);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(consultations);
        oos.close();

        int choice;

        //final confirmation message
        if (newlyAdded) {
            choice = JOptionPane.showConfirmDialog(null, "Oops, that doctor was already booked in the given date and time, therefore a new doctor with the Id "+"'"+newDocId+"'"+" was added to the consultation: Need to check?", "Doctor Changed!", JOptionPane.OK_CANCEL_OPTION);

        } else {
            choice = JOptionPane.showConfirmDialog(null, "New consultation was successfully booked: Need to check?", "Booked", JOptionPane.OK_CANCEL_OPTION);

        }


        // setting dialog box button actions
        if (choice == 0) {
            GUIImplements newMainGUI = new GUIImplements();
            newMainGUI.mainGUI_showConsultationList();

            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    //getting the doctor ids for the combo box
    private String[] getDocIds() throws IOException, ClassNotFoundException {

        File file = new File("doctor.txt");
        ArrayList<Doctor> doctor_list = new ArrayList<>();

        if (file.isFile() && file.length() != 0) {

            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(file));
            doctor_list = (ArrayList<Doctor>) ois.readObject();
            ois.close();
        } else {
            System.out.println("No Doctors enlisted at the centre: First add a doctor");
        }

        String[] array = new String[doctor_list.size()];

        for (int i = 0; i < doctor_list.size(); i++) {
            array[i] = doctor_list.get(i).getMedical_licence_number();
        }

        return array;
    }

    //getting days, months and years for the combo boxes
    private Integer[] getBirthDays(int end) { // getting the days and months for the ComboBox

        Integer[] array = new Integer[end];

        for (int i = 0; i < end; i++) {
            array[i] = i + 1;
        }

        return array;
    }
    private Integer[] getBirthYears() { // getting the years for the ComboBox

        Integer[] array = new Integer[106];

        for (int i = 0; i < 106; i++) {
            array[i] = i + Integer.parseInt(this_year.toString()) -106;
        }

        return array;
    }
}
