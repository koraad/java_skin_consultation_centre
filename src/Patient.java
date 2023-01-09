

import java.io.Serial;

public class Patient extends Person{

    @Serial
    private static final long serialVersionUID = 5313874857350640808L;

    private String patient_id;

    public Patient (String name, String surname, String mobile_number, String id){

        super(name, surname, mobile_number);
        setPatient_id(id);

    }


    //getters
    public String getPatient_id() {
        return patient_id;
    }

    // setters
    public void setPatient_id(String id){
        patient_id = id;
    }


    @Override
    public String toString() {
        return super.toString() + " " + "Patient Id:"+getPatient_id();
    }

}
