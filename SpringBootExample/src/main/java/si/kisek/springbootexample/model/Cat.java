package si.kisek.springbootexample.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;

@Entity
@Table(name="Cat")
public class Cat {

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private boolean hungry;

    @JsonCreator
    public Cat(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("hungry") boolean hungry) {
        this.name = name;
        this.description = description;
        this.hungry = hungry;
    }

    public Cat() {}

    @JsonProperty("name")
    @XmlAttribute(name="name")
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
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hungry='" + hungry + '\'' +
                '}';
    }

}
