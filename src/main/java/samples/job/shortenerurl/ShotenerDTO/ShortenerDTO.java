package samples.job.shortenerurl.ShotenerDTO;

import org.springframework.beans.BeanUtils;
import samples.job.shortenerurl.model.Shortener;

import java.sql.Timestamp;

public class ShortenerDTO {
    private String originLocation;
    private String hash;
    private Timestamp timestamp;
    private Integer count;


    public ShortenerDTO(Shortener shortener) {
        if (shortener != null) {
            this.originLocation = shortener.getOriginLocation();
            this.hash = shortener.getHash();
            this.timestamp = shortener.getTimestamp();
            this.count = shortener.getCount();
        }
        //public ShortenerDTO(Shortener shortener){
        //BeanUtils.copyProperties(shortener, this);
    }



    public ShortenerDTO () {
        //this.originlocation = originLocation();
        //this.hash = getHash();
        //this.timestamp = getTimestamp();
    }

    public String getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer value){
        this.count = value;
    }

    public void setOriginlocation(String originLocation) {

    }
}
