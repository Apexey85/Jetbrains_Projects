package contacts.Types;

import java.time.LocalDateTime;

public abstract class Contact {
    private String phoneNumber;
    private String name;
    private final String createdTime;
    private String editTime;
    public Contact(String name, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.createdTime = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));
        this.editTime = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        setEditTime();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setEditTime();
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime() {
        this.editTime = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime() {
        this.editTime = String.valueOf(LocalDateTime.now().withSecond(0).withNano(0));
    }
}
