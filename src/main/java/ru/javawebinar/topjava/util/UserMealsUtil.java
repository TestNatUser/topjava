package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMeal> filteredMeals = new ArrayList<UserMeal>();
        Map<LocalDate,Integer> map = new HashMap<>();
        for(UserMeal meal : meals){
            LocalDate date = meal.getDateTime().toLocalDate();
            if(TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(),startTime,endTime)){
                filteredMeals.add(meal);
            }
                map.merge(date, meal.getCalories(), Integer::sum);

        }

        /*for(int i=0;i<filteredMeals.size();i++){
            int sum=0;
            LocalDate date = filteredMeals.get(i).getDateTime().toLocalDate();
            for(UserMeal meal: meals){
                if(!map.containsKey(date)) {
                    if (meal.getDateTime().toLocalDate().equals(date)) {
                        sum += +meal.getCalories();
                    }
                }
            }
            map.put(date,sum);
        }*/
        List<UserMealWithExcess> list = new ArrayList<UserMealWithExcess>();
        for(UserMeal meal : filteredMeals){
            int cal = map.get(meal.getDateTime().toLocalDate());
            boolean excess = false;
            if(cal>caloriesPerDay){
                excess=true;
            }
            list.add(new UserMealWithExcess(meal.getDateTime(),meal.getDescription(),meal.getCalories(),excess));
        }
        return list;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        return null;
    }
}
