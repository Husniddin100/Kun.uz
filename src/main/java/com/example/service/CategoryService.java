package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.RegionDTO;
import com.example.entity.CategoryEntity;
import com.example.entity.RegionEntity;
import com.example.enums.LangEnum;
import com.example.exp.AppBadException;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public CategoryDTO create(CategoryDTO dto){
        CategoryEntity entity=new CategoryEntity();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());

        Optional<CategoryEntity>optional= Optional.of(categoryRepository.save(entity));
       if (optional.isEmpty()){
           throw new AppBadException("Error created");
       }
       dto.setId(entity.getId());
       dto.setVisible(entity.getVisible());
       dto.setCreatedDate(entity.getCreatedDate());
       return dto;
    }

    public Boolean update(Integer id, CategoryDTO dto) {
        Optional<CategoryEntity>optional=categoryRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("category not found");
        }
        CategoryEntity entity=optional.get();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_en(dto.getName_en());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        categoryRepository.save(entity);
        return true;
    }


    public Boolean delete(Integer id) {
      Optional<CategoryEntity>optional=categoryRepository.findById(id);
      if (optional.isEmpty()){
          throw new AppBadException("category not found"+id);
      }
      categoryRepository.delete(id);
      return true;
    }

    public List<CategoryDTO> getAll() {
        Sort sort = Sort.by(Sort.Direction.DESC, "orderNumber");
        Iterable<CategoryEntity> categoryList=categoryRepository.findAll(sort);
        List<CategoryDTO>dtoList=new LinkedList<>();
        for (CategoryEntity entity:categoryList){
            if (entity.getVisible().equals(Boolean.TRUE)){
                dtoList.add(toDTO(entity));
            }
        }
        return dtoList;
    }
    public CategoryDTO toDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName_uz(entity.getName_uz());
        dto.setName_ru(entity.getName_ru());
        dto.setName_en(entity.getName_en());
        dto.setOrder_number(entity.getOrderNumber());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<CategoryDTO> getLang(LangEnum lang) {
        List<CategoryDTO>dtoList=new LinkedList<>();
        Iterable<CategoryEntity> entityList=categoryRepository.findAll();
        for (CategoryEntity entity:entityList){
            CategoryDTO dto=new CategoryDTO();
            dto.setId(entity.getId());
            switch (lang){
                case en -> dto.setName(entity.getName_en());
                case ru -> dto.setName(entity.getName_ru());
                default -> dto.setName(entity.getName_uz());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
