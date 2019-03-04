package si.kisek.springbootexample.services;

import si.kisek.springbootexample.model.Cat;

import java.util.Collection;

public interface CatService {

    Collection<Cat> getAllCats();

    void addCat(String body);

    Cat getCatWithId(Long id);

    Collection<Cat> findCatWithName(String name);

    Cat updateCatFromDB(Long id, Cat cat);

    void deleteCatWithId(Long id);

    void deleteAllCats();


}
