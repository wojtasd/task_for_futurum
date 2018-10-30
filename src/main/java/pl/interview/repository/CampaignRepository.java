package pl.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.interview.model.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    public Campaign findFirstByName(String name);

}
