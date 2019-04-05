import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HolaMundo extends HttpServlet {
    public void doGet (  HttpServletRequest request , 
                                      HttpServletResponse response ) 
                                        throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Ejemplo HolaMundo</title></head><body>");
        out.println("<h1>¡Hola Mundo!</h1>");
        out.println("</body></html>");
    }
}