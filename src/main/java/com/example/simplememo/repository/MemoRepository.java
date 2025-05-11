package com.example.simplememo.repository;

import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Optional;

public interface MemoRepository {
    MemoResponseDto save(Memo memo);
    List<MemoResponseDto> getMemos();
    Optional<Memo> findMemoById(Long id);

    int updateMemo(Long id, String title, String content);
    int updateTitle(Long id, String title);

    int deleteMemoById(Long id);
}
