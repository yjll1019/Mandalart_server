package com.mandalart_server.controller;

import com.mandalart_server.model.Mandalart;
import com.mandalart_server.service.MandalartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mandalart")
public class MandalartController {

    @Autowired
    MandalartService mandalartService;

    //cookie에서 get하면 사용자 정보 읽을 수 있음. 그러면 userId 따로 받지 않아도 된다. -> 일단 받고 security 적용 시 수정.
    //사용자가 작성한 만다라트 리스트 get
    @GetMapping("list/{userId}")
    public List<Mandalart> getMandalartList(@PathVariable("userId") Long userId){
        return mandalartService.getMandalartList(userId);
    }

    //만다라트 조회
    @GetMapping("/{mandalartId}")
    public Mandalart getMandalart(@PathVariable("mandalartId") Long mandalartId){
        return mandalartService.getMandalart(mandalartId);
    }
}
