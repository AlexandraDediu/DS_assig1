package ro.tuc.ds2020.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import ro.tuc.ds2020.errors.ErrorConstants;
import ro.tuc.ds2020.errors.UnAuthorizedRequestException;
import ro.tuc.ds2020.login.ErrorResponse;
import ro.tuc.ds2020.services.security.CustomUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private final CustomUserDetailsService customUserDetailsService;
  private final JwtUtil jwtUtil;
  protected static ObjectMapper mapper = new ObjectMapper();

  public JwtRequestFilter(CustomUserDetailsService userDetailsService, JwtUtil jwtUtil) {
    this.customUserDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }

  public void setErrorResponse(HttpStatus status, HttpServletResponse response,
      HttpServletRequest request, Throwable ex) {
    response.setStatus(status.value());
    response.setContentType("application/json");

    try {
      ErrorResponse errorResponse = new ErrorResponse(Integer.toString(response.getStatus()),
          ex.getMessage(), request.getRequestURI());
      String errorString = new Gson().toJson(errorResponse);
      PrintWriter out = response.getWriter();
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      out.print(errorString);
      out.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain)
      throws ServletException, IOException {

    final String authorizationHeader = request.getHeader("Authorization");
    String username;
    String jwt;

    if (!authorizationHeader.equals("") && authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7);
      try {
        jwtUtil.validateToken(jwt);
      } catch (UnAuthorizedRequestException exception) {
        setErrorResponse(HttpStatus.UNAUTHORIZED, response, request, exception);
        return;
      }

      username = jwtUtil.extractUsername(jwt);
    } else {
      if (authorizationHeader.equals("")) {
        setErrorResponse(HttpStatus.UNAUTHORIZED, response, request,
            new UnAuthorizedRequestException(
                ErrorConstants.MISSING_TOKEN));
      } else {
        setErrorResponse(HttpStatus.UNAUTHORIZED, response, request,
            new UnAuthorizedRequestException(ErrorConstants.INVALID_HEADER));
      }
      return;

    }

    UserDetails userDetails = null;
    try {
      userDetails = this.customUserDetailsService.loadUserByUsername(username);
    } catch (UsernameNotFoundException exception) {
      setErrorResponse(HttpStatus.UNAUTHORIZED, response, request, exception);
    }
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
    usernamePasswordAuthenticationToken
        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    chain.doFilter(request, response);
  }
}