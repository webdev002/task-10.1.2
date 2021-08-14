package pdp.uz.task10.controller;

import javafx.beans.property.ReadOnlyObjectProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.task10.entity.Hotel;
import pdp.uz.task10.entity.Room;
import pdp.uz.task10.payload.RoomDto;
import pdp.uz.task10.repository.HotelRepository;

import java.awt.*;
import java.util.Optional;

@RestController
public class HotelController<GroupsDto> {

    @Autowired
     HotelRepository hotelRepository;
    @Autowired
     Room roomRepository;



    //Get room
    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public List<Room> getGroups(){return  roomRepository.findAll();}


    //ADDED Groups
    @RequestMapping(value = "/room", method = RequestMethod.POST)
    public String addRooms(@RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(roomDto.getClass());
        if (optionalRoom.isPresent()) {
            Room room = new Room();
            room.setHotelName(roomDto.getHotelName());
            room.setNumber(roomDto.getRoomNumber());
             room.setFloor(roomDto.getRoomFloor());
             room.setSize(roomDto.getRoomSize());
            roomRepository.save(room);
            return "Rooms added";
        }else {
            return "The room you want to add is not available in the database. " +
                    "If you want to add, you must first create a new hotel and try again.";
        }
    }

    //GET Groups BY ID
    @RequestMapping(value = "/room/{id}", method = RequestMethod.GET)
    public Room getGroupsId(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        return optionalRoom.orElseGet(Room::new);
    }

    //DELETE Groups
    @RequestMapping(value = "/groups/{id}", method = RequestMethod.DELETE)
    public String deleteRoom(@PathVariable Integer id){
        List<Room> rooms = roomRepository.findAll();
        for (Room selectedroom : rooms) {
            if (selectedroom.getId().equals(id)){
                roomRepository.deleteById(id);
                return "Room deleted";
            }
        }
        return "Room  not found";
    }

    //UPDATE Groups
    @RequestMapping(value = "/room/{id}", method = RequestMethod.PUT)
    public String updateGroups(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelName());
            if (optionalHotel.isPresent()) {
                Room editedRoom =optionalRoom.get();
                editedRoom.setHotelName(roomDto.getHotelName());
                editedRoom.setNumber(roomDto.getRoomNumber());
                editedRoom.setFloor(roomDto.getRoomFloor());
                editedRoom.setSize(roomDto.getRoomSize());

                return "Rooms edited";
            }else {
                return "The hotel you want to edit is not located in the database. " +
                        " you want to edit, you must add a new hotel first" ;
            }
        }else {
            return "Rooms not found";
        }
    }
}
