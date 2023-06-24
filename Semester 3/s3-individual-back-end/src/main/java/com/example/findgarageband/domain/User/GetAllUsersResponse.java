package com.example.findgarageband.domain.User;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetAllUsersResponse {
    List<User> users;
}
