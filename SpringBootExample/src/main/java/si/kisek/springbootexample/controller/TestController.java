package si.kisek.springbootexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.kisek.springbootexample.model.Cat;
import si.kisek.springbootexample.model.CatRepository;
import si.kisek.springbootexample.model.ListCats;
import si.kisek.springbootexample.utilities.Json;
import si.kisek.springbootexample.utilities.Xml;

import javax.inject.Inject;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {


    /* repository for cat data base */
    private CatRepository repository;

    @Inject
    public void setRepository(CatRepository repository) {
        this.repository = repository;
    }
    @RequestMapping(
            value="/api/cats",
            consumes="application/json",
            method = RequestMethod.POST)
    public ResponseEntity<?> addCat(@RequestBody String body) {
        Cat cat = Json.fromJson(body, Cat.class);
        return new ResponseEntity<>(repository.save(cat), HttpStatus.CREATED);
    }

    @RequestMapping(
            value="/api/cats",
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Cat>> getAllCats() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/cats/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Cat> getCatWithId(@PathVariable Long id) {
        return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(
            value="/api/cats",
            params = {"name"},
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Cat>> findCatWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(repository.findByName(name), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/cats/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Cat> updateCatFromDB(@PathVariable("id") long id, @RequestBody Cat cat) {

        Cat currentCat = repository.findById(id).get();
        currentCat.setName(cat.getName());
        currentCat.setDescription(cat.getDescription());
        currentCat.setHungry(cat.getHungry());

        return new ResponseEntity<>(repository.save(currentCat), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/cats/{id}",
            method = RequestMethod.DELETE)
    public void deleteCatWithId(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @RequestMapping(
            method = RequestMethod.DELETE)
    public void deleteAllCats() {
        repository.deleteAll();
    }















    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static List<String> data = new ArrayList<>();

    private static List<Cat> tableCats = new  ArrayList<Cat>();


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
            consumes = "application/json",
            produces = "text/plain",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> catsPost(@RequestBody String body) {
        Cat cat = Json.fromJson(body, Cat.class);
        tableCats.add(cat);
        String respond = cat.getName() + " added.";
        return ResponseEntity.ok(respond);
    }

    @RequestMapping(
            value = "/cats",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<String> catsGet() {
        String response = Json.toJson(tableCats);
        return ResponseEntity.ok(response);
    }



    /*   /catsAll  - uporeblja xml datoteko */
    @RequestMapping(
            value = "/catsAll",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<String> catsAllGet() {
        ListCats listCats = new ListCats();
        listCats.setCats(tableCats);
        Xml.toXml(listCats);
        String response = "Xml file has been made.";
        return ResponseEntity.ok(response);
    }










}

