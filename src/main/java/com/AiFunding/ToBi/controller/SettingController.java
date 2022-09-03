package com.AiFunding.ToBi.controller;

import com.AiFunding.ToBi.dto.setting.ChangeAccountNameDto;
import com.AiFunding.ToBi.service.setting.SettingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/setting")
@AllArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @PostMapping("/change-account-name")
    public ResponseEntity<Void> ChangeAccountName(@RequestBody ChangeAccountNameDto accountNameDto) throws Exception {

        try {
            settingService.changeAccountName(accountNameDto);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}