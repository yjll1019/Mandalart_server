package com.mandalart_server.security.oauth2;

import com.mandalart_server.dao.UserRepository;
import com.mandalart_server.exception.OAuth2AuthenticationProcessingException;
import com.mandalart_server.model.User;
import com.mandalart_server.security.UserPrincipal;
import com.mandalart_server.security.oauth2.user.KakaoOAuth2UserInfo;
import com.mandalart_server.security.oauth2.user.OAuth2UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Optional;

// access token 획득 후 호출된다.
// Oauth2 제공자로부터 사용자의 속성을 얻어올 때 사용되는 클래스.
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try{
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        }catch(AuthenticationException ex){
            throw ex;
        }catch(Exception ex){
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User){
        OAuth2UserInfo oAuth2UserInfo = new KakaoOAuth2UserInfo( oAuth2User.getAttributes());

        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())){
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());

        User user;

        if(userOptional.isPresent()){ //값이 있다면
            user = userOptional.get();
/*            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                //카카오톡 하나기 때문에 지워도 될 듯?
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }*/
            user = updateExistingUser(user, oAuth2UserInfo); //이미 존재하는 이메일이면 사용자 정보 update.
        }else{
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo); //새로운 사용자이면 사용자 create.
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo){
        User user = new User();

        //user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        //user.setProviderId(oAuth2UserInfo.getId());
        user.setUserId(String.valueOf(oAuth2UserInfo.getUserId()));
        user.setNickname(oAuth2UserInfo.getNickname());
        user.setEmail(oAuth2UserInfo.getEmail());

        return userRepository.save(user);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo){
        existingUser.setNickname(oAuth2UserInfo.getNickname());
        return userRepository.save(existingUser);
    }
}
