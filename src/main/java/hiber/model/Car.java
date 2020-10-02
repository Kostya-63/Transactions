package hiber.model;

import javax.persistence.*;

@Entity
@Table (name = "cars")
public class Car {
    @Id
    private Long userid;

    @Column (name = "series")
    private int series;

    @Column (name = "model")
    private String model;

    @OneToOne
    @MapsId
    private User user;

    public Car() {
    }

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    public Long getId() {
        return userid;
    }

    public void setId(Long userid) {
        this.userid = userid;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
