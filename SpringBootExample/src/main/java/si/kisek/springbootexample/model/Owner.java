package si.kisek.springbootexample.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="cats")
    /*@OneToMany(mappedBy = "id")*/
    private List<Cat> cats = new ArrayList<Cat>();


    @JsonCreator
    public Owner(@JsonProperty("name") String name) {
        this.name = name;
        /*this.cats = cats;*/
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



    /*@JsonProperty("cats")
    @XmlElement
    public List<Cat> getCats() {
        return cats;
    }



    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }
*/

    @Override
    public String toString() {
        return "Owner {" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

}
