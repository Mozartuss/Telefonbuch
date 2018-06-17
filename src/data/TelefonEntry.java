package data;

import javafx.beans.property.SimpleStringProperty;

public class TelefonEntry {

    private final SimpleStringProperty lastName = new SimpleStringProperty();
    private final SimpleStringProperty firstName = new SimpleStringProperty();
    private final SimpleStringProperty number = new SimpleStringProperty();


    public TelefonEntry(String lastName, String firstName, String number) {
        this.lastName.set(lastName);
        this.firstName.set(firstName);
        this.number.set(number);
    }


    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String value) {
        lastName.set(value);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String value) {
        firstName.set(value);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String value) {
        number.set(value);
    }

    public String toString() {
        return getFirstName() + getLastName() + getNumber();
    }

}
