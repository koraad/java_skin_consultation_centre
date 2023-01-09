

import java.io.IOException;

public interface GUIComps {

    void mainGUI_showDoctorList();
    void mainGUI_showConsultationList();
    void mainGUI_addConsultation() throws IOException, ClassNotFoundException;
    void consultationTable_remove() throws IOException, ClassNotFoundException;

}
