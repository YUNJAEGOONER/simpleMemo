package com.example.simplememo.service;

import com.example.simplememo.dto.MemoRequestDto;
import com.example.simplememo.dto.MemoResponseDto;
import com.example.simplememo.entity.Memo;
import com.example.simplememo.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service //비지니스 로직 수행
public class MemoServiceImpl implements MemoService {

    //의존성 주입
    private final MemoRepository memoRepository;
    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getTitle(), dto.getContent());
        MemoResponseDto saved = memoRepository.save(memo);
        return saved;
    }

    @Override
    public List<MemoResponseDto> getMemoList() {
        return memoRepository.getMemos();
    }

    @Override
    public MemoResponseDto findMemoByID(Long id) {
        Optional<Memo> memo = memoRepository.findMemoById(id);
        if(memo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        return new MemoResponseDto(memo.get());
    }

    @Transactional
    @Override
    public MemoResponseDto updateMemo(Long id, String title, String content) {

        //client가 title 또는 content를 입력하지 않은 경우...
        if(title == null || content == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title and content are required");
        }

        int update = memoRepository.updateMemo(id, title, content);

        if(update == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new MemoResponseDto(memoRepository.findMemoById(id).get());
    }

    @Transactional
    @Override
    public MemoResponseDto updateTitle(Long id, String title) {
        // client가 title을 입력하지 않은 경우
        if(title == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title are required");
        }

        int update = memoRepository.updateTitle(id, title);

        if(update == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new MemoResponseDto(memoRepository.findMemoById(id).get());
    }

    @Override
    public void deleteMemo(Long id) {
        int delete = memoRepository.deleteMemoById(id);
        if(delete == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
    }

}
