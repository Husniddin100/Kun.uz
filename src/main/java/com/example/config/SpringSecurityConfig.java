package com.example.config;


/*
@Configuration
public class SpringSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication
       */
/* String password = UUID.randomUUID().toString();
        System.out.println("User Pasword mazgi: " + password);

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}" + password)
                .roles("ADMIN")
                .build();

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));*//*

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MDUtil.encode(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorization
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/auth/login").permitAll()
                    .requestMatchers("/init/**").permitAll()
                    .requestMatchers("/category/all").permitAll()
                    .requestMatchers("/category/lang").permitAll()
                    .requestMatchers("/profile/", "profile/*").hasRole("ADMIN")
                    .requestMatchers("/article/").hasAnyRole("ADMIN", "PUBLISHER")
                    .requestMatchers("profile/adm", "profile/adm/**").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}*/
