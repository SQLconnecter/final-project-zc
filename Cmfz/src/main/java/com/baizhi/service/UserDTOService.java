package com.baizhi.service;

import com.baizhi.entity.UserDTO;

import java.util.List;

public interface UserDTOService {
    public List<UserDTO> showDto(int week);
    public List<UserDTO> showMan();
    public List<UserDTO> showWoman();
}
