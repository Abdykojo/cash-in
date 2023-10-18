package com.example.http.service.impl;

import com.example.http.commons.Exeptions.UserNotFoundExeption;
import com.example.http.commons.RespMessage;
import com.example.http.entity.Cards;
import com.example.http.entity.Type;
import com.example.http.entity.User;
import com.example.http.repository.CardsRepository;
import com.example.http.repository.UserRepository;
import com.example.http.service.TypeServise;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TypeServiseImpl implements TypeServise {
    private final UserRepository userRepository;
    private final CardsRepository cardsRepository;

    @Override
    public User findUser(String account, String type) {
        User user;
        if (type.equals(Type.PHONE.getType())) {
            user = userRepository.findByPhoneNumber(account)
                    .orElseThrow(() -> new UserNotFoundExeption(RespMessage.USER_NOT_FOUND.getMessage()));
            return user;
        }
        Cards cards = cardsRepository.getCardsByNumber(account);
        user = userRepository.findByPhoneNumber(cards.getAccount())
                .orElseThrow(() -> new UserNotFoundExeption(RespMessage.USER_NOT_FOUND.getMessage()));
        return user;
    }
}
