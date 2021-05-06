package com.tenniscourts.guests;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@AllArgsConstructor
public class GuestController {

    private final GuestService guestService;

    @GetMapping("/guests/{id}")
    public ResponseEntity<Guest> findGuestById(@PathVariable(value="id") Long id){
        Optional<Guest> guest= Optional.ofNullable(guestService.findById(id));

        if(guest.isPresent()){
            return ResponseEntity.ok().body(guest.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/guests/{id}")
    public void deleteGuest(@PathVariable Long id){
        guestService.deleteGuest(id);
    }

    @PostMapping("/guests/{id}")
    public ResponseEntity<Object> updateGuest(@RequestBody Guest guest,@PathVariable Long id){

             Optional<Guest> guestUser= Optional.ofNullable(guestService.findById(id));
             if(!guestUser.isPresent()){
                 return ResponseEntity.notFound().build();

             }
        guest.setId(id);
        guestService.save(guest);
        return ResponseEntity.noContent().build();

    }

}
