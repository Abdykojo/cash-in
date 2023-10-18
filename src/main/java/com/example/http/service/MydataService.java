package com.example.http.service;

import com.example.http.entity1.Mydata;
import org.json.simple.JSONArray;

import java.io.IOException;

public interface MydataService {

    Mydata create(String name);
}
