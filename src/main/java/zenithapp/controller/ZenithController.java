package zenithapp.controller;

import zenithapp.dto.ZenithDTO;
import zenithapp.service.ZenithService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/watches")
public class ZenithController {
    private final ZenithService zenithService;

    @Autowired
    public ZenithController(ZenithService zenithService) {
        this.zenithService = zenithService;
    }

    @GetMapping
    public List<ZenithDTO> getAll() {
        return zenithService.getAllWatches();
    }

    @PostMapping
    public ZenithDTO addWatches(@RequestBody ZenithDTO zenithDTO) {
        return zenithService.addWatches(zenithDTO);
    }

    @GetMapping("/{id}")
    public ZenithDTO getWatchById(@PathVariable Long id) {
        return zenithService.getWatchById(id);
    }

    @PutMapping("/{id}")
    public ZenithDTO updateWatch(@PathVariable Long id, @RequestBody ZenithDTO updateWatch) {
        return zenithService.updateWatch(id, updateWatch);
    }

    @DeleteMapping("/{id}")
    public void deleteWatch(@PathVariable Long id) {
        zenithService.deleteWatch(id);
    }
}
