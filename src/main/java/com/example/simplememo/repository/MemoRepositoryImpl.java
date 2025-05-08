package com.example.simplememo.repository;

import com.example.simplememo.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
}
