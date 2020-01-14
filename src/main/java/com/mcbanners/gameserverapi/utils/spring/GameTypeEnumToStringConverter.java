package com.mcbanners.gameserverapi.utils.spring;

import com.mcbanners.gameserverapi.game.GameType;
import org.springframework.core.convert.converter.Converter;

public class GameTypeEnumToStringConverter implements Converter<String, GameType> {
    @Override
    public GameType convert(String from) {
        try {
            return GameType.valueOf(from.toUpperCase());
        } catch (IllegalArgumentException ignored) {
            return null;
        }
    }
}
