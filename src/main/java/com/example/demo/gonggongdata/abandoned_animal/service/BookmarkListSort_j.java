package com.example.demo.gonggongdata.abandoned_animal.service;

import lombok.Data;

@Data
public class BookmarkListSort_j implements Comparable<BookmarkListSort_j> {

    private int number;
    private String desertionNo;

    @Override
    public int compareTo(BookmarkListSort_j o) {
        return this.number - o.getNumber();
    }
}
