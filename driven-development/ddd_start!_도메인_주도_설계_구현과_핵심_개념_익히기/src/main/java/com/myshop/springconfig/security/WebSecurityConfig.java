package com.myshop.springconfig.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String AUTH_COOKIE_NAME = "AUTH";

    private final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select member_id, password, 'true' from member where member_id = ?")
                .authoritiesByUsernameQuery("select member_id, authority from member_authorities where member_id = ?")
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .requestCache().disable()
                .securityContext().securityContextRepository(new CookieSecurityContextRepository(userDetailsService()))
                .and()
                .headers()
                    .frameOptions().sameOrigin().disable()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .successHandler(new CustomAuthSuccessHandler())
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/loggedOut")
                    .deleteCookies(AUTH_COOKIE_NAME)
                    .permitAll()
                .and()
                .authorizeRequests()
                    .antMatchers("/vendor/**", "/api/**", "/images/**", "/favicon.ico", "/h2-console/**").permitAll()
                    .antMatchers("/", "/home", "/categories/**", "/products/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
}
