package com.tomkasp.fitapp_common.web.rest;

import com.tomkasp.FitappApp;
import com.tomkasp.fitapp_common.domain.Authority;
import com.tomkasp.fitapp_common.domain.User;
import com.tomkasp.fitapp_common.security.AuthoritiesConstants;
import com.tomkasp.fitapp_common.service.UserService;
import com.tomkasp.training.application.TrainingSurveyApplicationServiceTest;
import com.tomkasp.training.application.TrainingSurveyQueryService;
import com.tomkasp.training.application.data.TrainingSurveyMapper;
import com.tomkasp.training.application.data.TrainingSurveyReadData;
import com.tomkasp.training.domain.*;
import com.tomkasp.training.resource.TrainingSurveyReadResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Tomasz Kasprzycki
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FitappApp.class)
public class TrainingSurveyQueryTest {

    private MockMvc restUserMockMvc;

    @Inject
    private TrainingSurveyQueryService trainingSurveyQueryService;

    @Inject
    private TrainingSurveyRepository trainingSurveyRepository;

    @Inject
    private TrainingSurveyMapper trainingSurveyMapper;

    @Mock
    private UserService mockUserService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        TrainingSurveyReadResource trainingSurveyReadResource = new TrainingSurveyReadResource(trainingSurveyQueryService);
        ReflectionTestUtils
            .setField(trainingSurveyReadResource, "trainingSurveyQueryService", trainingSurveyQueryService);

        ReflectionTestUtils
            .setField(trainingSurveyQueryService, "userService", mockUserService);

        TrainingSurvey trainingSurvey = new TrainingSurvey(
            "test",
            TrainingSurveyApplicationServiceTest.createBaseInformation(),
            TrainingSurveyApplicationServiceTest.createHealthInformation(),
            createNutritionIformation(),
            null,
            new MeasureSystem(MeasureType.Metric)
        );

        trainingSurveyRepository.save(trainingSurvey);

        this.restUserMockMvc = MockMvcBuilders
            .standaloneSetup(trainingSurveyReadResource)
            .build();
    }

    @Test
    public void testGetTrainingSurvey() throws Exception {
        Set<Authority> authorities = new HashSet<>();
        Authority authority = new Authority();
        authority.setName(AuthoritiesConstants.ADMIN);
        authorities.add(authority);

        User user = new User();
        user.setLogin("test");
        user.setFirstName("john");
        user.setLastName("doe");
        user.setEmail("john.doe@jhipter.com");
        user.setAuthorities(authorities);
        when(mockUserService.getUserWithAuthorities()).thenReturn(user);

        restUserMockMvc.perform(get("/api/trainingsurvey")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("test"));
    }

    @Test
    public void trainingSurveyMapperTest() {
        TrainingSurvey trainingSurvey = new TrainingSurvey(
            "test",
            TrainingSurveyApplicationServiceTest.createBaseInformation(),
            TrainingSurveyApplicationServiceTest.createHealthInformation(),
            createNutritionIformation(),
            null,
            new MeasureSystem(MeasureType.Metric));

        final TrainingSurveyReadData trainingSurveyReadData =
            trainingSurveyMapper
                .trainingSurveyToTrainingSurveyData(trainingSurvey);

        Assert.assertEquals(trainingSurveyReadData.getUsername(),
            trainingSurvey.getUsername());

    }

    public static NutritionInformation createNutritionIformation() {
        return new NutritionInformation(
            true,
            true,
            true,
            true
        );
    }

    public static TrainingGoal createTrainingGoal() {
        return new TrainingGoal(
            1d,
            1d,
            RunCategory.MARATHON
        );
    }


}
