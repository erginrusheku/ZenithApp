package ZenithApp.service;

import ZenithApp.model.Zenith;
import ZenithApp.repository.ZenithRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Service
public class ZenithService {
    private final ZenithRepository zenithRepository;

    @Autowired
    public ZenithService(ZenithRepository zenithRepository){
        this.zenithRepository = zenithRepository;
    }
    @GetMapping
    public List<Zenith> getAll() {
        return zenithRepository.findAll();
    }

    @PostMapping
    public Zenith addWatches(@RequestBody Zenith zenith) {
        return zenithRepository.save(zenith);
    }

    @GetMapping("/{id}")
    public Zenith getById(@PathVariable Long id) {
        return zenithRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("\"Watch not found with id: \"+ id"));
    }
    @PutMapping("/{id}")
    public Zenith updateWatches(Long id, Zenith updateWatch) {
        return zenithRepository.findById(id)
                .map(zenith -> {
                    zenith.setNameWatch(updateWatch.getNameWatch());
                    zenith.setDescription(updateWatch.getDescription());
                    zenith.setPrice(updateWatch.getPrice());
                    return zenithRepository.save(zenith);
                }).orElseThrow(() -> new IllegalArgumentException("Watch not found with id: "+ id));
    }
    @DeleteMapping("/{id}")
    public void deleteWatches(Long id) {
        zenithRepository.deleteById(id);
    }
}
