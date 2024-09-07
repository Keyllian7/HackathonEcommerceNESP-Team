package com.burguer_server.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageModel {
    private String from;
    private String body;
}
