package pl.interview.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Campaign {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true)
    private String name;
    @Column(nullable=false)
    private String keywords;
    @Column(nullable=false)
    private double bidAmount;
    @Column(nullable=false)
    private double campaignFund;
    @Column(nullable=false)
    private boolean status;
    private String town;
    @Column(nullable=false)
    private int radius;

    public Campaign(){}


    public Campaign(String name, String keywords, double bidAmount, double campaignFund, boolean status, String town, int radius) {
        this.name = name;
        this.keywords = keywords;
        this.bidAmount = bidAmount;
        this.campaignFund = campaignFund;
        this.status = status;
        this.town = town;
        this.radius = radius;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public double getCampaignFund() {
        return campaignFund;
    }

    public void setCampaignFund(double campaignFund) {
        this.campaignFund = campaignFund;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campaign)) return false;
        Campaign campaign = (Campaign) o;
        return Double.compare(campaign.getBidAmount(), getBidAmount()) == 0 &&
                Double.compare(campaign.getCampaignFund(), getCampaignFund()) == 0 &&
                isStatus() == campaign.isStatus() &&
                getRadius() == campaign.getRadius() &&
                Objects.equals(getName(), campaign.getName()) &&
                Objects.equals(getKeywords(), campaign.getKeywords()) &&
                Objects.equals(getTown(), campaign.getTown());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getKeywords(), getBidAmount(), getCampaignFund(), isStatus(), getTown(), getRadius());
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", keywords='" + keywords + '\'' +
                ", bidAmount=" + bidAmount +
                ", campaignFund=" + campaignFund +
                ", status=" + status +
                ", town='" + town + '\'' +
                ", radius=" + radius +
                '}';
    }
}
