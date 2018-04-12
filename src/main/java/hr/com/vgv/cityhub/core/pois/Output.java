package hr.com.vgv.cityhub.core.pois;

import org.bson.Document;

/**
 * Output.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Output {

    Iterable<Document> value() throws Exception;
}
