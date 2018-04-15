package hr.com.vgv.cityhub.core.pois;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Entities {

    MongoCollection<Document> value() throws Exception;
}
