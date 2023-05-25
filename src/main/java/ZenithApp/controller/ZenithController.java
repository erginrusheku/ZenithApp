package ZenithApp.controller;

import ZenithApp.model.Zenith;
import ZenithApp.service.ZenithService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watches")
public class ZenithController {

    private final ZenithService zenithService;
    @Autowired
    public ZenithController(ZenithService zenithService){
        this.zenithService = zenithService;
    }

    @GetMapping
    public List<Zenith> getAll(){
        return zenithService.getAll();

    }
    @PostMapping
    public Zenith addWatches(@RequestBody Zenith zenith){
        return zenithService.addWatches(zenith);
    }

    @GetMapping("/{id}")
    public Zenith getById(@PathVariable Long id){
        return zenithService.getById(id);
    }
    @PutMapping("/{id}")
    public Zenith updateWatches(@PathVariable Long id,@RequestBody Zenith updateWatch){
        return zenithService.updateWatches(id,updateWatch);
    }
    @DeleteMapping("/{id}")
    public void deleteWatches(@PathVariable Long id){
        zenithService.deleteWatches(id);
    }
}
