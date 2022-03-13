package com.ppikarin.back.challenge;


import com.ppikarin.back.core.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTest {

    @Spy
    private Random random;
    private ChallengeService challengeService;
    private ChallengeGeneratorService challengeGeneratorService;
    @Mock
    private HumanRepository userRepository;
    @Mock
    private AttemptRepository attemptRepository;

    @BeforeEach
    public void setUp() {

        challengeService = new ChallengeService(
                userRepository,
                attemptRepository);

        given(attemptRepository
                .save(any()))
                .will(returnsFirstArg());
    }

    @Test
    public void checkExistingUserTest() {

        // given
        Human existingHuman = new Human(1L, "john_doe");
        given(userRepository.findByName("john_doe"))
                .willReturn(Optional.of(existingHuman));
        AttemptRDTO attemptDTO = new AttemptRDTO(50, 60, "john_doe", 5000);

        // when
        Attempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isFalse();
        then(resultAttempt.getHuman()).isEqualTo(existingHuman);
        verify(userRepository, never()).save(any());
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    public void checkCorrectAttemptTest() {

        // given
        AttemptRDTO attemptDTO =
                new AttemptRDTO(50, 60, "john_doe", 3000);
        // when
        Attempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isTrue();

        // newly added lines
        verify(userRepository).save(new Human("john_doe"));
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    public void generateRandomFactorIsBetweenExpectedLimits() {

        challengeGeneratorService = new ChallengeGeneratorServiceImpl(random);

        // 89 is max - min range
        given(random.nextInt(89)).willReturn(20, 30);

        // when we generate a challenge
        Challenge challenge = challengeGeneratorService.randomChallenge();

        // then the challenge contains factors as expected
        then(challenge).isEqualTo(new Challenge(31, 41));
    }

    @Test
    public void checkCorrectAttemptTestSimple() {

        // given
        AttemptRDTO attemptDTO = new AttemptRDTO(50, 60, "john_doe", 3000);

        // when
        Attempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isTrue();
    }

    @Test
    public void checkWrongAttemptTest() {

        // given
        AttemptRDTO attemptDTO = new AttemptRDTO(50, 60, "john_doe", 5000);

        // when
        Attempt resultAttempt = challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isFalse();
    }


}
