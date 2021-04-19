package com.example.couchbase.service;

import com.example.couchbase.model.Track;
import com.example.couchbase.repository.TrackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TrackService {

    Logger logger = LoggerFactory.getLogger(TrackService.class);

    @Autowired
    private TrackRepository trackRepository;

    public void create() {
        if (trackRepository.countAll() == 0) {

            logger.info("Creating documents...");

            Track track1 = new Track();
            track1.setName("They don't care about us.");
            track1.setAlbumName("this is it.");
            track1.setSinger("Michael Jackson");

            Track track2 = new Track();
            track2.setName("Wanna be startin' something.");
            track2.setAlbumName("this is it.");
            track2.setSinger("Michael Jackson");

            Track track3 = new Track();
            track3.setName("Jam.");
            track3.setAlbumName("this is it.");
            track3.setSinger("Michael Jackson");

            Track track4 = new Track();
            track4.setName("Lovely one");
            track4.setAlbumName("One night in Japan");
            track4.setSinger("Michael Jackson");

            Track track5 = new Track();
            track5.setName("Wanna be startin' something");
            track5.setAlbumName("One night in Japan");
            track5.setSinger("Michael Jackson");

            trackRepository.saveAll(Arrays.asList(track1, track2, track3, track4, track5));
        } else {
            logger.info("Documents are already created.");
        }
    }

    public List<Track> findAllByALbumName(String albumName) {
        logger.info(" ***** ALBUM: {} *****", albumName);
        List<Track> tracks = trackRepository.findAllByAlbumName(albumName);
        tracks.forEach(t -> logger.info(t.getName()));
        return tracks;
    }

    public void deleteAll(){
        logger.info("DELETING ALL : LOADING ... ");
        trackRepository.deleteAllTracks();
        logger.info("All Tracks are deleted.");
    }



}
