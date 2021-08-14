package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.Continent;
import uz.pdp.Lesson7_vazifa23.repository.ContinentRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ContinentController {
    ContinentRepository continentRepository;

    //Create
    @RequestMapping(value = "/continent", method = RequestMethod.POST)
    public String addContinent(@RequestBody Continent continent) {
        List<Continent> continentList = continentRepository.findAll();
        for (Continent searchingContinent : continentList) {
            if (searchingContinent.getName().equals(continent.getName())) {
                return "Continent already exist";
            } else {
                continentRepository.save(continent);
            }
        }
        return "Continent added";
    }

    //Read
    @RequestMapping(value = "/continent", method = RequestMethod.GET)
    public List<Continent> getContinent() {
        return continentRepository.findAll();
    }

    //Update
    @RequestMapping(value = "/continent/{id}", method = RequestMethod.PUT)
    public String editContinent(@PathVariable Integer id, @RequestBody Continent continent) {
        Optional<Continent> optionalContinent = continentRepository.findById(id);
        if (optionalContinent.isPresent()) {
            Continent editingContinent = optionalContinent.get();
            editingContinent.setName(continent.getName());
            continentRepository.save(editingContinent);
            return "Successfully edited";
        }
        return "Continent not found";
    }

    //Delete
    @RequestMapping(value = "/continent/{id}", method = RequestMethod.DELETE)
    public String deleteSubject(@PathVariable Integer id) {
        continentRepository.deleteById(id);
        boolean deleted = continentRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Subject not found";
        }
    }
}
