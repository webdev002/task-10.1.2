package uz.pdp.Lesson7_vazifa23.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.Lesson7_vazifa23.entity.*;
import uz.pdp.Lesson7_vazifa23.payload.AddressDto;
import uz.pdp.Lesson7_vazifa23.repository.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AddressController {
    AddressRepository addressRepository;
    DistrictRepository districtRepository;

    //Create
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public String addAddress(@RequestBody AddressDto addressDto) {
        Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());
        Address address = new Address();
        optionalDistrict.ifPresent(address::setDistrict);
        address.setStreet(address.getStreet());
        address.setHouseNumber(address.getHouseNumber());
        addressRepository.save(address);
        return "Address added";
    }

    //Read
    @RequestMapping(value = "/address", method = RequestMethod.GET)
    public List<Address> getAddress() {
        return addressRepository.findAll();
    }


    //Update
    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    public String editAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address editingAddress = optionalAddress.get();
            District district = editingAddress.getDistrict();
            optionalDistrict.ifPresent(editingAddress::setDistrict);
            districtRepository.save(district);
            editingAddress.setHouseNumber(addressDto.getHouseNumber());
            editingAddress.setStreet(addressDto.getStreet());
            addressRepository.save(editingAddress);
            return "Successfully edited";
        }
        return "Address not found";
    }

    //Delete
    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    public String deleteAddress(@PathVariable Integer id) {
        addressRepository.deleteById(id);
        boolean deleted = addressRepository.existsById(id);
        if (deleted) {
            return "Successfully deleted";
        } else {
            return "Address not found";
        }
    }
}
