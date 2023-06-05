package zenithapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import zenithapp.model.ZenithDTO;
import zenithapp.repository.ZenithRepository;

import java.util.List;

@Service
public class ZenithService {
    private final ZenithRepository zenithRepository;
    @Autowired
    public ZenithService(ZenithRepository zenithRepository) {
        this.zenithRepository = zenithRepository;
    }

    public List<ZenithDTO> getAll() {
        return zenithRepository.findAll();
    }

    public ZenithDTO addWatches(@RequestBody ZenithDTO zenithDTO) {
        return zenithRepository.save(zenithDTO);
    }

    public ZenithDTO getById(@PathVariable Long id) {
        return zenithRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("\"Watch not found with id: \"+ id"));
    }

    public ZenithDTO updateWatches(Long id, ZenithDTO updateWatch) {
        return zenithRepository.findById(id)
                .map(zenith -> {
                    zenith.setNameWatch(updateWatch.getNameWatch());
                    zenith.setDescription(updateWatch.getDescription());
                    zenith.setPrice(updateWatch.getPrice());
                    return zenithRepository.save(zenith);
                }).orElseThrow(() -> new IllegalArgumentException("Watch not found with id: " + id));
    }

    public void deleteWatches(Long id) {
        zenithRepository.deleteById(id);
    }

}
