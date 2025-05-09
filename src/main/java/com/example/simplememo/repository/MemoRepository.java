package com.example.simplememo.repository;

import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import lombok.extern.java.Log;

import java.util.List;

public interface MemoRepository {
    public Memo save(Memo memo);
    public List<MemoResponseDto> getMemos();
    public Memo findMemoById(Long id);
    public void deleteMemoById(Long id);
}
