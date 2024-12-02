package com.ckronqvi.website.Resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.ckronqvi.website.Annotations.CurrentUser;
import com.ckronqvi.website.entities.User;
import com.ckronqvi.website.exceptions.UserWasNotAuthenticatedException;
import com.ckronqvi.website.repositories.UserRepository;


@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserRepository userRepository;

    public CurrentUserArgumentResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supportsParameter(@NonNull MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class) 
            && parameter.getParameterType().equals(User.class);
    }

    @Override
    public Object resolveArgument(@NonNull MethodParameter parameter, 
                                @Nullable ModelAndViewContainer mavContainer,
                                @NonNull NativeWebRequest webRequest, 
                                @Nullable WebDataBinderFactory binderFactory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserWasNotAuthenticatedException("User not authenticated");
        }

        return userRepository.findByUsername(authentication.getName())
            .orElseThrow(() -> new UserWasNotAuthenticatedException("User not found"));
    }
}
