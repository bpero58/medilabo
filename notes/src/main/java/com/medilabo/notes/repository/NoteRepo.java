package com.medilabo.notes.repository;

import com.medilabo.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

//An interface that interacts directly with mongoDB the method signature is translated into noSQL by springdata
//Since this returns a list of notes it will interact with our get-mapping as you can search notes on a given patient

public interface NoteRepo extends MongoRepository<Note, String>{
    List<Note> findByPatientIdOrderByCreatedAtDesc(Long patientID);
}
