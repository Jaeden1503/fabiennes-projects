package com.example.siouxmanagementsystem.business.impl.Guest;

import com.example.siouxmanagementsystem.persistence.entity.GuestEntity;

public class GuestConverter {
    private GuestConverter(){

    }
    public static GuestEntity convert(GuestEntity guest)
    {
        return GuestEntity.builder()
                .id(guest.getId())
                .name(guest.getName())
                .email_address(guest.getEmail_address())
                .license(guest.getLicense())
                .phone_number(guest.getPhone_number())
                .build();
    }
}
