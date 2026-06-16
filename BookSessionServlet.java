/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.lab.controller;

import com.DAO.SessionDAO;
import com.lab.bean.SessionBean;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookSessionServlet extends HttpServlet {
    private final SessionDAO sessionDAO = new SessionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }
        
        SessionBean loggedUser = (SessionBean) session.getAttribute("loggedUser");
        String matricNo = loggedUser.getsession_id();
        
        String action = request.getParameter("action");
        if (action == null) action = "list";

        if ("list".equals(action)) {
            List<SessionBean> list = SessionDAO.getSessionBySession_id(session_id);
            request.setAttribute("SessionList", list);
            request.getRequestDispatcher("/session/viewsession.jsp").forward(request, response);
            
        } else if ("add".equals(action)) {
            SessionBean newSub = new SessionBean();
            newSub.setsession_id(session_id);
            newSub.setStudentName(request.getParameter("studentName"));
            newSub.setBranchLocation(request.getParameter("branchLocation"));
            sessionDAO.addSession(newSub);
            response.sendRedirect("SubjectServlet?action=list");
            
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            SessionBean existingSub = sessionDAO.getSessionById(id);
            request.setAttribute("subject", existingSub);
            request.getRequestDispatcher("/subject/updateSubject.jsp").forward(request, response);
            
        } else if ("update".equals(action)) {
            SessionBean sub = new SessionBean();
            sub.setId(Integer.parseInt(request.getParameter("id")));
            sub.setSessionCode(request.getParameter("sessionCode"));
            sub.setSessionName(request.getParameter("sessionName"));
            sessionDAO.updateSubject(sub);
            response.sendRedirect("SubjectServlet?action=list");
            
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            sessionDAO.deleteSession(id);
            response.sendRedirect("SubjectServlet?action=list");
        }
    }
}