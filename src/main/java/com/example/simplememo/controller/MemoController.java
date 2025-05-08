package com.example.simplememo.controller;

import com.example.simplememo.dto.MemoRequestDto;
import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import com.example.simplememo.repository.MemoRepositoryImpl;
import com.example.simplememo.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos") //prefix
public class MemoController {

    private final MemoService memoService;

    //생성자 주입
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }


    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        return new ResponseEntity<>(memoService.saveMemo(dto), HttpStatus.CREATED);

    }

}
