package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import dao.StudentInTheGroupDao;
import dto.Group;
import dto.GroupWhithOutStudent;
import dto.Student;
import service.StudentInThrGroupService;
import service.StudentService;
import service.api.IStudInTheGroupServive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "StudentByGroupServlet",urlPatterns = "/studentbygroup")
public class StudentByGroupServlet extends HttpServlet {
    private final IStudInTheGroupServive studInTheGroupServive;
    private ObjectMapper mapper;

    public StudentByGroupServlet() {
        this.studInTheGroupServive = StudentInThrGroupService.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        Group group = studInTheGroupServive.getAll(Integer.valueOf(req.getParameter("idgroup")));
        writer.write(mapper.writeValueAsString(group));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        List<Student> studentList = Arrays.asList(mapper.readValue(req.getInputStream(), Student[].class));
        studInTheGroupServive.create(studentList, Integer.valueOf(req.getParameter("idgropcreat")));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        studInTheGroupServive.update(Integer.valueOf(req.getParameter("idstudent")),
                Integer.valueOf(req.getParameter("idgroup")));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        studInTheGroupServive.delete(Integer.valueOf(req.getParameter("iddelete")));
    }
}
