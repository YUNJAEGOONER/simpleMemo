package com.example.simplememo.service;

import com.example.simplememo.dto.MemoRequestDto;
import com.example.simplememo.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {
    MemoResponseDto saveMemo(MemoRequestDto dto);
    List<MemoResponseDto> getMemoList();
    MemoResponseDto findMemoByID(Long id);
    MemoResponseDto updateMemo(Long id, String title, String content);
    MemoResponseDto updateTitle(Long id, String title);
    void deleteMemo(Long id);
}
