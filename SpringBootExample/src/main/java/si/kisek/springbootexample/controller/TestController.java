package si.kisek.springbootexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import si.kisek.springbootexample.model.Cat;
import si.kisek.springbootexample.model.ListCats;
import si.kisek.springbootexample.utilities.Json;
import si.kisek.springbootexample.utilities.Xml;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static List<String> data = new ArrayList<>();

    private static ListCats tableCats = new ListCats();


    /*   /test  - uporablja navaden text */
    @RequestMapping(
            value = "/test",
            method = RequestMethod.POST,
            consumes = "text/plain",
            produces = "application/json"
    )
    public ResponseEntity<String> testPost(@RequestBody String string) {
        System.out.println(string);

        data.add(string);

        return ResponseEntity.ok("{ \"received\": \"" + string + "\"}");
    }

    @RequestMapping(
            value = "/test",
            method = RequestMethod.GET)
    public ResponseEntity<String> testGet() {

        List<String> withQuotes = data.stream().map(str -> "\"" + str + "\"").collect(Collectors.toList());

        String response = "[" + String.join(", ", withQuotes) + "]";


        return ResponseEntity.ok(response);
    }


    /* /cats   - uporablja Json*/
    @RequestMapping(
            value = "/cats",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> catsPost(@RequestBody String body) {
        Cat cat = Json.fromJson(body, Cat.class);
          List<Cat> cats = tableCats.getCats();
        cats.add(cat);
        tableCats.setCats(cats);
        String respond = cat.getName() + " added.";
        return ResponseEntity.ok(respond);
    }

    @RequestMapping(
            value = "/cats",
            method = RequestMethod.GET,
            produces = "text/plain")
    public ResponseEntity<String> catsGet() {
        String response = Json.toJson(tableCats.getCats());
        return ResponseEntity.ok(response);
    }



    /*   /catsAll  - uporeblja xml datoteko */
    @RequestMapping(
            value = "/catsAll",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<String> catsAllGet() {
        Xml.toXml(tableCats);
        String response = "Xml file has been made.";
        return ResponseEntity.ok(response);
    }










}

