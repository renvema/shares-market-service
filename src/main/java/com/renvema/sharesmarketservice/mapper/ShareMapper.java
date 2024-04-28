package com.renvema.sharesmarketservice.mapper;


import com.renvema.sharesmarketservice.controller.dto.ShareDto;
import com.renvema.sharesmarketservice.dao.entity.Share;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShareMapper {

    ShareDto fromEntity(Share source);

    Share toEntity(ShareDto destination);

}
