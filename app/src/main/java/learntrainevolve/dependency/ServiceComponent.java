package learntrainevolve.dependency;

import learntrainevolve.activity.CreateUserTrainingSessionActivity;
import learntrainevolve.activity.GetTrainingSessionsActivity;
import dagger.Component;
import learntrainevolve.activity.LogTrainingActivity;
import learntrainevolve.activity.SyncTrainingSessionsActivity;
import learntrainevolve.externalApis.GoogleCalEventDao;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, GoogleCalEventDaoModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return CreateUserActivity
     */
    CreateUserTrainingSessionActivity provideCreateUserTrainingSessionActivity();

    GetTrainingSessionsActivity provideGetTrainingSessionsActivity();

    LogTrainingActivity provideLogTrainingActivity();

    SyncTrainingSessionsActivity provideSyncTrainingSessionsActivity();



}
