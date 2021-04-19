package com.example.couchbase.repository;

import com.example.couchbase.model.Track;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TrackRepository extends CrudRepository<Track, String> {
    List<Track> findAllByAlbumName(String albumName);

    /**
     * N1QL query
     * @return
     */
    @Query("SELECT COUNT(s.id) AS c FROM sample s WHERE s.type = 'com.example.couchbase.model.Track'")
    int countAll();

    @Query("DELETE FROM sample s WHERE s.type = 'com.example.couchbase.model.Track'")
    List<Track> deleteAllTracks();
}
