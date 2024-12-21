package samples.job.shortenerurl.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "shortener")
public class Shortener {
    @Id
    @GeneratedValue(generator = "shortener_id_seq")
    private Long id;
    @Column(name = "origin_location")
    private String originLocation;
    @Column(name = "timestamp")
    private Timestamp timestamp;
    @Column(name = "hash")
    private String hash;
    @Column(name = "count")
    private String count;


    public Long getId() { return id; }
    public void setId(Long value) { this.id = value; }

    public String getOriginLocation() { return originLocation; }
    public void setOriginLocation(String value) { this.originLocation = value; }

    public Timestamp getTimestamp() { return timestamp; }
    public void setTimestamp(Timestamp value) { this.timestamp = value;
    }

    public String getHash() { return hash; }
    public void setHash(String value) { this.hash = value; }

    public String getCount() { return count; }
    public void setCount(String value) { this.count = value; }


}
