package com.foodDelivery.Servlet;

import java.io.IOException;

import com.foodDelivery.dao.UserDAO;
import com.foodDelivery.daoImpl.UserdaoImpl;
import com.foodDelivery.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserdaoImpl();

        // 🔐 Validate user
        User user = userDAO.getUserByEmailAndPassword(email, password);

        if (user != null) {
            // ✅ LOGIN SUCCESS
            HttpSession session = request.getSession();

            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUsername());
            session.setAttribute("role", user.getRole());

            // 🔁 Redirect back if needed
            String redirect = (String) session.getAttribute("redirectAfterLogin");

            if (redirect != null) {
                session.removeAttribute("redirectAfterLogin");
                response.sendRedirect(request.getContextPath() + "/" + redirect);
            } else {
                response.sendRedirect(request.getContextPath() + "/cart");
            }

        } else {
            // ❌ LOGIN FAILED
            response.setContentType("text/html");
            response.getWriter().println("<h3>Invalid email or password</h3>");
        }
    }
}
