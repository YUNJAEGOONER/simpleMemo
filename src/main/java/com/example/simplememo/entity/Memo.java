package com.example.simplememo.entity;


import com.example.simplememo.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@Setter
@Getter
@AllArgsConstructor //모든 필드(멤버 변수)를 가진 생성자
public class Memo {

    @Setter
    private Long id;
    private String title;
    private String content;

    //constructor
    public Memo(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public void Update(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title){
        this.title = title;
    }



}
