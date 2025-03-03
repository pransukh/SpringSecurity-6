package org.let;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/menu")
    public String menu(){
        return  "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Two Buttons</title>\n" +
                "    <style>\n" +
                "        .button-container {\n" +
                "            text-align: center;\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "        .button {\n" +
                "            font-size: 16px;\n" +
                "            padding: 10px 20px;\n" +
                "            margin: 10px;\n" +
                "            border: none;\n" +
                "            border-radius: 5px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "        .button-primary {\n" +
                "            background-color: #007bff;\n" +
                "            color: white;\n" +
                "        }\n" +
                "        .button-secondary {\n" +
                "            background-color: #6c757d;\n" +
                "            color: white;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"button-container\">\n" +
                "    <a href=\"http://localhost:8080/api/home\" class=\"button button-primary\">🏠</a>"+
                "    <a href=\"http://localhost:8080/api/public\" class=\"button button-secondary\">📢</a>"+
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }

    @GetMapping("/public")
    public String publicPage() {
        String message = "Don't use the UsernamePasswordAuthenticationToken everywhere. ";
        String message2= " Instread user Authentication subclasses";
        return "You are in public page. " +message + message2;
    }

    @GetMapping("/loggedIn")
    public String loggedIn(Authentication authenticationParam, HttpServletResponse response){
        System.out.println("🔑🔑🔑🔑 YOU ARE LOGGED IN WITH DB USER.");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "🏡 this is your home page after login. DB Auth is success 🎉🎉🎉🎉 with UserName: "+authentication.getName()+"\n" +
                "after login the Authentication object is filled and isAuthenticated() return true and prevents the security to apply further security. 🏁" +
                "check logs you will see 'isAuthenticated: true' ✅" +
                "At the end this very bad practice ❌ ☠💀 to auth user from DB, Use SSO, Token auth 🎟";
    }
    @GetMapping("/home")
//    @PreAuthorize("hasRole('admin')")
    public String home(Authentication authenticationParam, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//        authentication and authenticationParam are both same
//        same object state is saved in SecurityContextHolder
//        if you don't want to pass the authentication from param to param to param to deeply nested method, you can use
//        the SCH as well.
//        SCH is global but isolated for each request.
//        @PreAuthorize("hasRole('admin')") this comparison happens with SCH only.

        System.out.println("isAuthenticated: "+authentication.isAuthenticated());
        String userDetail = authentication.getName();
        try {
            response.sendRedirect("http://localhost:8080/api/loggedIn"); // this was react applicatin url, since you don't have your react app running at your local, you need to redicrect to you /api/home page.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
//        return "redirect:http://localhost:5173";
//        return "You are in home page.\n" +
//                "Spring Security produces Authentication Objects.\n" +
//                "These are used for :\n" +
//                "   1. Authentication (authn): Who the user is?\n" +
//                "   2. Authorization (authz): is the user allowed to perform xyz action."+authentication.isAuthenticated()+
//                " and Logged In user is : "+userDetail;
    }



}




