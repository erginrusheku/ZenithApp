package zenithapp.mapper;

import org.springframework.stereotype.Component;
import zenithapp.model.ZenithDTO;

@Component
public class ZenithMapper {

    public zenithapp.dto.ZenithDTO toDto(ZenithDTO zenith){
        zenithapp.dto.ZenithDTO zenithDTO = new zenithapp.dto.ZenithDTO();
        zenithDTO.setId(zenith.getId());
        zenithDTO.setNameWatch(zenith.getNameWatch());
        zenithDTO.setDescription(zenith.getDescription());
        zenithDTO.setPrice(zenith.getPrice());
        return zenithDTO;
    }

    public ZenithDTO toEntity(zenithapp.dto.ZenithDTO zenithDTO){
        ZenithDTO zenith = new ZenithDTO();
        zenith.setId(zenithDTO.getId());
        zenith.setNameWatch(zenithDTO.getNameWatch());
        zenith.setDescription(zenithDTO.getDescription());
        zenith.setPrice(zenithDTO.getPrice());
        return zenith;
    }

}
