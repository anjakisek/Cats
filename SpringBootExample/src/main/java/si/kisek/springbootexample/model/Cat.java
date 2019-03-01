package si.kisek.springbootexample.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="hungry")
    private boolean hungry;

    @JsonCreator
    public Cat(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("hungry") boolean hungry) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hungry = hungry;
    }



    public Cat() {}

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


    @JsonProperty("description")
    @XmlElement
    public String getDescription() {
        return description;
    }




    public void setDescription(String description) {
        this.description = description;
    }


    @JsonProperty("hungry")
    @XmlElement
    public boolean getHungry() {
        return hungry;
    }



    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }


    @Override
    public String toString() {
        return "Cat {" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hungry='" + hungry + '\'' +
                '}';
    }

}
