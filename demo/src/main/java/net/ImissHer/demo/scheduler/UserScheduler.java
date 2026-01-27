package net.ImissHer.demo.scheduler;


import net.ImissHer.demo.Enum.Sentiment;
import net.ImissHer.demo.entity.User;
import net.ImissHer.demo.entity.journalEntry;
import net.ImissHer.demo.model.SentimentData;
import net.ImissHer.demo.repo.UserRepositoryImplementation;
import net.ImissHer.demo.service.EmailService;
import net.ImissHer.demo.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImplementation userRepositoryImplementation;

    @Autowired
    private EmailService emailService;

    // Use KafkaTemplate<String, String> now


    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

   // Spring Boot auto-configured

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepositoryImplementation.getUserBySA();

        for (User user : users) {
            List<journalEntry> journalEntries = user.getJournalEntries();

            // Collect last 7 days sentiments
            List<Sentiment> sentiments = journalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(j -> j.getSentiment())
                    .collect(Collectors.toList());

            // Count frequencies
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for (Sentiment sentiment : sentiments) {
                if (sentiment != null)
                    sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
            }

            // Find most frequent
            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;
            for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
                if (entry.getValue() > maxCount) {
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }

            if (mostFrequentSentiment != null) {
                SentimentData sentimentData = SentimentData.builder()
                        .email(user.getEmail())
                        .sentiment("Sentiment for last 7 days " + mostFrequentSentiment)
                        .build();
            }
        }
    }
}
