package zenithapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import zenithapp.dto.ZenithDTO;
import zenithapp.mapper.ZenithMapper;
import zenithapp.model.Zenith;
import zenithapp.repository.ZenithRepository;
import zenithapp.service.ZenithService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ZenithServiceTest {
    @InjectMocks
    private ZenithService zenithService;

    @Mock
    private ZenithRepository zenithRepository;

    @Mock
    private ZenithMapper zenithMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllWatches() {
        // Mocking data
        Zenith watch1 = new Zenith(1L, "Watch 1", "Description 1", 540);
        Zenith watch2 = new Zenith(2L, "Watch 2", "Description 2", 440);
        List<Zenith> watches = Arrays.asList(watch1, watch2);

        Mockito.when(zenithRepository.findAll()).thenReturn(watches);
        Mockito.when(zenithMapper.toDto(watch1)).thenReturn(new ZenithDTO(1L, "Watch 1", "Description 1", 540));
        Mockito.when(zenithMapper.toDto(watch2)).thenReturn(new ZenithDTO(2L, "Watch 2", "Description 2", 440));

        // Test the service method
        List<ZenithDTO> result = zenithService.getAllWatches();

        // Assert the results
        assertEquals(watches.size(), result.size());
    }

    @Test
    public void testGetWatchById() {
        // Mocking data
        Long id = 1L;
        Zenith watch = new Zenith(id, "Watch 1", "Description 1", 540);
        ZenithDTO watchDTO = new ZenithDTO(id, "Watch 1", "Description 1", 540);

        Mockito.when(zenithRepository.findById(id)).thenReturn(Optional.of(watch));
        Mockito.when(zenithMapper.toDto(watch)).thenReturn(watchDTO);

        // Test the service method
        ZenithDTO result = zenithService.getWatchById(id);

        // Assert the results
        assertEquals(watchDTO, result);
    }

    @Test
    public void testAddWatch() {
        // Mocking data
        ZenithDTO watchDTO = new ZenithDTO(1L, "Watch 1", "Description 1", 540);
        Zenith watch = new Zenith(1L, "Watch 1", "Description 1", 540);

        when(zenithMapper.toEntity(watchDTO)).thenReturn(watch);
        when(zenithRepository.save(watch)).thenReturn(watch);
        when(zenithMapper.toDto(watch)).thenReturn(watchDTO);

        // Test the service method
        ZenithDTO result = zenithService.addWatches(watchDTO);

        // Assert the results
        assertEquals(watchDTO, result);
    }

    @Test
    public void testUpdateWatch() throws IllegalArgumentException {
        // Mocking data
        Long id = 1L;
        ZenithDTO updateWatchDTO = new ZenithDTO(id, "Watch 1", "Description 1", 540);
        Zenith updatedWatch = new Zenith(id, "Watch 1", "Description 1", 540);

        // Mock the behavior of the repository
        when(zenithRepository.findById(id)).thenReturn(Optional.of(updatedWatch));
        when(zenithRepository.save(any(Zenith.class))).thenReturn(updatedWatch);
        when(zenithMapper.toDto(updatedWatch)).thenReturn(updateWatchDTO);

        // Test the service method
        ZenithDTO result = zenithService.updateWatch(id, updateWatchDTO);

        // Assert the results
        assertEquals(updateWatchDTO, result);
    }

    @Test
    public void testDeleteWatch() {
        // Mocking data
        Long id = 1L;

        // Test the service method
        zenithService.deleteWatch(id);

        // Verify that the repository method was called
        verify(zenithRepository, times(1)).deleteById(id);
    }
}


