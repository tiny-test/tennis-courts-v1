package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.reservations.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository=null;
    public List<Guest> findAll(){
        return guestRepository.findAll();
    }

    public  Guest findById(Long id){
        Optional<Guest> guest= guestRepository.findById(id);
        if(!guest.isPresent()){
            throw new EntityNotFoundException("id: "+id);
        }
        return guest.get();
    }

    public void deleteGuest(Long id){
         if(id != null){
             try{
                 guestRepository.deleteById(id);
             }catch(Exception e){
                 throw new EntityNotFoundException("id: "+id);
             }
         }
        guestRepository.deleteById(id);
    }

    public Guest save(Guest guest){
        return guestRepository.save(guest);
    }


}
