package si.kisek.springbootexample.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name="cats")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListCats
{

    /* ta anotacija je tu zato, ker je sicer podvajal podatke*/
    @XmlTransient
    private List<Cat> macke = new ArrayList<Cat>();

    @XmlElement(name="cat")
    public List<Cat> getCats(){
        return macke;
    }

    public void setCats(List<Cat> cats){
        this.macke = cats;
    }

}
