

import java.io.Serial;
import java.io.Serializable;

public abstract class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = -372705978061029031L;

    private String name, surname, mobile_number;
    private Date date_of_birth;


    public Person (String name, String surname, String mobile_number){
        setName(name);
        setSurname(surname);
        setMobile_number(mobile_number);

    }


    // getters
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Date getDate_of_birth() {
        return date_of_birth;
    }
    public String getMobile_number() {
        return mobile_number;
    }


    // setters
    public void setName(String name){
        this.name=name;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public void setDate_of_birth(Date date_of_birth){
        this.date_of_birth=date_of_birth;
    }
    public void setMobile_number(String mobile_number){
        this.mobile_number=mobile_number;
    }



    public String toString() {
        return "Name:"+getName() + " " + "Surname:"+getSurname() + " " + "DOB:"+getDate_of_birth() + " " + "Tel:"+getMobile_number();
    }
}
