

import java.io.IOException;
import java.util.ArrayList;

public interface SkinConsultationManager {

    void addDoctor() throws IOException, ClassNotFoundException;

    void removeDoctor() throws IOException, ClassNotFoundException;

    void printDoctor() throws IOException, ClassNotFoundException;

    void sortDoctor() throws IOException, ClassNotFoundException;

    void saveToFile() throws IOException;

    void openGUI();

    ArrayList<Doctor> isFile() throws IOException, ClassNotFoundException;

    ArrayList<Doctor> getDoctor_list();

    void setDoctor_list(ArrayList<Doctor> getDoctor_list);

    void consoleMenuOptions();


}
