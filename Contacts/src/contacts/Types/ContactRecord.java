package contacts.Types;


public class ContactRecord {
    public String name;
    public String surname;
    public String phoneNumber;

    public ContactRecord() {
        this.name = "";
        this.surname = "";
        this.phoneNumber = "";
    }

    public void setName(String name){
        this.name = name;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
