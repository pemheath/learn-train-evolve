package learntrainevolve.dependency;

import learntrainevolve.activity.CreateUserTrainingSessionActivity;
import learntrainevolve.activity.GetTrainingSessionsActivity;
import dagger.Component;
import learntrainevolve.activity.LogTrainingActivity;

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

    LogTrainingActivity provideLogTrainingActivity();


}
