package com.demo.dto.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 分页对象
 *
 * @author hst on 2016/12/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {

    @Min(1)
    private Integer pageNum = 1;
    @Max(50)
    private Integer pageSize = 10;
}
