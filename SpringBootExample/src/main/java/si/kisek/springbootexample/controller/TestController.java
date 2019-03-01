package si.kisek.springbootexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.kisek.springbootexample.model.Cat;
import si.kisek.springbootexample.model.ListCats;
import si.kisek.springbootexample.model.Owner;
import si.kisek.springbootexample.services.CatService;
import si.kisek.springbootexample.services.OwnerService;
import si.kisek.springbootexample.utilities.Json;
import si.kisek.springbootexample.utilities.Xml;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {


/* //////////////////////////////////////////
        REST SERVICE Z UPORABO BAZE
 ////////////////////////////////////////// */
private final OwnerService ownerService;
private final CatService catService;

    public TestController(OwnerService ownerService, @Autowired CatService catService) {
        this.ownerService = ownerService;
        this.catService = catService;
    }




    @RequestMapping(
            value="/api/owners",
            consumes="application/json",
            method = RequestMethod.POST)
    public ResponseEntity<?> addOwner(@RequestBody String body){

        ownerService.addOwner(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value="/api/owners",
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Owner>> getAllOwners() {
        return new ResponseEntity<>(ownerService.getAllOwners(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/owners/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Owner> getOwnerWithId(@PathVariable Long id) {
        return new ResponseEntity<>(ownerService.getOwnerWithId(id), HttpStatus.OK);
    }

    @RequestMapping(
            value="/api/owners",
            params = {"name"},
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Owner>> findOwnerWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(ownerService.findOwnerWithName(name), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/owners/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Owner> updateOwnerFromDB(@PathVariable("id") long id, @RequestBody Owner owner) {


        return new ResponseEntity<Owner>(ownerService.updateOwnerFromDB(id, owner), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/owners/{id}",
            method = RequestMethod.DELETE)
    public void deleteOwnerWithId(@PathVariable Long id) {
        ownerService.deleteOwnerWithId(id);
    }

    @RequestMapping(
            value = "api/owners",
            method = RequestMethod.DELETE)
    public void deleteAllOwners() {
        ownerService.deleteAllOwners();
    }







    @RequestMapping(
            value="/api/cats",
            consumes="application/json",
            method = RequestMethod.POST)
    public ResponseEntity<?> addCat(@RequestBody String body){

        catService.addCat(body);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(
            value="/api/cats",
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Cat>> getAllCats() {
        return new ResponseEntity<>(catService.getAllCats(), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/cats/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<Cat> getCatWithId(@PathVariable Long id) {
        return new ResponseEntity<>(catService.getCatWithId(id), HttpStatus.OK);
    }

    @RequestMapping(
            value="/api/cats",
            params = {"name"},
            method = RequestMethod.GET)
    public ResponseEntity<Collection<Cat>> findCatWithName(@RequestParam(value = "name") String name) {
        return new ResponseEntity<>(catService.findCatWithName(name), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/cats/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Cat> updateCatFromDB(@PathVariable("id") long id, @RequestBody Cat cat) {


        return new ResponseEntity<Cat>(catService.updateCatFromDB(id, cat), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/cats/{id}",
            method = RequestMethod.DELETE)
    public void deleteCatWithId(@PathVariable Long id) {
        catService.deleteCatWithId(id);
    }

    @RequestMapping(
            value = "/api/cats",
            method = RequestMethod.DELETE)
    public void deleteAllCats() {
        catService.deleteAllCats();
    }











/* //////////////////////////////////////////
 PROBAVANJE REST SERVICA BREZ UPORABE BAZE
 ////////////////////////////////////////// */



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

