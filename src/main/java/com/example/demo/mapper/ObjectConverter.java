package com.example.demo.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ObjectConverter {

    public static ModelMapper mapper = new ModelMapper();

    public static <O,D> D convertObject(O origin, Class<D> destination){
        return mapper.map(origin,destination);
    }

    public static <O,D> List<D> convertObjectList(List<O> origin, Class<D> destination){
        List<D> convertedValue = new ArrayList<>();

        for (O o : origin){
            convertedValue.add(mapper.map(o,destination));
        }
        return convertedValue;
    }
}
