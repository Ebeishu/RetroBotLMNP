package org.grakovne.retrobot.repository;

import org.grakovne.retrobot.entity.CoreMessageSenderEntity;
import org.grakovne.retrobot.entity.OptSenderEntity;
import org.grakovne.retrobot.entity.enumeration.OptType;

import java.util.List;
import java.util.Optional;

public interface OptSenderRepository extends RetroRepository<OptSenderEntity> {

    List<OptSenderEntity> findByType(OptType type);

    Optional<OptSenderEntity> findBySender(CoreMessageSenderEntity sender);
}
