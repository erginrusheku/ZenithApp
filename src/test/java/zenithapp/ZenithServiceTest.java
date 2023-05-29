package zenithapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zenithapp.model.Zenith;
import zenithapp.repository.ZenithRepository;
import zenithapp.service.ZenithService;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ZenithServiceTest {
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
        List<Zenith> watches = List.of(new Zenith(1L, "Watch 1", "Description 1", 540));

        Mockito.when(zenithRepository.findAll()).thenReturn(watches);

        List<Zenith> result = zenithService.getAll();
        assertEquals(watches, result);
    }

    @Test
    public void testGetWatchById() {
        Zenith watch = new Zenith(1L, "Watch 1", "Description 1", 540);

        Mockito.when(zenithRepository.findById(1L)).thenReturn(Optional.of(watch));

        Zenith result = zenithService.getById(1L);
        assertEquals(watch, result);
    }

    @Test
    public void testAddWatch() {
        Zenith watch = new Zenith(1L, "Watch 1", "Description 1", 540);

        Mockito.when(zenithRepository.save(watch)).thenReturn(watch);

        Zenith result = zenithService.addWatches(watch);
        assertEquals(watch, result);
    }

    @Test
    public void testUpdateWatch() {
        Zenith watch = new Zenith(1L, "Watch 1", "Description 1", 540);

        Mockito.when(zenithRepository.findById(1L)).thenReturn(Optional.of(watch));

        Mockito.when(zenithRepository.save(watch)).thenReturn(watch);

        Zenith result = zenithService.updateWatches(1L, watch);
        assertEquals(watch, result);
    }

    @Test
    public void testDeleteWatch() {

        zenithService.deleteWatches(1L);

        Mockito.verify(zenithRepository, Mockito.times(1)).deleteById(1L);
    }
}

