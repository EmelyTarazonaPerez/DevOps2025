package com.example.arka.config;

import com.example.arka.adapters.driven.jpa.msql.adapter.UserAdapter;
import com.example.arka.adapters.driven.jpa.msql.mapper.IUserEntityMapper;
import com.example.arka.adapters.driven.jpa.msql.repository.IUserRepositoryJPA;
import com.example.arka.adapters.driven.security.adapter.JwtAdapter;
import com.example.arka.adapters.driven.security.adapter.AuthenticationAdapter;
import com.example.arka.adapters.driven.security.adapter.PasswordEncodeAdapter;
import com.example.arka.domain.api.IAuthenticationServicePort;
import com.example.arka.domain.api.IJwtServicePort;
import com.example.arka.domain.api.IRegisterServicePort;
import com.example.arka.domain.api.useCase.AuthenticationService;
import com.example.arka.domain.api.useCase.JwtService;
import com.example.arka.domain.api.useCase.RegisterService;
import com.example.arka.domain.spi.IAuthenticationPort;
import com.example.arka.domain.spi.IJwtPort;
import com.example.arka.domain.spi.IPasswordEncodePort;
import com.example.arka.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepositoryJPA userRepositoryJPA;
    private final IUserEntityMapper userEntityMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder PasswordEncoder;

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserAdapter(userRepositoryJPA, userEntityMapper);
    }
    @Bean
    public IAuthenticationPort authenticationPort () {
        return new AuthenticationAdapter(authenticationManager);
    }
    @Bean
    public IPasswordEncodePort passwordEncodePort(){ return new PasswordEncodeAdapter(PasswordEncoder);}
    @Bean
    public IRegisterServicePort userServicePort(){ return new RegisterService(userPersistencePort(), passwordEncodePort());
    }
    @Bean
    public IAuthenticationServicePort authenticationService(){return new AuthenticationService(authenticationPort());
    }
    @Bean
    public IJwtPort jwtPort () { return new JwtAdapter(); }
    @Bean
    public IJwtServicePort jwtService (){ return new JwtService(jwtPort());}
}
