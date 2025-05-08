package com.example.simplememo.service;

import com.example.simplememo.dto.MemoRequestDto;
import com.example.simplememo.dto.MemoResponseDto;

public interface MemoService {
    MemoResponseDto saveMemo(MemoRequestDto dto);
}
