package com.green.greengram.feed;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed")
@Slf4j
@Tag(name = "Feed 피드", description = "Feed CRUD")
public class FeedController {
    private final FeedService service;

}
