package com.green.greengram.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
//@Import({CustomFileUtils.class})
//@TestPropertySource(properties = {
//        "$file.dir=D:/download/greengram_ver1/"
//})
@SpringBootTest
@ActiveProfiles("test")
class CustomFileUtilsTest {
    @Autowired
    CustomFileUtils utils;

    @Test
    void makeFolders(){
        //CustomFileUtils utils = new CustomFileUtils("D:\\download\\greengram_ver1\\");
        String result = utils.makeFolders("ddd5");
        System.out.println("result: " + result);
    }
    @Test
    void makeRandomFileName(){
        String result = utils.makeRandomFileName();
        System.out.println("-------------------------------");
        System.out.println("makeRandomFileName: " + result);
        System.out.println("-------------------------------");
    }
    @Test
    void getExt(){
        String ext = utils.getExt("asasdfasdfdfg.asd.jpg");
        System.out.println("ext: " + ext);
    }
}