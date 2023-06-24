package com.example.siouxmanagementsystem.domain.Secretary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Secretary  {
    private Long id;
    private String name;
}
