package com.example.demo.services;

import com.example.demo.dto.ModelDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.exception.NotNullException;
import com.example.demo.mapper.ObjectConverter;
import com.example.demo.model.Model;
import com.example.demo.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    @Autowired
    ModelRepository repository;

    public List<ModelDTO> findAll() {
        return ObjectConverter.convertObjectList(repository.findAll(), ModelDTO.class);
    }

    public ModelDTO findById(Long id) {
        return ObjectConverter.convertObject(repository.findById(id).orElseThrow(() -> new NotFoundException("no records found for this ID")), ModelDTO.class);
    }

    public ModelDTO create(ModelDTO model) {
        if (model == null) {
            throw new NotNullException();
        }
        Model newModel = new Model();
        newModel.setName(model.getName());
        newModel.setMessage(model.getMessage());

        return ObjectConverter.convertObject(repository.save(newModel), ModelDTO.class);
    }

    public ModelDTO update(ModelDTO model) {
        if (model == null) {
            throw new NotNullException();
        }
        Model updatedEntity = ObjectConverter.convertObject(findById(model.getId()), Model.class);

        updatedEntity.setName(model.getName());
        updatedEntity.setMessage(model.getMessage());

        return ObjectConverter.convertObject(repository.save(updatedEntity), ModelDTO.class);
    }

    public void delete(Long id) {
        Model model = ObjectConverter.convertObject(findById(id), Model.class);
        repository.delete(model);

    }

}
