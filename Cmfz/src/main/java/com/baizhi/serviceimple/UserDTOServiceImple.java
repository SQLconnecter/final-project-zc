package com.baizhi.serviceimple;

import com.baizhi.dao.UserDTODao;
import com.baizhi.entity.UserDTO;
import com.baizhi.service.UserDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class UserDTOServiceImple implements UserDTOService {
    @Autowired
    private UserDTODao userDTODao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<UserDTO> showDto(int week) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for(int i=0;i<week;i++){
            UserDTO userDTO = new UserDTO();
            userDTO.setName("最近"+(i+1)+"周");
            userDTO.setValue(userDTODao.queryLastWeek((i+1)*7));
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public List<UserDTO> showMan() {
        List<UserDTO> userDTOS = userDTODao.queryMan();
        return userDTOS;
    }

    @Override
    public List<UserDTO> showWoman() {
        List<UserDTO> userDTOS = userDTODao.queryWoman();
        return userDTOS;
    }
}
