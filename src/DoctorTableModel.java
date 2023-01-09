

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DoctorTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "Name",
            "Surname",
            "Date of Birth",
            "Mobile",
            "Medical License No",
            "Specialization"
    };
    private final ArrayList<Doctor> doctor_list;

    public DoctorTableModel(ArrayList<Doctor> doctor_list) {

        this.doctor_list = doctor_list;
    }

    @Override
    public int getRowCount() {
        return doctor_list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        return switch (columnIndex) {
            case 0 -> doctor_list.get(rowIndex).getName();
            case 1 -> doctor_list.get(rowIndex).getSurname();
            case 2 -> doctor_list.get(rowIndex).getDate_of_birth();
            case 3 -> doctor_list.get(rowIndex).getMobile_number();
            case 4 -> doctor_list.get(rowIndex).getMedical_licence_number();
            case 5 -> doctor_list.get(rowIndex).getSpecialisation();
            default -> null;
        };
    }

    public String getColumnName(int col) {

        return columnNames[col];
    }
}