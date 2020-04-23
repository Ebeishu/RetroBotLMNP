package org.grakovne.retrobot.repository;


import org.grakovne.retrobot.entity.CoreMessageEntity;

import java.util.Optional;

/**
 * Retro message repository.
 */
public interface RetroMessageRepository extends RetroRepository<CoreMessageEntity> {

    Optional<CoreMessageEntity> findByText(String text);
}
