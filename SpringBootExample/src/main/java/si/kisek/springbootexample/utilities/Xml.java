package si.kisek.springbootexample.utilities;

import si.kisek.springbootexample.model.Cat;
import si.kisek.springbootexample.model.ListCats;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Xml {
//    public static void toXml(Cat value){
//        try {
//            String name = value.getName();
//
//            File file = new File("macke.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(Cat.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(value,file);
//            jaxbMarshaller.marshal(value, System.out);
//
//
//        } catch (JAXBException e) {
//        e.printStackTrace();
//        }
//    }

    public static void toXml(ListCats value){
        try {
            File file = new File("cats.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(ListCats.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(value,file);
            /*jaxbMarshaller.marshal(value, System.out);*/


        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void fromXml(File file){
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Cat.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Cat Cat = (Cat) jaxbUnmarshaller.unmarshal(file);
            System.out.println(Cat);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
