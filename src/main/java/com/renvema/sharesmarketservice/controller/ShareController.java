package com.renvema.sharesmarketservice.controller;

import com.renvema.sharesmarketservice.controller.dto.ShareDto;
import com.renvema.sharesmarketservice.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/share")
@RequiredArgsConstructor
public class ShareController {

    private final ShareService shareService;

    @GetMapping("/{username}")
    public List<ShareDto> getSharesByUser(@PathVariable String username) {
        return shareService.getAllSharesByUser(username);
    }
}
