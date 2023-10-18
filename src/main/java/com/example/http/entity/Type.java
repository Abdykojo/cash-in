package com.example.http.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public enum Type {
    PHONE("1"),
    CARDS("2");
    private String type;
}
