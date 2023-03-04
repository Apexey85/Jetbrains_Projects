package contacts;

import contacts.Types.ContactRecord;

import java.util.ArrayList;
import java.util.List;

public class AppController {
    private List<ContactRecord> contact = new ArrayList<>();
    private ContactRecord record;

    public AppController () {
    }

    public Boolean AddRecord(ContactRecord record) {
        try {
            this.contact.add(record);
        } catch (Exception e) {
            e.getMessage();
            return false;
        } finally {
            return true;
        }
    }
}
