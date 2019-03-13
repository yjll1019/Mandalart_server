package com.mandalart_server.service;

import com.mandalart_server.model.Mandalart;

import java.util.List;

public interface MandalartService {
     List<Mandalart> getMandalartList(Long userId);

     Mandalart getMandalart(Long mandalartId);
}
