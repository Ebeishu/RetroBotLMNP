package org.grakovne.retrobot.repository;

import org.grakovne.retrobot.entity.RetroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base Retro repository.
 *
 * @param <T> Retro Entity
 */
@NoRepositoryBean
public interface RetroRepository<T extends RetroEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
}
