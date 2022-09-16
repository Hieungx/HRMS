package com.user.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageCustom<E> {
    List<E> contents;
    int pageNumber;
    int size;
    long totalElement;
    int totalPage;
}
