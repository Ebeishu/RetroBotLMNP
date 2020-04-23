package org.grakovne.retrobot.repository;

import org.grakovne.retrobot.entity.CoreMessageSenderEntity;

import java.util.Optional;

/**
 * Retro Sender Repository.
 */
public interface RetroSenderRepository extends RetroRepository<CoreMessageSenderEntity> {

    Optional<CoreMessageSenderEntity> findByUsername(String username);
}
