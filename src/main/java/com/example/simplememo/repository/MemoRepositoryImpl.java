package com.example.simplememo.repository;

import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoRepositoryImpl implements MemoRepository {
    private final Map<Long, Memo> memos = new HashMap<>();

    @Override
    public Memo save(Memo memo) {
        Long memoId = memos.isEmpty() ? 1 : Collections.max(memos.keySet()) + 1;
        memo.setId(memoId);
        memos.put(memo.getId(), memo);
        return memo;
    }

    public List<MemoResponseDto> getMemos(){
        List<MemoResponseDto> memoResponseDtos = new ArrayList<>();
        for (Memo memo : memos.values()) {
            memoResponseDtos.add(new MemoResponseDto(memo));
        }
        return memoResponseDtos;
    }

    @Override
    public Memo findMemoById(Long id) {
        Memo found = memos.get(id);
        return found;
    }

    @Override
    public void deleteMemoById(Long id) {
        memos.remove(id);
    }

}
