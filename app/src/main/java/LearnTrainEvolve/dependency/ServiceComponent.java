package LearnTrainEvolve.dependency;

import LearnTrainEvolve.activity.CreateUserTrainingSessionActivity;
import LearnTrainEvolve.activity.GetTrainingSessionsActivity;
import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return CreateUserActivity
     */
    CreateUserTrainingSessionActivity provideCreateUserTrainingSessionActivity();

    GetTrainingSessionsActivity provideGetTrainingSessionsActivity();



}
