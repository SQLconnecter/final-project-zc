package com.baizhi.serviceimple;

import com.baizhi.dao.LogDao;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class LogServiceImple implements LogService {
    @Autowired
    private LogDao logDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Log> showPage(int page, int rows) {
        int start = (page-1)*rows;
        List<Log> logs = logDao.queryByPage(start, rows);
        return logs;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Log showId(String id) {
        Log log = logDao.selectByPrimaryKey(id);
        return log;
    }

    @Override
    public void put(Log log) {
        logDao.insert(log);
    }
}
