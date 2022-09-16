package com.user.domain.utils;

import com.user.domain.common.PageCustom;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageUtils<E> {
    public PageCustom getPage(List<E> contents, Page page) {
        PageCustom pageCustom = new PageCustom(contents, page.getNumber() + 1, page.getSize(), page.getTotalElements(), page.getTotalPages());
        return pageCustom;
    }
}
