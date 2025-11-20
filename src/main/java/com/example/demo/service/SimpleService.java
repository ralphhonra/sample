package com.example.demo.service;

import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.SimpleModel;
import com.example.demo.repository.SimpleRepository;
import com.example.demo.dto.ModelRequest;
import com.example.demo.dto.ModelResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class SimpleService implements SimpleImpl {
    private final ModelMapper modelMapper;
    private final SimpleRepository simpleRepository;

    @Override
    public SimpleModel addSimpleItem(ModelRequest modelRequest) {
        SimpleModel retrievedData = SimpleModel.builder()
                .description(modelRequest.getDescription())
                .isCompleted(modelRequest.isCompleted())
                .build();

        return simpleRepository.save(retrievedData);
    }

    @Override
    public ModelResponse getSimpleItem(UUID uuid) {
        return simpleRepository.findById(uuid)
                .map(this::convertToModelResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found."));
    }

    @Override
    public void deleteSimpleItem(UUID uuid) {
        simpleRepository.findById(uuid)
                .ifPresentOrElse(
                        simpleRepository::delete,
                        () -> { throw new ResourceNotFoundException("Simple item not found!"); }
                );
    }

    @Override
    public List<SimpleModel> getAllSimpleItem() {
        return simpleRepository.findAll();
    }

    private ModelResponse convertToModelResponse(SimpleModel simpleModel) {
        return modelMapper.map(simpleModel, ModelResponse.class);
    }
}
