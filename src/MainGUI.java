

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class MainGUI extends JPanel implements ActionListener {

    GUIImplements newMainGUI = new GUIImplements();

    JButton btn_doc, btn_add, btn_back, btn_consultation;
    JLabel label_main;

    public MainGUI() {
        //construct components
        btn_consultation = new JButton("Consultations");
        btn_back = new JButton("Go Back");
        btn_doc = new JButton("Our Doctors");
        btn_add = new JButton("New Consultation");
        label_main = new JLabel("Skin Consultation Manager");

        //adjust size and set layout

        setPreferredSize (new Dimension (752, 437));
        setLayout (null);

        //add components
        add (btn_consultation);
        add (btn_back);
        add (btn_doc);
        add (btn_add);
        add (label_main);

        //set component bounds (only needed by Absolute Positioning)
        btn_consultation.setBounds (175, 190, 400, 35);
        btn_back.setBounds (175, 270, 400, 35);
        btn_doc.setBounds (175, 150, 400, 35);
        btn_add.setBounds (175, 230, 400, 35);
        label_main.setBounds (295, 70, 195, 25);

        // Action listeners

        btn_doc.addActionListener(this);
        btn_add.addActionListener(this);
        btn_back.addActionListener(this);
        btn_consultation.addActionListener(this);

        //Removing focus
        btn_doc.setFocusable(false);
        btn_add.setFocusable(false);
        btn_back.setFocusable(false);
        btn_consultation.setFocusable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == btn_doc) {//displaying the doctors table

            newMainGUI.mainGUI_showDoctorList();

            GUIDispose(e);

        } else if (e.getSource() == btn_add) {//adding a new consultation

            try {
                newMainGUI.mainGUI_addConsultation();
            } catch (IOException | ClassNotFoundException ex) {
                try {
                    newMainGUI.mainGUI_addConsultation();
                } catch (IOException | ClassNotFoundException exc) {
                    throw new RuntimeException(exc);
                }
            }

            GUIDispose(e);


        } else if (e.getSource() == btn_consultation) {//displaying the booked consultations table

            newMainGUI.mainGUI_showConsultationList();

            GUIDispose(e);

        } else if (e.getSource() == btn_back) {//exiting the GUI

            GUIDispose(e);
        }

    }

    private void GUIDispose(ActionEvent e){
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();
    }
}
