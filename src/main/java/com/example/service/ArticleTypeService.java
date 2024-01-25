package com.example.service;

import com.example.dto.ArticleTypeDTO;
import com.example.dto.RegionDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.entity.RegionEntity;
import com.example.enums.LangEnum;
import com.example.exp.AppBadException;
import com.example.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO create(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());

       Optional<ArticleTypeEntity> optional= Optional.of(articleTypeRepository.save(entity));
         if (optional.isEmpty()){
             throw new AppBadException("error");
         }
         dto.setId(entity.getId());
         dto.setCreatedDate(entity.getCreatedDate());
         dto.setVisible(entity.getVisible());
         return dto;
    }

    public Boolean update(Integer id, ArticleTypeDTO dto) {
        Optional<ArticleTypeEntity> optional=articleTypeRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("not found");
        }
        ArticleTypeEntity entity=optional.get();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        entity.setVisible(Boolean.TRUE);
        articleTypeRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
     Optional<ArticleTypeEntity>optional = articleTypeRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("not found");
        }
        articleTypeRepository.delete(id);
        return true;
    }

    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ArticleTypeEntity> studentPage = articleTypeRepository.findAll(paging);

        List<ArticleTypeEntity> entityList = studentPage.getContent();
        Long totalElements = studentPage.getTotalElements();

        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity articleType : entityList) {
            if (articleType.getVisible().equals(Boolean.TRUE)) {
                dtoList.add(toDTO(articleType));
            }
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public List<ArticleTypeDTO> getByLanguage ( LangEnum language) {
        LinkedList<ArticleTypeDTO>list=new LinkedList<>();
        Iterable<ArticleTypeEntity> getAll=articleTypeRepository.findAll();

        for (ArticleTypeEntity entity:getAll){
            ArticleTypeDTO dto=new ArticleTypeDTO();
            dto.setId(entity.getId());
            switch (language) {
                case ru:
                    dto.setName(entity.getName_ru());
                    break;
                case en:
                    dto.setName(entity.getName_en());
                    break;
                default:
                    dto.setName(entity.getName_uz());
            }
            if (entity.getVisible().equals(Boolean.TRUE)) {
                list.add(dto);
            }
        }
        return list;
    }
    public ArticleTypeDTO toDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setName_uz(entity.getName_uz());
        dto.setName_ru(entity.getName_ru());
        dto.setName_en(entity.getName_en());
        dto.setOrder_number(entity.getOrderNumber());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
