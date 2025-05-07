package com.example.simplememo.controller;

import com.example.simplememo.dto.MemoRequestDto;
import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/memos") //prefix
public class MemoController {

    private final Map<Long, Memo> memos = new HashMap<>();

    @PostMapping
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto dto) {
        Long memoID = memos.isEmpty() ? 1 : Collections.max(memos.keySet()) + 1;

        //dto -> memo 객체
        Memo memo = new Memo(memoID, dto.getTitle(), dto.getContent());
        memos.put(memoID, memo);

        return new MemoResponseDto(memo);

    }

    @GetMapping("/{id}")
    public MemoResponseDto findMemoById(@PathVariable Long id) {
        Memo memo = memos.get(id);
        return new MemoResponseDto(memo);
    }

    //전체 수정 -> put
    @PutMapping("/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        Memo memo = memos.get(id);
        memo.Update(requestDto);
        return new MemoResponseDto(memo);
    }

    @DeleteMapping("/{id}")
    public void deleteMemo(@PathVariable Long id) {
        memos.remove(id);
    }

}
