package fast.campus.netplix3.movie;

public interface DownloadMoviePort {
    UserMovieDownload save(UserMovieDownload domain);

    long downloadCntToday(String userId);
}
