import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ConsultationTable extends JPanel implements ActionListener {

    private final JButton back_btn,delete;

    public ConsultationTable(ArrayList<Consultation> consultations) throws IOException {

        ConsultationTableModel tableModel = new ConsultationTableModel(consultations);
        JTable consultationTable = new JTable(tableModel);
        setLayout(new BorderLayout());

        setBounds(10, 10, 1300, 600);

        consultationTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(consultationTable);
        scrollPane.setPreferredSize(new Dimension(1500,700));

        consultationTable.setPreferredScrollableViewportSize(consultationTable.getPreferredSize());
        consultationTable.setRowHeight(80);

        JPanel panel = new JPanel();
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);

        back_btn = new JButton ("Go Back");
        delete = new JButton("Delete Consultation");
        delete.setFocusable(false);
        add(back_btn, BorderLayout.SOUTH);
        add(delete, BorderLayout.NORTH);
        back_btn.setSize (752, 40);
        back_btn.addActionListener(this);
        delete.setSize (752, 40);
        delete.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == back_btn) {

            disposeGUI(e);

        } else if (e.getSource() == delete) {
            GUIImplements removeGUI = new GUIImplements();

            try {

                disposeGUI(e);

                // giving the remove consultation option
                removeGUI.consultationTable_remove();

            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }


    // disposing the current GUI and initiating a new GUI
    private void disposeGUI(ActionEvent e) {
        JComponent comp = (JComponent) e.getSource();
        Window win = SwingUtilities.getWindowAncestor(comp);
        win.dispose();

        (new WestminsterSkinConsultationManager()).openGUI();
    }
}