package com.example.iknowboardserver.util.responseBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DTOResponseBody<T> {
    private T data;
    private String status;
}
