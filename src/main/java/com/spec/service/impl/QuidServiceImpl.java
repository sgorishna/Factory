package com.spec.service.impl;


import com.spec.dao.QuidDao;
import com.spec.exceptions.InvalidDataException;
import com.spec.model.Quid;
import com.spec.service.QuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Svetik on 20/08/2017.
 */
@Service
public class QuidServiceImpl implements QuidService {

    private final QuidDao quidDao;

    @Autowired
    public QuidServiceImpl(QuidDao quidDao) {
        this.quidDao = quidDao;
    }

    public void save(Quid quid) throws InvalidDataException {


            quidDao.save(quid);

    }

    public void delete(Quid quid) {

        quidDao.delete(quid);
    }

    public void update(Quid quid) throws InvalidDataException {

        quidDao.update(quid);

    }

    public Quid findById(int id) {
        return quidDao.findById(id);
    }

    public List<Quid> findByProductId(int id) {
        return quidDao.findByProductId(id);
    }


}
