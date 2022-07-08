package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;
    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "car")
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

    public void setId(Long carId) {
        this.carId = carId;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public Long getCarId() {
        return carId;
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
        sb.append("carId=").append(carId);
        sb.append(", model='").append(model).append('\'');
        sb.append(", series=").append(series);
        sb.append('}');
        return sb.toString();
    }
}
