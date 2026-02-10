package com.example.demo.service;

import com.example.demo.entity.EmailEntity;
import com.example.demo.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailHistoryService {

    private final EmailRepository emailRepository;

    public void save(EmailEntity emailEntity) {
        emailRepository.save(emailEntity);
    }

    public Integer getCodeByEmail(String email) {
       return emailRepository.findByEmail(email);
    }
}
