package com.example.Surveillance.Util;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private long number;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;



}
