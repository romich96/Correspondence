package ua.com.splan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OutOrganization")
public class OutOrganization {
    @Id
    @GeneratedValue
    private long id;

    private String Name;

    private String Author;

    public OutOrganization() {}

    public OutOrganization(String name) {
        Name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
