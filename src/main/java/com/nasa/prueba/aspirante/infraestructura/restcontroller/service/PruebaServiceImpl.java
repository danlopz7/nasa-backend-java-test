package com.nasa.prueba.aspirante.infraestructura.restcontroller.service;

import com.nasa.prueba.aspirante.dominio.entities.PruebaEntity;
import com.nasa.prueba.aspirante.infraestructura.repository.PruebaInterfaz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PruebaServiceImpl implements IPruebaService{

    @Autowired
    private PruebaInterfaz pruebaDao;

    @Override
    @Transactional(readOnly = true)
    public List<PruebaEntity> findAll() {
        return (List<PruebaEntity>) pruebaDao.findAll();
    }

    @Override
    public void save(PruebaEntity pruebaEntity) {
        pruebaDao.save(pruebaEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public PruebaEntity findOne(Long id) {
        return pruebaDao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        pruebaDao.deleteById(id);
    }

    public boolean existById(Long id) {
        return pruebaDao.existsById(id);
    }
}
