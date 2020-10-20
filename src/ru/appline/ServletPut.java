package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    Model model = Model.getINSTANCE();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while (reader.ready() && (line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.err.println("Error");
        }

        JsonObject json = gson.fromJson(String.valueOf(jb), JsonObject.class);

        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();
        String surname = json.get("surname").getAsString();
        String salary = json.get("salary").getAsString();

        User user = new User(name, surname, salary);
        model.add(user, id);

        resp.setContentType("application/json;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        pw.print(gson.toJson(model.getFromList()));
    }
}
