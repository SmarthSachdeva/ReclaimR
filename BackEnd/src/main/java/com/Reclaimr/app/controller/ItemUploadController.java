package com.Reclaimr.app.controller;

import com.Reclaimr.app.models.ApiResponse;
import com.Reclaimr.app.models.LnFItems;
import com.Reclaimr.app.models.User;
import com.Reclaimr.app.repository.ItemRepository;
import com.Reclaimr.app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/lf")
public class ItemUploadController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/upload/{userId}")
    public ResponseEntity<?> uploadItem(@RequestParam String userId , @RequestBody LnFItems item){

        if(userId==null){
            String msg = "UserId not passed";
            log.error(msg);
            ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST , msg , null);
            return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
        }
        if(item == null){
            String msg = "Item body is invalid";
            log.error(msg);
            ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST , msg , null);
            return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userRepository.findUser(userId);
        if(user.isPresent()){
            itemRepository.save(item);
            ApiResponse response = new ApiResponse(HttpStatus.OK,"Success" , item);
            return new ResponseEntity<>(response , HttpStatus.OK);
        }else{
            ApiResponse errorResponse = new ApiResponse(HttpStatus.BAD_REQUEST , "Failed to find user" , null);
            return new ResponseEntity<>(errorResponse , HttpStatus.BAD_REQUEST);
        }
    }
}

