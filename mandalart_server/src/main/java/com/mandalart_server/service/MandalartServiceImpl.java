package com.mandalart_server.service;

import com.mandalart_server.dao.MandalartRepository;
import com.mandalart_server.model.Mandalart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MandalartServiceImpl implements MandalartService {

    @Autowired
    MandalartRepository mandalartRepository;

    public List<Mandalart> getMandalartList(Long userId){
    return mandalartRepository.findAllByUserId(userId);
    }

    public Mandalart getMandalart(Long mandalartId){
        return mandalartRepository.findById(mandalartId).orElse(null);
    }

}
