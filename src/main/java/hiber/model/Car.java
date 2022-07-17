package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "car_id")
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;

    @MapsId
    @OneToOne(mappedBy = "car")
    @JoinColumn(name = "car_id")
    private User user;

    public void setUser(User user) {
        user.setCar(this);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public void id(Long carId) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Car{");
        sb.append("carId=").append(id);
        sb.append(", model='").append(model).append('\'');
        sb.append(", series=").append(series);
        sb.append('}');
        return sb.toString();
    }
}
