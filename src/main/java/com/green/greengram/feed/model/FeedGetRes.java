package com.green.greengram.feed.model;

import lombok.*;

import java.util.List;


@Getter
@Setter
public class FeedGetRes {
    private long feedId;
    private long writerId;
    private String writerNm;
    private String writerPic;
    private String contents;
    private String location;
    private String createdAt;

    private List<String> pics;
}

