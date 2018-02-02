package com.zhaihuilin.config;

/**
 * Created by zhaihuilin on 2018/2/1  10:20.
 */
public class Test {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .antMatchers("/index").permitAll()
//                .and()
//                .formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .loginProcessingUrl("/login")  // 自定义登录路径
//                .loginPage("/login")  // 自定义页面路径
//                .successHandler(
//                        new AuthenticationSuccessHandler() {
//                            @Override
//                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                                Gson gson = new Gson();
//                                ReturnMessages messages = new ReturnMessages(RequestState.SUCCESS, "登录成功。", null);
//                                response.setContentType("text/json;charset=utf-8");
//                                response.getWriter().write(gson.toJson(messages));
//                            }
//                        }
//                )
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//                        Gson gson = new Gson();
//                        ReturnMessages messages = new ReturnMessages(RequestState.ERROR, "用户名或密码错误。", null);
//                        httpServletResponse.setContentType("text/json;charset=utf-8");
//                        httpServletResponse.getWriter().write(gson.toJson(messages));
//                    }
//                })
//                .failureUrl("/login?error")
//                .permitAll()
//                .and()
//                .logout().logoutUrl("/logout").permitAll();
//        http.addFilterBefore(freshFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//    }
}
