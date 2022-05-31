package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import dto.Group;
import dto.GroupWhithOutStudent;
import service.GroupService;
import service.api.IGroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GroupServlet", urlPatterns = "/group")
public class GroupServlet extends HttpServlet {
    private IGroupService groupService;
    private ObjectMapper mapper;

    public GroupServlet() {
        this.groupService = GroupService.getInstance();
        this.mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        List<GroupWhithOutStudent> all = groupService.getAll();
        writer.write(mapper.writeValueAsString(all));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        GroupWhithOutStudent groupWhithOutStudent = mapper.readValue(req.getInputStream(), GroupWhithOutStudent.class);
        groupService.create(groupWhithOutStudent);
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        groupService.delete(Integer.valueOf(req.getParameter("deleteid")));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");
        GroupWhithOutStudent groupWhithOutStudent = mapper.readValue(req.getInputStream(), GroupWhithOutStudent.class);
        groupService.update(Integer.valueOf(req.getParameter("updateid")),groupWhithOutStudent);
    }
}
