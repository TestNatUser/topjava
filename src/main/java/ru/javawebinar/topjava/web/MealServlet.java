package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private final MealServiceImplementation mealServiceImplementation = new MealServiceImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String forward="/meals.jsp";
        request.setAttribute("meals", mealServiceImplementation.getAllMeals());
        try {
            String action = request.getParameter("action");
            if (action.equalsIgnoreCase("delete")) {
                int mealId = Integer.parseInt(request.getParameter("Id"));
                mealServiceImplementation.deleteMeal(mealId);
                //request.setAttribute("users", mealServiceImplementation.getAllMeals());
            } else if (action.equalsIgnoreCase("edit")) {
                int mealId = Integer.parseInt(request.getParameter("Id"));
                Meal meal = mealServiceImplementation.getMealById(mealId);
                forward="/editMeal.jsp";
                //request.getRequestDispatcher("/editMeal.jsp").forward(request, response);
            }
        } catch (Exception e){}
        request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        //int mealId = Integer.parseInt(request.getParameter("mealId"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime date = LocalDateTime.parse(request.getParameter("dateTime"));
        Meal meal = new Meal(date,description,calories);
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("create")) {
            mealServiceImplementation.addMeal(meal);
        } else if (action.equalsIgnoreCase("edit")) {
            mealServiceImplementation.updateMeal(meal);
        }
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
