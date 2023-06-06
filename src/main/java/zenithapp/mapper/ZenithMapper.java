package zenithapp.mapper;

import org.springframework.stereotype.Component;
import zenithapp.dto.ZenithDTO;
import zenithapp.model.Zenith;

@Component
public class ZenithMapper {

    public zenithapp.dto.ZenithDTO toDto(Zenith zenith){
        ZenithDTO zenithDTO = new ZenithDTO();
        zenithDTO.setId(zenith.getId());
        zenithDTO.setNameWatch(zenith.getNameWatch());
        zenithDTO.setDescription(zenith.getDescription());
        zenithDTO.setPrice(zenith.getPrice());
        return zenithDTO;
    }

    public Zenith toEntity(ZenithDTO zenithDTO){
        Zenith zenith = new Zenith();
        zenith.setId(zenithDTO.getId());
        zenith.setNameWatch(zenithDTO.getNameWatch());
        zenith.setDescription(zenithDTO.getDescription());
        zenith.setPrice(zenithDTO.getPrice());
        return zenith;
    }

}
