package com.xz.library.admin.entity.jd;

import lombok.Data;

import java.util.List;

/**
 * @author xz
 */
@Data
public class JdBusinessResult {
    private String channel;
    private Integer num;
    private List<JdNewsDetails> list;
}
