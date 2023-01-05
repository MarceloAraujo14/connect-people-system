package br.com.connectpeople.user.domain;

import br.com.connectpeople.user.repository.entity.UserEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class User {

    private String email;
    private String password;
    private Role roles;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .roles(this.roles)
                .build();
    }
}
