import java.io.Serial;

public class Doctor extends Person{

    @Serial
    private static final long serialVersionUID = 4099124515968713617L;

    private String medical_licence_number, specialisation;

    public Doctor (String name, String surname,String mobile_number, String license_num, String specs){

        super(name, surname, mobile_number);
        setMedical_licence_number(license_num);
        setSpecialisation(specs);

    }


    //getters
    public String getMedical_licence_number() {
        return medical_licence_number;
    }

    public String getSpecialisation() {
        return specialisation;
    }


    // setters
    public void setMedical_licence_number(String number){
       medical_licence_number = number;
    }

    public void setSpecialisation(String specs){
        specialisation = specs;
    }



    @Override
    public String toString() {
        return super.toString() + " " + "Medical Licence Number:"+getMedical_licence_number()+ " " +"Specialization:"+getSpecialisation();
    }
}
