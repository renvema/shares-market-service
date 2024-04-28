package com.renvema.sharesmarketservice.service;

import com.renvema.sharesmarketservice.controller.dto.ShareDto;
import com.renvema.sharesmarketservice.dao.repository.ShareRepository;
import com.renvema.sharesmarketservice.mapper.ShareMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShareService {

    private final ShareRepository shareRepository;

    private final ShareMapper shareMapper;

    public List<ShareDto> getAllSharesByUser(String username) {
        return shareRepository.findByUsername(username)
                .stream()
                .map(shareMapper::fromEntity)
                .collect(Collectors.toList());
    }
}
