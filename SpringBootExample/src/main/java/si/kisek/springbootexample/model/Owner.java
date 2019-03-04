package si.kisek.springbootexample.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name="name")
    private String name;

    private Set<Cat> setCats =  new HashSet<Cat>(0);

    @JsonCreator
    public Owner(@JsonProperty("name") String name) {
        this.name = name;
        this.setCats = setCats;
    }




    public Owner() {}

    @JsonProperty("id")
    @XmlAttribute(name="id")
    public long getId() {
        return this.id;
    }

    @JsonProperty("name")
    @XmlElement
    public String getName() {
        return name;
    }





    public void setName(String name) {
        this.name = name;
    }




    @Override
    public String toString() {
        return "Owner {" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
