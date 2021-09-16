package com.example.ReportPlayer.config;

import com.example.ReportPlayer.filter.JwtAuthenticationFilter;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;



    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }
    @Autowired
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }



    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Filtro Custom JWT
        httpSecurity
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // non abbiamo bisogno di una sessione
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(
                        //HttpMethod.GET,                        "/*.html",
                        "/favicon.ico",
                        "/resource/**",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.png",
                        "/**/*.ttf"
                ).permitAll()
                .antMatchers(
                        "/",
                        "/register",
                        "/report",
                        "/login",
                        "/emailForgotPassword",
                        "/player/{username}",
                        "/forgotPassword/{token}",
                        "/api/v1/registration/",
                        "/api/v1/authentication/",
                        "/api/v1/users/username/{username}",
                        "/api/v1/users/{email}",
                        "/api/v1/players/top",
                        "/api/v1/players/latest",
                        "/api/v1/players/reportsType",
                        "/api/v1/reports//players/{username}",
                        "/api/v1/reportType/regions",
                        "/api/v1/players/search",
                        "/api/v1/users/accounts/email/email_token",
                        "/api/v1/users/accounts/update_forgot_password",
                        "/api/v1/reports/last",
                        "/api/v1/reportTypeRegion/all",
                        "/api/v1/players/hello",
                        "/player/{username}",
                        "/api/v1/registration/provaTemplate",
                        "/pennarosso_rossa/"
                ).permitAll()
                .anyRequest().authenticated();
        httpSecurity.cors();
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


}
