import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConsultationTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "Consultation Id",
            "Doctor Id",
            "Doctor's Name",
            "Doctor's Mobile",
            "Specialization",
            "Patient Id",
            "Patient's Name",
            "Patient's DOB",
            "Patient's Mobile",
            "Consultation Date",
            "Time Slot (Hrs)",
            "Notes",
            "Image",
            "Cost"
    };
    private final ArrayList<Consultation> consultations;

    public ConsultationTableModel(ArrayList<Consultation> consultations) {

        this.consultations = consultations;
    }

    @Override
    public int getRowCount() {
        return consultations.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public Class getColumnClass(int column) {
        return (column == 12) ? Icon.class : Object.class;
    }
    public String getColumnName(int col) {

        return columnNames[col];
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return switch (columnIndex) {

            case 0 -> consultations.get(rowIndex).getConId();
            case 1 -> consultations.get(rowIndex).getDoctor().getMedical_licence_number();
            case 2 -> consultations.get(rowIndex).getDoctor().getName()+" "+consultations.get(rowIndex).getDoctor().getSurname();
            case 3 -> consultations.get(rowIndex).getDoctor().getMobile_number();
            case 4 -> consultations.get(rowIndex).getDoctor().getSpecialisation();
            case 5 -> consultations.get(rowIndex).getPatient().getPatient_id();
            case 6 -> consultations.get(rowIndex).getPatient().getName()+" "+consultations.get(rowIndex).getPatient().getSurname();
            case 7 -> consultations.get(rowIndex).getPatient().getDate_of_birth();
            case 8 -> consultations.get(rowIndex).getPatient().getMobile_number();
            case 9 -> consultations.get(rowIndex).getDate();
            case 10 -> consultations.get(rowIndex).getStart_time()+" to "+ String.format("%04d", (Integer.parseInt(consultations.get(rowIndex).getStart_time())+100));
            case 11 -> consultations.get(rowIndex).getNote();
            case 12 -> {
                try {

                    EncodeDecode decoder = new EncodeDecode();

                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(decoder.decodeImage(consultations.get(rowIndex).getImage())));
                    ImageIcon aboutIcon = new ImageIcon(image);

                    Image image1 = aboutIcon.getImage();
                    Image image2 = image1.getScaledInstance(110, 80, Image.SCALE_SMOOTH);


                    yield new ImageIcon(image2);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case 13 -> consultations.get(rowIndex).getCost();
            default -> null;

        };
    }


}