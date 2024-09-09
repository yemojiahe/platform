package com.example.Queryprocessing.service;

import com.example.CommonService.MessagingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;

class ForwardServiceTest {

    @Mock
    private MessagingService messagingService;

    @InjectMocks
    private ForwardService forwardService;

    public ForwardServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crowdCategoriesQuery() {
        // Call the method to be tested
        forwardService.crowdCategoriesQuery();
        verify(messagingService, times(1)).sendMessage("This is a test message");
    }
}