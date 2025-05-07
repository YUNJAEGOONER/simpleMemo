package com.example.simplememo.entity;


import com.example.simplememo.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Memo {

    private Long id;
    private String title;
    private String content;

    public void Update(MemoRequestDto memo){
        this.title = memo.getTitle();
        this.content = memo.getContent();
    }

}
