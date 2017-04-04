/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OOPPackage;

import java.util.*;

/**
 *
 * @author Thomas Sund Mjåland
 */
public class PersonRegister {

    private final List<Person> list = new ArrayList<>();

    /**
     *
     * @param person, the Person-object to be added
     * @return true if the object was added, if the object already is in the
     * list, then it is not added and it returns false
     */
    public boolean add(Person person) {
        if (!contains(person)) {
            list.add(person);
            return true;
        }
        return false;
    }

    /**
     * Removes a given person-object from the registry
     *
     * @param person, The person to be removed
     * @return True if the person was found in the list and removed, returning
     * false otherwise
     */
    public boolean remove(Person person) {
        return list.remove(person);
    }

    /**
     * Returns true if the given Person-object is found in list
     *
     * @param person, The person to be searched for
     * @return True if the object is present in the list, false if not
     */
    public boolean contains(Person person) {
        return list.contains(person);
    }

    /**
     * Returns an iterator to all objects registered in this register
     *
     * @return Iterator to all objects registered in this register
     */
    public Iterator<Person> iterator() {
        return list.iterator();
    }

    /**
     * Returns an iterator iterating through items of given class, please note
     * that manipulations done through the iterator such as delete will not
     * affect the object in the person-register.
     *
     * @param <T>, The generic class type wanted
     * @param wantedClass, class instance to search for
     * @return Iterator iterating all objects in register of given class type
     */
    public <T> Iterator<T> getIteratorStrict(Class<T> wantedClass) {
        return getListStrict(wantedClass).iterator();
    }

    /**
     * Returns a list of all items of the given class
     *
     * @param <T>
     * @param wantedClass, instance of the class type to be searched for
     * @return List containing all objects in person-register of given class
     */
    public <T> List<T> getListStrict(Class<T> wantedClass) {
        List<T> retList = new ArrayList<>();
        Iterator<Person> i = list.iterator();
        Person temp;

        while (i.hasNext()) {
            temp = i.next();
            if (wantedClass.equals(temp.getClass())) {
                retList.add(wantedClass.cast(temp));
            }
        }
        return retList;
    }
    

    /**
     * Returns an iterator iterating through all objects that are instances of the given class, please note
     * that manipulations done through the iterator such as delete will not
     * affect the object in the person-register.
     *
     * @param <T>, The generic class type wanted
     * @param wantedClass, class instance to search for
     * @return Iterator iterating all objects in register of given class type
     */
    public <T> Iterator<T> getIterator(Class<T> wantedClass) {
        return getListStrict(wantedClass).iterator();
    }

    /**
     * Returns a list of all items that are an instance of the given class
     *
     * @param <T>
     * @param wantedClass, instance of the class instance to be searched for
     * @return List containing all objects in person-register of given class
     */
    public <T> List<T> getList(Class<T> wantedClass) {
        List<T> retList = new ArrayList<>();
        Iterator<Person> i = list.iterator();
        Person temp;

        while (i.hasNext()) {
            temp = i.next();
            if (wantedClass.isInstance(temp)) {
                retList.add(wantedClass.cast(temp));
            }
        }
        return retList;
    }

    /**
     * Finds the first person object to fit all criterion, if a field is left
     * empty '""', then it is ignored
     *
     * @param firstName, The first name of the person to be found, if left empty
     * then first name will not be considered
     * @param lastName, The last name of the person to be found, if left empty
     * then last name will not be considered
     * @param emailAddress, The email address of the person to be found, if left
     * empty then the email address will not be considered
     * @return The person fitting the criterion, null if not found
     */
    public Person find(String firstName, String lastName, String emailAddress) {
        for (Person p : list) {
            if ((p.getFirstName().equals(firstName) || firstName.equals(""))
                    && (p.getLastName().equals(lastName) || lastName.equals(""))
                    && (p.getEmailAddress().equals(emailAddress) || emailAddress.equals(""))) {
                return p;
            }
        }
        return null;
    }

    /**
     * Finds the first person that has the searchString-content as either first
     * name, last name or email address
     *
     * @param searchString, The string representing either first name, last name
     * or email address of the person to be found
     * @return The person with a first name, last name or email address equal to
     * the given search-string
     */
    public Person find(String searchString) {
        Person p;
        p = find(searchString, "", "");
        if (p != null) {
            return p;
        }
        p = find("", searchString, "");
        if (p != null) {
            return p;
        }
        p = find("", "", searchString);
        if (p != null) {
            return p;
        }
        return null;
    }
}
