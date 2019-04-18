package com.vit.mychat.domain.usecase.user;

import com.vit.mychat.domain.ObservableUseCase;
import com.vit.mychat.domain.usecase.user.model.User;
import com.vit.mychat.domain.usecase.user.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

@Singleton
public class GetUserListUseCase extends ObservableUseCase<List<User>, Void> {

    @Inject
    UserRepository userRepository;

    @Inject
    public GetUserListUseCase(@Named("SchedulerType.IO") Scheduler threadExecutor,
                              @Named("SchedulerType.UI") Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<List<User>> buildUseCaseSingle(Void aVoid) {
        return userRepository.getUserList();
    }
}
