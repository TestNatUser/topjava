package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
            if (meal.isNew()) {
                meal.setUser(ref);
                em.persist(meal);
                return meal;
            } else {
                return em.merge(meal);
            }
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id",userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return (Meal) em.createNamedQuery(Meal.GET)
                .setParameter("id",id)
                .setParameter("user_id",userId)
                .getSingleResult();
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED,Meal.class)
                .setParameter("user_id",userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.DATE_BETWEEN,Meal.class)
                .setParameter("user_id",userId)
                .setParameter("date_time",startDateTime)
                .setParameter("date_time",endDateTime)
                .getResultList();
    }
}