package com.zzz.service.impl;

import com.zzz.dao.TypeRepository;
import com.zzz.exception.NotFoundException;
import com.zzz.pojo.Type;
import com.zzz.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Override
    @Transactional
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    @Transactional(rollbackFor = {})
    public Type updateType(Long id,Type type) {
        Type t = typeRepository.getOne(id);

        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);

        return typeRepository.save(t);
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        Type byName = typeRepository.findByName(name);
        return byName;
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0, size, sort) {
        };

        return  typeRepository.findTop(pageable);
    }
}
