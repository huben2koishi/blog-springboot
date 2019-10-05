package com.huben.blog.dao;

import com.huben.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author koishi
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    @Query("select b from Blog b where b.recommend = true order by b.createTime desc ")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    void updateView(Long id);

    @Query("select function('date_format',b.createTime,'%Y') as year from Blog b group by function('date_format',b.createTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from  Blog b where function('date_format',b.createTime,'%Y') = ?1 order by b.createTime desc ")
    List<Blog> findByYear(String year);
}
