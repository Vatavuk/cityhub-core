/**
 * Copyright (c) 2018, QUANTUM STUDIO d.o.o.
 * All rights reserved.
 *
 * General. The software, documentation and any fonts accompanying this
 * License whether on disk, in read only memory, on any other media or in any
 * other form (col- lectively the “Software”) are licensed, not sold,
 * to you by QUANTUM STUDIO d.o.o. (“QUANTUM”) for use only under the terms
 * of this License, and QUANTUM reserves all rights not expressly granted to
 * you. The rights granted herein are limited to QUANTUM’s intel- lectual
 * property rights in the QUANTUM Software and do not include any other patents
 * or intellectual property rights. You own the media on which the
 * Mildew Software is recorded but QUANTUM and/or QUANTUM’s licensor(s)
 * retain ownership of the Software itself
 */
package hr.com.vgv.cityhub.core.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import java.util.concurrent.TimeUnit;
import org.cactoos.Scalar;
import org.cactoos.list.SolidList;

/**
 * Mongo component.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CmMongo implements Scalar<MongoDatabase> {

    @Override
    public MongoDatabase value() {
        final int timeout = (int) TimeUnit.SECONDS.toMillis(15L);
        return new MongoClient(
            new ServerAddress(
                "ds021026.mlab.com",
                21026
            ),
            new SolidList<>(
                MongoCredential.createCredential(
                    "heroku_fgzl3rjk",
                    "heroku_fgzl3rjk",
                    "udn59tisnslmutrd4v17d8r9j5".toCharArray()
                )
            ),
            MongoClientOptions.builder()
                .maxWaitTime(timeout)
                .socketTimeout(timeout)
                .connectTimeout(timeout)
                .serverSelectionTimeout(timeout)
                .build()
        ).getDatabase("heroku_fgzl3rjk");
    }
}
