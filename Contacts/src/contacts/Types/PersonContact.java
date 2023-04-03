package contacts.Types;


import java.time.LocalDate;

public class PersonContact extends Contact{

    private String surname;
    private LocalDate birthday;
    private String gender;

    private PersonContact(String name, String surname, LocalDate birthday, String gender, String phoneNumber) {
        super(name, phoneNumber);
        this.birthday = birthday;
        this.surname = surname;
        this.gender = gender;
    }

    public String getGender() {
        return gender.equals("") ? "[no data]" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        return birthday == null ? "[no data]" : birthday.toString();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public static class Builder {
        private String name;
        private String surname;
        private LocalDate birthday;
        private String phoneNumber;
        private String gender;

        public Builder() {}

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname){
            this.surname = surname;
            return this;
        }

        public Builder setBirthday(String birthday){
            if (!birthday.equals("")) {
                this.birthday = LocalDate.parse(birthday);
            }
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }
        public Builder setPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public PersonContact build(){
            return new PersonContact(name, surname, birthday, gender, phoneNumber);
        }
    }

}
