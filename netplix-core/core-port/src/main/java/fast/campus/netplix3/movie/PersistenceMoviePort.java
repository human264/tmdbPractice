package fast.campus.netplix3.movie;


import java.util.List;

public interface PersistenceMoviePort {
    List<NetplixMovie> fetchBy(int page, int size);
    NetplixMovie findBy(String movieName);
    void insert(NetplixMovie netplixMovie);
}
