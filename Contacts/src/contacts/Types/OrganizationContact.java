package contacts.Types;


import java.time.LocalDateTime;

public class OrganizationContact extends Contact{
    private String address;

    private OrganizationContact(String name, String address, String phoneNumber) {
        super(name, phoneNumber);
        this.address = address;

    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        this.setEditTime();

    }

    public static class Builder {
        private String name;
        private String address;
        private String phoneNumber;

        public Builder() {}


        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setAddress(String address){
            this.address = address;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }


        public OrganizationContact build(){
            return new OrganizationContact(name, address, phoneNumber);
        }
    }

}
