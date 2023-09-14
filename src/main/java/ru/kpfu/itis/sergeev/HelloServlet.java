package ru.kpfu.itis.sergeev;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.stream.Collectors;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    private void write(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        Iterator<String> iterator = request.getHeaderNames().asIterator();
        while (iterator.hasNext()) {
            writer.println(iterator.next() + ": " + request.getHeader(iterator.next()));
        }
        writer.println("Params: " + request.getQueryString());
//        RequestBody reading
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        writer.println("Body: " + requestBody);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        write(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        write(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        write(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        write(request, response);
    }
}