package com.example.simplememo.service;

import com.example.simplememo.dto.MemoRequestDto;
import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import com.example.simplememo.repository.MemoRepository;
import com.example.simplememo.repository.MemoRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service //비지니스 로직 수행
public class MemoServiceImpl implements MemoService {

    private final MemoRepositoryImpl memoRepository;

    public MemoServiceImpl(MemoRepositoryImpl memoRepository) {
        this.memoRepository = memoRepository;
    }


    @Override
    public MemoResponseDto saveMemo(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getTitle(), dto.getContent());
        Memo saved = memoRepository.save(memo);
        return new MemoResponseDto(saved);
    }

    @Override
    public List<MemoResponseDto> getMemoList() {
        return memoRepository.getMemos();
    }

    @Override
    public MemoResponseDto findMemoByID(Long id) {
        Memo memo = memoRepository.findMemoById(id);
        if(memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateMemo(Long id, String title, String content) {
        Memo memo = memoRepository.findMemoById(id);
        if(memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        //client가 title 또는 content를 입력하지 않은 경우...
        if(title == null || content == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title and content are required");
        }

        memo.Update(title, content);
        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateTitle(Long id, String title) {
        Memo memo = memoRepository.findMemoById(id);
        if(memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        //client가 title을 입력하지 않은 경우
        if(title == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title are required");
        }

        memo.updateTitle(title);
        return new MemoResponseDto(memo);
    }

    @Override
    public void deleteMemo(Long id) {
        Memo memo = memoRepository.findMemoById(id);
        if(memo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        memoRepository.deleteMemoById(id);
    }


}
