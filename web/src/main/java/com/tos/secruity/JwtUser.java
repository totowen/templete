    package com.tos.secruity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.Collection;
    import java.util.Date;

    /**
     * 这个接口中规定了用户的几个必须要有的方法，
     * 所以我们创建一个JwtUser类来实现这个接口。
     * 为什么不直接使用User类et？
     * 因为这个UserDetails完全是为了安全服务的，
     * 它和我们的领域类可能有部分属性重叠，
     * 但很多的接口其实是安全定制的，
     * 所以最好新建一个类
     */
    public class JwtUser implements UserDetails {

        private final Long id;

        private final String username;

        private final String password;

        private final String email;

        private final Collection<? extends GrantedAuthority> authorities;

        private final Date lastPasswordResetDate;

        public JwtUser(
                Long id,
                String username,
                String password,
                String email,
                Collection<? extends GrantedAuthority> authorities,
                Date lastPasswordResetDate) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.email = email;
            this.authorities = authorities;
            this.lastPasswordResetDate = lastPasswordResetDate;
        }


        /**
         * 返回分配给用户的角色列表
         * @return
         */
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {

            return authorities;

        }

        @JsonIgnore
        public Long getId() {

            return id;

        }

        @JsonIgnore
        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        /**
         *  账户是否未过期
         * @return
         */
        @JsonIgnore
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        /**
         * 账户是否未锁定
         * @return
         */
        @JsonIgnore
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        /**
         * 密码是否未过期
         * @return
         */
        @JsonIgnore
        @Override
        public boolean isCredentialsNonExpired() {

            return true;

        }

        /**
         * 账户是否激活
         * @return
         */
        @JsonIgnore
        @Override
        public boolean isEnabled() {

            return true;

        }

        /**
         * 这个是自定义的，返回上次密码重置日期
         * @return
         */
        @JsonIgnore
        public Date getLastPasswordResetDate() {

            return lastPasswordResetDate;

        }

    }
