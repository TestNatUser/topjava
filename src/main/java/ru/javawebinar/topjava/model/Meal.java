package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id and m.user.id=:user_id"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:user_id ORDER BY m.datetime desc"),
        @NamedQuery(name = Meal.DATE_BETWEEN, query = "SELECT m FROM Meal m where m.user.id=:user_id and m.datetime >=:date_time and m.datetime <:date_time"),
        @NamedQuery(name = Meal.GET_MEAL, query = "SELECT m FROM Meal m where m.user.id=:user_id and m.id=:id"),
})

@Entity
@Table(name="meals",uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class Meal extends AbstractBaseEntity{
    public static final String DELETE = "Meal.delete";
    public static final String ALL_SORTED = "Meal.getAllSorted";
    public static final String DATE_BETWEEN = "Meal.getDateBetween";
    public static final String GET_MEAL = "Meal.getMeal";

    @Column(name = "date_time",nullable = false)
    private  LocalDateTime datetime;

    @Column(name = "description", nullable = false)
    @Size(max = 128)
    private  String description;

    @Column(name = "calories", nullable = false)
    @NotBlank
    @Size(max = 128)
    private  int calories;

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne (targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotBlank
    @Size(max = 128)
    public User user;

    public Meal(LocalDateTime datetime, String description, int calories) {
        this(null, datetime, description, calories);
    }

    public Meal(Integer id, LocalDateTime datetime, String description, int calories) {
        super(id);
        this.datetime = datetime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(){

    }

    public Meal(Meal m){
        this(m.id,m.datetime,m.description,m.calories);
    }


    public LocalDateTime getDatetime() {
        return datetime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {

        return datetime.toLocalDate();
    }

    public LocalTime getTime() {
        return datetime.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
