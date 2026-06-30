package org.radagon.day5.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;

    private String userName;
    private String userEmail;

    private List<BookDTO> books;
}
