package com.tomkasp.training.domain;

import com.tomkasp.common.domain.model.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tomasz Kasprzycki
 */
@Service
public class TrainingHistoryService {

    private final TrainingHistoryRepository trainingHistoryRepository;

    @Autowired
    public TrainingHistoryService(TrainingHistoryRepository trainingHistoryRepository) {
        this.trainingHistoryRepository = trainingHistoryRepository;
    }

    public void deleteTrainingHistory(Long trainingHistoryId) {
        final TrainingHistory trainingHistory = trainingHistoryRepository.getOne(trainingHistoryId);
        if (trainingHistory == null) {
            throw new IllegalStateException("Training history does not exist.");
        }
        trainingHistoryRepository.delete(trainingHistoryId);
        DomainEventPublisher
            .instance()
            .publish(new TrainingHistoryDeleted(trainingHistoryId));

    }
}
