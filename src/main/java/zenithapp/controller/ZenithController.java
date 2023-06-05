package zenithapp.controller;

import zenithapp.model.ZenithDTO;
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
        return zenithService.getAll();
    }

    @PostMapping
    public ZenithDTO addWatches(@RequestBody ZenithDTO zenithDTO) {
        return zenithService.addWatches(zenithDTO);
    }

    @GetMapping("/{id}")
    public ZenithDTO getById(@PathVariable Long id) {
        return zenithService.getById(id);
    }

    @PutMapping("/{id}")
    public ZenithDTO updateWatches(@PathVariable Long id, @RequestBody ZenithDTO updateWatch) {
        return zenithService.updateWatches(id, updateWatch);
    }

    @DeleteMapping("/{id}")
    public void deleteWatches(@PathVariable Long id) {
        zenithService.deleteWatches(id);
    }
}
