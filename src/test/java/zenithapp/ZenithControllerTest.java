package zenithapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import zenithapp.controller.ZenithController;
import zenithapp.dto.ZenithDTO;
import zenithapp.mapper.ZenithMapper;
import zenithapp.service.ZenithService;
import java.util.List;


@WebMvcTest(ZenithController.class)
public class ZenithControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZenithService zenithService;

    @Mock
    private ZenithMapper zenithMapper;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllWatches() throws Exception {
        List<ZenithDTO> zenithDTOS = List.of(new ZenithDTO(1L, "Watch 1", "description1", 540));
        Mockito.when(zenithService.getAllWatches()).thenReturn(zenithDTOS);

        mockMvc.perform(MockMvcRequestBuilders.get("/watches"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameWatch").value("Watch 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(540));
    }

    @Test
    public void testGetWatchById() throws Exception {
        Long id = 1L;
        ZenithDTO zenithDTO = new ZenithDTO(id, "Watch 1", "description1", 540);
        Mockito.when(zenithService.getWatchById(id)).thenReturn(zenithDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/watches/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameWatch").value("Watch 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(540));
    }

    @Test
    public void testAddWatch() throws Exception {
        ZenithDTO zenithDTO = new ZenithDTO(1L, "Watch 1", "description1", 540);
        Mockito.when(zenithService.addWatches(zenithDTO)).thenReturn(zenithDTO);

        String zenithJson = "{\"id\": 1, \"nameWatch\": \"Watch 1\", \"description\": \"description1\", \"price\": 540}";

        mockMvc.perform(MockMvcRequestBuilders.post("/watches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(zenithJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameWatch").value("Watch 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(540));
    }


    @Test
    public void testUpdateWatch() throws Exception {
        Long id = 1L;
        ZenithDTO updatedWatch = new ZenithDTO(id, "Watch 1", "description1", 540);
        Mockito.when(zenithService.updateWatch(id, updatedWatch)).thenReturn(updatedWatch);

        String updatedZenithJson = "{\"id\": 1, \"nameWatch\": \"Watch 1\", \"description\": \"description1\", \"price\": 540}";

        mockMvc.perform(MockMvcRequestBuilders.put("/watches/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedZenithJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameWatch").value("Watch 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(540));
    }

    @Test
    public void testDeleteWatch() throws Exception {
        Long id = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/watches/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(zenithService, Mockito.times(1)).deleteWatch(id);
    }
}
