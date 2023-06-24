package com.example.siouxmanagementsystem.business.impl.Secretary;

import com.example.siouxmanagementsystem.domain.Secretary.Secretary;
import com.example.siouxmanagementsystem.persistence.entity.SecretaryEntity;

public class SecretaryConverter {
    private SecretaryConverter() {
    }

    public static Secretary convert(SecretaryEntity secretary) {
        return Secretary.builder()
                .id(secretary.getId())
                .name(secretary.getName())
                .build();
    }
}
