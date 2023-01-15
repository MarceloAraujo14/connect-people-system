package br.com.connectpeople.security.domain;

import br.com.connectpeople.security.repository.entity.UserEntity;
import lombok.Builder;
import lombok.Data;
import lombok.With;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@With
@Data
public class User implements UserDetails {

    private String email;
    private String password;
    private Role role;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
