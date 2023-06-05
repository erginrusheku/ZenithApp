package zenithapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zenithapp.model.ZenithDTO;
import zenithapp.repository.ZenithRepository;
import zenithapp.service.ZenithService;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ZenithDTOServiceTest {
    @Mock
    private ZenithRepository zenithRepository;

    @Autowired
    private ZenithService zenithService;

    @BeforeEach
    public void setUp(){
        zenithService = new ZenithService(zenithRepository);
    }
    @Test
    public void testGetAllWatches() {
        List<ZenithDTO> watches = List.of(new ZenithDTO(1L, "Watch 1", "Description 1", 540));

        Mockito.when(zenithRepository.findAll()).thenReturn(watches);

        List<ZenithDTO> result = zenithService.getAll();
        assertEquals(watches, result);
    }

    @Test
    public void testGetWatchById() {
        ZenithDTO watch = new ZenithDTO(1L, "Watch 1", "Description 1", 540);

        Mockito.when(zenithRepository.findById(1L)).thenReturn(Optional.of(watch));

        ZenithDTO result = zenithService.getById(1L);
        assertEquals(watch, result);
    }

    @Test
    public void testAddWatch() {
        ZenithDTO watch = new ZenithDTO(1L, "Watch 1", "Description 1", 540);

        Mockito.when(zenithRepository.save(watch)).thenReturn(watch);

        ZenithDTO result = zenithService.addWatches(watch);
        assertEquals(watch, result);
    }

    @Test
    public void testUpdateWatch() {
        ZenithDTO watch = new ZenithDTO(1L, "Watch 1", "Description 1", 540);

        Mockito.when(zenithRepository.findById(1L)).thenReturn(Optional.of(watch));

        Mockito.when(zenithRepository.save(watch)).thenReturn(watch);

        ZenithDTO result = zenithService.updateWatches(1L, watch);
        assertEquals(watch, result);
    }

    @Test
    public void testDeleteWatch() {

        zenithService.deleteWatches(1L);

        Mockito.verify(zenithRepository, Mockito.times(1)).deleteById(1L);
    }
}

