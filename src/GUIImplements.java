import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GUIImplements implements GUIComps{ // this class implements most of the GUI actions which should be abstracted

    @SuppressWarnings("unchecked")
    @Override
    public void mainGUI_showDoctorList() {
        try {

            File file = new File("doctor.txt");
            ArrayList<Doctor> doctor_list = null;


            if (file.isFile()) {

                ObjectInputStream ois =new ObjectInputStream(new FileInputStream(file));
                doctor_list = (ArrayList<Doctor>) ois.readObject();
                ois.close();
            }

            assert doctor_list != null;
            if (doctor_list.size() == 0) {
                JOptionPane.showMessageDialog(null, "No doctors are enlisted at the centre at this moment");

                (new WestminsterSkinConsultationManager()).openGUI();
            } else {

                JFrame frame = new JFrame("Enlisted Doctors");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new DoctorsTable(doctor_list));
                frame.setBounds(200, 200, 752, 437);
                frame.setVisible(true);
                frame.setResizable(false);

                ImageIcon favicon = new ImageIcon("favicon.png");
                frame.setIconImage(favicon.getImage());
            }

        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No doctors are enlisted at the centre at this moment");

            (new WestminsterSkinConsultationManager()).openGUI();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void mainGUI_showConsultationList() {
        try {

            File file = new File("consultations.txt");
            ArrayList<Consultation> consultations = new ArrayList<>();


            if (file.isFile()) {

                if (file.length() != 0) {
                    ObjectInputStream ois =new ObjectInputStream(new FileInputStream(file));
                    consultations = (ArrayList<Consultation>) ois.readObject();
                    ois.close();
                }
            }

            if (consultations.size() == 0L) {
                JOptionPane.showMessageDialog(null, "No consultations are booked at the moment");

                (new WestminsterSkinConsultationManager()).openGUI();
            } else {
                JFrame frame = new JFrame ("Consultations Details");
                frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add (new ConsultationTable(consultations));
                frame.setBounds(0, 30, 1530, 800);
                frame.setVisible (true);
                frame.setResizable(false);

                ImageIcon favicon = new ImageIcon("favicon.png");
                frame.setIconImage(favicon.getImage());
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No consultations are booked at the moment");
            //throw new RuntimeException(ex);
            (new WestminsterSkinConsultationManager()).openGUI();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }

    @Override
    public void mainGUI_addConsultation() throws IOException, ClassNotFoundException {

        JFrame frame = new JFrame ("New Consultation");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        try {
            frame.getContentPane().add (new AddConsultationGUI());
        } catch (IOException | ClassNotFoundException ex) {
            frame.getContentPane().add (new AddConsultationGUI());
        }
        frame.setBounds(50, 50, 1100, 500);
        frame.setVisible (true);
        frame.setResizable(false);

        ImageIcon favicon = new ImageIcon("favicon.png");
        frame.setIconImage(favicon.getImage());

    }

    @Override
    public void consultationTable_remove() throws IOException, ClassNotFoundException {
        JFrame frame = new JFrame ("Remove Consultation");
        frame.setBounds(200, 200, 752, 437);
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add (new removeConsultationGUI());
        frame.setVisible (true);
    }

}
