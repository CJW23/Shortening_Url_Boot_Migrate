package com.cjw.shorturl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConstConfig {
    SAME_PASSWORD(1),
    WRONG_PASSWORD(2),
    CORRECT_PASSWORD(3),
    DELETE_COMPLETE(4);
    private final int val;
}
