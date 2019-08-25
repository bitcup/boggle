package com.bitcup.boggle.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
class SolutionDTO {
    private List<String> validWords;
    private long millis;
}
