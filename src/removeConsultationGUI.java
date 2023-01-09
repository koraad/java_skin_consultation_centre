

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class removeConsultationGUI extends JPanel implements ActionListener {

    private final JButton removeBtn;
    private final JComboBox<String> dropDown;
    public removeConsultationGUI() throws IOException, ClassNotFoundException {

        //construct preComponents
        String[] dropDownMenu = getComboBoxIds();

        //construct components
        removeBtn = new JButton ("Remove");
        dropDown = new JComboBox<> (dropDownMenu);
        JLabel title = new JLabel("Select from the dropdown to delete a consultation");

        //adjust size and set layout
        setPreferredSize (new Dimension(752, 437));
        setLayout (null);

        //add components
        add (removeBtn);
        add (dropDown);
        add (title);

        //set component bounds (only needed by Absolute Positioning)
        removeBtn.setBounds (430, 160, 120, 40);
        dropDown.setBounds (200, 160, 225, 40);
        title.setBounds (225, 75, 295, 30);

        //action listeners
        removeBtn.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeBtn) {

            try {
                deleteRecord(e);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @SuppressWarnings("unchecked")
    //getting the doctor ids for the combo box
    private String[] getComboBoxIds() throws IOException, ClassNotFoundException {

        File file = new File("consultations.txt");
        ArrayList<Consultation> consultations = new ArrayList<>();

        if (file.isFile()) {

            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(file));
            consultations = (ArrayList<Consultation>) ois.readObject();
            ois.close();
        }

        String[] array = new String[consultations.size()];

        for (int i = 0; i < consultations.size(); i++) {
            array[i] = consultations.get(i).getConId();
        }

        return array;
    }

    @SuppressWarnings("unchecked")
    private void deleteRecord(ActionEvent e) throws IOException, ClassNotFoundException {
        File file = new File("consultations.txt");
        ArrayList<Consultation> consultations = new ArrayList<>();

        if (file.isFile()) {

            ObjectInputStream ois =new ObjectInputStream(new FileInputStream(file));
            consultations = (ArrayList<Consultation>) ois.readObject();
            ois.close();
        }

        Consultation consultation = null;

        for (int i = 0; i < consultations.size(); i++) {

            consultation = consultations.get(dropDown.getSelectedIndex());

        }

        consultations.remove(consultation);


        JOptionPane.showMessageDialog(null, "Record successfully deleted");

        ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(consultations);
        oos.close();

        //closing the interface
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();

    }

}


