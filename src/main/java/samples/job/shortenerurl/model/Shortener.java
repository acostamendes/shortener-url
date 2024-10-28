package samples.job.shortenerurl.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shortener")
public class Shortener {
    @Id
    @GeneratedValue(generator = "shortener_id_seq")
    private Long id;
    @Column(name = "origin_location")
    private String originLocation;
    @Column(name = "event_time")
    private String eventTime;
    @Column(name = "hash")
    private String hash;
    @Column(name = "count")
    private String count;

    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }

    public String getOriginLocation() { return originLocation; }
    public void setOriginLocation(String value) { this.originLocation = value; }

    public String getEventTime() { return eventTime; }
    public void setEventTime(String value) { this.eventTime = value; }

    public String getHash() { return hash; }
    public void setHash(String value) { this.hash = value; }

    public String getCount() { return count; }
    public void setCount(String value) { this.count = value; }
}
