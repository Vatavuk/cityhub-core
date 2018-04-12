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
package hr.com.vgv.cityhub.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the app.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @checkstyle FinalClassCheck (500 lines)
 * @checkstyle HideUtilityClassConstructorCheck (500 lines)
 * @checkstyle ClassWithOnlyPrivateConstructorsShouldBeFinal (500 lines)
 * @since 1.0
 */
@SpringBootApplication
@SuppressWarnings("PMD.ClassWithOnlyPrivateConstructorsShouldBeFinal")
public class App {

    /**
     * Main.
     * @param args Arguments
     */
    public static void main(final String... args) {
        SpringApplication.run(App.class, args);
    }
}
