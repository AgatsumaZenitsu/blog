package com.zzz.vo;

import com.zzz.pojo.Blog;
import lombok.Data;

@Data
public class BlogQuery {
    private String title;

    private Long typeId;

    private boolean recommend;
}
