package com.titilope.web_inventory.AjokeStores.Config;

import com.titilope.web_inventory.AjokeStores.Service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AuthUserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                //.antMatchers("/employees/showForm*").hasAnyRole("MANAGER", "ADMIN")
                //.antMatchers("/users/showForm*").hasAnyRole("MANAGER", "ADMIN")
                //.antMatchers("/employees/save*").hasAnyRole("MANAGER", "ADMIN")
                //.antMatchers("/employees/delete").hasRole("ADMIN")
                //.antMatchers("/employees/**").hasRole("EMPLOYEE")
                //.antMatchers("/resources/**").permitAll()
                .antMatchers("/products/**").hasAnyAuthority("MANAGER")
                .antMatchers("/sales/pos-form").hasAnyAuthority("MANAGER", "SALES_OFFICER")
                .antMatchers("/sales/list").hasAnyAuthority("MANAGER")
                .antMatchers("/sales/track-sales").hasAnyAuthority("MANAGER", "SALES_OFFICER")
                .antMatchers("/data/find-productSales-by-product").permitAll()
                //.antMatchers("/books/**").hasAnyRole("MANAGER", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }
}
