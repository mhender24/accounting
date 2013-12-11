<%
    String userId = request.getParameter("userId");
    Cookie[] cookies = request.getCookies();
    
        if(cookies!=null)
        {
            for (int i=0; i < cookies.length; i++)
            {
                if ("User".equals(cookies[i].getName()))
                {
                    userId = cookies[i].getValue();
                    break;
                }
                else
                {
                    Cookie cookie = new Cookie("User", userId);
                    cookie.setMaxAge(1000000);
                    response.addCookie(cookie);
                }
            }
        }
        else
        {
            Cookie cookie = new Cookie("User", userId);
            cookie.setMaxAge(1000);
            response.addCookie(cookie);
        }

%>