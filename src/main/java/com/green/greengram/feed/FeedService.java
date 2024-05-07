package com.green.greengram.feed;

import com.green.greengram.common.CustomFileUtils;
import com.green.greengram.feed.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedService {
    private final FeedMapper mapper;
    private final CustomFileUtils customFileUtils;

    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p){ //feed pk 값 리턴.
        int result = mapper.postFeed(p);

        FeedPicPostDto picDto = FeedPicPostDto.builder().feedId(p.getFeedId()).build();
        try {
            String path = String.format("feed/%d", p.getFeedId());
            customFileUtils.makeFolders(path);
            for(MultipartFile pic : pics) {
                String saveFileName = customFileUtils.makeRandomFileName(pic);
                picDto.getFileNames().add(saveFileName);
                String target = String.format("%s/%s", path, saveFileName);
                customFileUtils.transferTo(pic, target);
            }
            int affectedRowsPics = mapper.postFeedPics(picDto);

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Feed 등록 오류");
        }

        return FeedPostRes.builder()
                .feedId(p.getFeedId())
                .pics(picDto.getFileNames())
                .build();
    }

    public List<FeedGetRes> getFeed(FeedGetReq p){
        List<FeedGetRes> list = mapper.getFeed(p);
        for(FeedGetRes res : list){
            List<String> pics = mapper.getFeedPicsByFeedId(res.getFeedId());
            res.setPics(pics);
        }
        return list;
    }
}
