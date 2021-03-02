package etu.etu.web.shared;

import java.io.Serializable;
public class MedicineReader implements Serializable {
    private static final long serialVersionUID = 1L;
    private String specialty;
    private String doctorName;
    private boolean isVisited;

    public MedicineReader() {
    }

    public MedicineReader(String _specialty, String _doctorName, boolean _isVisited) {
        this.isVisited = _isVisited;
        this.doctorName = _doctorName;
        this.specialty = _specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setAuthor(String author) {
        this.specialty = specialty;
    }
}