package com.yc.piclib.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;


@Data      //  lombok 注解，节省getter, setter
@Table(name = "pic")
public class Pic {
    @Id  // JPA注解,指定此属性为表中的主键
    private Integer id;   //注意: 对应的数据表中的字段名叫 book_id
    private String path;
    private String description;

}
