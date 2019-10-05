package com.huben.blog.service;

import com.huben.blog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author koishi
 */
public interface TypeService {
    Type saveType(Type type);

    Type updateType(Long id, Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    void deleteType(Long id);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);
}
