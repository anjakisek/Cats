package si.kisek.springbootexample.services;

import si.kisek.springbootexample.model.Owner;

import java.util.Collection;

public interface OwnerService {
    Collection<Owner> getAllOwners();

    void addOwner(String body);

    Owner getOwnerWithId(Long id);

    Collection<Owner> findOwnerWithName(String name);

    Owner updateOwnerFromDB(Long id, Owner owner);

    void deleteOwnerWithId(Long id);

    void deleteAllOwners();

}
