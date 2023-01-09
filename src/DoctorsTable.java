

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DoctorsTable extends JPanel implements ActionListener {

    private final JButton back_btn;
    public DoctorsTable(ArrayList<Doctor> doctor_list) {

        DoctorTableModel tableModel = new DoctorTableModel(doctor_list);
        JTable docTable = new JTable(tableModel);

        setBounds(200, 200, 752, 437);

        docTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(docTable);
        scrollPane.setPreferredSize(new Dimension(650,300));
        setLayout(new BorderLayout());

        JPanel panelCenter = new JPanel();
        panelCenter.add(scrollPane);
        add(panelCenter, BorderLayout.CENTER);


        back_btn = new JButton ("Go Back");
        back_btn.setSize(752, 40);
        back_btn.addActionListener(this);
        add(back_btn, BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back_btn) {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();

            (new WestminsterSkinConsultationManager()).openGUI();
        }
    }


}