package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    public void setUser(User user) {
        this.user = user;
    }
    public User getUser(){
        return user;
    }

    public Car() {
    }
    public Car(String model, int series){
        this.model = model;
        this.series = series;
    }

    public void setId(Long user_id) {
        this.user_id = user_id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public Long getId() {
        return user_id;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }
}
