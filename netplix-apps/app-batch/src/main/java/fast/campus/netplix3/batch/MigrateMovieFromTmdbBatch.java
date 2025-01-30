package fast.campus.netplix3.batch;


import fast.campus.netplix3.movie.FetchMovieUseCase;
import fast.campus.netplix3.movie.InsertMovieUseCase;
import fast.campus.netplix3.movie.response.MovieResponse;
import fast.campus.netplix3.user.FetchUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MigrateMovieFromTmdbBatch {
    private final static String BATCH_NAME = "MigrateMoviesFromTmdbBatch";
    private final FetchMovieUseCase fetchMovieUseCase;
    private final InsertMovieUseCase insertMovieUseCase;

    @Bean(name = "MigrateMoviesFromTmdbBatch")
    public Job job(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("MigrateMoviesFromTmdbBatch", jobRepository)
                .preventRestart()
                .start(step(jobRepository, transactionManager))
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean(name = "MigrateMoviesFromTmdbBatchStep")
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("MigrateMoviesFromTmdbBatchStep", jobRepository)
                .chunk(10, transactionManager)
                .reader(new HttpPageItemReader(1, fetchMovieUseCase))
                .writer(chunk -> {
                    List<MovieResponse> items = (List<MovieResponse>) chunk.getItems();
                    insertMovieUseCase.insert(items);
                })
                .build();
    }


}
