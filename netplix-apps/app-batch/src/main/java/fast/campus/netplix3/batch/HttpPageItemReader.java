package fast.campus.netplix3.batch;

import fast.campus.netplix3.movie.FetchMovieUseCase;
import fast.campus.netplix3.movie.NetplixMovie;
import fast.campus.netplix3.movie.response.MovieResponse;
import fast.campus.netplix3.movie.response.PageableMoviesResponse;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;

import java.util.LinkedList;
import java.util.List;

public class HttpPageItemReader extends AbstractItemCountingItemStreamItemReader<MovieResponse> {
    private int page;
    private final List<MovieResponse> contents = new LinkedList<>();
    private final FetchMovieUseCase fetchMovieUseCase;

    public HttpPageItemReader(int page, FetchMovieUseCase fetchMovieUseCase) {
        this.fetchMovieUseCase = fetchMovieUseCase;
        this.page = page;
    }

    @Override
    protected MovieResponse doRead() throws Exception {
        if (this.contents.isEmpty()) {
            readRow();
        }
        int size = this.contents.size();
        int index = size -1;
        if(index < 0 ) {
            return null;
        }

        return contents.remove(contents.size() - 1);
    }

    @Override
    protected void doOpen() throws Exception {
        setName(HttpPageItemReader.class.getName());

    }

    @Override
    protected void doClose() throws Exception {

    }

    private void readRow() throws Exception {
        PageableMoviesResponse pageableMoviesResponse = fetchMovieUseCase.fetchFromClient(page);
        contents.addAll(pageableMoviesResponse.getMovieResponses());
        page++;
    }
}
