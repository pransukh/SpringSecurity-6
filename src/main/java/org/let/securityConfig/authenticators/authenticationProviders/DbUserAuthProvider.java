package org.let.securityConfig.authenticators.authenticationProviders;

import org.let.modals.ModalUsers;
import org.let.services.ServiceUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

import java.util.Objects;

public class DbUserAuthProvider  implements AuthenticationProvider {


    ServiceUserDetails serviceUserDetails;

    @Autowired
    public DbUserAuthProvider(ServiceUserDetails serviceUserDetails) {
        this.serviceUserDetails = serviceUserDetails;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        System.out.println("🔑🔑🔑🔑🔑 Checking Authentication in DB User Auth Provider.");
        System.out.println("serviceUserDetails available?  "+serviceUserDetails);
        // load from db;
        ModalUsers modalUsers = serviceUserDetails.getUserWithUsernamePassword(authentication.getName(),authentication.getCredentials().toString());
        System.out.println(modalUsers.getUserId());
//        System.out.println(modalUsers.getModalUserDetails().getUserDetailId());
//        System.out.println(modalUsers.getModalUserDetails().getUserRealName());

        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.getName());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getAuthorities());
        if(modalUsers != null){
            var pran = User.withUsername(modalUsers.getUserName()).password(modalUsers.getPassword()).roles("user","admin").build();

            return UsernamePasswordAuthenticationToken.authenticated(
              pran,null,pran.getAuthorities()
            );
        }

        //dummy code
//        if (Objects.equals(authentication.getName(),"jita")){
//
//            var pran = User.withUsername("jita").password("jita").roles("user","admin").build();
//            return UsernamePasswordAuthenticationToken.authenticated(
//              pran,null,pran.getAuthorities()
//            );
//        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //tell us which classes, java types we support
        //in this case user is logging in with username and password
        // only the UsernamePasswordAuthenticationToken.class will be used
        // e.g in case of google this provider will not be provided
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
