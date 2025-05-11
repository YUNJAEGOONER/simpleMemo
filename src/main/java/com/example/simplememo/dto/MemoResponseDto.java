package com.example.simplememo.dto;

import com.example.simplememo.entity.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemoResponseDto {

    //요구 사항에 따라 달라짐
    //id가 포함된 응답이 필요한 경우 id를 포함
    //그렇지 않다면 포함하지 X

    private Long id;
    private String title;
    private String content;


    public MemoResponseDto(Memo memo) {
        id = memo.getId();
        title = memo.getTitle();
        content = memo.getContent();
    }
}
