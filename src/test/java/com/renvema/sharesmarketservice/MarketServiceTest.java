package com.renvema.sharesmarketservice;

import com.renvema.sharesmarketservice.controller.dto.ShareDto;
import com.renvema.sharesmarketservice.dao.entity.Operation;
import com.renvema.sharesmarketservice.dao.entity.Share;
import com.renvema.sharesmarketservice.dao.repository.ShareRepository;
import com.renvema.sharesmarketservice.mapper.ShareMapper;
import com.renvema.sharesmarketservice.service.MarketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.renvema.sharesmarketservice.dao.entity.Operation.BUY;
import static com.renvema.sharesmarketservice.dao.entity.Operation.SELL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarketServiceTest {

    private static final String USERNAME = "kevin.evib";
    private static final String TICKER = "APPL";
    @Mock
    private ShareMapper shareMapper;

    @Mock
    private ShareRepository shareRepository;

    @InjectMocks
    private MarketService marketService;

    @Test
    void processOperationBuyExistingShare() {
        ShareDto shareDto = createShareDto(USERNAME, TICKER, 200, BUY);

        Share existingShare = getExistingShare(USERNAME, TICKER, 100);

        when(shareRepository.findByUsernameAndTicker(anyString(), anyString())).thenReturn(Optional.of(existingShare));

        marketService.processOperation(shareDto);

        verify(shareRepository, times(1)).findByUsernameAndTicker(USERNAME, TICKER);
        verify(shareRepository, times(1)).save(existingShare);
        assertThat(existingShare.getAmount()).isEqualTo(300); // 100 existing + 200 bought
    }
    

    @Test
    void processOperationSellExistingShare() {
        ShareDto shareDto = createShareDto(USERNAME, TICKER, 200, SELL);

        Share existingShare = getExistingShare(USERNAME, TICKER, 1000);

        when(shareRepository.findByUsernameAndTicker(anyString(), anyString())).thenReturn(Optional.of(existingShare));

        marketService.processOperation(shareDto);

        verify(shareRepository, times(1)).findByUsernameAndTicker(USERNAME, TICKER);
        verify(shareRepository, times(1)).save(existingShare);
        assertThat(existingShare.getAmount()).isEqualTo(800); // 1000 existing - 200 sold
    }

    private static Share getExistingShare(String username, String ticker, int amount) {
        Share existingShare = new Share();
        existingShare.setUsername(username);
        existingShare.setTicker(ticker);
        existingShare.setAmount(amount);
        return existingShare;
    }

    private static ShareDto createShareDto(String username, String ticker, Integer amount, Operation operation) {
        ShareDto shareDto = new ShareDto();
        shareDto.setUsername(username);
        shareDto.setTicker(ticker);
        shareDto.setAmount(amount);
        shareDto.setOperation(operation);
        return shareDto;
    }
}
