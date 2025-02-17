package com.sociopath.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sociopath.service.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		
		http
			.authorizeRequests()
				.antMatchers(
						"/403",
						"/login/*",
						"/register/*",
						"/",
						"/#",
						"/search",
						"/leaderboard",
						"/register",
						"/registrationconfirmed",
						"/invaliduser",
						"/expiredtoken",
						"/verifyemail",
						"/confirmregister",
						"/profilephoto/*",
						"/sidebar",
						"/login",
						"/chart",
						"/index",
						"/chart/*",
						"/search",
						"/search/*",
						"/viewstatus",
						"/suddenlyPost",
						"/suddenlyPost/*"
						)
				.permitAll()
				.antMatchers(
					"/js/*",
					"/css/*",
					"/img/*")
				.permitAll()
				.antMatchers(
						"/god/*",
						"/",
						"/addstatus",
						"/editstatus",
						"/deletestatus",
						"/godDashboard",
						"/godDelete",
						"/godDeleteStudent",
						"/godCreateRep",
						"/godUpdateRep",
						"/godCreateFriend",
						"/godDeleteRep",
						"/godDeleteBothRep",
						"/godDeleteAllRep",
						"/godDeleteBothFriend",
						"/godDeleteAllFriend",
						"/createBasicRep",
						"/createBasicFriend",
						"/testForm",
						"/displayReputation",
						"/displayFriend",
						"/event1",
						"/event2",
						"/event3",
						"/event4",
						"/event5",
						"/event6",
						"/preEvent6",
						"/resultEvent6",
						"/resultEvent6/*",
						"/resultEvent3",
						"/resultEvent3/*",
						"/resultEvent4",
						"/resultEvent5",
						"/resultEvent5/*",
						"/resultEvent1",
						"/resultEvent1/*",
						"/resultEvent2",
						"/resultEvent2/*",
						"/godViewProfile",
						"/displayFriend/*"
						)
				.hasRole("ADMIN")
				.antMatchers(
						"/webjars/**",
						"/chat/**",
						"/profile",
						"/studentprofile",
						"/profile/*",
						"/edit-profile-about",
						"/upload-profile-photo",
						"/save-interest",
						"/delete-interest",
						"/conversation/*",
						"/chatview/*",
						"/messages",
						"/markread",
						"/dashboard",
						"/leaderboard",
						"/FindStranger",
						"/FindStranger/*",
						"/askStranger",
						"/resultAskStranger",
						"/MyFriends",
						"/MyFriends/*",
						"/ChitChat",
						"/ArrangeBook",
						"/eventResult",
						"/arrangeBookResult",
						"/Lunch",
						"/ScheduleLunch",
						"/MeetCrush",
						"/Friendship",
						"/resultEvent3",
						"/resultEvent3/*",
						"/resultEvent5",
						"/resultEvent5/*",
						"/resultEvent6",
						"/resultEvent6/*",
						"/preEvent6",
						"/event5",
						"/event6",
						"/suddenlyPost",
						"/suddenlyPost/*"
						)
				.authenticated()
				.anyRequest()
				.denyAll()
				.and()
			.formLogin().loginPage("/login")
				.defaultSuccessUrl("/dashboard", true)
				.permitAll()
				.and()
			.logout()
				.permitAll();
		
		// @formatter:on
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		// @formatter:off
		auth
			.inMemoryAuthentication()
			.withUser("{noop}guang")
			.password("guang")
			.roles("USER");
		
		// @formatter:on

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	
}