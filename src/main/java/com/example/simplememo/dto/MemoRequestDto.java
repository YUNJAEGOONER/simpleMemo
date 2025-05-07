package com.example.simplememo.dto;

import lombok.Getter;


@Getter
public class MemoRequestDto {
    
    //memo 객체를 생성하기 위해 client로 부터 입력 받아야 하는것
    //id의 경우 서버에서 관리?...
    //요청 받을 데이터는 title과 content
    private String title;
    private String content;

}
