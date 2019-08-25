package com.bitcup.boggle.api;

import lombok.Data;

@Data
class BoardDTO {
    private String letters;
    private int minWordLength;
}
