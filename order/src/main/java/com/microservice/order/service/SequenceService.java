package com.microservice.order.service;

import com.microservice.order.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/** Generates auto-incrementing order ids using an atomic counter document in MongoDB. */
@Service
public class SequenceService {

    @Autowired
    private MongoOperations mongoOperations;

    public int getNextOrderId() {
        Sequence counter = mongoOperations.findAndModify(
                Query.query(where("_id").is("sequence")),
                new Update().inc("sequence", 1),
                options().returnNew(true).upsert(true),
                Sequence.class);
        return counter.getSequence();
    }

}
