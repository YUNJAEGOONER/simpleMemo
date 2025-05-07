package com.example.simplememo.controller;

import com.example.simplememo.dto.MemoRequestDto;
import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/memos") //prefix
public class MemoController {

    private final Map<Long, Memo> memos = new HashMap<>();

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {
        Long memoID = memos.isEmpty() ? 1 : Collections.max(memos.keySet()) + 1;

        //dto -> memo 객체
        Memo memo = new Memo(memoID, dto.getTitle(), dto.getContent());
        memos.put(memoID, memo);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> getMemos() {
        List<MemoResponseDto> memoResponseDtos = new ArrayList<>();
        for (Memo memo : memos.values()) {
            memoResponseDtos.add(new MemoResponseDto(memo));
        }
//        memoResponseDtos = memos.values().stream()
//                .map(MemoResponseDto::new).toList();
        return new ResponseEntity<>(memoResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id) {
//        Optional<Memo> memo = Optional.ofNullable(memos.get(id));
//        if(memo.isPresent()){
//            return new ResponseEntity<>(new MemoResponseDto(memo.get()), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Memo memo = memos.get(id);
        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    //전체 수정 -> put
    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {

        Memo memo = memos.get(id);

        if (memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(requestDto.getTitle() == null || requestDto.getContent() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.Update(requestDto);

        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> udpateTitle(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        Memo memo = memos.get(id);
        if(memo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if(requestDto.getTitle() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memo.updateTitle(requestDto.getTitle());
        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
        if(memos.containsKey(id)){
            memos.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
