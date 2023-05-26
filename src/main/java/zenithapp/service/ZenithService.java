package zenithapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import zenithapp.model.Zenith;
import zenithapp.repository.ZenithRepository;

import java.util.List;

@Service
public class ZenithService {
    private final ZenithRepository zenithRepository;
    @Autowired
    public ZenithService(ZenithRepository zenithRepository) {
        this.zenithRepository = zenithRepository;
    }

    public List<Zenith> getAll() {
        return zenithRepository.findAll();
    }

    public Zenith addWatches(@RequestBody Zenith zenith) {
        return zenithRepository.save(zenith);
    }

    public Zenith getById(@PathVariable Long id) {
        return zenithRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("\"Watch not found with id: \"+ id"));
    }

    public Zenith updateWatches(Long id, Zenith updateWatch) {
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
