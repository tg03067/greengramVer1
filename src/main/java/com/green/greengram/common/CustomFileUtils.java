package com.green.greengram.common;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Getter
@ToString
public class CustomFileUtils {
    private final String uploadPath ;
    //value = xml에 저장된값을 사용하겠다.
    public CustomFileUtils(@Value("${file.dir}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    //폴더 만들기
    public String makeFolders(String path){
        File folder = new File(uploadPath, path);
        folder.mkdirs();
        return folder.getAbsolutePath();
    }

    //파일명에서 확장자 추출

    public String getExt(String fileName){
//        int idx = fileName.indexOf(".");
//        int idx2 = fileName.lastIndexOf(".");
//        System.out.println("idx: " + idx);
//        System.out.println("idx2: " + idx2);
//
//        System.out.println(fileName.substring(idx2 + 1));
//        String[] str = fileName.split("\\.");
//        System.out.println(str[str.length - 1]);
//
        int idx = fileName.lastIndexOf(".");
        return fileName.substring(idx);
    }

    //UUID 랜덤 파일명
    public String makeRandomFileName(){
        return UUID.randomUUID().toString();
    }

    //랜덤 파일명 with 확장자 만들기
    public String makeRandomFileName(String fileName){
        return makeRandomFileName() + getExt(fileName);
    }
    //랜덤 파일명 with 확장자 만들기 using MultipartFile
    public String makeRandomFileName(MultipartFile mf){
        return mf == null ? null : mf.isEmpty() ? null : makeRandomFileName(mf.getOriginalFilename());
    }

    //파일 저장 ( target : 경로 / 파일명 )
    public void transferTo(MultipartFile mf, String target) throws Exception {
        File saveFile = new File(uploadPath, target); // 해당경로의 파일명으로 mf를 이동 시켜줌.
        // 절대 경로 지정, 최종 경로
        mf.transferTo(saveFile);
    }
}