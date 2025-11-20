package com.example.demo.service;

import com.example.demo.dto.ModelRequest;
import com.example.demo.dto.ModelResponse;
import com.example.demo.model.SimpleModel;

import java.util.List;
import java.util.UUID;

public interface SimpleImpl {
    SimpleModel addSimpleItem(ModelRequest modelRequest);
    ModelResponse getSimpleItem(UUID uuid);
    void deleteSimpleItem(UUID uuid);
    List<SimpleModel> getAllSimpleItem();
}
