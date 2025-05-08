package com.example.simplememo.service;

import com.example.simplememo.dto.MemoRequestDto;
import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import com.example.simplememo.repository.MemoRepository;
import com.example.simplememo.repository.MemoRepositoryImpl;
import org.springframework.stereotype.Service;

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
}
