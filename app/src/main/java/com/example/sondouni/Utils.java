package com.example.sondouni;

import android.widget.Toast;

import com.example.sondouni.ch07.BookPersonActivity;

public class Utils {
    public static int parseStringToInt(String val){
        int intVal = 0;
        try{
            intVal = Integer.parseInt(val);
        }catch (Exception e){
            e.printStackTrace();

        }finally {

        }
        return intVal;







        /*
        //예외처리
        try{

        }catch (Exception e){//예외발생시 실행되고싶은것들을 작성
            e.printStackTrace();//에러내용을 로그에 찍는다
        }finally {
            //옵션,에러가 터지든 안터지든 무조건 실행
        }
        */


    }
}
