package zenithapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zenithapp.dto.ZenithDTO;
import zenithapp.mapper.ZenithMapper;
import zenithapp.model.Zenith;
import zenithapp.repository.ZenithRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZenithService {
    private final ZenithRepository zenithRepository;
    private final ZenithMapper zenithMapper;

    @Autowired
    public ZenithService(ZenithRepository zenithRepository, ZenithMapper zenithMapper) {
        this.zenithRepository = zenithRepository;
        this.zenithMapper = zenithMapper;
    }

    public List<ZenithDTO> getAllWatches() {
        List<Zenith> zeniths = zenithRepository.findAll();
        List<ZenithDTO> zenithDTOs = new ArrayList<>();

        for (Zenith zenith : zeniths) {
            ZenithDTO zenithDTO = zenithMapper.toDto(zenith);
            zenithDTOs.add(zenithDTO);
        }

        return zenithDTOs;
    }

    public ZenithDTO addWatches(ZenithDTO zenithDTO) {
        Zenith zenith = zenithMapper.toEntity(zenithDTO);
        Zenith savedZenith = zenithRepository.save(zenith);
        return  zenithMapper.toDto(savedZenith);
    }

    public ZenithDTO getWatchById(Long id) throws IllegalArgumentException {
        Zenith zenith = zenithRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Watch not found"));
        return zenithMapper.toDto(zenith);
    }

    public ZenithDTO updateWatch(Long id, ZenithDTO updatedWatch)  throws IllegalArgumentException {
        Zenith existingZenith = zenithRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Watch not found with id: " + id));

        existingZenith.setNameWatch(updatedWatch.getNameWatch());
        existingZenith.setDescription(updatedWatch.getDescription());
        existingZenith.setPrice(updatedWatch.getPrice());

        Zenith savedZenith = zenithRepository.save(existingZenith);

        return zenithMapper.toDto(savedZenith);
    }

    public void deleteWatch(Long id) {
        zenithRepository.deleteById(id);
    }
}

