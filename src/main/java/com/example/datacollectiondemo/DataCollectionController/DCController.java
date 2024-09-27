package com.example.datacollectiondemo.DataCollectionController;


import com.example.datacollectiondemo.DataCollectionModel.Data_ShangHu;
import com.example.datacollectiondemo.DataCollectionModel.ShuffledUsersID;
import com.example.datacollectiondemo.DataCollectionService.DCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*controller*/
@Controller
@RequestMapping("/users")
public class DCController {

    @Autowired
    private DCService dcService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUserData(@RequestBody Data_ShangHu data_shangHu){


        if (data_shangHu == null || data_shangHu.getUserSH_ID() == null) {
            return ResponseEntity.badRequest().body("Invalid input");
        }

        dcService.saveUserData(data_shangHu);

        return ResponseEntity.ok("UserData saved successfully!");
    }

    @PostMapping("/saveID")
    public ResponseEntity<String> saveUserID(@RequestBody ShuffledUsersID shuffledUsersID){

        dcService.saveUserID(shuffledUsersID);

        return ResponseEntity.ok("UserID saved successfully!");
    }



}
