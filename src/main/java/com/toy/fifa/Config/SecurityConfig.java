package com.toy.fifa.Config;


import com.toy.fifa.DTO.ResponseMessage;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그아웃하면 세션삭제
    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
            .and().formLogin()
                .loginPage("/user/join")
                .defaultSuccessUrl("/")
                .permitAll()
            .and().logout().
                invalidateHttpSession(true) // remove session
                .logoutSuccessUrl("/")
                .permitAll()
            .and().sessionManagement()
                .maximumSessions(1) // 세션 허용 개수
                .expiredUrl("/user/join")  // 세션 만료시 이동 페이지
                .maxSessionsPreventsLogin(true); // 동일한 사용자 로그인 불가능

    }
}
