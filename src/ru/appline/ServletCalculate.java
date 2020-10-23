package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@WebServlet(urlPatterns = "/calculate")
public class ServletCalculate extends HttpServlet {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while (reader.ready() && (line = reader.readLine()) != null) jb.append(line);
        } catch (Exception e) {
            System.err.println("Error");
        }

        JsonObject json = gson.fromJson(String.valueOf(jb), JsonObject.class);

        double a = json.get("a").getAsDouble();
        double b = json.get("b").getAsDouble();
        char math = json.get("math").getAsCharacter();

        JsonObject jsonObjectResult = new JsonObject();

        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US); //Создаем новый формат, чтобы разделителем была '.', вместо ','. Также целые числа будут отображаться без разделителя и лишних нулей.
        switch (math) {
            case ('*'):
                jsonObjectResult.addProperty("result", String.valueOf(df.format(a * b)));
                break;
            case ('/'):
                if (b != 0) {
                    jsonObjectResult.addProperty("result", String.valueOf(df.format(a / b)));
                } else {
                    jsonObjectResult.addProperty("result", "NaN");
                }
                break;
            case ('-'):
                jsonObjectResult.addProperty("result", String.valueOf(df.format(a - b)));
                break;
            case ('+'):
                jsonObjectResult.addProperty("result", String.valueOf(df.format(a + b)));
                break;
            default:
                break;
        }

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();

        pw.print(jsonObjectResult.toString());
    }

}
