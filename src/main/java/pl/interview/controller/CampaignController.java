package pl.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.interview.model.Campaign;
import pl.interview.repository.CampaignRepository;

import javax.print.attribute.standard.Media;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/campaign")
public class CampaignController {

    private CampaignRepository repository;

    @Autowired
    public CampaignController(CampaignRepository repository){
        this.repository = repository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Campaign>> allCampaigns() {
        List<Campaign> allCamp = repository.findAll();
        return ResponseEntity.ok(allCamp);
    }

    @DeleteMapping(path="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteEmployee(@PathVariable Long id) {
        System.out.println("Deleting campaign with Id: " + id);
        repository.deleteById(id);
        return ResponseEntity.ok(id);
    }
    @PutMapping
    public ResponseEntity<?> updateCampaign(@RequestBody Campaign campaign){
        System.out.println(campaign);
        Campaign update = repository.save(campaign);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("{id}").buildAndExpand(update.getId()).toUri();
        return ResponseEntity.created(location).body(update);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveCampaign(@RequestBody Campaign campaign){
        System.out.println(campaign);
        Campaign save = repository.save(campaign);
        System.out.println(save);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(location).body(save);
    }
}
