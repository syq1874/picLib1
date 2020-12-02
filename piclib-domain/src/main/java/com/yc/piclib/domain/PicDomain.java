package com.yc.piclib.domain;

import lombok.Data;

/*
 用于页面呈现的内容
 */
@Data
public class PicDomain extends PageDomain {
    private Integer id;   //注意: 对应的数据表中的字段名叫 book_id
    private String path;
    private String description;

    /**
     * 一张图片的其它属于，通过程序获得.
     */
    private Long fileSize;
    private String extension;
    private String realPath;

    public PicDomain(Integer id, String path, String description) {
        this.id = id;
        this.path = path;
        this.description = description;
    }


    public PicDomain(){

    }
}
