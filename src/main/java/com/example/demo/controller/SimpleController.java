package com.example.demo.controller;

import com.example.demo.dto.ModelRequest;
import com.example.demo.dto.ModelResponse;
import com.example.demo.model.SimpleModel;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.SimpleImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/simple")
public class SimpleController {
    private final SimpleImpl simpleService;

    @GetMapping("/{uuid}")
    public ResponseEntity<ApiResponse> getSimpleItem(@PathVariable UUID uuid) {
        ModelResponse modelResponse = simpleService.getSimpleItem(uuid);

        return ResponseEntity.ok(
                new ApiResponse("Transaction success", modelResponse)
        );
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAllSimpleItem() {
        List<SimpleModel> modelResponses = simpleService.getAllSimpleItem();

        return ResponseEntity.ok(
                new ApiResponse("Transaction success", modelResponses)
        );
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<ApiResponse> deleteSimpleItem(@PathVariable UUID uuid) {
        simpleService.deleteSimpleItem(uuid);

        return ResponseEntity.ok(
                new ApiResponse("Transaction success", null)
        );
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addSimpleItem(@RequestBody ModelRequest modelRequest) {
        SimpleModel simpleModel = simpleService.addSimpleItem(modelRequest);

        return ResponseEntity.ok(
                new ApiResponse(
                        "Transaction success",
                        new ModelMapper().map(simpleModel, ModelResponse.class)
                )
        );
    }
}
