package zenithapp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import zenithapp.controller.ZenithController;
import zenithapp.model.Zenith;
import zenithapp.service.ZenithService;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(ZenithController.class)
public class ZenithControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZenithService zenithService;

    @Test
    public void testGetAllWatches() throws Exception {
        List<Zenith> zeniths = List.of(new Zenith(1L, "Watch 1", "description1", 540));
        Mockito.when(zenithService.getAll()).thenReturn(zeniths);

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
        Zenith zenith = new Zenith(id, "Watch 1", "description1", 540);
        Mockito.when(zenithService.getById(id)).thenReturn(zenith);

        mockMvc.perform(MockMvcRequestBuilders.get("/watches/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameWatch").value("Watch 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(540));
    }

    @Test
    public void testAddWatch() throws Exception {
        Zenith zenith = new Zenith(1L, "Watch 1", "description1", 540);
        Mockito.when(zenithService.addWatches(any(Zenith.class))).thenReturn(zenith);

        String zenithJson = "{\"id\": 1, \"name\": \"Watch 1\", \"description\": \"description1\", \"price\": 540}";

        mockMvc.perform(MockMvcRequestBuilders.post("/watches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(zenithJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameWatch").value("Watch 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("description1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(540));
    }

    @Test
    public void testUpdateWatch() throws Exception {
        Long id = 1L;
        Zenith updatedWatch = new Zenith(id, "Watch 1", "description1", 540);
        Mockito.when(zenithService.updateWatches(id, updatedWatch)).thenReturn(updatedWatch);

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

        Mockito.verify(zenithService, Mockito.times(1)).deleteWatches(id);
    }
}
