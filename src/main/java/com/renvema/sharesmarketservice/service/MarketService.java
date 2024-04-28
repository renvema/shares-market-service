package com.renvema.sharesmarketservice.service;

import com.renvema.sharesmarketservice.controller.dto.ShareDto;
import com.renvema.sharesmarketservice.dao.entity.Share;
import com.renvema.sharesmarketservice.dao.repository.ShareRepository;
import com.renvema.sharesmarketservice.mapper.ShareMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketService {

    private final ShareMapper shareMapper;
    private final ShareRepository shareRepository;

    public void processOperation(ShareDto share) {
        switch (share.getOperation()) {
            case BUY:
                processBuy(share);
                break;
            case SELL:
                processSell(share);
                break;
        }
    }

    public void processBuy(ShareDto newShare) {

        Optional<Share> existingShare = shareRepository.findByUsernameAndTicker(newShare.getUsername(), newShare.getTicker());

        if (existingShare.isPresent()) {
            Share share = existingShare.get();
            int newAmount = share.getAmount() + newShare.getAmount();
            share.setAmount(newAmount);
            shareRepository.save(share);
        } else {
            shareRepository.save(shareMapper.toEntity(newShare));
        }
    }

    private void processSell(ShareDto newShare) {
        Optional<Share> existingShare = shareRepository.findByUsernameAndTicker(newShare.getUsername(), newShare.getTicker());
        if (existingShare.isPresent()) {
            Share share = existingShare.get();
            int existingAmount = share.getAmount();
            if (share.getAmount() >= newShare.getAmount()) {
                share.setAmount(existingAmount - newShare.getAmount());
                shareRepository.save(share);
            } else {
                log.error(String.format("User %s try to sell %d %s shares, but has only  %d",
                        newShare.getUsername(), newShare.getAmount(), newShare.getTicker(), share.getAmount()));
            }

        } else {
            log.error(String.format("User %s try to sell %d %s shares, but has 0 of them",
                    newShare.getUsername(), newShare.getAmount(), newShare.getTicker()));
        }
    }
}
