
import java.io.Serializable;


public class Consultation implements Serializable {

    private String start_time, cost, conId, duration, image, note;
    private Doctor doctor;
    private Patient patient;
    public Date date;

    public Consultation(String conId, Doctor doctor, Patient patient, String start_time, String duration, String cost, String note, String image) {

        setConId(conId);
        setDoctor(doctor);
        setPatient(patient);
        setStart_time(start_time);
        setDuration(duration);
        setCost(cost);
        setNote(note);
        setImage(image);
    }

    // getters
    public Doctor getDoctor() {
        return doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public String getConId() {
        return conId;
    }
    public String getStart_time() {
        return start_time;
    }

    public String getDuration() {
        return duration;
    }
    public String getCost() {
        return cost;
    }
    public Date getDate() {
        return date;
    }
    public String getNote() {

        EncryptionDecryption encryptDecrypt = new EncryptionDecryption();


        return encryptDecrypt.decrypt(this.note, getSecretKey());
    }

    public String getImage() {

        EncryptionDecryption encryptDecrypt = new EncryptionDecryption();


        return encryptDecrypt.decrypt(this.image, getSecretKey());
    }

    private String getSecretKey() {
        return "sec#@rete";
    }




    // setters
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public void setConId(String conId) {
        this.conId = conId;
    }
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setNote(String note) {

        EncryptionDecryption encryptDecrypt = new EncryptionDecryption();

        this.note = encryptDecrypt.encrypt(note, getSecretKey());
    }
    public void setImage(String image) {

        EncryptionDecryption encryptDecrypt = new EncryptionDecryption();

        this.image = encryptDecrypt.encrypt(image, getSecretKey());
    }



    @Override
    public String toString() {
        return
                "Consultation Id: "+ getConId()+" "+
                "Doc Id: "+getDoctor().getMedical_licence_number()+" "+
                "Doctor's Name: "+getDoctor().getName()+" "+getDoctor().getSurname()+" "+
                "Doctor's Mobile: "+getDoctor().getMobile_number()+" "+
                "Specialization: "+getDoctor().getSpecialisation()+" "+
                "Patient Id: "+getPatient().getPatient_id()+" "+
                "Patient's Name: "+getPatient().getName()+" "+getPatient().getSurname()+" "+
                "Patient's DOB: "+getPatient().getDate_of_birth()+" "+
                "Patient's Mobile: "+getPatient().getMobile_number()+" "+
                "Date: "+getDate()+" "+
                "Time Slot: "+getStart_time()+" to "+(Integer.parseInt(getStart_time())+getDuration())+" "+
                "Notes: "+getNote()+" "+
                "Cost: "+getCost();

    }
}
